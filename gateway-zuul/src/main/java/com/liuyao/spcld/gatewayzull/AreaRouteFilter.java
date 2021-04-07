package com.liuyao.spcld.gatewayzull;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 根据用户所在地区 路由到不同地区的服务
 */
@Component
public class AreaRouteFilter extends ZuulFilter {

	// 拦截后的具体业务逻辑
	@Override
	public Object run() throws ZuulException {

		//获取上下文（重要，贯穿 所有filter，包含所有参数）
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		String remoteAddr = request.getRemoteAddr();
        String uri = request.getRequestURI();

        /**
         * http://localhost:7100/user-provider/zuul-area-alive
         *
         * 网关地址来源：ZuulServerAutoConfiguration.java
         *  1 zuul从eureka种获得的服务
         *  2 配置文件中定义
         *
         * 404 需要加配置
         *  zuul:
         *    routes:
         *      aa: /zuul-area-alive/**
         *
         */
        if (uri.contains("/zuul-area-alive")) {
            System.out.println("AreaRouteFilter 拦截 来源uri："+uri);

            try {
                ctx.setRouteHost(new URL("http://localhost:8101/user/alive"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
		return null;
	}

	// 拦截类型，4种类型。
	@Override
	public String filterType() {
		return FilterConstants.ROUTE_TYPE;
	}

	// 值越小，越在前
	@Override
	public int filterOrder() {
		return 0;
	}

    // 该过滤器是否生效
    @Override
    public boolean shouldFilter() {
        return true;
    }

}
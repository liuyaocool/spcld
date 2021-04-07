package com.liuyao.spcld.gatewayzull.filter;

import com.liuyao.spcld.gatewayzull.config.Constant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 新旧url 路由转换过滤器
 *
 * 配置也可以做，但会穷举好多
 * zuul:
 *   routes:
 *     xxx:
 *       path: /forword1/test
 *       url: http://ip:port/newpath
 */
@Component
public class UrlRouteFilter extends ZuulFilter {


    // 该过滤器是否生效
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        // 被限流
        if (ctx.containsKey(Constant.CTX_REQUEST_LIMIT_KEY)
                && !ctx.getBoolean(Constant.CTX_REQUEST_LIMIT_KEY)) {
            return false;
        }

        return ctx.getBoolean("UrlRouteFilter");
    }

    // 拦截后的具体业务逻辑
	@Override
	public Object run() throws ZuulException {

		//获取上下文（重要，贯穿 所有filter，包含所有参数）
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		String remoteAddr = request.getRemoteAddr();
        String uri = request.getRequestURI();

        // http://localhost:7100/user-provider/zuul-alive
        if (uri.contains("/zuul-alive")) {  // 旧的路径，前端不想修改的
            System.out.println("UrlRouteFilter 拦截 来源uri："+uri);

            ctx.set(FilterConstants.SERVICE_ID_KEY, "user-provider");
            ctx.set(FilterConstants.REQUEST_URI_KEY, "/user/alive"); // 新服务的地址
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
		return 9;
	}

}
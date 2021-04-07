package com.liuyao.spcld.gatewayzull.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.liuyao.spcld.gatewayzull.config.Constant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.ZuulServlet;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 限流
 * @author yueyi2019
 */
@Component
public class LimitFilter extends ZuulFilter {

    // 2 qps(1秒  2个 请求 Query Per Second 每秒查询量)
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(2);

    @Override
	public boolean shouldFilter() {

        ZuulServlet zh;
		//获取上下文
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

        String remoteAddr = request.getRemoteAddr();

		String uri = request.getRequestURI();

		return true;
	}
	
	@Override
	public Object run() throws ZuulException {

		//获取上下文（重要，贯穿 所有filter，包含所有参数）
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		String remoteAddr = request.getRemoteAddr();

        String uri = request.getRequestURI();

        // 能拿到令牌
//        RATE_LIMITER.acquire(); // 阻塞
        if (RATE_LIMITER.tryAcquire()){ // 可以拿多个
            System.out.println("令牌通过");
            return null;
        }else {
            // 不能拿到令牌

            // 被流控的逻辑
            System.out.println("被限流了");
            // 不向route过滤器走 若不想往后边所有过滤器走，shouldFilter加条件
            ctx.set(Constant.CTX_REQUEST_LIMIT_KEY, false);
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
        }


		return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return -10; //最小
	}

}
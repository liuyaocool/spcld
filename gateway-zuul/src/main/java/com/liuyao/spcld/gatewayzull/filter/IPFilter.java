package com.liuyao.spcld.gatewayzull.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.ZuulServlet;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class IPFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String remoteAddr = request.getRemoteAddr();

        String uri = request.getRequestURI();

        // 高匿ip：反防ip  测试课程
        if (uri.equals("数据中的url")) {

        }

		return true;
	}

	@Override
	public Object run() throws ZuulException {
	    // ip 限制
        RequestContext ctx = RequestContext.getCurrentContext();

        /**
         * pre设置了，后面的route、post、error不走了
         * 不往下走，还走剩下的过滤器，但是不向后面的服务转发。
         *
         * RibbonRoutingFilter.shouldFilter
         *
         * 但是 后续的filter 需要
         *  shouldFilter() {
         *      return RequestContext.getCurrentContext().sendZuulResponse();
         *  }
         */
        ctx.setSendZuulResponse(false);
		return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
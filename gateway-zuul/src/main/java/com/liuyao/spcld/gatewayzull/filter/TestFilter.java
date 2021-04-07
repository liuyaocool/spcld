package com.liuyao.spcld.gatewayzull.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.ZuulServlet;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 鉴权filter
 * @author yueyi2019
 */
@Component
public class TestFilter extends ZuulFilter {

//	@Autowired
//	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 	该过滤器是否生效
     * 	放到DB中 存储过滤器开关
	 */
	@Override
	public boolean shouldFilter() {

        ZuulServlet zh;
		//获取上下文
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

        String remoteAddr = request.getRemoteAddr();

		String uri = request.getRequestURI();

//		//只有此接口/api-passenger/api-passenger-gateway-test/hello才被拦截
//		String checkUri = "/api-passenger/api-passenger-gateway-test/hello";
//		if(checkUri.equalsIgnoreCase(uri)) {
//			return true;
//		}
//        // 测试路径
//		if(uri.contains("api-driver")) {
//			return true;
//		}
		
		return true;
	}
	
	/**
	 * 	拦截后的具体业务逻辑
	 */
	@Override
	public Object run() throws ZuulException {

		//获取上下文（重要，贯穿 所有filter，包含所有参数）
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		String remoteAddr = request.getRemoteAddr();

        String uri = request.getRequestURI();
//        System.out.println("TestFilter 拦截 来源uri："+uri);


//        requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
//        requestContext.setResponseBody("auth fail");

//        requestContext.set("ifContinue",false);

		return null;
	}
	/**
	 * 拦截类型，4中类型。
	 */
	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return FilterConstants.ROUTE_TYPE;
	}

	/**
	 * 	值越小，越在前
	 */
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}


//	private void parseBody(HttpServletRequest request){
//        Map<String,Object> params = new HashMap<String, Object>();
//        BufferedReader br;
//        try {
//            br = request.getReader();
//            String str, wholeStr = "";
//            while((str = br.readLine()) != null){
//                wholeStr += str;
//            }
//            if(StringUtils.isNotEmpty(wholeStr)){
//                JSONObject jsonObject = JSONObject.fromObject(wholeStr);
//            }
//        } catch (IOException e1) {
//
//        }
//
//    }
}
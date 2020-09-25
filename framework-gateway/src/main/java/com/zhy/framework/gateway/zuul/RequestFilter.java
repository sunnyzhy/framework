package com.zhy.framework.gateway.zuul;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zhy.framework.common.model.ResponseEntity;
import com.zhy.framework.common.to.UserTo;
import com.zhy.framework.common.utils.JwtUtil;
import com.zhy.framework.gateway.configuration.ApplicationConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义zuul过滤器
 */
@Component
public class RequestFilter extends ZuulFilter {
    @Autowired
    private ApplicationConfiguration applicationConfiguration;

    /**
     * 定义filter的类型
     * PRE： 过滤器在请求被路由之前调用
     * ROUTING：过滤器将请求路由到微服务
     * POST：过滤器在路由到微服务以后执行
     * ERROR：在其他阶段发生错误时执行该过滤器
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 定义filter的顺序，数字越小表示顺序越高，越先执行
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 表示是否需要执行该filter，true表示执行，false表示不执行
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * filter需要执行的具体操作
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        final String requestUri = request.getRequestURI().substring(applicationConfiguration.getZuulPrefix().length());
        final String method = request.getMethod();
        // 不进行拦截的地址
        if (ignoreRoute(requestUri)) {
            return null;
        }
        String accessToken = request.getHeader("Access-Token");
        if (!JwtUtil.validateToken(accessToken)) {
            responseError(context, ResponseEntity.error(401, "Unauthorized!"));
            return null;
        }
        UserTo user = JwtUtil.getUserFromToken(accessToken);
        if (user == null) {
            responseError(context, ResponseEntity.error(401, "accessToken error!"));
            return null;
        }
        response(context);
        return null;
    }

    private void response(RequestContext requestContext) {
        // 对请求进行路由
        requestContext.setSendZuulResponse(true);
        requestContext.setResponseStatusCode(200);
        requestContext.set("isSuccess", true);
    }

    private void responseError(RequestContext requestContext,
                               ResponseEntity responseEntity) {
        // 不对请求进行路由
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(responseEntity.getCode());
        requestContext.setResponseBody(JSON.toJSONString(responseEntity));
        requestContext.set("isSuccess", false);
    }

    private boolean ignoreRoute(String requestUri) {
        return applicationConfiguration
                .getStartWith()
                .stream()
                .filter(x -> requestUri.startsWith(x))
                .findAny()
                .isPresent();
    }
}

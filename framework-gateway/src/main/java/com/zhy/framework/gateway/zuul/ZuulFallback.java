package com.zhy.framework.gateway.zuul;

import com.alibaba.fastjson.JSON;

import com.zhy.framework.common.model.ResponseEntity;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 路由失败时，回滚
 * 用于在配置文件里所配置的路由
 */
@Component
public class ZuulFallback implements FallbackProvider {
    /**
     * 设置要回滚的路由
     * 可以是具体的服务名称
     * 也可以是"*"或null，表示所有的服务都支持回滚
     * @return
     */
    @Override
    public String getRoute() {
        return "*";
    }

    /**
     * 回滚时响应的内容
     * @param route
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() {
                return 200;
            }

            @Override
            public String getStatusText() {
                return "OK";
            }

            @Override
            public void close() {

            }

            /**
             * response body
             * @return
             * @throws IOException
             */
            @Override
            public InputStream getBody() throws IOException {
                ResponseEntity responseEntity = new ResponseEntity();
                responseEntity.setCode(500);
                responseEntity.setMsg("服务器内部错误");
                String vo = JSON.toJSONString(responseEntity);
                return new ByteArrayInputStream(vo.getBytes("UTF-8"));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                //  需要跟getBody()中的编码一致，否则容易出现乱码
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}

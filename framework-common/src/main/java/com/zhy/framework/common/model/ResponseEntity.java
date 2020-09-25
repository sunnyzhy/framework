package com.zhy.framework.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * ResponseEntity
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResponseEntity<T> {
    /**
     * code
     */
    private Integer code;

    /**
     * message
     */
    private String msg;

    /**
     * data
     * 如果data为null，就不参加序列化
     */
    private T data;

    public static ResponseEntity success() {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setCode(0);
        responseEntity.setMsg("success");
        return responseEntity;
    }

    public static ResponseEntity success(Object data) {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setCode(0);
        responseEntity.setMsg("success");
        responseEntity.setData(data);
        return responseEntity;
    }

    public static ResponseEntity error(Integer code, String msg) {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setCode(code);
        responseEntity.setMsg(msg);
        return responseEntity;
    }
}

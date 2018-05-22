package com.bibased.leibobo.domain.support;

import lombok.Data;
/**
 * Created by boboLei on 2018/4/12
 *
 */
@Data
public class AjaxResponse {
    public static final int CODE_SUCCESS = 1;
    public static final int CODE_FAILURE = 0;
    public static final int CODE_ERROR = -1;
    private final int code;
    private final String msg;
    private final Object data;
    private final Object flag;
    private AjaxResponse(int code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
        flag = null;
    }
    private AjaxResponse(int code, String msg, Object data,Object flag){
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.flag = flag;
    }
    public static AjaxResponse successed(Object data,Object flag){
        return new AjaxResponse(CODE_SUCCESS, "SUCCESS", data,flag);
    }

    public static AjaxResponse succss(Object data){
        return new AjaxResponse(CODE_SUCCESS, "SUCCESS", data);
    }

    public static AjaxResponse failure(String msg){
        return new AjaxResponse(CODE_FAILURE, msg, null);
    }

    public static AjaxResponse error(String msg){
        return new AjaxResponse(CODE_ERROR, msg, null);
    }
}
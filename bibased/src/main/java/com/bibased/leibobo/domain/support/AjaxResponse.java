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
    private AjaxResponse(int code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
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
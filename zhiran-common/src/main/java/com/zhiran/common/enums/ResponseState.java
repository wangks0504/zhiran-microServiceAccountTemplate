package com.zhiran.common.enums;

public enum ResponseState {
    SUCCESS(200,"成功"),
    ERROR(-1,"失败"),
    UNKNOWN(-2,"未知错误");




    /**
     * 错误码
     */
    int code;
    /**
     * 错误信息
     */

    String errorMsg;
    ResponseState(int code , String errorMsg){
        this.errorMsg= errorMsg;
        this.code= code;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

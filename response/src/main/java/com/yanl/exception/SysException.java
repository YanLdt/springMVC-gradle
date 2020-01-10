package com.yanl.exception;

/**
 * @Author: YanL
 * @Time: 13:10 2020/1/10
 * @Email: imyanl.dt@gmail.com
 * @Description: 自定义异常
 */

public class SysException extends Exception {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SysException(String message) {
        this.message = message;
    }

}

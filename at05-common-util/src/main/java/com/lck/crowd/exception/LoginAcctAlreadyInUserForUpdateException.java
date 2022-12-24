package com.lck.crowd.exception;

/***
 #Create by LCK on 2022/11/24
 # 用法: 
 */
public class LoginAcctAlreadyInUserForUpdateException extends Exception{
    private static final long serialVersionUID = 1L;

    public LoginAcctAlreadyInUserForUpdateException() {
    }

    public LoginAcctAlreadyInUserForUpdateException(String message) {
        super(message);
    }

    public LoginAcctAlreadyInUserForUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctAlreadyInUserForUpdateException(Throwable cause) {
        super(cause);
    }

    public LoginAcctAlreadyInUserForUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package com.isma.school_ms_users.core.exceptions;

public class NoDataFoundException extends Exception {
    /***
     *
     * @param message
     */
    public NoDataFoundException(String message) {
        super(message);
    }
    /***
     *
     * @param message
     * @param cause
     */
    public NoDataFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

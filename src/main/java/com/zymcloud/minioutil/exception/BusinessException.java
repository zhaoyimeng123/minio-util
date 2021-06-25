package com.zymcloud.minioutil.exception;

import com.zymcloud.minioutil.enums.EnumInterface;
import com.zymcloud.minioutil.enums.ErrorEnum;
import com.zymcloud.minioutil.enums.ExceptionEnumInterface;

/**
 * 统一业务异常类
 */
public class BusinessException extends RuntimeException {

    private static final long      serialVersionUID = 236483930171554424L;

    private ExceptionEnumInterface exception;

    public BusinessException(ExceptionEnumInterface exception) {
        super(exception.getDesc());
        this.exception = exception;
    }

    public BusinessException(ExceptionEnumInterface exception, String message) {
        super(message);
        this.exception = exception;
    }

    public BusinessException(String message) {
        super(message);
        this.exception = ErrorEnum.UNKNOWN;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.exception = ErrorEnum.UNKNOWN;
    }


    public EnumInterface getErrorCode() {
        return exception;
    }
}

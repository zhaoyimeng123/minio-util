package com.zymcloud.minioutil.enums;


/**
 * 返回结果状态枚举接口
 */
public interface ExceptionEnumInterface extends EnumInterface {

    /**
     * 根据状态码获取枚举值
     * @return
     */
    static ExceptionEnumInterface getErrorEnum(String code) {
        ErrorEnum errorEnum = ErrorEnum.getErrorEnum(code);

        if (errorEnum != null) {
            return errorEnum;
        }

        return ErrorEnum.UNKNOWN;
    }
}

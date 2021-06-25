package com.zymcloud.minioutil.enums;

/**
 * @author zhaoyimeng
 * @date 2021/06/25
 */
public enum ErrorEnum implements ExceptionEnumInterface {

    SUCCESS("ok", "成功"),

    UNKNOWN("unknown", "未知错误"),

    UNAUTHENTICATED("unauthenticated", "未认证"),

    UNAUTHORIZED("unauthorized", "未授权"),

    INVALID_PARAMS("invalid_params", "参数错误");

    //4000-5000代表公用CODE

    /** 枚举编码 */
    private String code;
    /** 枚举描述 */
    private String desc;

    private ErrorEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ErrorEnum getErrorEnum(String code) {
        for (ErrorEnum error : ErrorEnum.values()) {
            if (error.code.equals(code)) {
                return error;
            }
        }
        throw new IllegalArgumentException("Param val mismatch.");
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}

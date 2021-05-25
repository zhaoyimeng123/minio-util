package com.zymcloud.minioutil.config;

/**
 * @author zhaoyimeng
 * @date 2021/05/25
 */
public enum FileSuffixEnum {
    PDF("pdf");

    private String fileSuffix;

    FileSuffixEnum(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }
}

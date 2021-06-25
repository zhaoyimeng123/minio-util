package com.zymcloud.minioutil.http.parse;

/**
 * @author zhaoyimeng
 * @date 2021/06/25
 */
public abstract class HTTPResponseParser<T> {

    public abstract T doParse(String responseText);

}

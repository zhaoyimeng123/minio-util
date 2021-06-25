/**
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.zymcloud.minioutil.http.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 * @author
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataApiResult<T> {


    @JSONField(name = "status")
    private int status;

    private String message;

    private List<T> data;

    private int rsize;

    private int total;
}
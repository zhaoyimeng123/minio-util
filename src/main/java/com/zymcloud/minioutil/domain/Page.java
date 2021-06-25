package com.zymcloud.minioutil.domain;

/**
 * @author zhaoyimeng
 * @date 2021/06/25
 */

import com.alibaba.fastjson.annotation.JSONType;

import java.io.Serializable;
import java.util.List;

/**
 * 分页类
 * @param <T>
 */
@JSONType(orders = { "pageSize", "pageNo", "total", "records"})
public class Page<T> implements Serializable {

    private static final long serialVersionUID  = 5636079738558757591L;

    private final static int  DEFAULT_PAGE_SIZE = 10;
    /**
     * 页宽
     */
    private int pageSize = DEFAULT_PAGE_SIZE;
    /**
     * 页码
     */
    private int pageNo;
    /**
     * 总条数
     */
    private int total;
    /**
     * 数据
     */
    private List<T> records;

    public Page() {
    }

    public Page(List<T> list) {
        com.github.pagehelper.Page page = (com.github.pagehelper.Page) list;
        this.pageNo = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.records = page;
        this.total = new Long(page.getTotal()).intValue();
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalCount() {
        return total;
    }

    public void setTotalCount(int totalCount) {
        this.total = totalCount;
    }

    public List<T> getData() {
        return records;
    }

    public void setData(List<T> data) {
        this.records = data;
    }
}

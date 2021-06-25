package com.zymcloud.minioutil.http.parse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zymcloud.minioutil.enums.ErrorEnum;
import com.zymcloud.minioutil.enums.ExceptionEnumInterface;
import com.zymcloud.minioutil.exception.BusinessException;
import com.zymcloud.minioutil.domain.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * @author zhaoyimeng
 * @date 2021/06/25
 */
public abstract class AbstractPageDataApiParser<T> extends HTTPResponseParser<Page<T>> {

    private static final Logger LOG     = LoggerFactory.getLogger(AbstractPageDataApiParser.class);

    private static final String STATUS = "status";
    private Class<T>            entityClass;
    private Integer             pageNum;
    private Integer             pageSize;

    public AbstractPageDataApiParser(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        entityClass = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public Page<T> doParse(String businessText) {
        Map<String, String> result = JSON.parseObject(businessText, new TypeReference<Map<String, String>>() {
        });
        try {
            String statusStr = result.get(STATUS);
            int status = Integer.valueOf(statusStr);
            if (status == 0) {
                List<T> data = JSON.parseArray(getData(result), entityClass);
                Page<T> page = new Page<>();
                Integer totalCount = getTotalCount(result);
                page.setTotalCount(null == totalCount ? CollectionUtils.isEmpty(data) ? 0 : data.size() : totalCount);
                page.setData(data);
                page.setPageNo(pageNum);
                page.setPageSize(pageSize);
                return page;
            } else {
                LOG.error("Acceess data api error, result : ", businessText);
                throw new BusinessException("请求超时，请刷新重试");
            }
        } catch (Exception e) {
            LOG.error("Acceess data api error, result : ", businessText);
            throw new BusinessException("请求超时，请刷新重试");
        }
    }

    protected abstract String getData(Map<String, String> result);

    protected abstract Integer getTotalCount(Map<String, String> result);
}

package com.zymcloud.minioutil.http.parse;

import org.springframework.util.ObjectUtils;
import java.util.Map;

/**
 * 解析http请求返回的json数据
 * @author zym
 * @date 2021/6/25
 * @param <T>
 */
public class DefaultPageDataApiParser<T> extends AbstractPageDataApiParser<T> {
    private static final String DATA  = "data";
    private static final String TOTAL = "total";

    public DefaultPageDataApiParser(Integer pageNum, Integer pageSize) {
        super(pageNum, pageSize);
    }

    @Override
    public String getData(Map<String, String> result) {
        return result.get(DATA);
    }

    @Override
    protected Integer getTotalCount(Map<String, String> result) {
        String totalCount = result.get(TOTAL);
        return ObjectUtils.isEmpty(totalCount) ? null : Integer.valueOf(totalCount);
    }
}

/**
 * 用法：
 *     @Override
 *     public Page<Gdxx> queryGdxx(BasicQueryRequest query) {
 *         HTTPGet get = new HTTPGet(dataapiGdxxUrl, buildBasicPageParameter(query));
 *         try {
 *             return httpClient.execute(get, new DefaultPageDataApiParser<Gdxx>(query.getPageNum(), query.getPageSize()) {
 *             });
 *         } catch (IOException ex) {
 *             LOG.error("调用数据平台股东信息接口: {}", get.requestUrl(), ex);
 *             throw new BusinessException("请求超时，请刷新重试");
 *         }
 *     }
 */

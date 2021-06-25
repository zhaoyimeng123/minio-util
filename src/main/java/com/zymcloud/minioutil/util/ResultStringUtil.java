package com.zymcloud.minioutil.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author zhaoyimeng
 * @date 2021/06/21
 */
public class ResultStringUtil {

    /**
     * 反射
     * 处理大数据接口返回数据里面字段的 "空"，"null" 值， 转换为 ""
     * @param list 要处理的数据集合
     * @param <T> 返回的数据集合
     * @return
     */
    public static  <T> List replaceBlankValue(List<T> list){
        if (!CollectionUtils.isEmpty(list)){
            for (T item : list) {
                //获取所有字段 除serialVersionUID
                Field[] fields = item.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    if ("serialVersionUID".equals(fieldName)){
                        continue;
                    }
                    try {
                        //获取字段值
                        Object s = field.get(item);
                        if (!ObjectUtils.isEmpty(s)){
                            String s1 = s.toString();
                            boolean b = StringUtils.isNotEmpty(s1) &&
                                    (
                                            "空".equals(s1)
                                            || "null".equalsIgnoreCase(s1)
                                            || "暂未入库".equals(s1)
                                            || "无法解析".equals(s1)
                                            || "[]".equals(s1.replaceAll(" ", ""))
                                    );
                            if (b){
                                //设置字段值为 ""
                                field.set(item,"");
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return list;
    }
}

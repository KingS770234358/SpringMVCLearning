package com.wq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

/***
 * 一个将对象转换成JSON字符串的工具类
 * 有很多关于时间格式的设置
 * 但实际上适用于所有的类
 * 只是同时实现了时间的功能:自定义时间格式/默认时间格式
 */

public class JSONUtil {
    public static String getJsonString(Object object){
        // 传入默认的sdf
        return getJsonString(object, "yyyy-MM-dd HH:mm:ss");
    }
    public static String getJsonString(Object object,String dateFormat){
        ObjectMapper objectMapper = new ObjectMapper();
        // 1.先将ObjectMapper将日期打印成时间戳的属性关闭
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 2.在传入自定义的时间格式
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        objectMapper.setDateFormat(sdf);
        try {
            return objectMapper.writeValueAsString(object);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}

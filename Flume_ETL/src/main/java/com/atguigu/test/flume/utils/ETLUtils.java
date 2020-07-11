package com.atguigu.test.flume.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-13 19:44
 */
public class ETLUtils {

    public static boolean isValued(String str){

        try {
            JSON.parse(str);
            return true;
        }catch (JSONException e){
            return false;
        }
    }

}

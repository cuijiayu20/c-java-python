package com.util;
/**
 * 字符串工具类
 * @author doct20
 * @date 2022年6月17日
 * @version 1.0
 * StribngUtil
 * isEmpty
 * isNotEmpty
 */
 
public class StringUtil {
    public static boolean isEmpty (String str){
        if(str==null || "".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }
//判断不为空
    public static boolean isNotEmpty (String str){
        if(str!=null && !"".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }
    
    
}

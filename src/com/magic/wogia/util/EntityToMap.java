package com.magic.wogia.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 功能：以类为对象把各属性值放入map
 * 编写人员：lzh
 * 时间：2016年9月1日上午10:10:53
 */
public class EntityToMap {

/*	public Map<String, Object> EntityToMapObject(Object obj){
		Field[] fields = obj.getClass().getDeclaredFields();  
		for(Field field : fields){  
		    String fieldName =  field.getName();  
		     String type = field.getType().toString();  
		        System.out.println("属性名："+fieldName+"\t属性类型："+type);  
		}  
		return 
	}*/
	  /** 
     * 将一个类查询方式加入map（属性值为int型时，0时不加入， 
     * 属性值为String型或Long时为null和“”不加入） 
     * 
     */  
    public static Map<String, Object> setConditionMap(Object obj){  
        Map<String, Object> map = new HashMap<String, Object>();  
        if(obj==null){  
            return null;  
        }  
        Field[] fields = obj.getClass().getDeclaredFields();  
        for(Field field : fields){  
            String fieldName =  field.getName();  
            if(getValueByFieldName(fieldName,obj)!=null)  
                map.put(fieldName,  getValueByFieldName(fieldName,obj));  
        }  
          
        return map;  
          
    }  
    /** 
     * 根据属性名获取该类此属性的值 
     * @param fieldName 
     * @param object 
     * @return 
     */  
    private static Object getValueByFieldName(String fieldName,Object object){  
        String firstLetter=fieldName.substring(0,1).toUpperCase();  
        String getter = "get"+firstLetter+fieldName.substring(1);  
        try {  
            Method method = object.getClass().getMethod(getter, new Class[]{});  
            Object value = method.invoke(object, new Object[] {});    
            return value;  
        } catch (Exception e) {  
            return null;  
        }   
          
    }  
}

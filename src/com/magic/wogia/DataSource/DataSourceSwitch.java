package com.magic.wogia.DataSource;

/**
 * 
 * 功能：数据源切换
 * 编写人员：lzh
 * 时间：2016年8月31日下午4:25:05
 */
public class DataSourceSwitch {
	 @SuppressWarnings("rawtypes")
	private static final ThreadLocal contextHolder=new ThreadLocal();  
     
	    @SuppressWarnings("unchecked")
		public static void setDataSourceType(String dataSourceType){  
	        contextHolder.set(dataSourceType);  
	    }  
	      
	    public static String getDataSourceType(){  
	        return (String) contextHolder.get();  
	    }  
	      
	    public static void clearDataSourceType(){  
	        contextHolder.remove();  
	    } 
}

package com.magic.wogia.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;  

/**
 * 
 * 功能：配置于applicationContext 中，线程局部变量ThreadLocal contextHolder 保存当前需要的数据源类型，
 *      当 DataSourceSwitch.setDataSourceType(DataSourceInstances.XXX) 保存当前需要的数据源类型的时候，
 *      DataSources 会从当前线程中查找线程变量的数据源类型，从而决定使用何种数据源  
 * 编写人员：lzh
 * 时间：2016年8月31日下午4:24:56
 */
public class DataSources extends AbstractRoutingDataSource{  
  
    @Override  
    protected Object determineCurrentLookupKey() {  
        return DataSourceSwitch.getDataSourceType();  
    }  
  
}  
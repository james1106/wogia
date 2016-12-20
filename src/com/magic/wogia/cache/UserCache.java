package com.magic.wogia.cache;

import java.util.HashMap;
import java.util.Map;

import com.magic.wogia.bean.UserBean;
import com.magic.wogia.service.UserService;

/**
 * 
 * 功能：缓存用户信息
 * 编写人员：lzh
 * 时间：2016年9月1日下午4:22:53
 */
public class UserCache {

	/**
	 * 登录成功后的用户的缓存池
	 */
	public static Map<Integer, Object> userCacheMap = new HashMap<Integer, Object>();
	
	public static UserService userService;
	private UserCache() {}  
    private static UserCache userCache=null;  
    //静态工厂方法   
    public static UserCache getInstance() {  
         if (userCache == null) {    
        	 userCache = new UserCache();  
         }    
        return userCache;  
    }  
	
    /**
     * 添加或更新缓存池
     * @param id
     * @param obj
     */
	public static void saveUserCache(Integer id, Object obj){
		synchronized (userCacheMap) {
			userCacheMap.put(id, obj);
		}
	}
	/**
	 * 把该用户移出缓存池
	 * @param id
	 */
	public static void remUserCache(Integer id){
		synchronized (userCacheMap) {
			userCacheMap.remove(id);
		}
	}
	
	/**
	 * 获取该用户的信息
	 * @param id
	 * @return
	 */
	public static Object findUserCache(Integer id){
		if (userCacheMap.get(id) == null || "".equals(userCacheMap.get(id))) {
			UserBean u = new UserBean();
			u.setId(id);
			//添加进入缓存
			saveUserCache(id,userService.findOnlyUser(u));
		}
		return userCacheMap.get(id);
	}
}

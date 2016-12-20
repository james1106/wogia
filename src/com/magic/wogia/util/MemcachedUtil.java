package com.magic.wogia.util;

import java.util.Date;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
/**
 *  MemCached缓存管理工具
 * @author QimouXie
 *
 */
public class MemcachedUtil {
	private static MemCachedClient memCachedClient;
	private static MemcachedUtil memcachedUtil = new MemcachedUtil();

	private MemcachedUtil() {
		memCachedClient = new MemCachedClient();
		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(new String[] { "127.0.0.1:11211" });
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000 * 6 * 2);
		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);
		pool.initialize();
		
	}

	public static MemcachedUtil getInstance() {
		if(null == memcachedUtil){
			memcachedUtil = new MemcachedUtil();
		}
		return memcachedUtil;
	}

	public void add(String key, Object value) {
		memCachedClient.add(key, value,0);
	}

	/**
	 *  以秒为单位
	 * @param key
	 * @param value
	 * @param milliseconds
	 */
	public void add(String key, Object value, int milliseconds) {
		memCachedClient.add(key, value, new Date(1000 * milliseconds));
	}
	
	public void set(String key, Object value) {
		memCachedClient.set(key, value,0);
	}
	public void set(String key, Object value, int milliseconds) {
		memCachedClient.set(key, value, new Date(1000 * milliseconds));
	}
	

	public Object get(String key) {
		return memCachedClient.get(key);
	}
	
	public void delObj(String key){
		memCachedClient.delete(key);
	}
	
	public boolean replace(String key,Object obj){
		return memCachedClient.replace(key, obj);
	}
	
	public static void main(String[] args) {
//		MemcachedUtil.getInstance().add("568", 123);
//		boolean isSuccess = MemcachedUtil.getInstance().replace("568", 1111);
		
//		memcachedUtil.add("123", 123);
//		System.out.println(MemcachedUtil.getInstance().get("568"));
//		memcachedUtil.delObj("123");
//		memcachedUtil.delObj("ada");
	}

}

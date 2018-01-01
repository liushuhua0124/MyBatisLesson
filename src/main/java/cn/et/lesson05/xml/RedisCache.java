package cn.et.lesson05.xml;

import java.io.IOException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;

import redis.clients.jedis.Jedis;

public class RedisCache implements Cache {
	
	//操作redis的对象
	Jedis jedis = new Jedis("localhost",6379); 	
	
	//缓存的id
	private String cacheID;
	
	public RedisCache(String cacheID) {
		this.cacheID = cacheID;
	}
	
	@Override
	public void clear() {
		
	}

	@Override
	public String getId() {
		return cacheID;
	}

	/**
	 * mybatis会自动调用getObject检测缓存中是否存在
	 */
	@Override
	public Object getObject(Object key) {
		try {
			byte[] bs = jedis.get(JavaRedis.objectToByteArray(key));
			if(bs == null) {
				return null;
			}
			return JavaRedis.byteArratToObject(bs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return new ReentrantReadWriteLock();
	}

	@Override
	public int getSize() {
		return 0;
	}

	/**
	 * mybatis读取数据时,将数据库中读取的数据通过putObject设置到缓存中
	 */
	@Override
	public void putObject(Object key, Object value) {
		//写入redis
		try {
			jedis.set(JavaRedis.objectToByteArray(key), JavaRedis.objectToByteArray(value));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * mybatis缓存策略:
	 * 	自动判断内存的大小,决定是否删除某些过期(久远)的数据
	 */
	@Override
	public Object removeObject(Object key) {
		Object object = getObject(key);
		try {
			jedis.del(JavaRedis.objectToByteArray(object));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return object;
	}

}

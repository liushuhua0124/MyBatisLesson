package cn.et.lesson05.xml;

import java.io.IOException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;

import redis.clients.jedis.Jedis;

public class RedisCache implements Cache {
	
	//����redis�Ķ���
	Jedis jedis = new Jedis("localhost",6379); 	
	
	//�����id
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
	 * mybatis���Զ�����getObject��⻺�����Ƿ����
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
	 * mybatis��ȡ����ʱ,�����ݿ��ж�ȡ������ͨ��putObject���õ�������
	 */
	@Override
	public void putObject(Object key, Object value) {
		//д��redis
		try {
			jedis.set(JavaRedis.objectToByteArray(key), JavaRedis.objectToByteArray(value));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * mybatis�������:
	 * 	�Զ��ж��ڴ�Ĵ�С,�����Ƿ�ɾ��ĳЩ����(��Զ)������
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

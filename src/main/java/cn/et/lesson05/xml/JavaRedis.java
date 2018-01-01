package cn.et.lesson05.xml;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import redis.clients.jedis.Jedis;

public class JavaRedis {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost",6379);
		jedis.set("myname", "jiaozi");
		System.out.println(jedis.get("myname"));
	}
	
	/**
	 * 序列化
	 * 主键 = student对象
	 * 写入数据到redis时
	 * 		jedis.set(主键,objectToByteArray(student对象))
	 * @param obj
	 * @return
	 * @throws IOException 
	 */
	public static byte[] objectToByteArray(Object obj) throws IOException {
		ByteOutputStream boss = new ByteOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(boss);
		oos.writeObject(obj);
		return boss.getBytes();
	}
	
	/**
	 * 反序列化
	 * 	byte[] bt = get("主键");
	 * Student s = byteArrayToObject(by)
	 * 默认redis没有实现mybatis缓存,需要自己实现
	 * @param by
	 * @return
	 * @throws Exception 
	 */
	public static Object byteArratToObject(byte[] by) throws Exception {
		ByteInputStream bis = new ByteInputStream(by,by.length);
		ObjectInputStream ois = new ObjectInputStream(bis);
		return ois.readObject();
	}
}

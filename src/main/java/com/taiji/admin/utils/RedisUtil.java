/**
 * 
 */
package com.taiji.admin.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * sw-view com.taiji.admin.utils RedisUtil.java
 *
 * @author hsl
 *
 * 2019年11月24日 下午12:05:08
 *
 * desc:
 */
public class RedisUtil {

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	
//	private final String ERR_EXPIRE_CODE = "E001";
	private final String ERR_EXPIRE_VALUE = "过期时间必须大于0";
//	private final String ERR_KEY_NO_EXIST_CODE = "E002";
	private final String ERR_KEY_NO_EXIST_VALUE = "缓存中不存在该key为的记录！";
	
	
	
	/**
	 * 基本数据类型，String，自定义类型
	 */
	
	/**
	 * 将value对象写入缓存
	 */
	public void set(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * 将value对象写入缓存，并设置失效时间
	 * @param time 失效时间(秒)
	 */
	public void set(String key, Object value, long time) {
		redisTemplate.opsForValue().set(key, value);
		if (time > 0)
			redisTemplate.expire(key, time, TimeUnit.SECONDS);
		else
			try {
				throw new Exception(ERR_EXPIRE_VALUE);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 * 获取缓存<br>
	 * 返回Object
	 */
	public Object get(String key) {
		return redisTemplate.boundValueOps(key).get();
	}

	/**
	 * 获取缓存<br>
	 * 对于临时存储的数据，读取之后从缓存中删除
	 * @param retain 是否保留
	 */
	public Object get(String key, boolean retain) {
		Object obj = redisTemplate.boundValueOps(key).get();
		if (!retain) {
			redisTemplate.delete(key);
		}
		return obj;
	}

	/**
	 * 获取缓存<br>
	 * 返回自定义类
	 */
	public <T> T get(String key, Class<T> clazz) {
		return (T) redisTemplate.boundValueOps(key).get();
	}

	/**
	 * 取得缓存（字符串类型）
	 * 返回String
	 */
	public String getStr(String key) {
		Object o = redisTemplate.boundValueOps(key).get();
		if (o == null)
			return null;
		return o.toString();
	}

	/**
	 * 指定缓存的失效时间
	 * @param time 失效时间(秒)
	 */
	public void expire(String key, long time) {
		if (time > 0) {
			redisTemplate.expire(key, time, TimeUnit.SECONDS);
		}
		else
			try {
				throw new Exception(ERR_EXPIRE_VALUE);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 * 删除缓存<br>
	 * 根据key精确匹配删除
	 */
	public void del(String... key) {
		if (key != null && key.length > 0) {
			if (key.length == 1) {
				redisTemplate.delete(key[0]);
			} else {
				redisTemplate.delete(CollectionUtils.arrayToList(key));
			}
		}
	}

	/**
	 * 批量删除<br>
	 * （该操作会执行模糊查询，请尽量不要使用，以免影响性能或误删）
	 */
	public void batchDel(String... pattern) {
		for (String kp : pattern) {
			redisTemplate.delete(redisTemplate.keys(kp + "*"));
		}
	}
	
	/**
	 * 清空缓存<br>
	 * 删除所有数据
	 */
	public void flush() {
		redisTemplate.delete(redisTemplate.keys("*"));
	}

	/**
	 * 模糊查询keys
	 */
	public Set keys(String pattern) {
		return redisTemplate.keys(pattern);
	}
	
	
	
	
	/**
	 * Json (com.alibaba.fastjson)
	 */

	/**
	 * 设置JSON
	 * 将value对象以JSON格式写入缓存
	 */
	public void setJson(String key, Object value) {
		stringRedisTemplate.opsForValue().set(key, JSONObject.toJSONString(value));
	}
	
	/**
	 * 设置JSON
	 * 将value对象以JSON格式写入缓存，并设置有效期
	 * @param time 失效时间(秒)
	 */
	public void setJson(String key, Object value, long time) {
		stringRedisTemplate.opsForValue().set(key, JSONObject.toJSONString(value));
		if (time > 0) {
			stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
		}
		else
			try {
				throw new Exception(ERR_EXPIRE_VALUE);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 * 更新key对象field的值
	 * @param key 缓存key
	 * @param field json field
	 * @param value json field对应的值
	 */
	public void setJsonField(String key, String field, String value) {
		JSONObject obj = JSONObject.parseObject(stringRedisTemplate.boundValueOps(key).get());
		if (obj != null) {
			obj.put(field, value);
			stringRedisTemplate.opsForValue().set(key, obj.toJSONString());
		}
		else
			try {
				throw new Exception(ERR_KEY_NO_EXIST_VALUE);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 * 获取缓存json对象<br>
	 * 返回自定义类
	 */
	public <T> T getJson(String key, Class<T> clazz) {
		return (T) JSONObject.parseObject(stringRedisTemplate.boundValueOps(key).get(), clazz);
	}
	
	
	
	
	/**
	 * list
	 */
	
	/**
	 * 添加list
	 */
	public void ladd(String key, Object... value) {
		for (Object o : value)
			redisTemplate.boundListOps(key).rightPush(o);
	}
	
	/**
	 * 读取list
	 * @return 
	 */
	public List lrange(String key, long start, long end) {
		return redisTemplate.boundListOps(key).range(start, end);
	}

	/**
	 * 删除list集合中的对象
	 * @param key
	 * @param count 有多个相同的该元素时，从集合开头算删除count个该元素
	 * @param value
	 */
	public void lrem(String key, long count, Object value) {
		redisTemplate.boundListOps(key).remove(count, value);
	}

	/**
	 * list重命名
	 */
	public void lrename(String oldkey, String newkey) {
		redisTemplate.boundListOps(oldkey).rename(newkey);
	}
	
	/**
	 * list元素数
	 */
	public long lsize(String key) {
		return redisTemplate.boundListOps(key).size();
	}
	
	/**
	 * list集合
	 */
	public List lmembers(String key) {
		return redisTemplate.boundListOps(key).range(0, redisTemplate.boundListOps(key).size());
	}
	
	
	
	/**
	 * set
	 */

	/**
	 * 添加set
	 */
	public void sadd(String key, Object... value) {
		for (Object o : value)
			redisTemplate.boundSetOps(key).add(o);
	}

	/**
	 * 删除set集合中的对象
	 */
	public void srem(String key, Object... value) {
		for (Object o : value)
			redisTemplate.boundSetOps(key).remove(o);
	}

	/**
	 * set重命名
	 */
	public void srename(String oldkey, String newkey) {
		redisTemplate.boundSetOps(oldkey).rename(newkey);
	}
	
	/**
	 * set元素数
	 */
	public long ssize(String key) {
		return redisTemplate.boundSetOps(key).size();
	}
	
	/**
	 * set集合
	 */
	public Set smembers(String key) {
		return redisTemplate.boundSetOps(key).members();
	}
	
	
	
	/**
	 * zset
	 */

	/**
	 * zset添加（同值覆盖）
	 */
	public void zadd(String key, Object value, double score) {
		redisTemplate.opsForZSet().add(key, value, score);
	}
	
	/**
	 * zset删除
	 * 按照值删除
	 */
	public void zrem(String key, Object... value) {
		for (Object o : value)
			redisTemplate.boundZSetOps(key).remove(o);
	}
	
	/**
	 * zset删除
	 * 按照坐标范围删除（包括范围值）
	 */
	public void zremoveRange(String key, long start, long end) {
		redisTemplate.boundZSetOps(key).removeRange(start, end);
	}
	
	/**
	 * zset删除
	 * 按照score范围删除（包括范围值）
	 */
	public void zremoveRangeByScore(String key, double start, double end) {
		redisTemplate.boundZSetOps(key).removeRangeByScore(start, end);
	}
	
	/**
	 * zset重命名
	 */
	public void zrename(String oldkey, String newkey) {
		redisTemplate.boundZSetOps(oldkey).rename(newkey);
	}
	
	/**
	 * zset计数
	 * 按照score范围计数
	 */
	public long zcount(String key, double start, double end) {
		return redisTemplate.boundZSetOps(key).count(start, end);
	}
	
	/**
	 * zset元素数
	 * 
	 */
	public long zsize(String key) {
		return redisTemplate.boundZSetOps(key).size();
	}
	
	/**
	 * zset获取元素
	 * 下标在start~end区间的数据集（按照score升序排列）
	 */
	public Set zrange(String key, long start, long end) {
		return redisTemplate.boundZSetOps(key).range(start, end);
	}
	
	/**
	 * zset获取元素
	 * score在start~end区间的数据集（按照score升序排列）
	 */
	public Set zrangeByScore(String key, double start, double end) {
		return redisTemplate.boundZSetOps(key).rangeByScore(start, end);
	}
	
	/**
	 * zset获取元素
	 * 下标在start~end区间的数据集（按照score降序排列）
	 */
	public Set zreverseRange(String key, long start, long end) {
		return redisTemplate.boundZSetOps(key).reverseRange(start, end);
	}
	
	/**
	 * zset获取元素
	 * score在start~end区间的数据集（按照score降序排列）
	 */
	public Set zreverseRangeByScore(String key, double start, double end) {
		return redisTemplate.boundZSetOps(key).reverseRangeByScore(start, end);
	}
	
	
	/**
	 * map
	 */

	/**
	 * 添加map元素
	 * 键值参数
	 */
	public void put(String key, String field, Object value) {
		redisTemplate.opsForHash().put(key, field, value);
	}
	
	/**
	 * 添加map元素
	 * map参数
	 */
	public void put(String key, Map<String, Object> map) {
		redisTemplate.opsForHash().putAll(key, map);
	}

	/**
	 * 删除map集合中的对象
	 * 键参数
	 */
	public void mrem(String key, String... field) {
		for (String s : field)
			redisTemplate.boundHashOps(key).delete(s);
	}
	
	/**
	 * 判断map是否包含键为field的元素
	 */
	public boolean hasKey(String key, String field) {
		return redisTemplate.boundHashOps(key).hasKey(field);
	}
	
	/**
	 * map的键集合
	 */
	public Set mkeys(String key) {
		return redisTemplate.boundHashOps(key).keys();
	}
	
	/**
	 * 获取map元素
	 * 键参数
	 */
	public Object get(String key, String field) {
		return redisTemplate.boundHashOps(key).get(field);
	}

	/**
	 * map重命名
	 */
	public void mrename(String oldkey, String newkey) {
		redisTemplate.boundHashOps(oldkey).rename(newkey);
	}
	
	/**
	 * map元素数
	 */
	public long msize(String key) {
		return redisTemplate.boundHashOps(key).size();
	}

	/**
	 * map集合
	 */
	public Map<Object, Object> mmembers(String key) {
		return redisTemplate.boundHashOps(key).entries();
	}

}


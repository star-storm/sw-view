/**
 * 
 */
package com.taiji.admin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 
 * sw-view com.taiji.admin.utils JsonObjectUtil.java
 *
 * @author hsl
 *
 * 2019年11月24日 下午12:03:59
 *
 * desc:
 */
public class JsonObjectUtil {
	
	public static String jsonToString(JSONObject json) {
		return json.toString();
	}
	
	public static JSONObject stringToJson(String json) {
		return JSONObject.fromObject(json);
	}
	
	public static JSONObject mapToJson(Map<?, ?> map) {
		return JSONObject.fromObject(map);
	}
	
	public static JSONObject mapDateToJson(Map<?, ?> map) {
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		return JSONObject.fromObject(map, config);
	}
	
	public static Object getValueByName(JSONObject json, String name) {
		return json.get(name);
	}
	
	public static Object getValueByName(String json, String name) {
		return JSONObject.fromObject(json).get(name);
	}
	
	public static class JsonDateValueProcessor implements JsonValueProcessor {

		public Object processArrayValue(Object value, JsonConfig config) {
			return getDateValue(value);
		}

		public Object processObjectValue(String key, Object value, JsonConfig config) {
			return getDateValue(value);
		}
		
		private Object getDateValue(Object value) {
			if (value == null)
				return "";
			else if (value instanceof Date)
				return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(value);
			else
				return value;
		}
		
	}
	
	public static String arrayToString(JSONArray json) {
		return json.toString();
	}
	
	public static JSONArray stringToArr(String json) {
		return JSONArray.fromObject(json);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		JSONObject json = JSONObject.fromObject("{\"name\":\"json\",\"bool\":true,\"int\":1}");
//		System.out.println(json.get("name"));
//		System.out.println(json.toString());
		
//		mapTest();
		ztree();
	}
	
	private static void ztree() {
		JSONArray result = new JSONArray();
		String resultStr = "["
				+ "{ id:1, pId:0, name:'根 Root', open:true},"
				+ "{ id:11, pId:1, name:'父节点 1-1', open:true},"
				+ "{ id:111, pId:11, name:'叶子节点 1-1-1'},"
				+ "{ id:112, pId:11, name:'叶子节点 1-1-2'},"
				+ "{ id:113, pId:11, name:'叶子节点 1-1-3'},"
				+ "{ id:114, pId:11, name:'叶子节点 1-1-4'},"
				+ "{ id:12, pId:1, name:'父节点 1-2', open:true},"
				+ "{ id:121, pId:12, name:'叶子节点 1-2-1'},"
				+ "{ id:122, pId:12, name:'叶子节点 1-2-2'},"
				+ "{ id:123, pId:12, name:'叶子节点 1-2-3'},"
				+ "{ id:124, pId:12, name:'叶子节点 1-2-4'},"
				+ "{ id:13, pId:1, name:'父节点 1-3', open:true},"
				+ "{ id:131, pId:13, name:'叶子节点 1-3-1'},"
				+ "{ id:132, pId:13, name:'叶子节点 1-3-2'},"
				+ "{ id:133, pId:13, name:'叶子节点 1-3-3'},"
				+ "{ id:134, pId:13, name:'叶子节点 1-3-4'},"
				+ "]";
		result = JSONArray.fromObject(resultStr);
		System.out.println(result.get(0));
	}
	
	@SuppressWarnings("unused")
	private static void mapTest() throws ParseException {
		Map<String, Date>map = getMap();
		viewMap(map);
		JSONObject json = mapToJson(map);
		System.out.println(jsonToString(json));
		System.out.println(getValueByName(json, "zzz"));
		json = mapDateToJson(map);
		System.out.println(jsonToString(json));
		System.out.println(getValueByName(json, "zzz"));
	}
	
	private static Map<String, Date> getMap() throws ParseException {
		Map<String, Date> map = new HashMap<String, Date>();
		map.put("z", new Date());
		map.put("zz", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2014-04-12 00:00:00"));
		map.put("zzz", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2014-12-12 00:00:00"));
		return map;
	}
	
	private static void viewMap(Map<String, Date> map) {
		Set<String>set = map.keySet();
		Iterator<String>it = set.iterator();
		while (it.hasNext()) {
			String key = it.next();
			Date value = map.get(key);
			System.out.println("key = [" + key + "], value = [" + value + "]");
		}
	}

}

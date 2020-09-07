/**
 * 
 */
package com.taiji.admin.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.StringUtils;

/**
 * 
 * sw-view com.taiji.admin.utils XmlUtil.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午9:51:54
 *
 * desc:
 */
public class XmlUtil {
	
	/**
	 * 解析xml
	 */
	public static List<Map<String, String>> parseXml(String s, String nodeName, String... fields) throws IOException, DocumentException {
		if (StringUtils.isEmpty(s))
			return new ArrayList<>();
		Document doc = DocumentHelper.parseText(s);
		Element rootElement = doc.getRootElement();
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		iterateXml(rootElement, nodeName, list, fields);
		return list;
	}
	@SuppressWarnings("unchecked")
	private static void iterateXml(Element node, String nodeName, List<Map<String, String>> list, String... fields){
		//当前节点的名称、文本内容和属性
//		System.out.println("当前节点名称："+node.getName());//当前节点名称
//		System.out.println("当前节点的内容："+node.getTextTrim());//当前节点名称
//		for (String field : fields) {
//			if (node.getName().equals(field)) {
//				if (list.size() > 0)
//					list.get(list.size() - 1).put(field, node.getTextTrim());
//				else {
//					Map<String, String> map = new HashMap<>();
//					map.put(field, node.getTextTrim());
//					list.add(map);
//				}
//				break;
//			}
//		}
		for (String field : fields) {
			if (node.getName().equals(nodeName)) {
				Map<String, String> map = new IdentityHashMap<>();
				list.add(map);
				break;
			}
			if (node.getName().equals(field)) {
				list.get(list.size() - 1).put(new String(field), node.getTextTrim());
				break;
			}
		}
//		List<Attribute> listAttr=node.attributes();//当前节点的所有属性的list
//		for(Attribute attr:listAttr){//遍历当前节点的所有属性
//			String name=attr.getName();//属性名称
//			String value=attr.getValue();//属性的值
//			System.out.println("属性名称："+name+"属性值："+value);
//		}
		//递归遍历当前节点所有的子节点
		List<Element> listElement=node.elements();//所有一级子节点的list
		for(Element e:listElement){//遍历所有一级子节点
			iterateXml(e, nodeName, list, fields);//递归
		}
	}
	
	/**
	 * 获取某节点XML内容
	 */
	public static List<String> parseXmlNode(String s, String nodeName) throws IOException, DocumentException {
		if (StringUtils.isEmpty(s))
			return null;
		Document doc = DocumentHelper.parseText(s);
		Element rootElement = doc.getRootElement();
		List<String> list = new ArrayList<>();
		iterateXmlNode(rootElement, nodeName, list);
		return list;
	}
	@SuppressWarnings("unchecked")
	private static void iterateXmlNode(Element node, String nodeName, List<String> list){
		//当前节点的名称、文本内容和属性
//		System.out.println("当前节点名称0："+node.getName());//当前节点名称
//		System.out.println("当前节点的内容0："+node.getTextTrim());//当前节点名称
		if (node.getName().equals(nodeName)) {
			list.add(node.asXML());
		}
		else {//递归遍历当前节点所有的子节点
			List<Element> listElement=node.elements();//所有一级子节点的list
			if (listElement.size() > 0) {
				for(Element e:listElement){//遍历所有一级子节点
					iterateXmlNode(e, nodeName, list);//递归
				}
			}
		}
	}
	
	/**
	 * XML转对象
	 * @param s
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Object getObject(String s, Class<?> clazz) throws Exception {
		Object obj = clazz.newInstance();// 创建对象
		Field[] fields = clazz.getDeclaredFields();
		Document document = DocumentHelper.parseText(s);
		// 获取根节点
		Element root = document.getRootElement();
		List<Element> properties = root.elements();
		for (Element pro : properties) {
			// 获取属性名(首字母大写)
			String propertyname = pro.getName();
			String propertyvalue = pro.getText();
			for (Field field : fields) {
				if (field.getName().equalsIgnoreCase(propertyname)) {
					Method m = obj.getClass().getMethod("set" + formatField(propertyname), field.getClass());
					m.invoke(obj, propertyvalue);
				}
			}
		}
		return obj;
	}
	
	//格式化属性：首字母大写，其他不变
	public static String formatField(String field) {
		if (StringUtils.isEmpty(field))
			return "";
		String tmp1 = field.substring(0, 1).toUpperCase();
		String tmp2 = "";
		if (field.length() > 1)
			tmp2 = field.substring(1);
		return new StringBuffer().append(tmp1).append(tmp2).toString();
	}
	
}

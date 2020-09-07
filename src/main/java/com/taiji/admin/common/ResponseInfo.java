/**
 * 
 */
package com.taiji.admin.common;

/**
 * 
 * sw-view com.taiji.admin.comon ResponseInfo.java
 *
 * @author hsl
 *
 * 2019年11月24日 上午10:30:51
 *
 * desc:
 */
public class ResponseInfo {
	
	//返回码
	private int code;
	//记录数
	private long count;
	//返回数据
	private Object data;
	//错误信息
	private String msg;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}

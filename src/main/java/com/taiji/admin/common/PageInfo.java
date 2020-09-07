/**
 * 
 */
package com.taiji.admin.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * sw-view com.taiji.admin.common PageInfo.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午9:23:01
 *
 * desc:
 */
public class PageInfo {
	
	// 默认显示的记录数
	private final static int PAGESIZE = 10;
	//当前页
	@JsonIgnore
	private int nowPage;
	//每页显示的记录数
	@JsonIgnore
	private int pageSize;
	//记录开始数--查询用
	private int from;
	//记录数--查询用
	private int size;
	
	public PageInfo(Integer nowPage, Integer pageSize) {
		//当前页
		if (nowPage == null || nowPage <= 0)
			this.nowPage = 1;
		else
			this.nowPage = nowPage;
		
		//页数
		if (pageSize == null || pageSize <= 0)
			this.pageSize = PAGESIZE;
		else
			this.pageSize = pageSize;
		
		//计算查询用-记录开始数
		this.from = (this.nowPage - 1) * this.pageSize;
		//计算查询用-记录数
		this.size = this.pageSize;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public static void main(String[] args) {
		PageInfo info = new PageInfo(1, 12);
		System.out.println(info.from);
		System.out.println(info.size);
	}
	
}

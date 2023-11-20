package com.ifyou.skypivot.sample.infrastructure.util;

import lombok.Data;

@Data
public class PageOrder {
	/**
	 * 升序排序
	 */
	public static final String ASC="ASC";

	/**
	 * 降序排序
	 */
	public static final String DESC="DESC";
	/**
	 * 升序降序
	 */
	private String sort;

	/**
	 * 升序降序字段
	 */
	private String sortName;

	/**
	 * 从0开始的页码
	 */
	private Integer pageIndex;

	/**
	 * 页面大小
	 */
	private Integer pageSize;

	public PageOrder() {
		initValue();
	}

	private void initValue() {
		sortName = "id";	//默认排序列名id
		sort = "ASC";		//默认升序
		pageIndex = 0;		//默认页码为0
		pageSize = 10;		//默认页面大小10条
	}

	public PageOrder(String sort, String sortName, Integer pageIndex, Integer pageSize) {
		initValue();
		if(null != sort) {
			this.sort = sort;
		}
		if(null != sortName) {
			this.sortName = sortName;
		}
		if(null != pageIndex) {
			this.pageIndex = pageIndex;
		}
		if(null != pageSize) {
			this.pageSize = pageSize;
		}
	}
}

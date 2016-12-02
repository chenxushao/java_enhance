package com.chenxushao.java.page;

import java.util.List;

/**
 * 分页模型
 * 
 * @author chenxushao@gmail.com
 */
public class PagingModel<T> {

	/** 当前页 */
	private Integer currentPageNo;

	/** 每页显示记录数 */
	private Integer perPageSize;

	/** 总记录数 */
	private Long totalRecordCount;

	/** 记录集合 */
	private List recordList;

	/** 总页数 */
	private Integer totalPageCount;

	/**
	 * 默认的空参构造数
	 *
	 */
	private PagingModel() {
	}

	/**
	 * 构造函数,计算总页数、是否有上一页、下一页等.
	 * 
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            每页显示记录数
	 * @param recordCount
	 *            总记录数
	 * @param recordList
	 *            记录集合
	 */
	public PagingModel(int currentPage, int perPageSize, Long totalRecordCount,
			List recordList) {
		this.currentPageNo = currentPage;
		if (currentPage < 1) {
			this.currentPageNo = 1;
		}
		this.perPageSize = perPageSize;
		this.totalRecordCount = totalRecordCount;
		this.recordList = recordList;

		// 计算总页数
		this.totalPageCount = (int) Math.ceil(totalRecordCount
				/ (double) perPageSize);
		if (this.currentPageNo > this.totalPageCount) {
			this.currentPageNo = this.totalPageCount;
		}
	}

	public Integer getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(Integer currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public Integer getPerPageSize() {
		return perPageSize;
	}

	public void setPerPageSize(Integer perPageSize) {
		this.perPageSize = perPageSize;
	}

	public Long getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(Long totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public List getRecordList() {
		return recordList;
	}

	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}

	public Integer getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(Integer totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
}

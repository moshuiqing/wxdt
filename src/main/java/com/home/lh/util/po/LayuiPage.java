package com.home.lh.util.po;

public class LayuiPage {

	/**
	 * 每页的条数
	 */
	private Long limit;

	/**
	 * 页数
	 */
	private Long page;
	
	
	
	


	public LayuiPage() {
		super();
	}

	public LayuiPage(Long limit, Long page) {
		super();
		this.limit = limit;
		this.page = page;
	}

	public Long getStart() {

		return (page - 1) * limit;
	}

	public Long getEnd() {
		return limit;
	}

	public Long getLimit() {
		return limit;
	}

	public void setLimit(Long limit) {
		this.limit = limit;
	}

	public Long getPage() {
		return page;
	}

	public void setPage(Long page) {
		this.page = page;
	}

}

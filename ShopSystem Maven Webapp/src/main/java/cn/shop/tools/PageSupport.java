package cn.shop.tools;

/**
 * 
 *分页显示工具类
 */
public class PageSupport {
	private int totalPageCount = 1; // 总页数
	private int totalCount = 0; // 总记录数
	private int currentPageNo = 1;// 当前页码
	private int pageSize = 0;// 当前页显示条数

	public PageSupport() {
		super();
	}

	public PageSupport(int totalPageCount, int totalCount, int currentPageNo,
			int pageSize) {
		super();
		this.totalPageCount = totalPageCount;
		this.totalCount = totalCount;
		this.currentPageNo = currentPageNo;
		this.pageSize = pageSize;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
		}
		// 设置总页数
		this.setTotalPageCountByRs();
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		if (currentPageNo > 0) {
			this.currentPageNo = currentPageNo;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize > 0) {
			this.pageSize = pageSize;
		}
	}

	/**
	 * 设置总页数
	 */
	public void setTotalPageCountByRs() {
		if (this.totalCount % this.pageSize == 0) {
			this.totalPageCount = this.totalCount / this.pageSize;
		} else if (this.totalCount % this.pageSize > 0) {
			this.totalPageCount = this.totalCount / this.pageSize + 1;
		} else {
			this.totalPageCount = 0;
		}
	}
}

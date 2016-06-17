package com.tu.util;

public class PageStateVO_Prototype {
	private int totalNumberOfElements; // 总的数据条目数量，0表示没有数据
	private int pageSize; // 每一页显示的条目数
	private int lastPageNumber; // 获取最后一页页码，也就是总页数
	private int thisPageNumber; // 当前页的页码
	private boolean isFirstPage; // 是否是首页（第一页），第一页页码为1
	private boolean isLastPage; // 是否是最后一页
	private boolean hasNextPage; // 是否有下一页
	private boolean hasPreviousPage; // 是否有上一页
	private int thisPageFirstElementNumber; // 获取当前页的首条数据的行编码（从0开始计数）
	private int thisPageLastElementNumber; // 获取当前页的末条数据的行编码（从0开始计数）
	private int nextPageNumber; // 获取下一页编码
	private int previousPageNumber; // 获取上一页编码

	public PageStateVO_Prototype() {
		// TODO Auto-generated constructor stub
	}

	public boolean getIsFirstPage() {
		return isFirstPage;
	}

	public void setIsFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public boolean getIsLastPage() {
		return isLastPage;
	}

	public void setIsLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public int getLastPageNumber() {
		return lastPageNumber;
	}

	public void setLastPageNumber(int lastPageNumber) {
		this.lastPageNumber = lastPageNumber;
	}

	public int getNextPageNumber() {
		return nextPageNumber;
	}

	public void setNextPageNumber(int nextPageNumber) {
		this.nextPageNumber = nextPageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPreviousPageNumber() {
		return previousPageNumber;
	}

	public void setPreviousPageNumber(int previousPageNumber) {
		this.previousPageNumber = previousPageNumber;
	}

	public int getThisPageFirstElementNumber() {
		return thisPageFirstElementNumber;
	}

	public void setThisPageFirstElementNumber(int thisPageFirstElementNumber) {
		this.thisPageFirstElementNumber = thisPageFirstElementNumber;
	}

	public int getThisPageLastElementNumber() {
		return thisPageLastElementNumber;
	}

	public void setThisPageLastElementNumber(int thisPageLastElementNumber) {
		this.thisPageLastElementNumber = thisPageLastElementNumber;
	}

	public int getThisPageNumber() {
		return thisPageNumber;
	}

	public void setThisPageNumber(int thisPageNumber) {
		this.thisPageNumber = thisPageNumber;
	}

	public int getTotalNumberOfElements() {
		return totalNumberOfElements;
	}

	public void setTotalNumberOfElements(int totalNumberOfElements) {
		this.totalNumberOfElements = totalNumberOfElements;
	}

	public void setPageStateVOMemberProperty(int totalNumberOfElements,
			int onePageSize, int currentPageNumber) {
		setTotalNumberOfElements(totalNumberOfElements); // 设置总的数据条目数量，0表示没有数据
		setPageSize(onePageSize);
		setThisPageNumber(currentPageNumber);
		if (totalNumberOfElements == 0) {
			setIsFirstPage(true);
			setIsLastPage(true);
			setHasNextPage(false);
			setHasPreviousPage(false);
		} else {
			int totalPageNumber = (totalNumberOfElements + onePageSize - 1)
					/ onePageSize;

			setLastPageNumber(totalPageNumber); // 设置最后一页页码，也就是总页数
			setIsFirstPage((currentPageNumber == 1) ? true : false); // 当前页是否是首页（第一页）
			if (currentPageNumber != totalPageNumber) {
				setIsLastPage(false); // 如果当前页数不等于总页数，则表明当前页不是最后一页
			} else {
				setIsLastPage(true); // 如果当前页数等于总页数，则表明当前页是最后一页
			}
			if (currentPageNumber < totalPageNumber) { // 如果当前页数小于总页数，则表明仍然有下一页
				setHasNextPage(true);
			} else {
				setHasNextPage(false); // 如果当前页数大于或者等于总页数，则表明没有了下一页
			}
			if (currentPageNumber == 1) { // 如果当前页数等于首页（第一页），则表明没有上一页
				setHasPreviousPage(false);
			} else { // 如果当前页数不等于首页（第一页），则表明有上一页
				setHasPreviousPage(true);
			}

			int thisPageFirstElementNumber = (currentPageNumber - 1)
					* onePageSize;
			int thisPageEndElementNumber = thisPageFirstElementNumber
					+ (((currentPageNumber != totalPageNumber) || (totalNumberOfElements % getPageSize()) == 0) ? getPageSize()
							: // 当前页不是最后一页时或者总记录为整页数，最后记录号应该为一页的大小
							(totalNumberOfElements % getPageSize())); // 当前页是最后一页时

			setThisPageFirstElementNumber(thisPageFirstElementNumber); // 设置当前页的首条数据的行编码，（从0开始计数）
			setThisPageLastElementNumber(thisPageEndElementNumber); // 设置当前页的末条数据的行编码（从0开始计数）
			setNextPageNumber(currentPageNumber + 1); // 设置下一页编码
			setPreviousPageNumber(currentPageNumber - 1); // 设置上一页编码
		}
	}
}

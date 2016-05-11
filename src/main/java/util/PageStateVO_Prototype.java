package util;

public class PageStateVO_Prototype {
	private int totalNumberOfElements; // �ܵ�������Ŀ������0��ʾû������
	private int pageSize; // ÿһҳ��ʾ����Ŀ��
	private int lastPageNumber; // ��ȡ���һҳҳ�룬Ҳ������ҳ��
	private int thisPageNumber; // ��ǰҳ��ҳ��
	private boolean isFirstPage; // �Ƿ�����ҳ����һҳ������һҳҳ��Ϊ1
	private boolean isLastPage; // �Ƿ������һҳ
	private boolean hasNextPage; // �Ƿ�����һҳ
	private boolean hasPreviousPage; // �Ƿ�����һҳ
	private int thisPageFirstElementNumber; // ��ȡ��ǰҳ���������ݵ��б��루��0��ʼ������
	private int thisPageLastElementNumber; // ��ȡ��ǰҳ��ĩ�����ݵ��б��루��0��ʼ������
	private int nextPageNumber; // ��ȡ��һҳ����
	private int previousPageNumber; // ��ȡ��һҳ����

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
		setTotalNumberOfElements(totalNumberOfElements); // �����ܵ�������Ŀ������0��ʾû������
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

			setLastPageNumber(totalPageNumber); // �������һҳҳ�룬Ҳ������ҳ��
			setIsFirstPage((currentPageNumber == 1) ? true : false); // ��ǰҳ�Ƿ�����ҳ����һҳ��
			if (currentPageNumber != totalPageNumber) {
				setIsLastPage(false); // �����ǰҳ����������ҳ�����������ǰҳ�������һҳ
			} else {
				setIsLastPage(true); // �����ǰҳ��������ҳ�����������ǰҳ�����һҳ
			}
			if (currentPageNumber < totalPageNumber) { // �����ǰҳ��С����ҳ�����������Ȼ����һҳ
				setHasNextPage(true);
			} else {
				setHasNextPage(false); // �����ǰҳ�����ڻ��ߵ�����ҳ���������û������һҳ
			}
			if (currentPageNumber == 1) { // �����ǰҳ��������ҳ����һҳ���������û����һҳ
				setHasPreviousPage(false);
			} else { // �����ǰҳ����������ҳ����һҳ�������������һҳ
				setHasPreviousPage(true);
			}

			int thisPageFirstElementNumber = (currentPageNumber - 1)
					* onePageSize;
			int thisPageEndElementNumber = thisPageFirstElementNumber
					+ (((currentPageNumber != totalPageNumber) || (totalNumberOfElements % getPageSize()) == 0) ? getPageSize()
							: // ��ǰҳ�������һҳʱ�����ܼ�¼Ϊ��ҳ��������¼��Ӧ��Ϊһҳ�Ĵ�С
							(totalNumberOfElements % getPageSize())); // ��ǰҳ�����һҳʱ

			setThisPageFirstElementNumber(thisPageFirstElementNumber); // ���õ�ǰҳ���������ݵ��б��룬����0��ʼ������
			setThisPageLastElementNumber(thisPageEndElementNumber); // ���õ�ǰҳ��ĩ�����ݵ��б��루��0��ʼ������
			setNextPageNumber(currentPageNumber + 1); // ������һҳ����
			setPreviousPageNumber(currentPageNumber - 1); // ������һҳ����
		}
	}
}

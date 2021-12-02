package com.bbs.vo;

public class Paging {
	
	
	// 페이지
	// 1 2 3 4 5 =  1
	// 6 7 8 9 10 = 2
	
	// 다음버튼
	// min : (현재 sec -1) * 5 +1
	// max : 다음버튼 < 현재섹션 * 게시물 수 => 현재 sec*5
	//		 다음버튼 
	
	private int pageNumber; // 현재 페이지 번호
	private int count;		// 전체 게시물 갯수
	private int minPage;	// 페이징 최솟값
	private int maxPage;	// 페이징 최댓값
	private boolean next;	// 다음 섹션이 존재하는지 여부
	private int before;		// 이전 버튼을 눌렸을 때 이동할 페이지 번호
	private int forward;	// 다음 버튼을 눌렸을 때 이동할 페이지 번호
	
	
	public Paging() {}
	
	public Paging(int pageNumber, int count)	{
		
		// 한 페이지에 보여줄 게시물의 수
		final int BOARD_CNT = 10;
		// 한 섹션에 보여줄 페이지의 수
		final int PAGING_CNT = 5;
		
		this.pageNumber = pageNumber;
		this.count	 	= count;
		
		// 현재페이지가 어떤 섹션인가 구하기
		int section; 
		if(pageNumber % PAGING_CNT == 0) section = pageNumber /PAGING_CNT;
						   else section = pageNumber / PAGING_CNT+1;
		
		// 다음 섹션이 존재하려면 : sec*50 < cnt
		next = count > section * (PAGING_CNT * BOARD_CNT);
		
		// 다음버튼 만들기
		// 최소페이지
		minPage = 1 + (section - 1) * PAGING_CNT;
		// 최대페이지
		if(next) maxPage = section * PAGING_CNT;
		else {
			
			if(count % BOARD_CNT == 0) maxPage = count / BOARD_CNT;
						   else maxPage = count / BOARD_CNT + 1;
		}
		
		// 이동할 페이지
		// 1sec>6p 2sec>11p 3sec>16p
		forward = 1 + section * PAGING_CNT;
		// 5>2 10>3 15>4
		before = (section -1 ) * PAGING_CNT;
		
	}
		

	
	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getMinPage() {
		return minPage;
	}

	public void setMinPage(int minPage) {
		this.minPage = minPage;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public boolean getNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getBefore() {
		return before;
	}

	public void setBefore(int before) {
		this.before = before;
	}

	public int getForward() {
		return forward;
	}

	public void setForward(int forward) {
		this.forward = forward;
	}

}

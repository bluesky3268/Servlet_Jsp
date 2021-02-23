package com.newlecture.app.console;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.newlecture.app.entity.Notice;
import com.newlecture.app.service.NoticeService;

public class NoticeConsole {
	// 출력구현을 위한 데이터를 콘솔이 가지고 있기 위해서 NoticeServie클래스(CRUD) import
	private NoticeService service;
	private int page;
	private int count;
	private String searchWord;
	private String searchField;
	
	
	public NoticeConsole() {
		service = new NoticeService();	
		page = 1;
		searchField="title";
		searchWord="";
	}	
	
	// 출력 구현
	public void printNoticeList() throws ClassNotFoundException, SQLException {
		
		List<Notice> list = service.getList(page, searchField, searchWord);
		int count = service.getCount(); // 리스트를 구할 때 마다 값이 달라지기 때문에 지역변수로 사용.
		int lastPage = count / 10;
		lastPage = count%10 > 0 ? lastPage+1 : lastPage;
		
		System.out.println("=====================================");
		System.out.printf("         <공지사항> 총 %d 게시글\n", count);
		System.out.println("=====================================");
		for(Notice n : list) {
			System.out.printf("%d. %s / %s / %s\n", n.getId(), n.getTitle(), n.getWriterId(), n.getRegDate());
			
		}
		System.out.println("=====================================");
		System.out.printf("             %d/%d pages\n", page,lastPage);
		
	}
	// 입력받기
	public int inputNoticeMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.print("1. 상세조회 / 2. 이전 / 3. 다음 / 4. 글쓰기 / 5. 검색 / 6. 종료 >>");
		String menu_ = sc.nextLine();
		int menu = Integer.valueOf(menu_);
		return menu;
	}

	public void movePrevList() {
		
		if(page == 1) {
			System.out.println("-----------------");
			System.out.println("--첫 페이지 입니다.--");
			System.out.println("-----------------");
			return;
		}
		page--;
		
	}

	public void moveNextList() throws ClassNotFoundException, SQLException {
		// 공지사항 게시글의 총 개수를 알아야 페이지로 따졌을 때 몇 페이지인지가 계산되고 마지막 페이지를 계산할 수 있다. 
		// 조회를 하는 중간에 게시글이 추가되고 삭제될 수 있기 때문에 printNoticeList()의 lastPage 값을 가져오면 안되고 
		// 조회할 때 다시 값을 계산해줘야 한다
		int count = service.getCount(); 
		int lastPage = count / 10;
		lastPage = count%10 > 0 ? lastPage+1 : lastPage;
		
		if(page == lastPage) {
			System.out.println("-----------------");
			System.out.println("-마지막 페이지입니다.-");
			System.out.println("-----------------");
			return;
		}
		page++;
		
	}

	public void inputSearchWord() {
		Scanner sc = new Scanner(System.in);
		System.out.println("검색범주(title/content/writerID) 중 하나를 입력하세요.");
		System.out.println(">>");
		searchField = sc.nextLine();
		System.out.println("검색어를 입력하세요.>");
		System.out.println(">>");
		searchWord = sc.nextLine();
		
		
	}
	
}

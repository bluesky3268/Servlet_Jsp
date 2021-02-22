package ex1;

import java.sql.SQLException;

import com.newlecture.app.console.NoticeConsole;

public class Program5 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		NoticeConsole console = new NoticeConsole();
		// 출력
		console.printNoticeList();
		// 메뉴 입력받기
		int menu =  console.inputNoticeMenu();
		
		switch(menu) {
		
		case 1: 
			// 상세조회
			break;
		case 2:
			// 이전으로
			break;
		case 3: 
			// 다음으로
			break;
		case 4: 
			// 글쓰기
			break;
		}
		
	}

}

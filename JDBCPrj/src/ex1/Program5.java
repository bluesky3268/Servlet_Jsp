package ex1;

import java.sql.SQLException;

import com.newlecture.app.console.NoticeConsole;

public class Program5 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		NoticeConsole console = new NoticeConsole();
		// 출력
//		int page;
		EXIT:
		while(true) {
			console.printNoticeList();
			// 메뉴 입력받기
			int menu =  console.inputNoticeMenu();
			
			
			switch(menu) {
			
			case 1: 
				// 상세조회
				break;
			case 2:
				console.movePrevList();
//				page--;
				// 이전으로
				break;
			case 3: 
				console.moveNextList();
//				page++;
				// 다음으로
				break;
			case 4: 
				// 글쓰기
				break;
			case 5 : 
				console.inputSearchWord();
				break;
			case 6 : 
				// 종료
				System.out.println("종료되었습니다.");
				break EXIT;
			default : 
				System.out.println("숫자는 1~4까지만 입력할 수 있습니다.");
				break;
			} // End Switch
		}//End while	
	}

}

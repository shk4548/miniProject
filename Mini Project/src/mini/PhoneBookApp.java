package mini;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PhoneBookApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {

			System.out.println("****************************************");
			System.out.println("*          전화번호 관리 프로그램           *");
			System.out.println("****************************************");
			System.out.println("1.리스트 2.등록 3.삭제 4.검색 5.종료 ");
			System.out.println("________________________________________");
			System.out.print("> 메뉴번호:");

			int menu = sc.nextInt();

//	PhoneBookVO vo = new PhoneBookVO();

			switch (menu)

			{
			case 1:
				showList();

				break;
			case 2:
				insertData(sc);
	
				break;
			case 3:
				deleteData(sc);


				break;
			case 4:
				searchData(sc);

				break;
			case 5:
				System.out.println("****************************************");
				System.out.println("*               감사합니다                *");
				System.out.println("****************************************");
				return;

			}
			
		}

		
	}
	
	private static void searchData(Scanner sc) {
		
		System.out.print("<4.검색>");
		System.out.println("이름 :");
		String name = sc.next();


		PhoneBookDAO dao = new PhoneBookDAOImpl();
		List<PhoneBookVO> list = dao.search(name);
		Iterator<PhoneBookVO> it = list.iterator();
		

		while(it.hasNext()) {
			PhoneBookVO del = it.next();
			System.out.printf("%d\t%s\t%d\t%d%n",
					del.getid(),
					del.getname(),
					del.gethp(),
					del.gettel());}
		

		
	}
	
	
	private static void deleteData(Scanner sc) {
		showList();
		System.out.print("번호:");
		int id = sc.nextInt();
		
		PhoneBookDAO dao = new PhoneBookDAOImpl();
		boolean success = dao.delete(Long.valueOf(id));
		
		System.out.println("삭제되었습니다."+(success ? "성공":"실패"));
		showList();
			
	}

	private static void insertData(Scanner sc) {
		showList();
	System.out.println("2.<리스트>");
	System.out.print("이름:");
	String name = sc.next();
	System.out.print("휴대전화 :");
	String hp = sc.next();
	System.out.print("집전화 :");
	String tel = sc.next();

	PhoneBookVO vo = new PhoneBookVO(null, name, tel, hp);
	PhoneBookDAO dao = new PhoneBookDAOImpl();
	
	boolean success = dao.insert(vo);
	
	System.out.println("INSERT " + (success ? "성공": "실패") );
	}

	private static void showList() {
		PhoneBookDAO dao = new PhoneBookDAOImpl();

		List<PhoneBookVO> list = dao.getList();

		Iterator<PhoneBookVO> it = list.iterator();
		while (it.hasNext()) {
			PhoneBookVO item = it.next();
			System.out.println("1.<리스트>");
			System.out.printf("%d\t%s\t%d\t%d%n", item.getid(), item.getname(), item.gethp(), item.gettel());
		}
	}

}

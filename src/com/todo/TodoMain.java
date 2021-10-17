package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
	//	l.importData("todolist.txt"); // 주의! : 초기에 딱 한번만 실행할 것

		boolean quit = false;
		
		do {
			Menu.prompt();
			String menu = sc.next();
			System.out.println(menu + " 모드를 선택하셨습니다.");
			
			switch (menu) {
			
			case "help":
				Menu.displaymenu();
				break;

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "find":
				String keyword = sc.nextLine().trim();
				TodoUtil.findList(l, keyword);
				break;
				
			case "ls_cate":
				TodoUtil.listCateAll(l);
				break;
				
			case "find_cate":
				String cate = sc.nextLine().trim();
				TodoUtil.findCateList(l, cate);
				break;
				
			case "ls_name":
				System.out.println("제목순으로 정렬하였습니다.");
				TodoUtil.listAll(l, "title", 1);
				break;
				
			case "ls_name_desc":
				System.out.println("제목역순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"title",0);
				break;
				
			case "ls_date":
				System.out.println("날짜순으로 정렬하였습니다.");
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_date_desc":
				System.out.println("날짜역순으로 정렬하였습니다.");
				TodoUtil.listAll(l, "due_date", 0);
				break;
				
			case "comp":
				int index = sc.nextInt();
				TodoUtil.completeItem(l, index);
			
			case "ls_comp":
				System.out.println("완료된 항목을 출력합니다.");
				TodoUtil.listAll(l, 1);
				break;
				
			//1. 완료된 목록들을 한번에 모두 제거 (multi-item 기능)
			case "del_complete":
				System.out.println("완료된 항목을 모두 삭제합니다.");
				TodoUtil.deleteAllComplete(l, 1);
				
			//새로운 기능
			//1. 완료된 리스트와 완료되지 않은 리스트를 한번에 정렬해 보여줌
			case "ls_complete":
				System.out.println("완료된 항목과 완료되지 않은 항목을 구분하여 출력합니다.");
				TodoUtil.listComplete(l);
				break;
				
				
			//2. 주기적인 항목을 정렬해서 보여주기.
			case "add_repeat":
				System.out.println("주기적인 항목을 정렬해서 출력합니다.");
				TodoUtil.listRepeat(l, 1);
				
				
				
			case "exit":
				quit = true;
				break;
				

			default:
				System.out.println("존재하지 않는 명령어가 입력되었습니다. 다시 입력해주세요!");
				break;
			}
			
		} while (!quit);
		
		System.out.println("프로그램을 종료합니다.");
	}
}

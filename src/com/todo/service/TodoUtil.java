package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList l) {
		
		String category, title, desc, due_date, due_time;
		int is_completed = 0, is_repeat = 0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[항목 추가]\n");
	
		
		System.out.print("제목 >> ");
		title = sc.next();
	/*	if (list.isDuplicate(title)) {
			System.out.println("제목이 중복됩니다!\n");
			return;
		} */
		sc.nextLine();
		
		System.out.print("카테고리 >> ");
		category = sc.next();
		
		sc.nextLine();
		
		System.out.print("내용 >> ");
		desc = sc.nextLine().trim();
		
		System.out.print("마감일자 >> ");
		due_date = sc.nextLine().trim();
		
		System.out.print("마감시간 >> ");
		due_time = sc.nextLine().trim();
		
		System.out.print("반복여부(맞을경우 1, 아닐경우 0 입력) >> ");
		is_repeat = sc.nextInt();
		
		TodoItem t = new TodoItem(title, desc, category, due_date, due_time, is_repeat, is_completed);
		if(l.addItem(t)>0) {
			System.out.println("추가되었습니다.\n");
		}
		else {
			System.out.println("추가되지 않았습니다.\n");
		}
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[항목 삭제]\n"
				+ "삭제할 항목의 번호 입력해주세요 >> ");
		int num = sc.nextInt();
		if(num<1) {
			System.out.println("입력된 번호가 잘못되었습니다.");
			return;
		}
		
		if(l.deleteItem(num)>0) {
			System.out.println("삭제되었습니다.");
		}
		else {
			System.out.println("삭제되지 않았습니다.");
		}
	}


	public static void updateItem(TodoList l) {
		String new_title, new_desc, new_category, new_due_date, new_due_time;
		int new_is_repeat = 0;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[항목 수정]\n"
				+ "수정할 항목의 번호를 입력하세요 >>"
		);
		int num = sc.nextInt();
		if (num < 1 || num > l.getCount()) {
			System.out.println(num + "번 항목이 존재하지 않습니다.");
			return;
		}

		System.out.print("새로운 제목을 입력하세요 >> ");
		new_title = sc.next().trim();
		sc.nextLine();
		
		System.out.print("새로운 카테고리를 입력하세요 >> ");
		new_category = sc.next().trim();
		sc.nextLine();
		
		System.out.print("새로운 항목의 내용을 입력하세요 >> ");
		new_desc = sc.nextLine();
		
		System.out.print("새로운 마감일자를 입력하세요 >> ");
		new_due_date = sc.nextLine().trim();
		
		System.out.print("새로운 마감시간을 입력하세요 >> ");
		new_due_time = sc.nextLine().trim();
		
		System.out.println("반복일정 유무를 입력하세요(반복일정일시 1, 아닐시 0 입력) >>");
		new_is_repeat = sc.nextInt();
		
		int new_is_completed = 0;

		
				
		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date, new_due_time, new_is_repeat, new_is_completed);
		t.setId(num);
		if(l.updateItem(t) > 0) {
			System.out.println("항목이 수정되었습니다!");
		}

	}
	
	public static void listAll(TodoList l) {
		System.out.printf("[전체 목록, 총 %d개]\n",l.getCount());
		for(TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	


	/*
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			int count =0;
			while((line = br.readLine())!=null) {
				StringTokenizer st = new StringTokenizer(line, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String description = st.nextToken();
				String due_date = st.nextToken();
				String due_time = st.nextToken();
				String current_date = st.nextToken();
				int is_repeat = (int) st.nextElement();
				int is_completed = (int) st.nextElement();
				TodoItem item = new TodoItem(category, title, description, due_date, due_time, is_repeat, is_completed);
				item.setCurrent_date();
				l.addItem(item);
				count++;
			}
			br.close();
			System.out.println(count + "개의 항목을 읽었습니다.");
			
		} catch(FileNotFoundException e){
			System.out.println(filename + " 파일이 존재하지 않습니다.");
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	*/
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for(TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count ++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}
	
	public static void listCateAll(TodoList l) {
		int count = 0;
		for(String item : l.getCategories()) {
			System.out.print(item + " ");
			count ++;
			
		}
		System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다.\n", count);
	}
	
	public static void findCateList(TodoList l, String cate) {
		int count=0;
		for(TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count ++;
		}
		System.out.printf("\n총 %d개의 항목을 찾았습니다.\n",count);
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for(TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}

	public static void completeItem(TodoList l, int index) {
		// TODO Auto-generated method stub
		l.completeItem(index);
		
	}
	
	
	//완료된 항목 출력
	public static void listAll(TodoList l, int is_completed) {
		// TODO Auto-generated method stub
		int count = 0;
		System.out.println("[완료된 목록]");
		for(TodoItem item : l.getList(is_completed)) {
			count++;
			System.out.println(item.toString());
		}
		
		System.out.printf("총 %d개의 항목을 완료 했습니다.\n", count);
		
	}
	
	
	//완료된 항목과 완료되지 않은 항목을 나눠서 출력
	public static void listComplete(TodoList l) {
		// TODO Auto-generated method stub
		int com_count=0;
		int n_com_count=0;
		System.out.println("[완료된 목록]");
		for(TodoItem item : l.getList(1)) {
			com_count++;
			System.out.println(item.toString());
		}
		System.out.println("\n[완료되지 않은 목록]");
		for(TodoItem item : l.getList(0)) {
			n_com_count++;
			System.out.println(item.toString());
		}
		
		System.out.printf("총 %d개의 항목을 완료 했고, 총 %d개의 항목을 완료하지 못했습니다.\n", com_count, n_com_count);
	}
	
	//완료된 모든 항목을 삭제
	public static void deleteAllComplete(TodoList l, int is_completed) {
		int del_index = 0;
		int count = 0;
		// TODO Auto-generated method stub
		for(TodoItem item : l.getList(is_completed)) {
			del_index = item.getId();
			l.deleteItem(del_index);
			count ++;
			System.out.printf("%d, ", del_index);
		}
		System.out.print("번의 항목이 삭제되었습니다.\n");
		System.out.printf("총 %d개의 완료된 항목이 삭제되었습니다.\n", count);
	}
	
	//주기적인 항목을 정렬해서 보여줌
	public static void listRepeat(TodoList l, int is_repeat) {
		// TODO Auto-generated method stub
		System.out.println("[반복 일정 목록]\n");
		for(TodoItem item : l.getRepeatList("due_time", 1)) {
			System.out.println(item.toString());
		}
		
	}
	
	
	
}

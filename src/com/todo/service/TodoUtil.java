package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList l) {
		
		String category, title, desc, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[항목 추가]\n");
	
		
		System.out.print("제목 >> ");
		title = sc.next();
	/*	if (list.isDuplicate(title)) {
			System.out.println("제목이 중복됩니다!\n");
			return;
		} */
		if(l.isDuplicate(title)) {
			System.out.println("제목이 중복됩니다!");
			return;
		}
		System.out.print("카테고리 >> ");
		category = sc.next();
		
		sc.nextLine();
		
		System.out.print("내용 >> ");
		desc = sc.nextLine().trim();
		
		System.out.print("마감일자 >> ");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		if(l.addItem(t)>0) {
			System.out.println("추가되었습니다.");
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
		String new_title, new_desc, new_category, new_due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[항목 수정]\n"
				+ "수정할 항목의 번호를 입력하세요 >>"
		);
		int num = sc.nextInt();
		if (num < 1 || num > l.getList().size()) {
			System.out.println(num + "번 항목이 존재하지 않습니다.");
			return;
		}

		System.out.println("새로운 제목을 입력하세요 >> ");
		new_title = sc.next().trim();
		
		System.out.print("새로운 카테고리를 입력하세요 >> ");
		new_category = sc.next().trim();
		sc.nextLine();
		
		System.out.print("새로운 항목의 내용을 입력하세요 >> ");
		new_desc = sc.nextLine();
		
		System.out.print("새로운 마감일자를 입력하세요 >> ");
		new_due_date = sc.nextLine().trim();
		
		
	//	l.deleteItem(l.getList().get(num-1));
		
		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date);
		t.setId(num);
		if(l.updateItem(t) > 0) {
			System.out.println("항목이 수정되었습니다!");
		}
		
			
		

	}

	public static void listAll(TodoList l) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for(TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			for(TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			System.out.println("모든 데이터가 파일에 저장되었습니다.");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
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
				String current_date = st.nextToken();
				TodoItem item = new TodoItem(category, title, description, due_date);
				item.setCurrent_date(current_date);
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
	
	
	
/*	public static void find(TodoList l, String keyword) {
		int num = 0;
		int index = 0;
		System.out.print("찾으시려는 키워드를 입력하세요 >> ");
		for(TodoItem item : l.getList()) {
			if(item.toSaveString().contains(keyword)) {
				num++;
				System.out.println( (index+1) + ". [" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
			}
		index ++;
		}
		
		System.out.println("총 " + num + "개의 항목을 찾았습니다.");
	}
*/
	
}

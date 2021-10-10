package com.todo.menu;

import java.util.Scanner;

public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("< ToDoList 관리 명령어 사용법 >");
        System.out.println("(1) add : 항목 추가");
        System.out.println("(2) del : 항목 삭제");
        System.out.println("(3) edit : 항목 수정");
        System.out.println("(4) ls : 항목 전체 보기");
        System.out.println("(5) ls_name : 항목을 제목순으로 정렬");
        System.out.println("(6) ls_name_desc : 항목을 제목역순으로 정렬");
        System.out.println("(7) ls_date : 항목이 만들어진 날짜순으로 정렬");
        System.out.println("(8) ls_date_desc : 항목이 만들어진 날짜의 역순으로 정렬");
        System.out.println("(9) ls_cate : 전체 카테고리 보기");
        System.out.println("(10) find_cate [keyword] : 카테고리가 keyword인 요소 보기");
        System.out.println("(11) find [keyword]: keyword가 들어간 항목 찾기");
        
        System.out.println("(0) exit :프로그램 종료");
            
    }
    
    public static String prompt() {
    	System.out.print("명령어를 입력하세요 (명령어를 잊어버렸을 시 \"help\"를 입력하세요) >> ");
    	Scanner sc = new Scanner(System.in);
    	String menu = sc.next();
    	
    	System.out.println(menu + " 모드를 선택하셨습니다.");
    	
    	return menu;
    }
}

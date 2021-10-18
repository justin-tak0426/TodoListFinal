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
        System.out.println("(7) ls_date : 마감일이 빠른 순으로 정렬");
        System.out.println("(8) ls_date_desc : 마감일이 느린 순으로 정렬");
        System.out.println("(9) ls_cate : 전체 카테고리 보기");
        System.out.println("(10) find_cate [keyword] : 카테고리가 keyword인 요소 보기");
        System.out.println("(11) find [keyword]: keyword가 들어간 항목 찾기");
        System.out.println("(12) comp [번호]: 입력한 번호를 완료항목으로 만들");
        System.out.println("(13) ls_comp: 완료항목을 출력");
        System.out.println("(14) del_complete: 완료된 항목을 모두 삭제");
        System.out.println("(15) ls_complete: 완료된 항목과 완료되지 않은 항목을 구분하여 출력");
        System.out.println("(16) ls_repeat: 주기적인 항목을 정렬해서 출력");
        
        System.out.println("(0) exit :프로그램 종료");
            
    }
    
    public static void prompt() {
    	System.out.print("명령어를 입력하세요 (명령어를 잊어버렸을 시 \"help\"를 입력하세요) >> ");
   
    }
}

package com.todo.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	private String category;
    private String title;
    private String desc;
    private String current_date;
    private String due_date;
    private String due_time; //마감일의 몇시에 일정인지 알려주는 필드
    private int is_completed;
    private int is_repeat; //반복적인 일정인지 알려주는 필드
    
    private int id;


    public TodoItem(String title, String desc, String category, String due_date, String due_time, int is_repeat, int is_completed){
        this.category=category;
    	this.title=title;
        this.desc=desc;
        this.due_date=due_date;
        this.due_time=due_time;
        this.is_repeat=is_repeat;
        this.is_completed = is_completed;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        this.current_date = dateFormat.format(date);
        
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String current_date = dateFormat.format(date);
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }

    

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id = id;
		
	}

	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	

	@Override
	public String toString() {
		return id + ". [" + category + "] " + title + " - " + desc + " - "
				+ due_date + ", " + due_time + "까지  (등록일: " + current_date + ")" + "<반복일정여부: " + is_repeat + "> <완료여부: " + is_completed +">";
	}

	public String getDue_time() {
		return due_time;
	}

	public void setDue_time(String due_time) {
		this.due_time = due_time;
	}

	public int getIs_repeat() {
		return is_repeat;
	}

	public void setIs_repeat(int is_repeat) {
		this.is_repeat = is_repeat;
	}

	public int getComplete() {
		return is_completed;
	}

	public void setComplete(int is_completed) {
		this.is_completed = is_completed;
	}


}

package com.todo.dao;

import java.sql.Connection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.todo.service.DbConnect;
import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList {
	private List<TodoItem> list;
	Connection conn;

	public TodoList() {
		this.conn = DbConnect.getConnection();
	}

	public int addItem(TodoItem t) {
		String sql = "insert into list (title, memo, category, current_date, due_date, due_time, is_repeat, is_completed)"
				+ "values (?,?,?,?,?,?,?,?);";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getDesc());
			pstmt.setString(3, t.getCategory());
			pstmt.setString(4, t.getCurrent_date());
			pstmt.setString(5, t.getDue_date());
			pstmt.setString(6, t.getDue_time());
			pstmt.setInt(7, t.getIs_repeat());
			pstmt.setInt(8, t.getComplete());
			
			count = pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int deleteItem(int index) {
		String sql = "delete from list where id=?;";
		PreparedStatement pstmt;
		int count=0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, index);
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int updateItem(TodoItem t) {
		String sql = "update list set title=?, memo=?, category=?, current_date=?, due_date=?, due_time=?, is_repeat=?"
				+ " where id = ?;";
		PreparedStatement pstmt;
		int count = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getDesc());
			pstmt.setString(3, t.getCategory());
			pstmt.setString(4, t.getCurrent_date());
			pstmt.setString(5, t.getDue_date());
			pstmt.setString(6, t.getDue_time());
			pstmt.setInt(7, t.getIs_repeat());
			pstmt.setInt(8,  t.getId());
			count = pstmt.executeUpdate();
			pstmt.close();

		} catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public ArrayList<TodoItem> getList(){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list;";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String due_time = rs.getString("due_time");
				String current_date = rs.getString("current_date");
				int is_repeat = rs.getInt("is_repeat");
				int is_completed = rs.getInt("is_completed");
				TodoItem t = new TodoItem(title, description, category, due_date, due_time, is_repeat, is_completed);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<TodoItem> getList(String keyword) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		keyword = "%"+keyword+"%";
		try {
			String sql = "SELECT * FROM list WHERE title like ? or memo like ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2,  keyword);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String due_time = rs.getString("due_time");
				String current_date = rs.getString("current_date");
				int is_repeat = rs.getInt("is_repeat");
				int is_completed = rs.getInt("is_completed");
				TodoItem t = new TodoItem(title, description, category, due_date, due_time, is_repeat, is_completed);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			pstmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getList(int num){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		
		try {
			String sql = "SELECT * FROM list WHERE is_completed like ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String due_time = rs.getString("due_time");
				String current_date = rs.getString("current_date");
				int is_repeat = rs.getInt("is_repeat");
				int is_completed = rs.getInt("is_completed");
				TodoItem t = new TodoItem(title, description, category, due_date, due_time, is_repeat, is_completed);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			pstmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	

	
	public ArrayList<String> getCategories(){
		ArrayList<String> list = new ArrayList<String>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT DISTINCT category FROM list;";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String category = rs.getString("category");
				
				list.add(category);
				
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<TodoItem> getListCategory(String keyword){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "SELECT * FROM list WHERE category = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  keyword);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String due_time = rs.getString("due_time");
				String current_date = rs.getString("current_date");
				int is_repeat = rs.getInt("is_repeat");
				int is_completed = rs.getInt("is_completed");
				TodoItem t = new TodoItem(title, description, category, due_date, due_time, is_repeat, is_completed);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int getCount() {
		Statement stmt;
		int count = 0;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT count(id) FROM list;";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(id)");
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public void sortByName() {
		Collections.sort(list, new TodoSortByName());

	}

	
	public void reverseList() {
		Collections.reverse(list);
	}

	public void sortByDate() {
		Collections.sort(list, new TodoSortByDate());
	}

	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}

	public Boolean isDuplicate(String title) {
		for (TodoItem item : list) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}
	
	/*
	public void importData(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			String sql = "insert into list (title, memo, category, current_date, due_date)" + " values (?, ?, ?, ?, ?);";
			int records = 0;
			
			while((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String description = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, description);
				pstmt.setString(3, category);
				pstmt.setString(4, current_date);
				pstmt.setString(5, due_date);
				int count = pstmt.executeUpdate();
				if(count > 0) records ++;
				pstmt.close();
			}
			System.out.println(records + " records read!!");
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	public ArrayList<TodoItem> getOrderedList(String orderby, int ordering){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();	
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list ORDER BY " + orderby;
			if(ordering == 0) {
				sql += " desc";
			}
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String due_time = rs.getString("due_time");
				String current_date = rs.getString("current_date");
				int is_repeat = rs.getInt("is_repeat");
				int is_completed = rs.getInt("is_completed");
				TodoItem t = new TodoItem(title, description, category, due_date, due_time, is_repeat, is_completed);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	
	}
	
	public TodoItem getItem(int index) {
		return list.get(index);
	}
	
	
	public void completeItem(int index) {
		String sql = "update list set is_completed=?"
				+ " where id = ?;";
		PreparedStatement pstmt;
		int count=0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, index);
			count = pstmt.executeUpdate();
			pstmt.close();

		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		if(count>0) {
			System.out.println("수정 완료되었습니다.");
		}
		else {
			System.out.println("수정에 실패하였습니다.");
		}
		
		
	}
	
	public ArrayList<TodoItem> getRepeatList(String orderby, int ordering){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();	
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list WHERE is_repeat=1 ORDER BY " + orderby;
			if(ordering == 0) {
				sql += " desc";
			}
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String due_time = rs.getString("due_time");
				String current_date = rs.getString("current_date");
				int is_repeat = rs.getInt("is_repeat");
				int is_completed = rs.getInt("is_completed");
				TodoItem t = new TodoItem(title, description, category, due_date, due_time, is_repeat, is_completed);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	
	}


}

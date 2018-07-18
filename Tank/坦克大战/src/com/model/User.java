package com.model;

public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private int level;
	private int time;
	public User() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public User(int id, String username, String password, String email,
			int level, int time) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.level = level;
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", email=" + email + ", level=" + level
				+ ", time=" + time + "]";
	}
	

}

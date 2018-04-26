package project001.admin.entity;

public class Admin {
	private int id;
	private String user;
	private String password;
	
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}
	
	public String getUser(){
		return this.user;
	}
	public void setUser(String user){
		this.user = user;
	}
	
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String password){
		this.password = password;
	}
}

package com.ht.beans;

public class User {
  private int id;
  
  private String username;
  private String password;
  private String name;
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
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}


/**
 * @return the id
 */
public int getId() {
	return id;
}
/**
 * @param id the id to set
 */
public void setId(int id) {
	this.id = id;
}
public User(int id,String username, String password, String name) {
	this.id = id;
	this.username = username;
	this.password = password;
	this.name = name;
}
public User(String username, String password, String name) {
	this.username = username;
	this.password = password;
	this.name = name;
}
public User(String username, String password) {
	this.username = username;
	this.password = password;
}
public User(String username) {
	this.username = username;
}
public User() {

}  
}

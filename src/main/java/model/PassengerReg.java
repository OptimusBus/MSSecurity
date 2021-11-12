package model;

import javax.xml.bind.annotation.XmlRootElement;

import org.bson.Document;

@XmlRootElement
public class PassengerReg {
	private String username;
	private String password;
	private String email;
	private String name;
	private String surname;
	private String age;
	private String key;
	private String id;
	
	public PassengerReg(String id, String username, String password, String email, String name, String surname, String age, String key) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.key = key;
		this.id = id;
	}
	
	public PassengerReg(String id, String username, String password, String email, String name, String surname, String age) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	static public PassengerReg decodePassengerRegRequest(Document d) {
		String id = d.getString("id");
		String username = d.getString("username");
		String name = d.getString("name");
		String surname = d.getString("surname");
		String email = d.getString("email");
		String password = d.getString("password");
		String age = d.getString("age");
		return new PassengerReg(id, username, password, email, name, surname, age);
	}
	
	static public PassengerReg decodePassengerReg(Document d) {
		String id = d.getString("id");
		String username = d.getString("username");
		String name = d.getString("name");
		String surname = d.getString("surname");
		String email = d.getString("email");
		String password = d.getString("password");
		String age = d.getString("age");
		String key = d.getString("key");
		return new PassengerReg(id, username, password, email, name, surname, age, key);
	}
	
	static public Document encodePassengerReg(PassengerReg p) {
		Document pass = new Document();
		pass.append("id", p.getId());
		pass.append("username", p.getUsername());
		pass.append("password", p.getPassword());
		pass.append("email",p.getEmail());
		pass.append("name", p.getName());
		pass.append("surname", p.getSurname());
		pass.append("age", p.getAge());
		pass.append("key", p.getKey());
		return pass;
	}
}

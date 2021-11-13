package model;

import javax.xml.bind.annotation.XmlRootElement;

import org.bson.Document;

@XmlRootElement
public class VehicleReg {	
	public VehicleReg() {}
	public VehicleReg(String id, String username, String password, String email, String key) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.key = key;
	}
	
	public VehicleReg(String id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public String getId() {
		return id;
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
	
	public static VehicleReg decodeVehicleRegRequest(Document d) {
		if(d == null) return null;
		if(d.size() == 0) return null;
		String id = d.getString("id");
		String us = d.getString("username");
		String pwd = d.getString("password");
		String email = d.getString("email");
		return new VehicleReg(id, us, pwd, email);
	}
	
	public static VehicleReg decodeVehicleReg(Document d) {
		if(d == null) return null;
		if(d.size() == 0) return null;
		String id = d.getString("id");
		String us = d.getString("username");
		String pwd = d.getString("password");
		String email = d.getString("email");
		String key = d.getString("key");
		return new VehicleReg(id, us, pwd, email, key);
	}
	
	public static Document encodeVehicleReg(VehicleReg vr) {
		Document d = new Document("username", vr.getUsername());
		d.append("id", vr.getId());
		d.append("password", vr.getPassword());
		d.append("email", vr.getEmail());
		d.append("key", vr.getKey());
		return d;
	}

	private String id;
	private String username;
	private String password;
	private String email;
	private String key;
}

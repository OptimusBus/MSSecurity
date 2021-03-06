package model;

import javax.xml.bind.annotation.XmlRootElement;

import org.bson.Document;

@XmlRootElement
public class VehicleReg {	
	public VehicleReg() {}
	public VehicleReg(String vehicleId, String username, String password, String email, String key) {
		super();
		this.vehicleId = vehicleId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.key = key;
	}
	
	public VehicleReg(String vehicleId, String username, String password, String email) {
		super();
		this.vehicleId = vehicleId;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
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
		String vehicleId = d.getString("vehicleId");
		String us = d.getString("username");
		String pwd = d.getString("password");
		String email = d.getString("email");
		return new VehicleReg(vehicleId, us, pwd, email);
	}
	
	public static VehicleReg decodeVehicleReg(Document d) {
		if(d == null) return null;
		if(d.size() == 0) return null;
		String vehicleId = d.getString("vehicleId");
		String us = d.getString("username");
		String pwd = d.getString("password");
		String email = d.getString("email");
		String key = d.getString("key");
		return new VehicleReg(vehicleId, us, pwd, email, key);
	}
	
	public static Document encodeVehicleReg(VehicleReg vr) {
		Document d = new Document("username", vr.getUsername());
		d.append("vehicleId", vr.getVehicleId());
		d.append("password", vr.getPassword());
		d.append("email", vr.getEmail());
		d.append("key", vr.getKey());
		return d;
	}

	private String vehicleId;
	private String username;
	private String password;
	private String email;
	private String key;
}

package model;

import javax.xml.bind.annotation.XmlRootElement;

import org.bson.Document;

@XmlRootElement
public class Authentication {
	private String username;
	private String password;
	
	public Authentication(String username, String password) {
		this.username = username;
		this.password = password;
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

	public static Authentication decodeAuth(Document d) {
		return new Authentication(d.getString("username"), d.getString("password"));
	}
	
	public static Document encodeAuth(Authentication a) {
		Document d = new Document();
		d.append("username", a.getUsername());
		d.append("password", a.getPassword());
		return d;
	}
	
}

package model;

import javax.xml.bind.annotation.XmlRootElement;

import org.bson.Document;

@XmlRootElement
public class VehicleReg {

	public VehicleReg(String username, String email, String password) {
		// TODO Auto-generated constructor stub
	}

	public static Document convertPassengerRegToDocument(VehicleReg v) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	public static VehicleReg convertDocumentToVehicleReg(Document d) {
		// TODO Auto-generated method stub
		return null;
	}

}

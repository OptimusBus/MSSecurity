package service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.bson.Document;

import db.MongoConnector;
import model.Authentication;
import model.PassengerReg;
import model.VehicleReg;

public class Branch implements BranchLocal {
	private MongoConnector mdb = new MongoConnector();
	private static enum Type{VEHICLE, PASSENGER}
	public Branch() {}
	@Override
	public List<VehicleReg> getAllVehicleReg() {
		List<Document> list = mdb.getAllVehiclesReg();
		if(list.isEmpty()) {
			System.err.println("No VehicleReg found");
			return null;
		}
		return this.convertVehiRegDocumentList(list);
	}

	@Override
	public List<PassengerReg> getAllPassengerReg() {
		List<Document> list = mdb.getAllPassengersReg();
		if(list.isEmpty()) {
			System.err.println("No PassengerReg found");
			return null;
		}
		return this.convertPassRegDocumentList(list);
	}

	@Override
	public boolean createVehicleReg(VehicleReg v) throws NoSuchAlgorithmException {
		Document d = mdb.getVehicleRegByUsername(v.getUsername());
		if(d == null) {
			d = mdb.getVehicleRegByEmail(v.getEmail());
			if(d == null) {
				//Create vehicleReg
				String token = createApiToken(v.getUsername(), v.getEmail(), Type.VEHICLE);
				v.setKey(token);
				mdb.vehicleRegPersist(v);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean createPassengerReg(PassengerReg p) throws NoSuchAlgorithmException {
		Document d = mdb.getPassengerRegByUsername(p.getUsername());
		if(d == null) {
			d = mdb.getPassengerRegByEmail(p.getEmail());
			if(d == null) {
				//Create passengerReg
				String token = createApiToken(p.getUsername(), p.getEmail(), Type.PASSENGER);
				p.setKey(token);
				mdb.passengerRegPersist(p);
				return true;
			}
		}
		return false;
	}

	@Override
	public PassengerReg getPassRegByUsername(String username) {
		Document d = mdb.getPassengerRegByUsername(username);
		if(d != null) {
			return PassengerReg.decodePassengerReg(d);
		}else {
			System.err.println("No PassengerReg for username: " + username);
		}
		return null;
	}

	@Override
	public PassengerReg getPassRegByEmail(String email) {
		Document d = mdb.getPassengerRegByEmail(email);
		if(d != null) {
			return PassengerReg.decodePassengerReg(d);
		}else {
			System.err.println("No PassengerReg for email: " + email);
		}
		return null;
	}

	@Override
	public VehicleReg getVehiRegByUsername(String username) {
		Document d = mdb.getVehicleRegByUsername(username);
		if(d != null) {
			return VehicleReg.decodeVehicleReg(d);
		}else {
			System.err.println("No PassengerReg for username: " + username);
		}
		return null;
	}

	@Override
	public VehicleReg getVehiRegByEmail(String email) {
		Document d = mdb.getVehicleRegByEmail(email);
		if(d != null) {
			return VehicleReg.decodeVehicleReg(d);
		}else {
			System.err.println("No PassengerReg for email: " + email);
		}
		return null;
	}
	
	@Override
	public boolean checkToken(String email, String token, String type) {
		if(type.equalsIgnoreCase(Type.PASSENGER.toString())) {
			PassengerReg p = PassengerReg.decodePassengerReg(mdb.getPassengerRegByEmail(email));
			if(p.getKey().equals(token)) return true;
		}else if(type.equalsIgnoreCase(Type.VEHICLE.toString())){
			VehicleReg v = VehicleReg.decodeVehicleReg(mdb.getVehicleRegByEmail(email));
			if(v.getKey().equals(token)) return true;
		}
		return false;
	}
	
	@Override
	public PassengerReg getPassengerByCredential(Authentication a) {
		PassengerReg p = PassengerReg.decodePassengerReg(mdb.getPassengerIdByCredential(a.getUsername(), a.getPassword()));
		return p;
	}
	
	@Override
	public VehicleReg getVehicleByCredential(Authentication a) {
		VehicleReg v = VehicleReg.decodeVehicleReg(mdb.getVehcileIdByCredential(a.getUsername(), a.getPassword()));
		return v;
	}
	
	private String createApiToken(String username, String email, Type type) throws NoSuchAlgorithmException {
		String token = "";
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		String init = username + email;
		String salt = ""+System.currentTimeMillis();
		String t = init + salt;
	    md.update(t.getBytes());
	    byte[] digest = md.digest();
	    token = DatatypeConverter.printHexBinary(digest).toUpperCase();
	    String prefix = "VE";
	    if(type.toString().equalsIgnoreCase(Type.PASSENGER.toString())) prefix = "PA";
		return prefix + ".ob." + token;
	}
	
	private List<VehicleReg> convertVehiRegDocumentList(List<Document> docs) {
		List<VehicleReg> vs = new ArrayList<VehicleReg>();
		for(Document d : docs) {
			vs.add(VehicleReg.decodeVehicleReg(d));
		}
		return vs;
	}
	
	private List<PassengerReg> convertPassRegDocumentList(List<Document> docs) {
		List<PassengerReg> ps = new ArrayList<PassengerReg>();
		for(Document d : docs) {
			ps.add(PassengerReg.decodePassengerReg(d));
		}
		return ps;
	}

}

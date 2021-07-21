package service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import db.MongoConnector;
import model.PassengerReg;
import model.VehicleReg;

public class Branch implements BranchLocal {
	private MongoConnector mdb = new MongoConnector();
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
	public boolean createVehicleReg(VehicleReg v) {
		Document d = mdb.getVehicleRegByUsername(v.getUsername());
		if(d == null) {
			d = mdb.getVehicleRegByEmail(v.getEmail());
			if(d == null) {
				//Create vehicleReg
				mdb.addVehicleReg(v);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean createPassengerReg(PassengerReg p) {
		Document d = mdb.getPassengerRegByUsername(p.getUsername());
		if(d == null) {
			d = mdb.getPassengerRegByEmail(p.getEmail());
			if(d == null) {
				//Create passengerReg
				mdb.addPassengerReg(p);
				return true;
			}
		}
		return false;
	}

	@Override
	public PassengerReg getPassRegByUsername(String username) {
		Document d = mdb.getPassengerRegByUsername(username);
		if(d != null) {
			return PassengerReg.convertDocumentToPassengerReg(d);
		}else {
			System.err.println("No PassengerReg for username: " + username);
		}
		return null;
	}

	@Override
	public PassengerReg getPassRegByEmail(String email) {
		Document d = mdb.getPassengerRegByEmail(email);
		if(d != null) {
			return PassengerReg.convertDocumentToPassengerReg(d);
		}else {
			System.err.println("No PassengerReg for email: " + email);
		}
		return null;
	}

	@Override
	public VehicleReg getVehiRegByUsername(String username) {
		Document d = mdb.getVehicleRegByUsername(username);
		if(d != null) {
			return VehicleReg.convertDocumentToVehicleReg(d);
		}else {
			System.err.println("No PassengerReg for username: " + username);
		}
		return null;
	}

	@Override
	public VehicleReg getVehiRegByEmail(String email) {
		Document d = mdb.getVehicleRegByEmail(email);
		if(d != null) {
			return VehicleReg.convertDocumentToVehicleReg(d);
		}else {
			System.err.println("No PassengerReg for email: " + email);
		}
		return null;
	}
	
	private List<VehicleReg> convertVehiRegDocumentList(List<Document> docs) {
		List<VehicleReg> vs = new ArrayList<VehicleReg>();
		for(Document d : docs) {
			vs.add(VehicleReg.convertDocumentToVehicleReg(d));
		}
		return vs;
	}
	
	private List<PassengerReg> convertPassRegDocumentList(List<Document> docs) {
		List<PassengerReg> ps = new ArrayList<PassengerReg>();
		for(Document d : docs) {
			ps.add(PassengerReg.convertDocumentToPassengerReg(d));
		}
		return ps;
	}

}

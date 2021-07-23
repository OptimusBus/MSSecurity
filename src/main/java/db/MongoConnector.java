package db;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import model.PassengerReg;
import model.VehicleReg;

public class MongoConnector {
	private MongoClient m = new MongoClient("172.18.10.144", 31181);
	public MongoConnector() {}
	
	public List<Document> getAllVehiclesReg(){
		MongoDatabase db = m.getDatabase("SecurityDB");
		MongoCollection<Document> coll = db.getCollection("vehiclesReg");
		List<Document> ve = coll.find().into(new ArrayList<Document>());
		return ve;
	}
	
	public List<Document> getAllPassengersReg(){
		MongoDatabase db = m.getDatabase("SecurityDB");
		MongoCollection<Document> coll = db.getCollection("passengersReg");
		List<Document> pass = coll.find().into(new ArrayList<Document>());
		return pass;
	}
	
	public Document getVehicleRegByUsername(String username) {
		MongoDatabase db = m.getDatabase("SecurityDB");
		MongoCollection<Document> coll = db.getCollection("vehiclesReg");
		return coll.find(Filters.eq("username", username)).first();
	}
	
	public Document getVehicleRegByEmail(String email) {
		MongoDatabase db = m.getDatabase("SecurityDB");
		MongoCollection<Document> coll = db.getCollection("vehiclesReg");
		return coll.find(Filters.eq("email", email)).first();
	}
	
	public Document getPassengerRegByUsername(String username) {
		MongoDatabase db = m.getDatabase("SecurityDB");
		MongoCollection<Document> coll = db.getCollection("passengersReg");
		return coll.find(Filters.eq("username", username)).first();
	}
	
	public Document getPassengerRegByEmail(String email) {
		MongoDatabase db = m.getDatabase("SecurityDB");
		MongoCollection<Document> coll = db.getCollection("passengersReg");
		return coll.find(Filters.eq("email", email)).first();
	}
	
	public void addPassengerReg(PassengerReg p) {
		MongoDatabase db = m.getDatabase("SecurityDB");
		MongoCollection<Document> coll = db.getCollection("passengersReg");
		Document pass = PassengerReg.convertPassengerRegToDocument(p);
		coll.insertOne(pass);
	}
	
	public void addVehicleReg(VehicleReg v) {
		MongoDatabase db = m.getDatabase("SecurityDB");
		MongoCollection<Document> coll = db.getCollection("vehiclesReg");
		Document pass = VehicleReg.encodeVehicleReg(v);
		coll.insertOne(pass);
	}
}

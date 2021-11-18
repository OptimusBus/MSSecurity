package controller;

import java.security.NoSuchAlgorithmException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import mail.MailSender;
import model.Authentication;
import model.PassengerReg;
import model.VehicleReg;
import service.Branch;
import service.BranchLocal;

@Consumes("application/json")
@Produces("application/json")
@Path("/security")
public class SecurityController {
	private BranchLocal branch = new Branch();
	public SecurityController() {
		super();
	}
	
	@POST
	@Path("/requestVehicleReg")
	public Response requestVehicleReg(String request) throws NoSuchAlgorithmException {
		BasicDBObject b = BasicDBObject.parse(request);
		Document d = new Document(b);
		VehicleReg v = VehicleReg.decodeVehicleReg(d);
		boolean res = branch.createVehicleReg(v);
		if(res) {
			return Response.status(201).entity("Vehicle registered").build();
		}
		return Response.status(500).entity("Impossible to create the vehicle").build();
	}
	
	@POST
	@Path("/authVehicle")
	public Response authVehicle(String request) {
		BasicDBObject b = BasicDBObject.parse(request);
		Document d = new Document(b);
		Authentication a = Authentication.decodeAuth(d);
		VehicleReg v = branch.getVehicleByCredential(a);
		if(v != null) {
			Document doc = new Document();
			doc.append("vehicleId", v.getVehicleId());
			return Response.ok(doc).build();
		}
		return Response.status(401).entity("Unauthorized").build();
	}
	
	@POST
	@Path("/forgotVehicleCredentials")
	public Response forgotVehicleCredentials(String request) throws AddressException, MessagingException {
		BasicDBObject b = BasicDBObject.parse(request);
		String email = b.getString("email");
		VehicleReg v = branch.getVehiRegByEmail(email);
		if(v != null) {
			MailSender.sendMail(v.getPassword(), email, "Password Recovery");
			return Response.ok("Mail Sent").build();
		}
		return Response.status(404).entity("No passenger found").build();
	}
	
	@POST
	@Path("/requestPassengerReg")
	public Response requestPassengerReg(String request) throws NoSuchAlgorithmException {
		BasicDBObject b = BasicDBObject.parse(request);
		Document d = new Document(b); 
		PassengerReg p = PassengerReg.decodePassengerRegRequest(d);
		boolean res = branch.createPassengerReg(p);
		if(res) {
			return Response.status(201).entity("Passenger registered").build();
		}
		return Response.status(500).entity("Registering Error").build();
	}
	
	@POST
	@Path("/authPassenger")
	public Response authPassenger(String request) {
		BasicDBObject b = BasicDBObject.parse(request);
		Document d = new Document(b);
		Authentication a = Authentication.decodeAuth(d);
		PassengerReg p = branch.getPassengerByCredential(a);
		if(p != null) { 
			Document doc = new Document();
			doc.append("passengerId", p.getPassengerId());
			return Response.ok(doc).build();
		}
		return Response.status(401).entity("Unauthorized").build();
	}
	
	@POST
	@Path("/forgotPassengerCredentials")
	public Response forgotPassengerCredentials(String request) throws AddressException, MessagingException {
		BasicDBObject b = BasicDBObject.parse(request);
		String email = b.getString("email");
		PassengerReg p = branch.getPassRegByEmail(email);
		if(p != null) {
			MailSender.sendMail(p.getPassword(), email, "Password Recovery");
			return Response.ok("Mail Sent").build();
		}
		return Response.status(404).entity("No passenger found").build();
	}
	
	@GET
	@Path("/getPassengerReg/{username}")
	public Response getPassenger(@PathParam("username")String username) {
		PassengerReg p = branch.getPassRegByUsername(username); 
		if(p != null) return Response.ok(p).build();
		return Response.status(404).entity("Not Found").build();
	}
	
	@GET
	@Path("/validateToken")
	public Response validateToken(@QueryParam("apitoken")String token, @QueryParam("email")String email) {
		String[] t = token.split(".");
		if(t[0].equalsIgnoreCase("PA")) {
			PassengerReg p = branch.getPassRegByEmail(email);
			if(p != null && branch.checkToken(email, token, "PASSENGER")) return Response.ok("PASSENGER").build();
		}else if(t[0].equalsIgnoreCase("VE")) {
			VehicleReg v = branch.getVehiRegByEmail(email);
			if(v != null && branch.checkToken(email, token, "VEHICLE")) return Response.ok("VEHICLE").build();
		}else return Response.status(400).entity("Invalid Api Token").build();
		return Response.status(401).entity("Unathorized").build();
	}
}

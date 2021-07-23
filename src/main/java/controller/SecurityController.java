package controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.bson.Document;

import com.mongodb.BasicDBObject;

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
	public Response requestVehicleReg(String request) {
		BasicDBObject b = BasicDBObject.parse(request);
		Document d = new Document(b);
		VehicleReg v = VehicleReg.decodeVehicleReg(d);
		boolean res = branch.createVehicleReg(v);
		if(res) {
			return Response.ok().entity("Vehicle registered").build();
		}
		return Response.noContent().entity("Impossible to create the vehicle").build();
	}
	
	@POST
	@Path("/authVehicle")
	public Response authVehicle(String request) {
		BasicDBObject b = BasicDBObject.parse(request);
		Document d = new Document(b);
		Authentication a = Authentication.convertDocumentToAuthentication(d);
		return null;
	}
	
	@POST
	@Path("/forgotVehicleCredentials")
	public Response forgotVehicleCredentials(String request) {
		BasicDBObject b = BasicDBObject.parse(request);
		String email = b.getString("email");
		return null;
	}
	
	@POST
	@Path("/requestPassengerReg")
	public Response requestPassengerReg(String request) {
		BasicDBObject b = BasicDBObject.parse(request);
		Document d = new Document(b); 
		PassengerReg p = PassengerReg.convertDocumentToPassengerReg(d);
		return null;
	}
	
	@POST
	@Path("/authPassenger")
	public Response authPassenger(String request) {
		BasicDBObject b = BasicDBObject.parse(request);
		Document d = new Document(b);
		Authentication a = Authentication.convertDocumentToAuthentication(d);
		return null;
	}
	
	@POST
	@Path("/forgotPassengerCredentials")
	public Response forgotPassengerCredentials(String request) {
		BasicDBObject b = BasicDBObject.parse(request);
		String email = b.getString("email");
		//Send email per il recupero della pssword
		return null;
	}
	
	@GET
	@Path("/getPassengerId")
	public Response getPassenger(@QueryParam("username")String username) {
		PassengerReg p = branch.getPassRegByUsername(username); 
		//interrogazione passenger service per ottenere il passengerId
		return Response.ok().entity("OK").build();
	}
}

package service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import model.*;

public interface BranchLocal {
	
	public List<VehicleReg> getAllVehicleReg();
	public List<PassengerReg> getAllPassengerReg();
	public boolean createVehicleReg(VehicleReg v) throws NoSuchAlgorithmException;
	public boolean createPassengerReg(PassengerReg v) throws NoSuchAlgorithmException;
	public PassengerReg getPassRegByUsername(String username);
	public PassengerReg getPassRegByEmail(String email);
	public VehicleReg getVehiRegByUsername(String username);
	public VehicleReg getVehiRegByEmail(String email);
	public boolean checkToken(String email, String token, String type);
	public PassengerReg getPassengerByCredential(Authentication a);
	public VehicleReg getVehicleByCredential(Authentication a);
}

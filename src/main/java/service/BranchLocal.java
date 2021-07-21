package service;

import java.util.List;

import model.*;

public interface BranchLocal {
	
	public List<VehicleReg> getAllVehicleReg();
	public List<PassengerReg> getAllPassengerReg();
	public boolean createVehicleReg(VehicleReg v);
	public boolean createPassengerReg(PassengerReg v);
	public PassengerReg getPassRegByUsername(String username);
	public PassengerReg getPassRegByEmail(String email);
	public VehicleReg getVehiRegByUsername(String username);
	public VehicleReg getVehiRegByEmail(String email);

}

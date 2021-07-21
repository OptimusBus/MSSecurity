package controller;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api-v1")
public class SecurityApplication {
	public Set<Class<?>> getClasses(){
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(controller.SecurityController.class);
		return s;
	}
	
	public Set<Object> getSingletons(){
		Set<Object> s = new HashSet<Object>();
		s.add(new controller.SecurityController());
		return s;
	}
}

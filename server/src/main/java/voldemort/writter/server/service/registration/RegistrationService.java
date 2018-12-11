package voldemort.writter.server.service.registration;

import voldemort.writter.server.persistence.entity.User;

public interface RegistrationService {
	
	String register(User userDetails);

}

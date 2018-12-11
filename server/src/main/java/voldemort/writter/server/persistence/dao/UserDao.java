package voldemort.writter.server.persistence.dao;

import voldemort.writter.server.persistence.entity.User;

public interface UserDao {
	
	User createUser(User user);
	
	User findByUsername(String username);
	
	User findByEmail(String email);

}

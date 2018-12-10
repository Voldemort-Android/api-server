package voldemort.writter.server.service.login;

import java.util.Map;

public interface LoginService {

	String login(Map<String, String> credentials);
	
}

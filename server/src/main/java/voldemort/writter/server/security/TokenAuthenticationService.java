package voldemort.writter.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import voldemort.writter.server.dto.security.UserAuthentication;
import voldemort.writter.server.exception.RestException;

@Service
public class TokenAuthenticationService {
	
	private final String jwtPrefix = "Bearer ";

	private final String jwtSecret = "secret";
	
	@Autowired
	private ObjectMapper mapper;
	
	public boolean validateToken(String token) {
		try {
			UserAuthentication authentication = parseToken(token);
			if (authentication.getId() != null && !StringUtils.isEmpty(authentication.getUsername())) {
				return true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			// Do nothing...let it throw the exception at the end.
		}
		throw new RestException(HttpStatus.UNAUTHORIZED, "Authorization token is missing, expired, or invalid");
	}

	public String generateToken(String username, String password) {
		if(username != null && password != null) {
			UserAuthentication payload = new UserAuthentication();
			payload.setId(0L);
			payload.setUsername(username);
			String jwt = Jwts.builder()
					.claim("user", payload)
					.signWith(SignatureAlgorithm.HS256, jwtSecret)
					.compact();
			return jwtPrefix + jwt;
		}
		throw new RestException(HttpStatus.UNAUTHORIZED, "Invalid username or password.");
	}
	
	private UserAuthentication parseToken(String token) throws Exception {
		if (StringUtils.isEmpty(token)) {
			throw new Exception("Null or empty token");
		}
		if (token.startsWith(jwtPrefix)) {
			token = token.substring(jwtPrefix.length());
		}
		Claims claims = Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(token)
				.getBody();
		
		// Serialize the map into a string.
		Object user = claims.get("user");
		String serializedUser = mapper.writeValueAsString(user);
		
		// Deserialize the string into a user authentication object.
		return mapper.readValue(serializedUser, UserAuthentication.class);
	}
	
}

package voldemort.writter.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import voldemort.writter.server.dto.security.AuthenticationDetails;
import voldemort.writter.server.exception.RestException;
import voldemort.writter.server.persistence.entity.User;

@Service
public class TokenAuthenticationService {
	
	private final String jwtPrefix = "Bearer ";

	private final String jwtSecret = "secret";
	
	@Autowired
	private ObjectMapper mapper;
	
	public AuthenticationDetails validateToken(String token) {
		try {
			AuthenticationDetails authentication = parseToken(token);
			if (authentication.getId() != null && !StringUtils.isEmpty(authentication.getUsername())) {
				return authentication;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			// Do nothing...let it throw the exception at the end.
		}
		throw new RestException(HttpStatus.UNAUTHORIZED, "Authorization token is missing, expired, or invalid");
	}

	public String generateToken(User user) {
		AuthenticationDetails authentication = new AuthenticationDetails();
		authentication.setId(user.getId());
		authentication.setUsername(user.getUsername());
		authentication.setEmail(user.getEmail());
		String jwt = Jwts.builder()
				.claim("user", authentication)
				.signWith(SignatureAlgorithm.HS256, jwtSecret)
				.compact();
		return jwtPrefix + jwt;
	}
	
	private AuthenticationDetails parseToken(String token) throws Exception {
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
		return mapper.readValue(serializedUser, AuthenticationDetails.class);
	}
	
}

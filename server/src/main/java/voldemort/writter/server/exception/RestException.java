package voldemort.writter.server.exception;

import org.springframework.http.HttpStatus;

public class RestException extends RuntimeException {
	
	private static final long serialVersionUID = -1830426682342910389L;
	
	private HttpStatus status;
	
	public RestException() {
		this("Internal Server Error");
	}
	
	public RestException(HttpStatus status) {
		this(status,status.getReasonPhrase());
	}
	
	public RestException(String message) {
		this(HttpStatus.INTERNAL_SERVER_ERROR, message);
	}
	
	public RestException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

}

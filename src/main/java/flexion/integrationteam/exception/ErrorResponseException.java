package flexion.integrationteam.exception;

public class ErrorResponseException extends Exception {

	private static final long serialVersionUID = 1L;
	private int errorCode;
	
	public ErrorResponseException() {}
	
	public ErrorResponseException(int errorCode) {
		this.errorCode = errorCode;
	}
	
	@Override
	public String toString() {
		return "The server returned an error code : " + errorCode;
	}

}

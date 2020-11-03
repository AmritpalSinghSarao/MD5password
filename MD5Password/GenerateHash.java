import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GenerateHash {

	private String messageOne;
	private String messageTwo;
    private String message;
    
    
	public GenerateHash(String messageOne, String messageTwo) throws NoSuchAlgorithmException {
		
		this.messageOne=messageOne;
		this.messageTwo=messageTwo;
		message=messageOne+messageTwo;
		
	}
	
	
	public String hashValue() throws NoSuchAlgorithmException {
		
		message =messageOne+messageTwo;
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(message.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);
		return hashtext;
		
		
	}
	
}
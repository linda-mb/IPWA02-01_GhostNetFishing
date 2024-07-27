import java.io.Serializable;
import java.security.SecureRandom;

public class BuchungsnummerGenerator implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// copied from https://stackoverflow.com/a/157202
	static final String AB = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();

	public String generateBuchungsnummer(int len){
	   StringBuilder sb = new StringBuilder(len);
	   for(int i = 0; i < len; i++)
	      sb.append(AB.charAt(rnd.nextInt(AB.length())));
	   return sb.toString();
	}
	
}

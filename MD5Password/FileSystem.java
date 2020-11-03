import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class FileSystem {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		// TODO Auto-generated method stub

		GenerateHash gh=new GenerateHash("This is a test", "");
		System.out.print("MD5(''This is a test'')"+"="+gh.hashValue()+"\n\n\n");
      
		if(args[0].equals("-i")){
			
			LogginIn li=new LogginIn();
		
		}else if(args[0].equals("-d")){
			CreationSystem cs=new CreationSystem();
			cs.askUsername();
		}
	
		

			
	}

}
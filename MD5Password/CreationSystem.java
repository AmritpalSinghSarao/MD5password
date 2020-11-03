import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

public class CreationSystem {

	private String username;
	private String password;
	Scanner in= new Scanner(System.in);
	private static final String digits = "0123456789";
    private static final SecureRandom random = new SecureRandom();
private String path = System.getProperty("user.dir");

	private String salt;
	
	// ask username to the user
	public void askUsername() throws IOException, NoSuchAlgorithmException {
	
		System.out.print("Create new user\n\n");
		System.out.print("Username :");
		username= in.nextLine();
		salt=generateDigits(8);
	
		
		if(checkUsername(username)==false) {
		
			if(askPassword()) {
			GenerateHash gh=new GenerateHash(password,salt);
			addUsernameSalt(username,salt);
			addShadow(username,gh.hashValue());
		}
	}
	}
	// generate the salt
	   public static String generateDigits(int count) {
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < count; ++i) {
	            sb.append(digits.charAt(random.nextInt(digits.length())));
	        }
	        return sb.toString();
	    }

	
	// check whether the username exist or not
	public boolean  checkUsername(String username) {
		try {

		      File file = new File(path+"/salt.txt");
		      Scanner in = new Scanner(file);
		      while (in.hasNext()) {
		        String data = in.nextLine();
		        String[] words=data.split("\\:");
		        
		        if(username.equals(words[0])) {
		        	System.out.println("Username alredy exists");
		        	return true;}
		        
		      }
		      
		      in.close();
		      return false;
		      
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		in.close();
		return false;
	}
	
	
	
	
	// write on salt file username and salt
	public void addUsernameSalt(String username, String salt ) throws IOException {
		String filepath=path+"/salt.txt";
		System.out.print("scrivo sul file\n");
		BufferedWriter outputSalt= new BufferedWriter( new FileWriter(filepath,true));
		outputSalt.write(username+":"+salt);
        outputSalt.newLine();
		outputSalt.flush();
		outputSalt.close();
		
		
	}
	
	
	
	
	// ask to the username the password, show the requirement and if satisfy the requirement then confirm password 
	public boolean askPassword() {
		Scanner in= new Scanner(System.in);
		String requirement = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
		String passwordConfirm;
		do {
		System.out.print("Password :");
		password=in.nextLine();
		if(password.matches(requirement)!=true)
		{
			System.out.print("Password requirement not satisfied. Retype the password \n");
		System.out.print("Atleast 8 characters, "+" \n[A-Z] Uppercase letter"
				+ "\n[a-z] Lowercase letter"
				+ "\n[0-9] Number"
				+ "\n[@#$%^&+=] Atleast one sysmbol"+ "\n");}
		} while(password.matches(requirement)!=true);
		
			
		do {
			System.out.print("Confirm password: ");
			passwordConfirm=in.nextLine();
		}while(passwordConfirm.equals(password)!=true);
		
	      
		
		return true;
		
		
	}
	
	
	// add to the shadow file the username, hash and clearance
	public void addShadow(String username, String hash ) throws IOException {
		
		String filepath=path+"/shadow.txt";
		System.out.println("User clearance (0 or 1 or 2 or 3): ");
		int clearanceUser;
		Scanner in= new Scanner(System.in);
		
		clearanceUser=in.nextInt();
	   while(clearanceUser!=0 ||clearanceUser!=1||clearanceUser!=2||clearanceUser!=3) {
		   if(clearanceUser==0 ||clearanceUser==1||clearanceUser==2||clearanceUser==3) {
			   break;
		   }
		   System.out.println("User clearance should be (0 or 1 or 2 or 3) ");
		   clearanceUser=in.nextInt();
	   };
		BufferedWriter outputShadow= new BufferedWriter( new FileWriter(filepath,true));
		outputShadow.write(username+":"+hash+":"+clearanceUser);
        outputShadow.newLine();
		outputShadow.flush();
		outputShadow.close();
		
		System.out.println("New user created ");
		
	}
	

	
}

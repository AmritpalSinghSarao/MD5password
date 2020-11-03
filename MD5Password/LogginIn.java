import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;




public class LogginIn {
	private String username;
	private String password;
	Scanner in= new Scanner(System.in);
	private String salt;
	private String passSaltHash;
private String path = System.getProperty("user.dir");
    
    private boolean optionLoop=true;
	private boolean exitLoop=false;
	private String[] saltWords;
	
	public LogginIn() throws NoSuchAlgorithmException, FileNotFoundException {
		System.out.print("Login into the file system:\n\n ");
	System.out.print("Username: ");
	username=in.nextLine();
	//System.out.print("Password: ");
	//password=in.nextLine();
	if(checkUsername(username)) {
		
		System.out.print("Password: ");
		password=in.nextLine();
		
		GenerateHash gh=new GenerateHash(password,salt);
		passSaltHash=gh.hashValue();
		
		if(retrieveHash(username).equals(passSaltHash)) {
			System.out.println(saltWords[0]+" found in salt.txt\n");
        	System.out.println("Salt retrieved: "+saltWords[1]+"\n");
        	System.out.println("hashing...\n");
        	System.out.print("hash value :"+passSaltHash+" \n");
			System.out.print("Authentication for user "+username+" complete\n ");
		System.out.print("The clearance for "+username+" is "+ getClearance(username)+"\n\n");
		
		// actions allowed
		actions(username,getClearance(username));
		
	}else {
		System.out.println("Information doesn't match\n");
	}
	} else {
		System.out.println("Username doesn't exist");
	}
	}
	public  boolean checkUsername(String username){
		try {
	
	      File file = new File(path+"/salt.txt");
	      Scanner in = new Scanner(file);
	      while (in.hasNext()) {
	       String data = in.nextLine();
	        //String[] words=data.split("\\:");
	       saltWords=data.split("\\:");
	        salt=saltWords[1];
	        if(username.equals(saltWords[0])) {
	        	//System.out.println(saltWords[0]+" found in salt.txt\n");
	        	//System.out.println("Salt retrieved: "+saltWords[1]+"\n");
	        	//System.out.println("hashing...\n");
	        	in.close();
	        	return true;
	        	}
	        
	      }
	      
	      in.close();
	      return false;
	      
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	return false;

}
	
	public String retrieveHash(String username) throws FileNotFoundException {
		
		
		 File file = new File(path+"/shadow.txt");
	      Scanner in = new Scanner(file);
	      while (in.hasNext()) {
	        String data = in.nextLine();
	        String[] words=data.split("\\:");
	        if(words[0].equals(username)) {
	        	in.close();
	        return salt=words[1];
	        
	}
	}
	      in.close();
		return username;
	
}
	
  public String getClearance(String username) throws FileNotFoundException {
	  
	  File file = new File(path+"/shadow.txt");
      Scanner in = new Scanner(file);
      while (in.hasNext()) {
        String data = in.nextLine();
        String[] words=data.split("\\:");
        if(words[0].equals(username)) {
        	in.close();
        return salt=words[2];
  } 
}
      in.close();
	return username;
  }
  
public void actions(String useranme,String clearance) throws FileNotFoundException {
	Scanner in= new Scanner(System.in);
	
	while(optionLoop){
	System.out.print("\nOptions: (C)reate, (A)ppend, (R)ead, (W)rite, (L)ist, (S)ave, or (E)xit.\n\n");
	exitLoop=true;
	
	String Option=in.nextLine();
	
	if(Option.equals("C") || Option.equals("c")) {
		System.out.print("\nFilename: ");
		String fileName=in.nextLine();
		create(fileName,username,clearance);
		
	}else if(Option.equals("A") || Option.equals("a")) {
		System.out.print("Append a file");
		System.out.print("\nFilename: ");
		String fileName=in.nextLine();
		append(fileName,username,clearance);
		
	}else if(Option.equals("R") || Option.equals("r")) {
		System.out.print("Read the file");
		System.out.print("\nFilename: ");	
		String fileName=in.nextLine();
		read(fileName,username,clearance);
		
	}else if(Option.equals("W") || Option.equals("w")) {
		System.out.print("Write the file");
		System.out.print("\nFilename: ");
		String fileName=in.nextLine();
		write(fileName,username,clearance);
		
	}else if(Option.equals("L") || Option.equals("l")) {
		System.out.print("List of all the files");
		list();
		
	}else if(Option.equals("S") || Option.equals("s")) {
		save();
		
	} else if(Option.equals("E") ||Option.equals("e")) {
		System.out.println("Shut down the Filesystem? (Y)es or (N)o");
		exitSystem();
	}
	}
	in.close();
}



public void create(String fileName,String username, String clearance) {
	
 boolean createLoop=true;

 try {
     File newFile = new File(path+"/"+fileName+".txt");
     if (newFile.createNewFile()) {
       System.out.println("File created: " + newFile.getName());
       String filepath=path+"File.store";
		
		BufferedWriter fileDetail= new BufferedWriter( new FileWriter(filepath,true));
		fileDetail.write(fileName+":"+username+":"+clearance);
        fileDetail.newLine();
		fileDetail.flush();
		fileDetail.close();
		
     } else {
       System.out.println("File already exists.");
     }
   } catch (IOException e) {
     System.out.println("An error occurred.");
     e.printStackTrace();
   }
 
          
    
}




public void append(String fileName,String username, String clearance) throws FileNotFoundException {
	  File file = new File(path+"/File.store");
      Scanner in = new Scanner(file);
      while (in.hasNext()) {
        String data = in.nextLine();
        String[] words=data.split("\\:");
        if(words[0].equals(fileName)) {
        	if(Integer.parseInt(clearance)>=Integer.parseInt(words[2])) {
        		System.out.print("File appended successfully");
        	}else {
        		System.out.print("Failed to apppend");
        	}
        }
        }
        
}
public void read(String fileName,String username, String clearance ) throws FileNotFoundException {
	File file = new File(path+"/File.store");
    Scanner in = new Scanner(file);
    while (in.hasNext()) {
      String data = in.nextLine();
      String[] words=data.split("\\:");
      if(words[0].equals(fileName)) {
      	if(Integer.parseInt(clearance)<=Integer.parseInt(words[2])) {
      		System.out.print("File read successfully");
      	}else {
      		System.out.print("Failed to read");
      	}
      }
      }
}
public void write(String fileName,String username, String clearance) throws FileNotFoundException {
	File file = new File(path+"/File.store");
    Scanner in = new Scanner(file);
    while (in.hasNext()) {
      String data = in.nextLine();
      String[] words=data.split("\\:");
      if(words[0].equals(fileName)) {
      	if(Integer.parseInt(clearance)>=Integer.parseInt(words[2])) {
      		System.out.print("File written successfully");
      	}else {
      		System.out.print("Failed to write");
      	}
      }
      }
}
public void list() {
	 
    File directoryPath = new File(path);
    //List of all files and directories
    String contents[] = directoryPath.list();
    System.out.println("List of files and directories in the specified directory:");
    for(int i=0; i<contents.length; i++) {
       System.out.println(contents[i]);
    }
}
public void save() {
	System.out.println("Saved succesfully");
}



public void exitSystem() {
	Scanner in= new Scanner(System.in);
	while(exitLoop) {
		String exit=in.nextLine();
	if(exit.equals("Y") ||exit.equals("y")) {
		optionLoop=false;
		exitLoop=false;	
	}else if(exit.equals("N")||exit.equals("n")) {		
		exitLoop=false;
	} else {
		System.out.print("Command not accepted\n");
	}
	}
	
}

}



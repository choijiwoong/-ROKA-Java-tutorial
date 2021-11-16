import java.util.Scanner;

import java.io.BufferedReader;//FILE IO, Exception
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Genie{
	public static void main(String[] args){
		
		//---practice_section
		try{
			//entry.entry_execution();
		}catch(Exception e){ e.printStackTrace(); }
		
		while(true){
			System.out.println("Choose work!\n1. Search Song\n2. Play Song in playlist");
			try{
				Scanner sc=new Scanner(System.in);
				int menu=sc.nextInt();
				//sc.close();
				if(menu==1){
					try{
						searchSong.searchSong_execution();
					}catch(Exception e){ e.printStackTrace(); }
				} else if(menu==2){
					playSong.playSong_execution();
				} else{
					System.out.println("Wrong work! Try again");
				}
			}catch(Exception e){ e.printStackTrace(); }
			
			System.out.println("PROGERAM DONE!!!");
			return;
		}
	}
}

class searchSong{//use Jsoup
	public static void searchSong_execution() throws Exception{
		//get title
		//search & download
		//add
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter title of song: ");
		String searchWord="https://www.youtube.com/results?search_query=";
		searchWord+=sc.next();
		
		Document doc=Jsoup.connect(searchWord).get();
		Elements contents=doc.select(".style-scope ytd-video-renderer");//Exception NoClassDefFoundError
		String text=contents.text();
		System.out.println(text);
		
	}
}

class playSong{
	public static void playSong_execution() throws IOException{
		
	}
}

class playlist{//save as .out

	class song{
		
	}
}

class entry{
	public static void entry_execution() throws IOException{//throws because of Scanner
		while(true){
			Scanner sc=new Scanner(System.in);
			
			System.out.println("[Welcome to Genie!]");
			System.out.println("Please login or register first.\n1. login\n2. register");
			int menu=sc.nextInt();
			
			if(menu==1){
				try{
					entry.login();
					break;//escape
				}catch(IOException e){ e.printStackTrace(); }
			}else if(menu==2){
				try{
					entry.register();
					continue;
				}catch(IOException e){}//we have to handle exception
			}else{
				System.out.println("Try Again!");
				continue;
			}
		}
		return;
	}
	
	public static boolean register() throws IOException{//throws because of Scanner
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Type your new Id: ");
		String id=sc.next();
		System.out.println("Type your Password: ");
		String userInformation_buffer=new String(id+" "+sc.next());//"id password"
		
		if(isExistDB(userInformation_buffer)){//check same id
			System.out.println("[Register Failed!] Invalid Information!");
			return false;
		} else{
			try{//file is already exist
				FileWriter fw=new FileWriter("userInformation.txt", true);//append mode
				userInformation_buffer+="\n";
				fw.write(userInformation_buffer);
				fw.close();
				System.out.println("[Register Success!]");
				return true;
			}catch(FileNotFoundException e){//file is not exist(cannot use append mode)
				//make file.
				FileWriter fw2=new FileWriter("userInformation.txt");//write mode
				userInformation_buffer+="\n";
				fw2.write(userInformation_buffer);
				fw2.close();
				System.out.println("[Register Success!]");
				return true;
			}catch(IOException e){ 
				e.printStackTrace(); 
				return false;
			}
		}
	}

	public static boolean login() throws IOException{//throws because of Scanner
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Type yout Id: ");
		String id=sc.next();
		System.out.println("Type your Password: ");
		String userInformation_buffer=new String(id+" "+sc.next());//"id password"
		
		try{
			BufferedReader br=new BufferedReader(new FileReader("userInformation.txt"));
			while(true){
				String line=br.readLine();
				//System.out.println(line+";"+userInformation_buffer);
				if(line==null){
					br.close();
					System.out.println("[Login Failed!] Information is not exist");
					return false;
				} else if(line.equals(userInformation_buffer)){
					br.close();
					System.out.println("[Login Success!] Welcome!");
					//String userInfo=line.split(" ");//userInfo[0]: id, userInfo[1]: password
					return true;
				}
			}
		}catch(FileNotFoundException e){//no DB file
			System.out.println("[Login Failed!] not exist DB. Try register first.");
			return false;
		}catch(IOException e){ 
			e.printStackTrace(); 
			return false;
		}
	}
	
	public static boolean isExistDB(String userInformation_buffer){
		try{
			BufferedReader br=new BufferedReader(new FileReader("userInformation.txt"));
			while(true){
				String line=br.readLine();
				if(line==null){
					br.close();
					return false;
				}
				if(line.equals(userInformation_buffer)){
					br.close();
					return true;
				}
			}
		}catch(FileNotFoundException e){//No userinformation.txt -> first login before first register. 
			System.out.println("Register first!");
			e.printStackTrace();
		}catch(IOException e){ e.printStackTrace(); }
		
		System.out.println("[Error Occur!] in isExistDB");
		return false;
	}
}
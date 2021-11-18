import java.util.Scanner;

import java.io.BufferedReader;//FILE IO, Exception
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;//selenium
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.google.common.io.Files;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;//mkdir, copy song to playlist
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.BufferedWriter;



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
				if(menu==1)
					searchSong.searchSong_execution();
				else if(menu==2)
					playSong.playSong_execution();
				else
					System.out.println("Wrong work! Try again");
				
			}catch(Exception e){ e.printStackTrace(); }
			
			System.out.println("PROGERAM DONE!!!");
			return;
		}
	}
}

class searchSong{//use Jsoup
	private static WebDriver driver;
	private static int playlistSize=0;
	private static String youtubeLink;
	private static String downloadHtml=new String("https://loader.to/ko26/youtube-wav-converter.html");;
	
	public static void searchSong_execution(){
		searchSong();//Set youtubeLink variable with return int
		downloadSong();
		//mp3 file is in C:\Users\admin0!\Downloads now.
		movePlaylist();
	}
	
	public static int searchSong(){
		//get title
			//search & download
			//add
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter title of song: ");
			String youtubeSearch="https://www.youtube.com/results?search_query=";
			String searchWord=sc.next();
			
			//***First*** FeedBack_Youtube uses CSR(Client-Side Rendering) not SSR(Server-Side Rendering). so use selenium that support Xpath regardless of excuting speed.
			//System.out.println("searchWord: "+searchWord);
			//Document doc=Jsoup.connect(searchWord).get();//Document connection is Success! not selected!
			//Elements contents=doc.select("#video-title");
			//System.out.println("contents: "+contents.first());
				
			//***Second*** FeedBack_make Driver object.
			//java.util.ServiceConfigurationError: org.openqa.selenium.remote.http.HttpClient$Factory: Error accessing configuration file
			//Caused by: java.nio.file.NoSuchFileException: C:\Users\admin0
			//just do continue, this error will be handled in weekend.
				
			System.setProperty("webdriver.chrome.driver", "C:/Program Files/ChromeWebdriver/chromedriver.exe");
			ChromeOptions options=new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--disable-popup-blocking");
				
			driver=new ChromeDriver(options);
			try {
				driver.get(youtubeSearch+searchWord);//List page of search word on youtube.
				WebElement result=driver.findElement(By.xpath("//*[@id=\"video-title\"]"));//get element of first vedio-title's tag with like href="/watch?v=Nd-kL7Txqpk"
				if(result!=null)
					System.out.println(result);
			} catch(Exception e){
				e.printStackTrace();
				return 0;
			}finally { driver.quit(); }
				
			//Proecess of result to get href to first video-title link. like href="/watch?v=Nd-kL7Txqpk"
				
			String videoLink=new String();//like "ND-kL7Txqpk"
			youtubeLink=new String("https://www.youtube.com/"+videoLink);//set private variable
	
			return 1;
	}
	
	public static int downloadSong(){
		if(youtubeLink==null) {//check is ready for download
			try {
				throw new Exception("YoutubeLink is not sent!");
			}catch(Exception e) {e.printStackTrace();}
			return 0;
		}
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--disable-popup-blocking");
		
		driver=new ChromeDriver(options);
		try {
			driver.get(downloadHtml);
			WebElement inputURL=driver.findElement(By.xpath("//*[@id=\"link\"]"));
			inputURL.sendKeys(youtubeLink);
			WebElement convertStartButton=driver.findElement(By.xpath("//*[@id=\"load\"]"));
			convertStartButton.click();
			
			Thread.sleep(10000);//convert time
			WebElement downloadStartButton=driver.findElement(By.xpath("//*[@id=\"7046dfee98b0d1a28798a0c94c9_downloadButton\"]"));
			downloadStartButton.click();
			Thread.sleep(30000);//download time
			//****third feedback****
			//check download by download's file list[0] element's name compare not sleep;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally { driver.quit(); }
		
		return 1;	
	}
	
	public static int movePlaylist() {
		//get .mp3 file to File object
		File dir=new File("C:\\Users\\admin0!\\Downloads");//change by UserName
		FilenameFilter filter=new FilenameFilter() {
			public boolean accept(File f, String name) {
				return name.endsWith(".mp3");
			}
		};//for some of accuracy
		File files[]=dir.listFiles(filter);
		//our downloaded song object is in files[0] now.
		
		
		//copy to playlist directory of Genie
		if(files[0]!=null) {
			//Check & Make playlistFolder
			File playlistFolder=new File("C:/Users/admin0!/Desktop/_2jimo/Java/Genie/Geniw/src/Playlist");
			if(!playlistFolder.exists()) {//check directory is exist
				try {
					playlistFolder.mkdir();
				}catch(Exception e) {
					e.printStackTrace();
					return 0;
				}
			}
			
			//copy by stream
			File newFile=new File(new String("C:/Users/admin0!/Desktop/_2jimo/Java/Genie/Geniw/src/Playlist/Song"+(playlistSize+1)));
			try {
				FileInputStream input=new FileInputStream(files[0]);
				FileOutputStream output=new FileOutputStream(newFile);
				
				byte[] buf=new byte[1024];
				
				int readData;
				while((readData=input.read(buf))>0)
					output.write(buf,0,readData);
				
				input.close();
				output.close();
			}catch(Exception e) {
				e.printStackTrace();
				return 0;
			}
		}else {
			try {
				throw new Exception("No mp3 file in download file");
			}catch(Exception e) {e.printStackTrace();}
		}
		return 1;
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
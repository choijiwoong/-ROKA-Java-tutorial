import java.util.Scanner;

import java.io.BufferedReader;//FILE IO, Exception
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;//selenium
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import java.io.File;//mkdir, copy song to playlist
import java.io.FileInputStream;
import java.io.FilenameFilter;

import javax.sound.sampled.AudioInputStream;//.wav
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Genie{
	public static void main(String[] args){
		entry.entry_execution();
		
		while(true){
			System.out.println("Choose work!\n1. Search Song\n2. Play Song in playlist");
			try{
				Scanner sc=new Scanner(System.in);
				int menu=sc.nextInt();
				
				if(menu==1)
					searchSong.searchSong_execution();
				else if(menu==2)
					playSong.playSong_execution();
				else
					System.out.println("Wrong work! Try again");
			}catch(Exception e) {e.printStackTrace();}
			
			System.out.println("\n");
		}
	}
}

class playSong {
	public static void playSong_execution(){//Not bad to making by multi-thread
		if(!playSongWork())
			System.out.println("playSong .....Bad");
		else
			System.out.println("playSong .....Good");
	}
	
	public static boolean playSongWork() {
		int playlistSize=searchSong.getPlaylistSize();
		if(playlistSize==0) {
			System.out.println("No music in playlist!");
			return false;
		}
		
		for(int i=1; i<=playlistSize; i++) {//play all
			try {
				System.out.println("music."+i+" is starting...");
				
				File wavFile=new File("/playlist/Song"+i+".wav");
				AudioInputStream wavStream=AudioSystem.getAudioInputStream(wavFile);
				Clip wavClip=AudioSystem.getClip();
				
				wavClip.open(wavStream);
				wavClip.start();
				
				Thread.sleep(wavClip.getMicrosecondLength()/1000);
			}catch(Exception e) { 
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
}

class searchSong{//use Jsoup
	private static WebDriver driver;//for ChromeDriver object
	private static int playlistSize=0;//number of songs is playlist
	 public static int getPlaylistSize() { return playlistSize; }//return playlistsize for using in other class method.
	private static String videoUrl;//youtube like to searched video for using other method in this class
	private final static String downloadHtml=new String("https://loader.to/ko26/youtube-wav-converter.html");//we will use this site for downloading .wav file from youtube video
	private final static String youtubeSearch_query="https://www.youtube.com/results?search_query=";//use this url for search youtube video by word
	private final static String youtubeUrl="https://www.youtube.com/";
	
	private final static File dirDownload=new File("C:/Users/admin0!/Downloads");//change by UserName
	private final static FilenameFilter filter=new FilenameFilter() {
		public boolean accept(File f, String name) { 
			return name.endsWith(".wav"); 
		}
	};
	
	public static void searchSong_execution(){
		if(!search())
			System.out.println("searchSong .....Bad");
		else
			System.out.println("searchSong .....Good");
		//set private youtubeLink
		
		if(!downloadSong())
			System.out.println("downloadSong .....Bad");
		else
			System.out.println("downloadSong .....Good");
		//.wav file is in C:\Users\admin0!\Downloads now.
		
		if(!movePlaylist())
			System.out.println("movPlaylist .....Bad");
		else
			System.out.println("movPlaylist .....Good");
		//.wav file is in Genie/playlist/
	}
	
	public static boolean search(){
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter title of song: ");
			String searchWord=sc.next();//searchWord for search on youtube
			
			//***First*** FeedBack_Youtube uses CSR(Client-Side Rendering) not SSR(Server-Side Rendering). so use selenium that support Xpath regardless of excuting speed.
			//System.out.println("searchWord: "+searchWord);
			//Document doc=Jsoup.connect(searchWord).get();//Document connection is Success! not selected!
			//Elements contents=doc.select("#video-title");
			//System.out.println("contents: "+contents.first());
				
			//***Second*** FeedBack_make Driver object.
			//java.util.ServiceConfigurationError: org.openqa.selenium.remote.http.HttpClient$Factory: Error accessing configuration file
			//Caused by: java.nio.file.NoSuchFileException: C:\Users\admin0
			//just do continue, this error will be handled in weekend.
			
			//***fourth*** FeedBack_use firefoxdriver not chrome
			//same error occur
			
			String path=System.getProperty("user.dir");//get this_path(preparing execution of chromedriver)
			System.setProperty("webdriver.chrome.driver", path+"/src/ChromeWebdriver/chromedriver.exe");//set chromedriver's location
			ChromeOptions options=new ChromeOptions();//make options object for argument of ChromeDriver's constructor
			options.addArguments("--start-maximized");//monitor max
			options.addArguments("--disable-popup-blocking");//no popup
			
			driver=new ChromeDriver(options);//Make Webdriver object! **ERROR OCCUR**
			try {
				driver.get(youtubeSearch_query+searchWord);//List page of search word on youtube.
				WebElement result=driver.findElement(By.xpath("//*[@id=\"video-title\"]"));//get element of first vedio-title's tag with like href="/watch?v=Nd-kL7Txqpk".
				//result has information of href that points first element link of search list
				if(result!=null)
					System.out.println(result);
			} catch(Exception e){
				e.printStackTrace();
				return false;
			}finally { driver.quit(); }
			//Proecess of result to get href to first video-title link. like href="/watch?v=Nd-kL7Txqpk"
			String videoLink=new String();//Suppose like "ND-kL7Txqpk"
			videoUrl=new String(youtubeUrl+videoLink);//set private variable
	
			return true;
	}
	
	public static boolean downloadSong(){
		if(videoUrl==null) {//check is ready for download
			try {
				throw new Exception("YoutubeLink is not sent!");
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--disable-popup-blocking");
		
		driver=new ChromeDriver(options);
		try {
			driver.get(downloadHtml);
			//below process is only working on downloadHtml's content(download site's Xpath is used)
			WebElement inputURL=driver.findElement(By.xpath("//*[@id=\"link\"]"));
			inputURL.sendKeys(videoUrl);//input vedioUrl that we want to convert to .wav
			WebElement convertStartButton=driver.findElement(By.xpath("//*[@id=\"load\"]"));
			convertStartButton.click();//convert start
			
			Thread.sleep(10000);//convert time
			File files[]=dirDownload.listFiles();//Save first element of Download directory. for notifying download's end by compare buffer's File & now File
			File buffer=files[0];
			WebElement downloadStartButton=driver.findElement(By.xpath("//*[@id=\"7046dfee98b0d1a28798a0c94c9_downloadButton\"]"));//***is it final with other video?****
			downloadStartButton.click();//downloading start!
			System.out.print("downloading");
			while(true) {
				System.out.print(".");
				files=dirDownload.listFiles(filter);
				if(files[0].equals(buffer))
					break;
				Thread.sleep(3000);//check by 3seconds
			}
			System.out.println("Download Complete!");
			//Thread.sleep(30000);//download time
			//****third feedback****
			//check download by download's file list[0] element's name compare not sleep;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally { driver.quit(); }
		
		return true;
	}
	
	public static boolean movePlaylist() {
		File files[]=dirDownload.listFiles(filter);//for get first element of Download directory(new added .wav file)
		
		//copy to playlist directory of Genie
		if(files[0]!=null) {
			//Check & Make playlistFolder
			File playlistFolder=new File("C:/Users/admin0!/Desktop/_2jimo/Java/Genie/Geniw/src/Playlist");
			if(!playlistFolder.exists()) {//check directory is exist
				try {
					playlistFolder.mkdir();
				}catch(Exception e) {
					e.printStackTrace();
					return false;
				}
			}//playlist dir must exist now 
			
			//copy by stream
			File newFile=new File(new String("C:/Users/admin0!/Desktop/_2jimo/Java/Genie/Geniw/src/Playlist/Song"+(++playlistSize)+".wav"));
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
				return false;
			}
		}else {
			try {
				throw new Exception("No mp3 file in download file_Download is not completed!");
			}catch(Exception e) {
				e.printStackTrace(); 
				return false;
			}
		}
		return true;
	}
}

class entry{
	public static void entry_execution(){//throws because of Scanner
		while(true){
			Scanner sc=new Scanner(System.in);
			
			System.out.println("[Welcome to Genie!]");
			System.out.println("Please login or register first.\n1. login\n2. register");
			int menu=sc.nextInt();
			
			if(menu==1){
				if(entry.login()) {
					return;//escape
				}
			}else if(menu==2){
				entry.register();
				continue;
			}else{
				System.out.println("Try Again!");
				continue;
			}
		}
	}
	
	public static boolean register(){//throws because of Scanner
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Type your new Id: ");
		String id=sc.next();
		System.out.println("Type your Password: ");
		String userInformation_buffer=new String(id+" "+sc.next());//"id password"
		
		if(isExistDB(userInformation_buffer)){//check same id
			System.out.println("Already exist!");
			return false;
		} else{
			try{//file is already exist
				FileWriter fw=new FileWriter("userInformation.txt", true);//append mode
				userInformation_buffer+="\n";
				fw.write(userInformation_buffer);
				fw.close();
				System.out.println("[Register Success!]\n");
				return true;
			}catch(FileNotFoundException e){//file is not exist(cannot use append mode)
				//make file.
				FileWriter fw2;
				try {
					fw2 = new FileWriter("userInformation.txt");//write mode
					userInformation_buffer+="\n";
					fw2.write(userInformation_buffer);
					fw2.close();
					System.out.println("[Register Success!]\n");
					return true;
				} catch (IOException e1) {//about try in catch
					e1.printStackTrace();
					return false;
				}//write mode
			}catch(IOException e){ //about first try
				e.printStackTrace(); 
				return false;
			}
		}
	}

	public static boolean login(){//throws because of Scanner
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Type yout Id: ");
		String id=sc.next();
		System.out.println("Type your Password: ");
		String userInformation_buffer=new String(id+" "+sc.next());//"id password"
		
		String line;
		try{
			BufferedReader br=new BufferedReader(new FileReader("userInformation.txt"));
			while((line=br.readLine())!=null){
				if(line.equals(userInformation_buffer)){
					br.close();
					System.out.println("Login Success!\n");
					return true;
				}
			}
			br.close();
			System.out.println("cannot find user information!\n");
			return false;
		}catch(FileNotFoundException e){//no DB file
			System.out.println("register first");
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
			return false;//will be rechecked by next line of register
		}catch(IOException e){ 
			e.printStackTrace(); 
			return false;
		}
	}
}
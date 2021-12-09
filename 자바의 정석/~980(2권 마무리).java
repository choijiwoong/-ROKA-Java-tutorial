import java.util.*;
import java.io.*;
import java.net.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class tutorial{
	public static void main(String[] args) throws Exception{
		System.out.println("[p.953]");//common use of URL. get information to URL object.
		URL url=new URL("http://www.codechobo.com:80/sample/"+"hello.html?referer=codechobo#index1");
		
		System.out.println("url.getAuthority(): "+url.getAuthority());//www.codechobo.com:80
		System.out.println("url.getContent(): "+url.getContent());//sun.get.www.protocol.http.HttpURLConnection$HttpInputStream@c17164
		System.out.println("url.DefaultPort(): "+url.getDefaultPort());//80
		System.out.println("url.getPort(): "+url.getPort());//80
		System.out.println("url.getFile(): "+url.getFile());// /sample/hello.html?referer=codechobo
		System.out.println("url.getHost(): "+url.getHost());//www.codechobo.com
		System.out.println("url.getPath(): "+url.getPath());// /sample/hello.html
		System.out.println("url.getProtocol(): "+url.getProtocol());//http
		System.out.println("utl.getQuery(): "+url.getQuery());//referer=codechobo
		System.out.println("url.getRef(): "+url.getRef());//index1
		System.out.println("url.getUserInfo(): "+url.getUserInfo());//null
		System.out.println("url.toExternalForm(): "+url.toExternalForm());//http://www.codechobo.com:80/sample/hello.html?referer=codechobo#index1
		System.out.println("url.toURI(): "+url.toURI());//http://www.codechobo.com:80/sample/htllo.html?referer=codechobo#index1
		
		
		System.out.println("\n\n[p.962]");//basic application of socket programming_TcpIpServer, TcpIpClient
		//In TcpIpServer
		//[01:24:31]Server is ready.
		//[01:24:31]waiting connect request...
		//[01:24:57]/127.0.0.1 sent connection request!
		//[01:24:57]Data is sent.
		//[01:24:57]Server is ready.
		//...
		
		//In TcpIpClient
		//connecting to server...127.0.0.1
		//Message of Server: [Notice] Test Message1 from Server.
		//Kill connection...
		//Connection is done.
		
		
		System.out.println("\n\n[p.969]");//Server with multi-threading_TcpIpServer2, TcpIpClient
		//In TcpIpServer2
		//[12:09:47]main Server is ready.
		//[12:09:47]Thread-0 is waiting connection request...
		//[12:09:47]Thread-1 is waiting connection request...
		//[12:09:47]Thread-2 is waiting connection request...
		//[12:09:47]Thread-3 is waiting connection request...
		//[12:09:47]Thread-4 is waiting connection request...
		//[12:09:53]Thread-0/127.0.0.1 sent connection request!
		//[12:09:53]Thread-0 Data is sent
		//[12:09:53]Thread-0 is waiting connection request...
		//[12:09:55]Thread-1/127.0.0.1 sent connection request!
		//[12:09:55]Thread-1 Data is sent
		//[12:09:55]Thread-1 is waiting connection request...
		//[12:09:56]Thread-2/127.0.0.1 sent connection request!
		//[12:09:56]Thread-2 Data is sent
		//[12:09:56]Thread-2 is waiting connection request...
		//[12:09:56]Thread-3/127.0.0.1 sent connection request!
		//[12:09:56]Thread-3 Data is sent
		//[12:09:56]Thread-3 is waiting connection request...
		
		
		System.out.println("\n\n[p.970]");//chatting program by make Sender, Receiver class
		//By using Sender & Receiver, both server & client can write or read msg each other.
		//they use IOStream of socket. Sender & Receiver is extending Thread class, so it can handle msg seperately.
		
		
		System.out.println("\n\n[p.972]");//MultichatServer program
	}//main
}//tutorial

class TcpIpServer{//p.961_basic application of TCP socket programming
	public static void main(String args[]) {
		ServerSocket serverSocket=null;
		
		try {
			serverSocket=new ServerSocket(7777);//Create ServerSocket with binding port to 7777
			System.out.println(getTime()+"main Server is ready.");
		}catch(IOException e) { e.printStackTrace(); }
		
		while(true) {
			try {
				System.out.println(getTime()+"is waiting connect request...");
				Socket socket=serverSocket.accept();//wait request, if request occur, make new Socket for connection 
				System.out.println(getTime()+socket.getInetAddress()+" send connection request!");
				
				OutputStream out=socket.getOutputStream();//get socket's outputstream
				DataOutputStream dos=new DataOutputStream(out);
				
				dos.writeUTF("[Notice] Test Message1 from Server.");
				System.out.println(getTime()+"Data is sent.");
				
				dos.close();
				socket.close();
			}catch(IOException e) { e.printStackTrace(); }
		}
	}
	static String getTime() {
		SimpleDateFormat f=new SimpleDateFormat("[hh:mm:ss]");
		return f.format(new Date());
	}
}
class TcpIpClient{
	public static void main(String args[]) {
		try {
			String serverIp="127.0.0.1";
			System.out.println("connecting to server..."+serverIp);
			Socket socket=new Socket(serverIp, 7777);//send request by serverip, port
			
			InputStream in=socket.getInputStream();//make inputstream for input
			DataInputStream dis=new DataInputStream(in);
			
			System.out.println("Message of server: "+dis.readUTF());//read!
			System.out.println("Kill connection...");
			
			dis.close();
			socket.close();
			System.out.println("Connection is done.");
		}catch(ConnectException ce) {
			ce.printStackTrace();
		}catch(IOException ie) {
			ie.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static String getTime() {
		SimpleDateFormat f=new SimpleDateFormat("[hh:mm:ss]");
		return f.format(new Date());
	}
}


class TcpIpServer2 implements Runnable{//p.949_multi-thread SocketProgramming
	ServerSocket serverSocket;
	Thread[] threadArr;
	
	public static void main(String args[]) {
		TcpIpServer2 server=new TcpIpServer2(5);
		server.start();
	}
	
	public TcpIpServer2(int num) {//Constructor
		try {
			ServerSocket serverSocket=new ServerSocket(7777);//binding
			System.out.println(getTime()+"Server is ready");
			
			threadArr=new Thread[num];//make thread[] as much as we want
		}catch(IOException e) { e.printStackTrace(); }
	}
	
	public void start() {
		for(int i=0; i<threadArr.length; i++) {
			threadArr[i]=new Thread(this);//Make new Thread to threadArr's element by this class.
			threadArr[i].start();//and do start
		}
	}
	public void run() {
		while(true) {
			try {
				System.out.println(getTime()+"is wating connection request...");
				Socket socket=serverSocket.accept();//wait for request
				System.out.println(getTime()+socket.getInetAddress()+"sent connection request!");
				
				OutputStream out=socket.getOutputStream();
				DataOutputStream dos=new DataOutputStream(out);
				
				dos.writeUTF("[Notice] Test Message2 from Server.");
				System.out.println(getTime()+" Data is sent.");
				
				dos.close();
				socket.close();
			}catch(IOException e) { e.printStackTrace(); }
		}
	}
	
	static String getTime() {
		String name=Thread.currentThread().getName();//get Thread name
		SimpleDateFormat f=new SimpleDateFormat("[hh:mm:ss]");
		return f.format(new Date())+name;
	}
}


class TcpIpServer3{//p.970 chatting program
	public static void main(String args[]) {
		ServerSocket serverSocket=null;
		Socket socket=null;//Make ServerSocket & Socket
		
		try {
			serverSocket=new ServerSocket(7777);//Make ServerSocket with port binding
			System.out.println("Server is ready.");
			
			socket=serverSocket.accept();//wait request for connection to client
			
			Sender sender=new Sender(socket);//If request is occcur, pass socket to Sender & Receiver
			//In Sender, initialize DateOutputStream to socket. In run(), make Scanner for getting user inut and write UserInput to DataOutputStream 
			Receiver receiver=new Receiver(socket);
			//In Receiver, initialize DataInputStream to socket. In run(), just read UTF of DataInputStream.
			
			//So In Server, start Sender(write) & Receiver(read) to socket's IOStream.
			
			sender.start();//and Start
			receiver.start();
		}catch(Exception e) { e.printStackTrace(); }
	}
}
class Sender extends Thread{
	Socket socket;
	DataOutputStream out;
	String name;
	
	Sender(Socket socket){//constructor
		this.socket=socket;//set socket
		try {
			out=new DataOutputStream(socket.getOutputStream());//get stream
			name="["+socket.getInetAddress()+": "+socket.getPort()+"]";//get name
		}catch(Exception e) {}
	}
	
	public void run() {
		Scanner scanner=new Scanner(System.in);
		while(out!=null) {
			try {
				out.writeUTF(name+scanner.nextLine());
			}catch(IOException e) {}
		}
	}
}
class Receiver extends Thread{
	Socket socket;
	DataInputStream in;
	
	Receiver(Socket socket){
		this.socket=socket;
		try {
			in=new DataInputStream(socket.getInputStream());
		}catch(IOException e) {}
	}
	
	public void run() {
		while(in!=null){
			try {
				System.out.println(in.readUTF());
			}catch(IOException e) {}
		}
	}
}

class TcpIpClient3{
	public static void main(String args[]) {
		try {
			String serverIp="127.0.0.1";
			Socket socket=new Socket(serverIp, 7777);//try connect to Server
			System.out.println("Server is connected");
			
			Sender sender=new Sender(socket);//get Sender and Receiver.
			Receiver receiver=new Receiver(socket);
			
			sender.start();//and start
			receiver.start();
		}catch(ConnectException ce) {
			ce.printStackTrace();
		}catch(IOException ie) {
			ie.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}


class TcpIpMultichatServer{//p.972
	HashMap clients;//for saving clients information
	
	TcpIpMultichatServer(){
		clients=new HashMap();
		Collections.synchronizedMap(clients);//make HashMap to synchronizedMap
	}
	
	public void start() {
		ServerSocket serverSocket=null;//Make ServerSocket, Socket
		Socket socket=null;
		
		try {
			serverSocket=new ServerSocket(7777);//binding
			System.out.println("Server start");
			
			while(true) {
				socket=serverSocket.accept();//wait request
				System.out.println("["+socket.getInetAddress()+": "+socket.getPort()+"] is connected!");
				
				ServerReceiver thread=new ServerReceiver(socket);//make ServerReceiver with socket and Start
				thread.start();
			}
		}catch(Exception e) { e.printStackTrace(); }
	}
	
	void sendToAll(String msg) {//Send msg to All client by using client's DataOutputStream on HashMap
		Iterator it=clients.keySet().iterator();
		
		while(it.hasNext()) {
			try {
				DataOutputStream out=(DataOutputStream)clients.get(it.next());
				out.writeUTF(msg);
			}catch(IOException e) {}
		}
	}
	
	public static void main(String args[]) {
		new TcpIpMultichatServer().start();//run
	}
	
	class ServerReceiver extends Thread{//Thread!_When request occur, make it and run per one request. it works on one socket of client.
		Socket socket;
		DataInputStream in;
		DataOutputStream out;
		
		ServerReceiver(Socket socket){
			this.socket=socket;
			try {
				in=new DataInputStream(socket.getInputStream());//connect to client's IOStream
				out=new DataOutputStream(socket.getOutputStream());
			}catch(IOException e) {}
		}
		
		public void run() {
			String name="";
			
			try {
				name=in.readUTF();//get Name first
				sendToAll("#"+name+"is entered");
				
				clients.put(name,  out);
				System.out.println("Current people: "+clients.size());
				
				while(in!=null) {
					sendToAll(in.readUTF());//read msg on InputStream & sent To All by using clients
					//sendToAll that msg server read
				}
			}catch(IOException e) {
				//ignore
			}finally {//if serverReceiver's done ??
				sendToAll("#"+name+"is exited");
				clients.remove(name);
				System.out.println("["+socket.getInetAddress()+": "+socket.getPort()+"] is finish connection");
				System.out.println("Current people: "+clients.size());
			}
		}
	}
}

class TcpIpMulticharClient{
	public static void main(String args[]) {
		if(args.length!=1) {
			System.out.println("USAGE: java TcpIpMulticharClient 대화명");
			System.exit(0);
		}
		
		try {
			String serverIp="127.0.0.1";
			Socket socket=new Socket(serverIp, 7777);//make request
			System.out.println("Server connected");
			
			Thread sender=new Thread(new ClientSender(socket, args[0]));//make ClientSender & ClientReceiver to Thread with socket
			Thread receiver=new Thread(new ClientReceiver(socket));
			
			sender.start();//run
			receiver.start();
		}catch(ConnectException ce) {
			ce.printStackTrace();
		}catch(Exception e) {}
	}
	
	static class ClientSender extends Thread{
		Socket socket;
		DataOutputStream out;
		String name;
		
		ClientSender(Socket socket, String name){
			this.socket=socket;
			try {
				out=new DataOutputStream(socket.getOutputStream());//get socket's output
				this.name=name;
			}catch(Exception e) {}
		}
		
		public void run() {
			Scanner scanner=new Scanner(System.in);
			try {
				if(out!=null)
					out.writeUTF(name);//write name first
				while(out!=null)
					out.writeUTF("["+name+"]"+scanner.nextLine());//write content
			}catch(IOException e) {}
		}
	}
	
	static class ClientReceiver extends Thread{
		Socket socket;
		DataInputStream in;
		
		ClientReceiver(Socket socket){
			this.socket=socket;
			try {
				in=new DataInputStream(socket.getInputStream());//get inputstream
			}catch(IOException e) {}
		}
		
		public void run() {
			while(in!=null) {
				try {
					System.out.println(in.readUTF());//read all & print
				}catch(IOException e) {}
			}
		}
	}
	
	
}                       
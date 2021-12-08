import java.util.*;
import java.io.*;
import java.util.Date;

public class tutorial{
	static int found=0;//p.925
	
	public static void main(String[] args) throws Exception{
		/*
		System.out.println("[p.877]");//Warining point in writing to stream
		byte[] inSrc= {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc=null;
		byte[] temp=new byte[4];
		
		ByteArrayInputStream input=null;
		ByteArrayOutputStream output=null;
		input=new ByteArrayInputStream(inSrc);//set inSrc to stream source
		output=new ByteArrayOutputStream();//make outputStream
		
		System.out.println("Input Source: "+Arrays.toString(inSrc));
		
		try {
			while(input.available()>0) {
				//input.read(temp);//read to temp
				//output.write(temp);//write to output with temp's content
				
				//At last write, {8,9,6,7} is printed not {8, 9, 0, 0} if we use same buffer(temp) writing some sontent not fully.
				//So We can solve this problem by using write(temp, 0, len); (write as much as we read)
				int len=input.read(temp);//read and return length
				output.write(temp, 0, len);//(space, start_point, length)
				//ouput source doesn't have 6, 7!
				
				System.out.println("temp: "+Arrays.toString(temp));
				
				outSrc=output.toByteArray();//get output's content to ByteArray
				printArrays(temp, outSrc);//print temp, outSrc.
			}
		}catch(IOException e) {}
		
		
		System.out.println("\n\n[p.887]");//write to DataOutputStream, hexize decimal ByteArrayOutputStream
		ByteArrayOutputStream bos=null;
		DataOutputStream dos=null;
		
		byte[] result=null;
		
		try {
			bos=new ByteArrayOutputStream();
			dos=new DataOutputStream(bos);
			
			//write to DataOutputStream
			dos.writeInt(10);
			dos.writeFloat(20.0f);
			dos.writeBoolean(true);
			
			result=bos.toByteArray();//get ByteArray of DataOutputStream
			String[] hex=new String[result.length];//for save resault
			for(int i=0; i<result.length; i++) {
				if(result[i]<0)//if negative
					hex[i]=String.format("%02x", result[i]+256);//make positive and convert decimal to hex
				else
					hex[i]=String.format("%02x", result[i]);
			}
			
			System.out.println("10진수: "+Arrays.toString(result));//initial state of data
			System.out.println("16진수: "+Arrays.toString(hex));//converted data
			
			dos.close();
		} catch(IOException e) { e.printStackTrace(); }
		
		
		
		System.out.println("\n\n[p.893]");//SequenceInputStream like flatMap, now flatVecrtor!
		byte[] arr1= {0,1,2};
		byte[] arr2= {3,4,5};
		byte[] arr3= {6,7,8};
		byte[] outSrc=null;
		
		Vector v=new Vector();
		v.add(new ByteArrayInputStream(arr1));
		v.add(new ByteArrayInputStream(arr2));
		v.add(new ByteArrayInputStream(arr3));
		
		SequenceInputStream input=new SequenceInputStream(v.elements());//Make SequenceInputStream {0,1,2},{3,4,5} to {0,1,2,3,4,5}
		ByteArrayOutputStream output=new ByteArrayOutputStream();
		
		int data=0;
		try {
			while((data=input.read())!=-1)//read to SequenceInputStream
				output.write(data);//write
		}catch(IOException e) {}
		
		outSrc=output.toByteArray();//save
		
		System.out.println("Input Source1: "+Arrays.toString(arr1));
		System.out.println("Input Source2: "+Arrays.toString(arr2));
		System.out.println("Input Source3: "+Arrays.toString(arr3));
		System.out.println("Output Source:"+Arrays.toString(outSrc));
		
		
		System.out.println("\n\n[p.897]");//Common I/O
		int i=65;
		float f=1234.56789f;
		
		Date d=new Date();
		
		System.out.printf("문자 %c의 코드는 %d%n", i,i);
		System.out.printf("%d는 8진수로 %o, 16진수로 %x%n", i, i, i);
		System.out.printf("%3d%3d%3d%n", 100, 90, 80);
		System.out.println();
		System.out.printf("123456789012345678901234567890%n");
		System.out.printf("%s%-5s%5s%n", "123", "123", "123");//normal print, print front, print behind
		System.out.println();
		System.out.printf("오늘은 %tY년 %tm월 %td일 입니다.%n",d,d,d);//Date format
		System.out.printf("지금은 %tH시 %tM분 %tS초 입니다.%n",d,d,d);
		System.out.printf("지금은 %1$tH시 %1$tM분 %1$tS초 입니다.",d);//We can assign location of argumant we want by %n$
		
		
		System.out.println("\n\n[p.901]");//PipedReader, PipedWriter on Thread
		InputThread inThread=new InputThread("InputThread");
		OutputThread outThread=new OutputThread("OutputThread");
		
		inThread.connect(outThread.getOutput());
		
		inThread.start();
		outThread.start();
		Thread.sleep(1);
		
		
		System.out.println("\n\n[p.906]");//check ENCODING on current OS
		InputStreamReader check=new InputStreamReader(System.in);
		System.out.println("Current OS's Encoding is "+check.getEncoding());
		
		
		System.out.println("\n\n[p.916]");//Examples of File.
		File f=new File("C:\\Users\\admin0!\\Desktop\\_2jimo\\Java\\out.txt");//if we want to create File, call createNewFile().
		String fileName=f.getName();
		int pos=fileName.lastIndexOf(".");//get .'s location
		
		System.out.println("filename without path - "+f.getName());
		System.out.println("filename without extension - "+fileName.substring(0, pos));
		System.out.println("extension - "+fileName.substring(pos+1));//Idea is good
		
		System.out.println("filename with path - "+f.getPath());
		System.out.println("file's absolute path - "+f.getAbsolutePath());
		System.out.println("file's connonical path - "+f.getCanonicalPath());
		System.out.println("directory of file - "+f.getParent());
		System.out.println();
		System.out.println("File.pathSeparator - "+File.pathSeparator);//path Separator
		System.out.println("File.pathSaparatorChar - "+File.pathSeparatorChar);
		System.out.println("File.separator - "+File.separator);//common Separator
		System.out.println("File.separatorChar - "+File.separatorChar);
		System.out.println();
		System.out.println("user.dir - "+System.getProperty("user.dir"));
		System.out.println("sun.boot.class.path - "+System.getProperty("sun.boot.class.path"));//base path set in here
		
		
		System.out.println("\n\n[p.926]");//find keyword in directory
		if(args.length!=2) {//force use of main's arguments
			System.out.println("USAGE: java tutorial DIRECTORY KEYWORD");//Check isValid input! important
			System.exit(0);
		}
		
		File dir=new File(args[0]);
		String keyword=args[1];
		if(!dir.exists() || !dir.isDirectory()) {//Check isValid input! important
			System.out.println("Unvalid Directory");
			System.exit(0);
		}
		
		try {
			findInFiles(dir, keyword);
		}catch(IOException e) { e.printStackTrace(); }
		
		System.out.println();
		System.out.println("we found "+keyword+" in "+found+"lines");
		*/
		
		
		System.out.println("n\n[p.942]");//User designed Serializable
		//read class!
		
	}//main
	
	static void printArrays(byte[] temp, byte[] outSrc) {
		System.out.println("temp\t:"+Arrays.toString(temp));
		System.out.println("Output Source: "+Arrays.toString(outSrc));
	}
	
	
	public static void findInFiles(File dir, String keyword) throws IOException{//p.926
		File[] files=dir.listFiles();
		
		for(int i=0; i<files.length; i++) {
			if(files[i].isDirectory()) {//if element is directory too,
				findInFiles(files[i], keyword);//recursive!
			}else {
				String filename=files[i].getName();
				String extension=filename.substring(filename.lastIndexOf(".")+1);
				extension=","+extension+",";
				
				if(",java,txt,bak,".indexOf(extension)==-1)//check only these type file.
					continue;
				
				filename=dir.getAbsolutePath()+File.separator+filename;//get os's separator by File.separator
				
				FileReader fr=new FileReader(files[i]);
				BufferedReader br=new BufferedReader(fr);
				
				String data="";
				int lineNum=0;
				
				while((data=br.readLine())!=null) {
					lineNum++;
					if(data.indexOf(keyword)!=-1) {//if found
						found++;
						System.out.println("["+filename+"("+lineNum+")"+"]"+data);
					}
				}
			}
		}
	}
}//tutorial

class InputThread extends Thread{//p.901_IT'S THREAD!!! BY INHERITANCE
	PipedReader input=new PipedReader();//PipedStream is used on thread. it connects inputstream with outputstream, outputstream with inputstream
	StringWriter sw=new StringWriter();
	
	InputThread(String name){ super(name); }//set Thread's name
	
	public void run() {
		try {
			int data=0;//buffer
			
			while((data=input.read())!=-1)
				sw.write(data);//write read data to StringWriter
			System.out.println(getName()+" received: "+sw.toString());
		}catch(IOException e) {}
	}
	
	public PipedReader getInput() { return input; }//pipedReader
	
	public void connect(PipedWriter output) {
		try {
			input.connect(output);//PipedReader connect to PipedWriter
		}catch(IOException e) {}
	}
}

class OutputThread extends Thread{
	PipedWriter output=new PipedWriter();
	
	OutputThread(String name){ super(name); }
	
	public void run() {
		try {
			String msg="Hello";
			System.out.println(getName()+" sent: "+msg);
			output.write(msg);//write msg to PipedWriter
			output.close();
		}catch(IOException e) {}
	}
	
	public PipedWriter getOutput() { return output; }
	
	public void connect(PipedReader input) {//connect PipedStream.
		try {
			output.connect(input);
		}catch(IOException e) {}
	}
}


class SuperUserInfo{//p. 942 Serializable
	String name;
	String password;
	
	SuperUserInfo(){ this("Unknown", "1111"); }
	SuperUserInfo(String name, String password){ this.name=name; this.password=password; }
}

class UserInfo2 extends SuperUserInfo implements java.io.Serializable{
	int age;
	
	public UserInfo2() { this("Unknown", "1111", 0); }
	public UserInfo2(String name, String password, int age) { super(name, password); this.age=age; }
	
	public String toString() { return "("+name+", "+password+", "+age+")"; }
	
	private void writeObject(ObjectOutputStream out) throws IOException{
		out.writeUTF(name);
		out.writeUTF(password);
		out.defaultWriteObject();//expression of end
	}
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
		name=in.readUTF();//MUST 순서대로!!!!
		password=in.readUTF();
		in.defaultReadObject();//expression of end
	}
}
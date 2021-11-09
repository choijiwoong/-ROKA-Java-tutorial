import java.io.IOException;
import java.io.FileOutputStream;//get file's name & write by byte. so we have to convert String to byte by using method .getBytes();
import java.io.FileWriter;//get file's name & write by String. (if additional argument true set, become append mode)
import java.io.PrintWriter;//same with FileWriter & But use println(String) not .write(String) (true->append mode is same with FileWriter)

import java.io.FileInputStream;//get file's name & read by byte. so we have to convert byte array to String by using new String(byte_array)
import java.io.FileReader;//get file's name & read by String
import java.io.BufferedReader;//get FileReader object & read by line by using method .readLine();

public class Main{
	public static void main(String[] args) throws IOException{
	//[1. File write]
//		//way.1 FileOutputStream per byte
//		FileOutputStream output=new FileOutputStream("C:\\Users\\admin0!\\Desktop\\_2jimo\\Java\\out.txt");//we have to pass name of file as argument of Constructor of FileOutputStream
//		for(int i=1; i<11; i++){
//			String data="line."+i+" by FileOutputStream!\r\n";//\r\n is same to \n in C. \r\n well works in editor like notepad
//			output.write(data.getBytes());//Because FileOutputStream uses byte, we have to convert String to byte[]. Little unconvenient! 
//		}
//		output.close();//it can be skipped because it's automatically closed when program is done. close just for prevention of user mistake like reuse..
		
		//way.2 FileWriter
//		FileWriter fw=new FileWriter("C:\\\\Users\\\\admin0!\\\\Desktop\\\\_2jimo\\\\Java\\\\out.txt");
//		for(int i=1; i<11; i++) {
//			String data="line."+i+" by FileWriter!\r\n";//Little unconvenient because of '\r\n'!
//			fw.write(data);
//		}
//		fw.close();
		
		//way.2 PrintWriter with println without \r\n
		PrintWriter pw=new PrintWriter("C:\\\\Users\\\\admin0!\\\\Desktop\\\\_2jimo\\\\Java\\\\out.txt");
		for(int i=1; i<11; i++) {
			String data="line."+i+" by PrintWriter!";
			pw.println(data);
		}
		pw.close();
		
	//[2. File content append]
		FileWriter fw2=new FileWriter("C:\\\\Users\\\\admin0!\\\\Desktop\\\\_2jimo\\\\Java\\\\out.txt", true);//append mode
		for(int i=11; i<21; i++) {
			String data="line."+i+" content appended by FileWriter!\r\n";
			fw2.write(data);
		}
		fw2.close();
		
		PrintWriter pw2=new PrintWriter(new FileWriter("C:\\\\Users\\\\admin0!\\\\Desktop\\\\_2jimo\\\\Java\\\\out.txt", true));
		//for use PrintWriter in append mode, FileWriter is used as argument of Constructor of PrintWriter.
		for(int i=21; i<31; i++) {
			String data="line."+i+" content appended by PrintWriter!";
			pw2.println(data);
		}
		pw2.close();
		
	//[3. File read]
		//per byte
//		byte[] b=new byte[1100];//unit of FileInputStream is byte. we have to design size. very unconvenient!
//		FileInputStream input=new FileInputStream("C:\\\\Users\\\\admin0!\\\\Desktop\\\\_2jimo\\\\Java\\\\out.txt");
//		input.read(b);
//		System.out.println(new String(b));//convet to String
//		input.close();
		
		//per line (before enter)
		BufferedReader br=new BufferedReader(new FileReader("C:\\\\Users\\\\admin0!\\\\Desktop\\\\_2jimo\\\\Java\\\\out.txt"));
		while(true) {//no while(1)...ㄷㄷ
			String line=br.readLine();
			if(line==null)
				break;
			System.out.println(line);
		}
		br.close();
		
	}
}
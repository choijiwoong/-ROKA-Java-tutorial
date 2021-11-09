import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.Scanner;


public class Main{
	public static void main(String[] args) throws IOException{//we have to handle IOexception
		//[1. Console Input]
		//way.1 InputStream to variables
//		InputStream in=System.in;//abc
//
//		int a;
//		a=in.read();//read 1byte!
//		int b;
//		b=in.read();
//		int c;
//		c=in.read();
//		
//		System.out.println(a);//97
//		System.out.println(b);
//		System.out.println(c);
		
		//way.2 InputStream to array
//		InputStream in=System.in;
//		
//		byte[] A=new byte[3];
//		in.read(A);
//		
//		System.out.println(A[0]);
//		System.out.println(A[1]);
//		System.out.println(A[2]);
		
		//way.3 InputStreamReader; read as char not byte.
//		InputStream in=System.in;
//		InputStreamReader reader=new InputStreamReader(in);
//		char[] a=new char[3];//unconvenient!
//		reader.read(a);
//		
//		System.out.println(a);
		
		//way.4 BufferedReader
//		InputStream in=System.in;//byte
//		InputStreamReader reader=new InputStreamReader(in);//char
//		BufferedReader br=new BufferedReader(reader);//String
//		
//		String a=br.readLine();
//		System.out.println(a);
		
		//way5. Scanner; .next(token), .nextLine(line), .nextInt(integer)
		Scanner sc=new Scanner(System.in);//it needs InputStream as argument of Constructor
		System.out.println(sc.next());//.next method reads one Token
		
		//[2. Console Output]
		//System.out is object of PrintStream class.
		//System.err is same to System.out but, it's used at print of error message
	}
}
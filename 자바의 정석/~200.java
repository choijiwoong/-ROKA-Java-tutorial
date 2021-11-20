public class tutorial {
	public static void main(String[] args){
		System.out.println("\n");
		//p.78_float, double, casting
		float f=9.1234657f;//already trash value is in left memory
		double d=9.1234567;
		double d2=(double)f;//do regardless of casting, d2 gets f's trash value too.
		
		System.out.printf("f=%20.18f\n", f);//f=9.123465538024902000
		System.out.printf("d=%20.18f\n", d);//d=9.123456700000000000
		System.out.printf("d2=%20.18f\n", d2);//d2=9.123465538024902000
		
		System.out.println("\n");
		//p.98_usual arithmetic conversion
		byte a=10;
		byte b=20;
		byte c=(byte)(a+b);//usual arithmetic conversion to int! so we have to casting int to byte
		System.out.println(c);
		
		System.out.println("\n");
		//p.100_late casting
		int A=1_000_000;
		int B=2_000_000;
		long C=A*B;//A&B is int! so overflow already occur to -1454759936 not 2_000_000_000_000 before saving as long type. 
		System.out.println(C);
		
		System.out.println("\n");
		//p.103
		char c1='a';
		//char c2=c1+1;//ERROR! 
		char c3='a'+1;//Good because of literal operation is changed to result value in compile time by optimization
		
		System.out.println("\n");
		//p.113_difference of String constructor without new & with new
		String str1="abc";//address that points "abc"
		String str2=new String("abc");//address of new String object contains "abc"
		System.out.printf("\"abc\"==\"abc\" ? %b%n", "abc"=="abc");//"abc"=="abc" ? true
		System.out.printf("str1==\"abc\" ? %b%n", str1=="abc");//str1=="abc" ? true
		System.out.printf("str2==\"abc\" ? %b%n", str2=="abc");//str2=="abc" ? false
		
		System.out.println("\n");
		//p.144_necessity of {} in double if
		int num=1;
		char sign;
		if(num>=0)
			if(num!=0)
				sign='+';
		else//this else is of if(num!=0) not if(num>=0)!
			sign='-';	
		
		//p.147_Switch's default can be placed in anywhere in switch. And if we put it last of switch, we don't have to put break;
		
		System.out.println("\n");
		//p.164_use usual arithmetic conversion
		for(int i=1; i<=3; i++)
			for(int j=1;j<=3; j++)
				for(int k=1; k<=3; k++)
					System.out.println(""+i+j+k);
		
		System.out.println("\n");
		//p.178_labeled for...maybe for understanding of concept not execution..
//		Loop1: for(int i=1; i<=9; i++) {
//			for(int j=1; j<=9; j++) {
//				if(j==5)
//					break Loop1;//break to Loop1
//					break;
//					continue Loop1;//continue to Loop1
//					continue;
//				System.out.println(i+"*"+j+"="+i*j);
//			}
//			System.out.println();
//		}
		
		System.out.println("\n");
		//p.195_arraycopy
		char[] abc= {'A', 'B', 'C', 'D'};
		char[] numar= {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		System.out.println(abc);
		System.out.println(numar);
		
		//make result=abc+numar
		char[] result=new char[abc.length+numar.length];
		System.arraycopy(abc, 0, result, 0, abc.length);
		System.arraycopy(numar, 0, result, abc.length, numar.length);
		
		//copy abc to numar partly
		System.arraycopy(abc, 0, numar, 0, abc.length);
		System.out.println(numar);
		
		//copy part of numar to abc
		System.arraycopy(abc, 0, numar, 6, 3);
		System.out.println(numar);
	}	
}
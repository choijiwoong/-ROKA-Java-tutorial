//find self number; d(91)=9+1+91=101. 91 is generator of 101; some number get one more generator. like d(100)=101
//self-number(Kaprekar) doesn't have gererator

//get sum of selfnumber in 1to 5000

import java.util.ArrayList;//for make 

public class SelfNumber{
	private static ArrayList<Integer> numbersHasGenerator;//Integer... maybe wrapper class of int..?
	
	public static void main(String[] args){
		calculateNumberHasGenerator();//calculate all HasGenerator number
		int sum=0;
		for(int i=0; i<5001; i++)
			if(!hasGenerator(i))//if not exist in numberHasGernerator
				sum+=i;
		
		System.out.println("sum: "+sum);
	}
	
	private static boolean hasGenerator(int num){
		return numberHasGenerator.contains(num);//ㅇㅇ 1, ㄴㄴ 0
	}
	
	private static void calculateNumbersHasGenerator(){
		numbersHasGenerator=new ArrayList<Integer>();//initialization private variable.
		for(int i=0; i<=5000; i++){
			String num=String.valueOf(i);//i to String
			int no=0;//sum of chars of number
			for(int n=0; n<num.length(); n++)//for each char of num(String)
				no+=Integer.parseInt(num.substring(n, n+1));//갑자기 혼자 치트쓰네.. get substring(index n) & make Integer class by substring(that is parsed to int) & += to no
			numbersHasGenerator.add(no+i);//sum of each place+self. Approach reversely. make all self_number and add to ArrayList.
			//And judge by whether contains
		}
	}
}
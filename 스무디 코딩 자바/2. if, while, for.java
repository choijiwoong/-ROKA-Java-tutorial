import java.util.Scanner;

public class Main{
	public static void main(String[] args){
		//1. Switch
		Scanner sc=new Scanner(System.in);
		System.out.println("pick a color: ");
		int pickColor=sc.nextInt();
		
		switch(pickColor){
			case 0:
				System.out.println("you picked Red");
				break;
			case 1:
				System.out.println("you picked Green");
				break;
			case 2:
				System.out.println("you picked Blue");
				break;
			default:
				System.out.println("no such color");
				break;
		}
		sc.close();
	}
}

/*
1.	일반 어플리케이션에서 프로그램 GUI의 일부분(명령프롬프트던, 키보드나 마우스 표현이던)은 무한루프 로직으로 돌고있고, 앱개발 프레임워크에서도 무한루프로직이 이미 구현되어 있거나 구현해야하는 경우가 많다.
	네트워크 프로그래밍에서 쓰이는 아이디어도 producer-consumer에서 무한정 통신을 해야하는데 속도차이로 인해 어떤 연결을 구상하고 설계할 것인가에 대한 것에 도움이 된다.
2.	C언어는 제어문->배열->메모리->포인터 순으로 middle에서 low level로 내려가는 학습방법이지만, 자바는 제어문->객체모형 순으로 middle에서 high level로 올라가는 구조이다.
*/
//뭐 함수명이 오일러라고 거창한게 아니라 점프 투 자바에서 예시로 쓴 문제가 프로젝드오릴러라는 싸이트의 문제여서 그럼...존나 거창하네..
public class Euler1{
	int max;
	public Euler1(int max){
		this.max=max;//그러고보니 C++에서는 같은 클래스면 그냥 max로 썼는데,,, 그리고 max를 initializer_list로 그냥 초기화했었는데,,,그립다. 굳이 this써야하나..
	}
	
	public int sumOf3And5(){//대충 범위내에 3과 5로 나눠지는 수의 합 구하는거임..
		int sum=0;
		for(int i=0; i<max; i++){
			if(i%3==0 || i%5==0)
				sum+=i;
		}
		
		return sum;
	}
	
	public static void main(String[] args){
		System.out.println(new Euler1(10).sumOf3And5());//그러고보니 이건 좀 신기하네..객체 생성 저장된 변수도 없는데 바로 메소드 사용 가능한거.. C++도 괄호로 묶으면 됬으려나..
		System.out.println(new Euler1(1000).sumOf3And5());
	}
}
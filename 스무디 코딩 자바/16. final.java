public class Main{
	public static void main(String[] args){
		final int CON_NUMBER=999;//const
		System.out.println(NoInheritance.MAX);
		
	}
}

abstract final class NoInheritance{//ㅋㅋㅋ인스턴스생성불가인데 상속못하쥬? static만 이용하셔요~ 애초에 이런 선언 자체가 불가하쥬,,끌끌
	public void displaySomeThing(){}//오버로딩을하려면 상속...이 안되쥬?ㅋㅋ final을 여따 붙이면 상속뿐만이 아니라 오버로딩도 안되쥬?
	//final public void displaySomeThing(){} 안한걸 다행으로 생각해유ㅎ
	public static final int MAX=99999;//static const 이거나써라
}

class NotValid extends NoInheritance{//안되쥬? 오류나쥬?
	@Override
	public void displaySomeThing(){ System.out.println("아맞다~"); }
}

/*
1. 변수앞에 붙이면 상수_final int CON_NUMBER=999;(static과 같이쓰면 인스턴스없이 쓸 수 있는 클래스 상수)
2. 클래스와 쓰면 상속불가_final class NoInheritance
3. 메소드와 쓰면 오버라이드 불가_final public void showInfo(){}
*/
import java.util.ArrayList;

public class Main{
	public static void main(String[] args){
		//1. example of array[][]
		int[][] array2D=new int[11][11];
		
		here: for(int i=0; i<array2D.length; i++){
			for(int j=0; j<array2D[i].length; j++){
				if(i==10)
					break here;
				if(j==10){
					array2D[i][j]=0;
				}else{
					array2D[i][j]=i*j;
				}
			}
		}
		
		for(int i=0; i<array2D.length; i++){
			for(int j=0; j<array2D[i].length; j++)
				System.out.printf("%2d ", array2D[i][j]);
			System.out.println();
		}
		
		//2. simple example of ArrayList
		ArrayList<String> arrayList=new ArrayList<String>();
		
		arrayList.add("orange");
		arrayList.add("blue");
		arrayList.add("night");
		
		System.out.println(arrayList);
		arrayList.add(1, "coke");
		System.out.println(arrayList);
		
		//3. simple example of Arraylist2
		ArrayList<myObject> arrayList=new ArrayList<myObject>();
		
		arrayList.add(new myObject(201, "Police"));//new returns hashkey
		arrayList.add(new myObject(202, "Firefighter"));
		arrayList.add(new myObject(202, "nurse"));
		
		System.out.println(arrayList);
		
		for(int i=0; i<arrayList.size(); i++)
			arrayList.get(i).showInfo();
		arrayList.add(0, new myObject(301, "doctor"));
		System.out.println(arrayList);
		
		for(int i=0; i<arrayList.size(); i++)
			arrayList.get(i).showInfo();
	}
}
class myObject{
	int id;
	String name;
	myObject(){}
	public myObject(int id, String name){
		this.id=id;
		this.name=name;
	}
	public void showInfo(){
		System.out.println("(id): "+id);
		System.out.println("(name): "+name);
	}
}

/*
1.	JDK의 ArrayList는 Generic프로그래밍<>에 자주 사용되는데 이는 COllections Framework에 속한 클래스로 자료구조를 개발하지 않아도 빠르고 안정된 응용프로그램을 개발할 수 있게 한다.
2.	일반적으로 List는 Linked List를 의미하는데, 탐색속도는 느리지만 삽입과 삭제가 자유로운 자료형이다. ArrayList는 배열의 탐색속도, 리스트의 빠른 삽입&삭제가 가능한 클래스이다.
3.	ArrayList에 객체를 담아 print하면 new에서 반환된 해쉬값을 볼 수 있다.
*/
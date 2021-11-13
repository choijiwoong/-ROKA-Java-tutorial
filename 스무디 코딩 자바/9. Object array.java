public class Main{
	public static void main(String[] args){
		//1. umm..uncommon error...!
		//myObject[] arrayObj=new myObject[3];//yet uninitialized
		//for(int i=0; i<array.Obj.length; i++)
		//	System.out.println("arrayObj["+i+"] = "+arrayObj[i]);//ERROR NullPointerException
		
		//2.solution
		myObject[] arrayObj=new myObject[3];
		//arrayObj[0].id=10 no!
		arrayObj[0]=new myObject(101, "first array, John");//we have to use 'new' twice.
		arrayObj[1]=new myObject(102, "second array, Mary");
		arrayObj[2]=new myObject(103, "third array, Smith");
		
		for(int i=0; i<arrayObj.length; i++)
			System.out.println("arrayObj["+i+"] = "+arrayObj[i]);//hashkey
		
		arrayObj[0].showInfo();
		arrayObj[1].showInfo();
		arrayObj[2].showInfo();
		
		
		//3. copy of reference variable(shallow copy)
		int[] myArray=new int[]{1,3,5,7,9};
		
		System.out.println("myArray = "+myArray);
		for(int i=0; i<myArray.length; i++)
			System.out.println("myArray.length["+i+"]: "+myArray[i]);
		
		int[] newArray=myArray;
		System.out.println("newArray = "+newArray);
		for(int i=0; i<newArray.length; i++)
			System.out.println("newArray.length["+i+"]: "+newArray[i]);
		
		//4. copy of instance(deep copy)
		int[] Array1=new int[]{1,3,5,7,9};
		int[] cpArray=new int[5];//if we didn't initialize it, Error occur(variable cpArray might not have been initialized)
		
		System.arraycopy(Array1,0, cpArray,0,myArray.length);
		System.out.println("Array1: "+Array1+", cpArray: "+cpArray);
		
		//5. try deep copy to myObject
		//use 2. arrayObj
		myObject[] arrayCpyObj=new myObject[5];
		System.arraycopy(arrayObj,0,arrayCpyObj,0,3);
		System.out.println(arrayObj[0]+", "+arrayCpyObj[0]);//logic error! same! it's shallow copy not deep copy
		
		//6. real deep copy to object
		myObject[] realCpyObj=new myObject[3];
		realCpyObj[0]=new myObject();
		realCpyObj[1]=new myObject();
		realCpyObj[2]=new myObject();
		
		for(int i=0; i<realCpyObj.length; i++){
			realCpyObj[i].id=arrayObj[i].id;
			realCpyObj[i].description=arrayObj[i].description;
		}
		for(int i=0; i<realCpyObj.length; i++){
			System.out.println("realCpyObj = ["+i+"]: "+realCpyObj[i]);
		}
	}
}
class myObject{
	int id;
	String description;
	
	myObject(){}
	public myObject(int id, String description){
		this.id=id;
		this.description=description;
	}
	public void showInfo(){
		System.out.println("(id): "+id);
		System.out.println("(description): "+description);
	}
}

/*
0. (p.s) System은 java.lang패키지에 속하며 JVM에 기본으로 로드하기에 import가 필요없다.
1.	new는 heap메모리를 점유하지만 삭제하지 않아도 GC가 자동으로 메모리를 해제시킨다. 다만 참조변수는 스택메모리에 저장이 되는데,
	크기가 고정된 스택 메모리에 기본형 변수 및 참조 변수를 두고 실제 값은 가변적 힙메모리에 자유롭게 생성하고 소멸시키게 하는 것이다.
2.	배열의 원소값들을 다 복사하는데에는 System.arraycopy함수를 이용하면 편리하다.
	다만 구조체 배열의 값은 deep copy를 위해선 하나하나 복사해야한다.
*/
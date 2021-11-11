//[1. Abstract Class]

abstract class Predator extends Animal{//real pure virtual class..in C++
	public abstract String getFood();//real pure virtual function..in C++
	
	public boolean isPredator(){ return true; }//it can be include real method! difference of interface, pure virtual class in C++
}//but for readabilty, use interface first!

class Tiger extends Predator implements Barkable{//Predator is abstract CLASS so use extends not implements(interface)
	public String getFood(){ return "apple"; }
	public void bark(){ System.out.println("ang><"); }
}
class Lion extends Predator implements Barkable{
	public String getFood(){ return "banana"; }
	public void bark(){ System.out.println("ung><"); }
}

public class Main{
	public static void main(String[] args){
		
	}
}
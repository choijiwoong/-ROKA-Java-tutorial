class Animal{
	String name;
	public void setName(String name) { this.name=name; }
}
class Dog extends Animal{
	public Dog() {}//default constuctor!
	public void sleep() { System.out.println(this.name+" zzz"); }
}
class HouseDog extends Dog{
	public HouseDog() {}//if we make constructor, creation of object must be occured in Constructor that we designed. so for using default constructor with designed constructor, we have to make it
	public HouseDog(String name) { this.setName(name); }//Constructor with initialization!
	public HouseDog(int type) {
		if(type==1)
			this.setName("yorkshire");
		else if(type==2)
			this.setName("bulldog");
		else
			this.setName(null);
	}
	public void sleep() { System.out.println(this.name+" zzz in house"); };
	public void sleep(int hour) { System.out.println(this.name+" zzz in house for "+hour+" hours"); }
}

public class Main{
	public static void main(String[] args) {
		HouseDog houseDog_noConstructor=new HouseDog();
		System.out.println("noConstructor: "+houseDog_noConstructor.name);//before setName, print null
		HouseDog houseDog=new HouseDog("happy");
		System.out.println("Constructor: "+houseDog.name);
		
		HouseDog houseDog_1=new HouseDog(1);
		System.out.println("HouseDog(1)'s name: "+houseDog_1.name);
		HouseDog houseDog_3=new HouseDog(3);
		System.out.println("HouseDog(3)'s name: "+houseDog_3.name);
		
		houseDog.sleep();
		houseDog.sleep(3);
	}
}
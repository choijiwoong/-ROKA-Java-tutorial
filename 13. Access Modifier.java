//[1. Access Modifier]_private->default->protected->public

//1. private
public class AccessModifier{
	private String secret;//only access in AccessModifier class!
	private String getSecret(){
		return this.secret;
	}
}


//2. default(not defined specially)
//HouseKim.java
package Java.house;

public class HouseKim{
	String lastname="kim";//default access modifier without special definition of access modifier.
}
//HousePark.java
package Java.house;

public class House.park{
	String lastname="park";
	
	public static void main(String[] args){
		HouseKim kim=new HouseKim();//it can be use in same package. ***how about subpackage or uppackage?***
		System.out.println(kim.lastname);
	}
}


//3. protected(default+inferitanced external class)
//HousePark.java
package Java.house;

public class HousePark{
	protected String lastname="park";
}
//EungYongPark.java
package Java.house.person;

import house.HousePark;

public class EungYongPark extends HousePark{
	public static void main(String[] args){
		EungYongPark eyp=new EungYongPark();
		System.out.println(eyp.lastname);//it can be use in same package, inheritanced external class
		//if HousePark.lastname is defined default, it will make compile error.
	}
}


//4. public
package Java.house;

public class HousePark{
	protected String lastname="park";
	public String info="this is public message.";//any class!
}

//5. In addition, Inner Class(Class in Class) can be also controled by access modifier.
//	 Access modifier exist only for mistake of programmer & elemination of danger elements.
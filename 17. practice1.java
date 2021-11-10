public class GuGu{//Base Class
	int dan;//format: dan*seq
	//we have to make GuGu class per dan!
	
	public GuGu(int dan){//constructor for setting dan
		this.dan=dan;
	}
	
	public int get(int seq){//return result of dan*seq(argument)
		return this.dan*seq;
	}
	
	public int[] all(){//for get result per dan
		int[] result=new int[9];
		for(int i=0; i<9; i++)
			result[i]=this.get(i+1);
		return result;//return result of dan*1~9 as int[]
	}
	
	public String toString(){//convert int[](return value of all) to String for convenience of print
		StringBuffer sb=new StringBuffer();//make new StringBuffer object.
		int[] result=all();//get data by int[]
		for(int i=0; i<result.length; i++){//for each element of int[]
			sb.append(result[i]);//add to StringBuffer object
			if(i!=result.length-1)
				sb.append(",");
		}
		
		return sb.toString();//return StringBuffer as String
	}
	
	public static void main(String[] args){
		GuGu gugu=new GuGu(2);//make 2dan class GuGu
		System.out.println(gugu.get(3));//print 2(dan)*3(.get(argument))
		System.out.println(gugu);//**IMPORTANT** if 'toString' is defined in Class, that class return result of toString() when we use that class in print format
		//System.our.println(gugu.toString());//it's same!
		for(int i=2; i<10; i++)
			System.out.println(new GuGu(i));//it's same! execute by toString method.
	}
}
package package2;

import package1.Foo;

public class Bar {
	public static void main(String[] args){
		System.out.println(new Bar().getInfo());
		System.out.println(new Foo().getInfo());	
	}

	public String getInfo(){
		return "Bar " + getClass().getModule();
	}
}


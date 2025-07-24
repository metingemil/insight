package modules.secondModule.insight.package2;
//package package2;  uncomment this and remove previous line

import modules.firstModule.insight.package1.Foo;
//import package1.Foo;  uncomment this and remove previous line

public class Bar {
	public static void main(String[] args){
		System.out.println(new Bar().getInfo());
		System.out.println(new Foo().getInfo());	
	}

	public String getInfo(){
		return "Bar " + getClass().getModule();
	}
}


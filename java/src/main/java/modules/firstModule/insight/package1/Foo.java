package package1;

import com.sun.net.httpserver.HttpServer;

public class Foo{
    public static void main(String[] args){
	System.out.println("start Foo");
	try{	
	    HttpServer server = HttpServer.create();
	    System.out.println(server);
	}catch(Exception ex){
	    System.out.println(ex);
	}	
	System.out.println(new Foo().getInfo());        
    }

   public String getInfo(){
	try{	
	    HttpServer server = HttpServer.create();
	    System.out.println(server);
	}catch(Exception ex){
	    System.out.println(ex);
	}	
   	return "Foo " + getClass().getModule();
   } 
}

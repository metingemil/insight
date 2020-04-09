package javaInsight.lambdas;

import java.util.ArrayList;
import java.util.List;

public class Lambdas {

	public static void main(String[] args) {
		List<String> names = new ArrayList<>();
		names.add("name1");
		names.add("name2");
		names.forEach(System.out::println);
	
	}
}

package javaInsight.interfaces;

public class Main {
	public static void main(String[] args) {

		Main main = new Main();
		PersonList pers = main.getPersons();

		boolean contains;

		ICustomObject obj = main.getPerson();
		contains = pers.contains(obj);

		System.out.println(String.format("Person exists : %s", Boolean.toString(contains)));
	}

	PersonList getPersons() {
		PersonList persons = new PersonList();
		persons.add(new Person(1, "Nume_1", "Prenume_1"));
		persons.add(new Person(2, "Nume_2", "Prenume_2"));
		persons.add(new Person(3, "Nume_3", "Prenume_3"));
		persons.add(new Person(4, "Nume_4", "Prenume_4"));
		return persons;
	}

	public Person getPerson() {
		return new Person(3, "Nume_3", "Prenume_3");
	}
}

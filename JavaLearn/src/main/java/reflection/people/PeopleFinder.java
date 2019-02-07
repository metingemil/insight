/*
 * $Id$
 */
package reflection.people;

import java.util.ArrayList;
import java.util.List;

public class PeopleFinder
{
    public List<Person> getPeople()
    {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Nume1", "Prenume1"));
        people.add(new Person("Nume2", "Prenume2"));
        people.add(new Person("Nume3", "Prenume3"));
        people.add(new Person("Nume4", "Prenume4"));
        return people;
    }
}

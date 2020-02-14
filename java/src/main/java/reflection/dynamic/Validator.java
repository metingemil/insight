/*
 * $Id$
 */
package reflection.dynamic;

import java.util.ArrayList;
import java.util.List;

import reflection.ReturnVal;
import reflection.dynamic.subpackage1.GuidGenerator;
import reflection.people.PeopleFinder;
import reflection.people.Person;

public class Validator
{
    public static ReturnVal enterMethod(String version)
    {
        ReturnVal ret = new ReturnVal();
        List<String> msgs = new ArrayList<>();
        ret.setValid(false);
        PeopleFinder finder = new PeopleFinder();
        for (Person person : finder.getPeople())
        {
            if (version.equals(person.getName()))
            {
                ret.setValid(true);
                msgs.add(person.getName() + " " + person.getSurname() + " : " + GuidGenerator.getGuid());
                ret.setList(msgs);
            }
        }
        return ret;
    }
}

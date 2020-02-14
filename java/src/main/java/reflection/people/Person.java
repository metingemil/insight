/*
 * $Id$
 */
package reflection.people;

public class Person
{
    public Person(String name, String surname)
    {
        mName = name;
        mSurname = surname;
    }
    
    private String mName;
    
    private String mSurname;

    public String getName()
    {
        return mName;
    }

    public void setName(String name)
    {
        this.mName = name;
    }

    public String getSurname()
    {
        return mSurname;
    }

    public void setSurname(String surname)
    {
        this.mSurname = surname;
    }
}

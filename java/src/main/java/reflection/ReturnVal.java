/*
 * $Id$
 */
package reflection;

import java.util.ArrayList;
import java.util.List;

public class ReturnVal
{
    private List<String> mList = new ArrayList<>();

    private boolean      valid;

    public List<String> getList()
    {
        return mList;
    }

    public void setList(List<String> list)
    {
        this.mList = list;
    }

    public boolean isValid()
    {
        return valid;
    }

    public void setValid(boolean valid)
    {
        this.valid = valid;
    }
}

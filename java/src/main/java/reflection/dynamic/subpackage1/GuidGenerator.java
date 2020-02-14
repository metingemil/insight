/*
 * $Id$
 */
package reflection.dynamic.subpackage1;

import java.util.UUID;

public class GuidGenerator
{
    public static String getGuid()
    {
        return UUID.randomUUID().toString();
    }
}

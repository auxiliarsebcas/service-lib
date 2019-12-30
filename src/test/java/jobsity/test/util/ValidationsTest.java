
package jobsity.test.util;

import org.junit.Assert;
import org.junit.Test;

public class ValidationsTest {
    @Test
    public void testValidationsShouldExist() {

        Assert.assertEquals("validations should return a valid result"
                , true
                , Validations.shouldExist("t1", "test").isValid()
        );

        Assert.assertEquals("validations should return a invalid result"
          , true
          , Validations.shouldExist(null, "test").isInvalid()
        );

        Assert.assertEquals("validations should return a invalid result with a error message"
                , "the field test should exist"
                , Validations.shouldExist(null, "test").fold( s -> s.head(), r->"" )
        );
    }
}

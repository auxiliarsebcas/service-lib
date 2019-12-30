package jobsity.test.util;

import cyclops.control.Option;
import cyclops.control.Validated;
import cyclops.data.NonEmptyList;

public class Validations {

    public static <V> Validated<String,V> shouldExist(V value, String valueName) {
        return
                Option
                  .ofNullable(value)
                  .fold(v -> Validated.valid(value), ()-> Validated.invalid( NonEmptyList.of("the field "+valueName+" should exist") ) );
    }

    public static <V> Validated<String,V> validateMandatory(Option<V> maybe, String valueName) {
        return
                maybe
                .fold( v -> Validated.valid(v)
                     , ()-> Validated.invalid( NonEmptyList.of("field "+valueName+" is mandatory") )
                );
    }
}

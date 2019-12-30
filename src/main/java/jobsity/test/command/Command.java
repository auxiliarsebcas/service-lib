package jobsity.test.command;


import cyclops.data.NonEmptyList;
import cyclops.monads.Witness.*;
import cyclops.control.Reader;
import cyclops.monads.transformers.EitherT;
import io.vavr.Tuple2;
import jobsity.test.Environment;
import jobsity.test.Event;
import jobsity.test.ServiceError;

import java.util.List;


public interface Command {
    public Reader<Tuple2<Environment,String>, EitherT<future, NonEmptyList<ServiceError>,List<Event>>> execute();
}

package jobsity.test.command;


import cyclops.monads.Witness.*;
import cyclops.control.Reader;
import cyclops.monads.transformers.EitherT;
import jobsity.test.Environment;
import jobsity.test.Event;
import jobsity.test.ServiceError;

import java.util.List;


public interface Command {
    public Reader<Environment, EitherT<future,List<ServiceError>,List<Event>>> execute();
}

package jobsity.test.util;

import com.google.protobuf.Timestamp;

import java.time.Instant;

public class TimeConverter {

    static Instant timeStampToInstant(Timestamp timestamp) {
        return
                Instant.ofEpochSecond(timestamp.getSeconds(), Integer.toUnsignedLong( timestamp.getNanos() ));
    }

    static Timestamp instantToTimeStamp(Instant instant){
        return
                Timestamp.newBuilder().setSeconds(instant.getEpochSecond()).setNanos(instant.getNano()).build();
    }
}

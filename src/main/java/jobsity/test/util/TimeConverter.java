package jobsity.test.util;

import com.google.protobuf.Timestamp;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class TimeConverter {
	//TODO add zone time
	  private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static Instant timeStampToInstant(Timestamp timestamp) {
        return
                Instant.ofEpochSecond(timestamp.getSeconds(), Integer.toUnsignedLong( timestamp.getNanos() ));
    }

    public static Timestamp instantToTimeStamp(Instant instant){
        return
                Timestamp.newBuilder().setSeconds(instant.getEpochSecond()).setNanos(instant.getNano()).build();
    }

    public static String instantToString(Instant instant) {
    	return fmt.format(instant);
    }

    public static Instant stringToInstant(String date) {
    	return fmt.parse(date,Instant::from);
    }
}

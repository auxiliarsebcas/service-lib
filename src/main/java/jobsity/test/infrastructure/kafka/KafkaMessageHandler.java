package jobsity.test.infrastructure.kafka;

import cyclops.control.Option;
import cyclops.data.NonEmptyList;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KafkaMessageHandler {
    public final NonEmptyList<KafkaHandle> handles;

    public KafkaMessageHandler(NonEmptyList<KafkaHandle> handles){
        this.handles = handles;
    }

    public List<Option<Map<TopicPartition, OffsetAndMetadata>>> process(ConsumerRecord record) {
    	//TODO improve the implementation to a good future implementation, this is not a good way
	    return
        handles
	        .filter( handle -> handle.getTopic().equals( record.topic() ) )
          .map( handle -> handle.onMessage( record.value(), record.offset() ) )
	        .map( futureResult -> futureResult.toOption() )
	        .<Option<Map<TopicPartition, OffsetAndMetadata>>>map( maybe ->
		        maybe.map( result ->
			        ( new HashMap() )
				        .put( new TopicPartition(record.topic(), record.partition() )
				        ,  new OffsetAndMetadata(record.offset(), record.leaderEpoch(), null) )
			      )
	        ).toList();
    }

}

package jobsity.test.infrastructure.kafka;

import cyclops.control.Option;
import cyclops.control.Try;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.net.InetAddress;
import java.time.Duration;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class KafkaConsumerHandler {
    private boolean shutdown = false;

    public Try<KafkaConsumer,Throwable> startConsumers(KafkaConfig config, KafkaMessageHandler hadler) {
        return
        generateProperties(config).flatMap( properties ->
          run(properties, hadler)
        );
    }

    public Try<Properties, Throwable> generateProperties(KafkaConfig config) {
        return
        Try.withCatch( () -> {
        	String servers =
		        config.hosts.stream()
			        .reduce("", (reduced, host) -> {
			        	if(reduced.equals("")) { return host + ":" + config.port ; }
					      else { return reduced + ","+  host + ":" + config.port;}
			        });

            Properties properties = new Properties();
            properties.put("client.id", InetAddress.getLocalHost().getHostName());
            properties.put("group.id", config.groupId);
            properties.put("bootstrap.servers", servers);
            return properties;
        }
         , Error.class
        );
    }

    public Try<KafkaConsumer, Throwable> run(Properties properties, KafkaMessageHandler kafkaMessageHandler) {
        return
        Try.withCatch(() -> {
            KafkaConsumer consumer = new KafkaConsumer<>(properties);
            consumer.subscribe(
                kafkaMessageHandler.handles.stream()
                  .map( handle -> handle.getTopic() )
                  .collect(Collectors.toList())
            );

            while (!this.shutdown) {
                ConsumerRecords records = consumer.poll( Duration.ofSeconds(10L) );

                StreamSupport.stream( records.spliterator(), true)
                  .map( record -> kafkaMessageHandler.process( (ConsumerRecord) record) )
                  .filter(result -> ((Option) result).isPresent())
                  .forEach(offset -> consumer.commitSync() );
            }
            //TODO is necessary modify the way in which the connection is closed.
            //consumer.close();

            return consumer;
          }
        , Error.class
        );
    }

}

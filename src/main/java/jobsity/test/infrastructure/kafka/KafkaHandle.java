package jobsity.test.infrastructure.kafka;

import cyclops.control.Future;

public interface KafkaHandle<T extends Object> {
    public Future onMessage(T message, long offset );
    public String getTopic();
}

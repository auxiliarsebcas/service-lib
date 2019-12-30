package jobsity.test.infrastructure.kafka;

import java.util.List;

public class KafkaConfig {
	public final String groupId;
	public final List<String> hosts;
	public final String port;

	public KafkaConfig(String groupId, List<String> hosts, String port){
		this.groupId = groupId;
		this.hosts   = hosts;
		this.port    = port;
	}
}



import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

public class MyPartition implements Partitioner {

	@Override
	public void configure(Map<String, ?> configs) {
		// TODO Auto-generated method stub

	}

	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		// TODO Auto-generated method stub
		List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
		int numPartitions = partitions.size();
		int partitionNum = 0;
		
		if (key == null) {
			Random random = new Random();
			int n = random.nextInt(numPartitions);
			System.out.println("--numPartitions:"+n );
			return n;
		} else {
			try {
				partitionNum = Integer.parseInt((String) key);
			} catch (Exception e) {
				partitionNum = key.hashCode();
			}
			int n = Math.abs(partitionNum % numPartitions);
			System.out.println("--numPartitions:"+n );
			return n;
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			Random random = new Random();
			System.out.println(random.nextInt(3));
		}

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}
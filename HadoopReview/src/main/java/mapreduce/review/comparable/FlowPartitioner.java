package mapreduce.review.comparable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-03 21:03
 */
public class FlowPartitioner extends Partitioner<FlowBean, Text> {
    @Override
    public int getPartition(FlowBean flowBean, Text text, int numPartitions) {
        String s = text.toString();
        String substring = s.substring(0, 3);
        int partition = 4;

        if (substring.equals("136")){
            partition = 0;
        }else if (substring.equals("137")){
            partition = 1;
        }else if (substring.equals("138")){
            partition = 2;
        }else if (substring.equals("139")){
            partition = 3;
        }

        return partition;
    }
}

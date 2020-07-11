package mypartitioner.flowbean;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-17 21:46
 */
public class MyPartitioner extends Partitioner<Text,FlowBean2> {

    @Override
    public int getPartition(Text text, FlowBean2 flowBean2, int numPartitions) {

        String string = text.toString();

        if (string.startsWith("136")){
            return 0;
        }else if (string.startsWith("137")){
            return 1;
        }else if (string.startsWith("138")){
            return 2;
        }else if (string.startsWith("139")){
            return 3;
        }else {
            return 4;
        }
    }
}

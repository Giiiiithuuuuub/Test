package mypartitioner.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-17 22:19
 */
public class WordCountPartitioner extends Partitioner<Text, IntWritable> {
    @Override
    public int getPartition(Text text, IntWritable intWritable, int numPartitions) {
        String string = text.toString();
        String[] s = string.split(" ");

        int partition = 0;
        for (String s1 : s) {
//            if (s1.charAt(0) <= 112 ){
//                partition = 0;
//            }else {
//                partition = 1;
//            }

            if (s1.matches("^[a-p].*")){
                partition = 0;
            }else {
                partition = 1;
            }
        }

        return partition;
    }
}

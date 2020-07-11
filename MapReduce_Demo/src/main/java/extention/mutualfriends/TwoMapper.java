package extention.mutualfriends;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-23 16:43
 */
public class TwoMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String string = value.toString();
        String[] split = string.split("\t");
        String[] persons = split[1].split(",");
        Arrays.sort(persons);

        for (int i = 0; i < persons.length - 1; i++) {
            for (int j = i + 1; j < persons.length; j++) {
                context.write(new Text(persons[i] + "-" + persons[j]),new Text(split[0]));
            }
        }
    }
}
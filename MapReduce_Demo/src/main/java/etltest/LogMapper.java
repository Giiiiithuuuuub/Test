package etltest;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-20 8:07
 */
public class LogMapper extends Mapper<LongWritable, Text,Text, NullWritable> {
    private Text outK = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();

        boolean result = parseLog(line, context);

        if (!result){
            return;
        }
        outK.set(line);
        context.write(outK,NullWritable.get());
    }

    private boolean parseLog(String line, Context context) {
        String[] s = line.split(" ");

        if (s.length > 11){
            context.getCounter("map","true").increment(1);//统计符合要求的数据量
            return true;
        }else {
            context.getCounter("map","false").increment(1);//统计不符合要求的数据量
            return false;
        }
    }
}

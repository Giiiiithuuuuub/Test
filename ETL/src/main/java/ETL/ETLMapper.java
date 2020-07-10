package ETL;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-29 20:20
 */
public class ETLMapper extends Mapper<LongWritable, Text,Text, NullWritable> {

    private Text outK = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String temp = ETLUtils.ETLData(value.toString());

        if (temp == null){
            return;
        }
        outK.set(temp);
        context.write(outK,NullWritable.get());
    }
}

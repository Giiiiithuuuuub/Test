package mapreduce.review.comparator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-03 21:18
 */
public class GoodsMapper extends Mapper<LongWritable, Text,GoodsBean, NullWritable> {

    private GoodsBean outK = new GoodsBean();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String s = value.toString();
        String[] split = s.split("\t");

        outK.setId(split[0]);
        outK.setPrice(Double.parseDouble(split[2]));

        context.write(outK,NullWritable.get());
    }
}

package mycompartor;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-18 20:15
 */
public class GoodsMapper extends Mapper<LongWritable, Text,GoodsBean, NullWritable> {

    //0000001	Pdt_01	222.8

    private GoodsBean outK = new GoodsBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String string = value.toString();
        String[] split = string.split("\t");

        outK.setId(split[0]);
        outK.setPdt(split[1]);
        outK.setPrice(Double.parseDouble(split[2]));

        context.write(outK,NullWritable.get());

    }
}

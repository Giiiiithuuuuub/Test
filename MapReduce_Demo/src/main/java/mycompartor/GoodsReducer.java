package mycompartor;

import mycomparable1.FlowBean;
import mycomparable1.FlowDriver;
import mycomparable1.FlowMapper;
import mycomparable1.FlowReducer;
import org.apache.commons.io.input.NullReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-18 20:15
 */
public class GoodsReducer extends Reducer<GoodsBean, NullWritable, GoodsBean, NullWritable> {

    private double money;
    @Override
    protected void reduce(GoodsBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
//        context.write(key,NullWritable.get());

        money = key.getPrice();

        for (NullWritable value : values) {

            if (key.getPrice() != money){
                return;
            }else {
                context.write(key,value);
            }
        }

    }
}

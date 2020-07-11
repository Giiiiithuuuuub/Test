package mapreduce.review.comparator;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-03 21:18
 */
public class GoodsReducer extends Reducer<GoodsBean, NullWritable,GoodsBean,NullWritable> {

    @Override
    protected void reduce(GoodsBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {


        context.write(key,values.iterator().next());//不遍历就是一条，也可以写NullWritable.get();
    }
}

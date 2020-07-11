package mapreduce.review.reducejoin;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-06 11:30
 */
public class OrderMapper extends Mapper<LongWritable, Text, IntWritable,OrderBean> {
    private IntWritable outK = new IntWritable();
    private OrderBean outV = new OrderBean();
    private String name;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {

        FileSplit inputSplit = (FileSplit) context.getInputSplit();
        name = inputSplit.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String s = value.toString();

        String[] split = s.split("\t");

        //这种直接用长度有局限性，因为如果两个表字段相等则无法区分，所以还是建议使用setup方法获得文件名的方式。
//        if (split.length == 3){
        if (name.contains("order")){
            outK.set(Integer.parseInt(split[1]));
            outV.setId(Integer.parseInt(split[0]));
            outV.setPid(Integer.parseInt(split[1]));
            outV.setAmount(Integer.parseInt(split[2]));
            outV.setPname("");
            outV.setFlag("order");
        }else {
            outK.set(Integer.parseInt(split[0]));
            outV.setId(0);
            outV.setPid(Integer.parseInt(split[0]));
            outV.setAmount(0);
            outV.setPname(split[1]);
            outV.setFlag("pd");
        }

        context.write(outK,outV);
    }
}

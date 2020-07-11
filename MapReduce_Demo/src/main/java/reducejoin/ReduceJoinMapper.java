package reducejoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-19 22:39
 */
public class ReduceJoinMapper extends Mapper<LongWritable, Text,Text,OrderBean> {

    private Text outK = new Text();
    private OrderBean outV = new OrderBean();
    private String name;

    //因为进来的切片是来自哪个文件不知道，所以没法进行split切分，所以需要获取文件名
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSplit inputSplit = (FileSplit) context.getInputSplit();
        name = inputSplit.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String string = value.toString();
        String[] split = string.split("\t");

        //没有数值的字段必须显式置空，不然默认是null，会报错
        if (name.contains("order")){
            outK.set(split[1]);
            outV.setId(split[0]);
            outV.setPid(split[1]);
            outV.setAmount(Integer.parseInt(split[2]));//valueOf和parseInt都行
            outV.setLable("order");
            outV.setpName("");
        }else {
            outK.set(split[0]);
            outV.setPid(split[0]);
            outV.setpName(split[1]);
            outV.setLable("pd");
            outV.setId("");
            outV.setAmount(0);
        }

        context.write(outK,outV);
    }
}

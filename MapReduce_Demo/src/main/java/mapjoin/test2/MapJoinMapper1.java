package mapjoin.test2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-20 7:02
 */
public class MapJoinMapper1 extends Mapper<LongWritable, Text, Text, NullWritable> {
    //为了将小表提前加载到内存中，需要使用setup，它会在map运行时只运行一次

    private Map<String, String> pdMap = new HashMap<>();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {

        //直接设置流，进行读取
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.newInstance(conf);

        FSDataInputStream in = fs.open(new Path("E:\\BigData\\Exercise\\inputpd\\pd.txt"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = reader.readLine()) != null){
            String[] split = line.split("\t");
            pdMap.put(split[0],split[1]);
        }
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //map用来读取大表的数据
        String string = value.toString();
        String[] split = string.split("\t");

        String pName = pdMap.get(split[1]);
        String result = split[0] + "\t" + pName + "\t" + split[2];

        context.write(new Text(result), NullWritable.get());

    }
}

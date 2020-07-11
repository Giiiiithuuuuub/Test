package mapjoin;

import mapjoin.test2.MyCounter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.kerby.config.Conf;

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
public class MapJoinMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    //为了将小表提前加载到内存中，需要使用setup，它会在map运行时只运行一次

    private Map<String, String> pdMap = new HashMap<>();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //可以自己设置个流，将数据加载进来，也可以使用API提供的方法。

        //计数器
//        context.getCounter("MapJoin","setup").increment(1);
        context.getCounter(MyCounter.SETUP).increment(1);

        //1. 获取缓存文件
        URI[] cacheFiles = context.getCacheFiles();
        URI cacheFile = cacheFiles[0];
        //2. 获取fs对象
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.newInstance(conf);
        //3. 获取流
        FSDataInputStream in = fs.open(new Path(cacheFile));
        //4. 读取数据
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = reader.readLine()) != null) {
            //5. 数据切割，放到Map中，就是放到内存中
            String[] split = line.split("\t");
            pdMap.put(split[0], split[1]);
        }

    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //计数器
        context.getCounter("MapJoin","map").increment(1);
        //map用来读取大表的数据
        String string = value.toString();
        String[] split = string.split("\t");

        String pName = pdMap.get(split[1]);
        String result = split[0] + "\t" + pName + "\t" + split[2];

        context.write(new Text(result), NullWritable.get());

    }
}

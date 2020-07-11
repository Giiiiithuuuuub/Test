package mapreduce.review.mapjoin;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-07 10:32
 */
public class MapJoinMapper extends Mapper<LongWritable, Text,Text, NullWritable> {
    private Text outK = new Text();
    private Map<String,String> map = new HashMap<>();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //1. get chche-file uri
        URI[] cacheFiles = context.getCacheFiles();
        //2. get path for stream
        Path path = new Path(cacheFiles[0]);
        //3. get filesystem
        FileSystem fs = FileSystem.get(context.getConfiguration());
        //4. get inputstream
        FSDataInputStream open = fs.open(path);
        //5. wrap a bufferedreader for the readline method
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(open));
        //6. traversal
        String line;
        while (StringUtils.isNotEmpty((line = bufferedReader.readLine()))){
            String[] split = line.split("\t");
            map.put(split[0],split[1]);

        }

        //7. close the stream
        IOUtils.closeStream(bufferedReader);

    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");
//        String concat = split[0].concat(map.get(split[1])).concat(split[2]);
        String concat= split[0] + "\t" + map.get(split[1]) + "\t" + split[2];
        outK.set(concat);

        context.write(outK,NullWritable.get());
    }
}

package mapreduce.review.top10;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-20 16:57
 */
public class TopMapper extends Mapper<LongWritable, Text,Text,TopBean> {
    private Text outK = new Text();
    private TreeMap<TopBean,String> treeMap = new TreeMap<>();
    private TopBean bean;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String string = value.toString();
        String[] split = string.split("\t");
        bean = new TopBean();
        int u = Integer.parseInt(split[1]);
        int d = Integer.parseInt(split[2]);
        int s = Integer.parseInt(split[3]);

        bean.setUpFlow(u);
        bean.setDownFlow(d);
        bean.setSumFlow(s);
        treeMap.put(bean,split[0]);


    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        //将遍历集合写在cleanup方法中，因为这个方法在map方法全部执行完之后只执行一次
//        Set<TopBean> topBeans = treeMap.keySet();
//        for (TopBean topBean : topBeans) {
//            outK.set(treeMap.get(topBean));
//            context.write(outK,topBean);
//        }
        treeMap.forEach((x,y) -> {
            outK.set(y);
            try {
                context.write(outK,x);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}

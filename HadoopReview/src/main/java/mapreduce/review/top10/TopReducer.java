package mapreduce.review.top10;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-20 16:57
 */
public class TopReducer extends Reducer<Text, TopBean,Text,TopBean> {

    private Text outK = new Text();
    private TreeMap<TopBean,String> treeMap = new TreeMap<>();
    private TopBean bean;

    @Override
    protected void reduce(Text key, Iterable<TopBean> values, Context context) throws IOException, InterruptedException {
        int totalUp = 0;
        int totalDown = 0;
        int totalSum = 0;
        for (TopBean value : values) {
            totalSum += value.getSumFlow();
            totalUp += value.getUpFlow();
            totalDown += value.getDownFlow();
        }

        bean = new TopBean();
        bean.setUpFlow(totalUp);
        bean.setDownFlow(totalDown);
        bean.setSumFlow(totalSum);
        treeMap.put(bean,key.toString());

        if (treeMap.size() > 10){
            treeMap.remove(treeMap.lastKey());
        }

    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
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

package mapreduce.review.reducejoin;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-06 11:30
 */
public class OrderReducer extends Reducer<IntWritable,OrderBean,OrderBean, NullWritable> {
    private List<OrderBean> list = new ArrayList<>();//用来封装order表中的元素，因为不止一条
    private OrderBean temp = new OrderBean();//用来封装pd表中的元素，只有一条所以用个类就行

    @Override
    protected void reduce(IntWritable key, Iterable<OrderBean> values, Context context) throws IOException, InterruptedException {

        for (OrderBean value : values) {
            if ("order".equals(value.getFlag())){
                //因为底层实现是对象固定，key值修改，所以不能直接用add方法添加，需要使用深拷贝,每次创建一个对象，
                //从而可以有效避免仅仅覆盖
                OrderBean bean = new OrderBean();
                //使用工具类，事半功倍
                try {
                    BeanUtils.copyProperties(bean,value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

                list.add(bean);
            }else {
                //因为pt表数据只有一条，所以声明在外面就行
                try {
                    BeanUtils.copyProperties(temp,value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

            //遍历来合并

            for (OrderBean bean : list) {
                bean.setPname(temp.getPname());

                context.write(bean,NullWritable.get());
            }

            //尖叫提示，就是记得每次清空集合
            list.clear();
        }
    }
}

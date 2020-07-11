package reducejoin;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-19 22:39
 */
public class ReduceJoinReducer extends Reducer<Text,OrderBean,OrderBean, NullWritable> {

    private ArrayList<OrderBean> orders = new ArrayList<>();//用来封装order对象
    private OrderBean temp = new OrderBean();//用来封装pd对象，因为这个对象肯定只有一个，所以声明到外面可行。

    @Override
    protected void reduce(Text key, Iterable<OrderBean> values, Context context) throws IOException, InterruptedException {
        //思路：将进入reduce的同一组数据，order类型的数据封装到集合中，pd类型的数据封装到对象中，
        // 然后遍历集合，将对象中的pName字段进行添加。

        for (OrderBean value : values) {
            if ("order".equals(value.getLable())){
                //如果简单的进行orders.add()操作，因为OrderBean对象时唯一的，每次迭代其实就是改变对象在堆空间的值，
                //这样直接添加每次都是同一个对象，只会起到覆盖的作用，所以需要使用深拷贝
                //深拷贝：完全复制一个对象，原对象改变自己不变。浅拷贝：复制索引，原对象改变跟着改变

                OrderBean tempOrder = new OrderBean();//每次创建一个对象，所以每个对象不一样，则可以实现上述要求
                try {
                    BeanUtils.copyProperties(tempOrder,value);//使用工具类实现直接复制，否则需要挨个属性进行设置！
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                orders.add(tempOrder);//将复制过来的对象依次添加到集合中
            }else {
                try {
                    BeanUtils.copyProperties(temp,value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        /*for (OrderBean order : orders) {
            order.setpName(temp.getpName());//将对象中的pName依次添加到集合中的OrderBean对象中

            context.write(order,NullWritable.get());//依次写出
        }*/

        Object[] objects = orders.toArray();
        for (int i = objects.length - 1; i >= 0; i--) {
            if (objects[i] instanceof OrderBean){
                OrderBean object = (OrderBean) objects[i];
                object.setpName(temp.getpName());
                context.write(object,NullWritable.get());
            }
        }

        orders.clear();//每一次要清空集合，让下一组数据添加



    }
}

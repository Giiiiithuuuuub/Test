package com.atguigu.test.hive;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-28 21:35
 */

//需求：将指定的字符串通过指定的分隔符，进行拆分，返回多行数据
    //扩展：如果传入两个参数，则分割为一列，如果传过来三个参数，则分割为两列
public class MyUDTF extends GenericUDTF {

    private ArrayList<String> list = new ArrayList<>();//用来封装输出的数据，因为每个列的类型和名称为集合，
                //用集合封装结果，使得每个集合的索引位置相对应。如果产生的结果是一列，那么索引0对应一个列的名字、类型、
                // 和产生的值，如果产生了两列，则放到索引位置1，所以要使用集合来封装结果

    /*
    * @Description:    这个方法用来指定返回值的列的名称以及列的值的类型
    * @author:         Yu HaiFeng
    * @email:          15548737663@163.com
    * @createDate:     2020/4/28 21:40
     * @param argOIs
    * @return:
    */
    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        //这个用来指定输出的列的名字，由于可能不止一列，所以使用集合的形式
        ArrayList<String> fieldsName = new ArrayList<>();
        //用来指定输出的列的类型
        ArrayList<ObjectInspector> fieldsOIs = new ArrayList<>();

        fieldsName.add("ColumnOne");//第一列名字
        fieldsName.add("ColumnTwo");//第二列名字

        fieldsOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);//第一列值类型
        fieldsOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);//第二列值类型

        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldsName,fieldsOIs);
    }

    /*
    * @Description:    这个方法用来写主要的业务逻辑
    * @author:         Yu HaiFeng
    * @email:          15548737663@163.com
    * @createDate:     2020/4/28 21:37
    * @param args
    * @return:         void
    */
    @Override
    public void process(Object[] args) throws HiveException {
        //先进行写简单的判断
        if (args.length <2 || args == null || args.length > 3){
            throw new HiveException("InputArgsIllegalException");
        }

        if (args.length == 2){
            String s = args[0].toString();
            String[] split = s.split(args[1].toString());
            for (String s1 : split) {
                //因为集合是复用的，所以先将封装结果的集合清空
                list.clear();
                list.add(s1);//每次清空保证输出的数据都在集合索引0的位置，将来都会在同一列
                //将结果写出
                forward(list);
            }
        }else {
            String s= args[0].toString();
            String[] splitOut = s.split(args[1].toString());
            for (String s1 : splitOut) {
                //同样，清空集合
                list.clear();
                String[] splitIn = s1.split(args[2].toString());
                for (String s2 : splitIn) {
                    list.add(s2);//由于产生两列，所以每个元素放到不同的索引位置，将来出现在前后两个列
                }
                //写出
                forward(list);
            }

        }

    }

    /*
    * @Description:    这个方法用来关闭需要关闭的东西，没有则不写
    * @author:         Yu HaiFeng
    * @email:          15548737663@163.com
    * @createDate:     2020/4/28 21:36
    * @param
    * @return:         void
    */

    @Override
    public void close() throws HiveException {

    }
}

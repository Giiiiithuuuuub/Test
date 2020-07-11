package com.atguigu.myfun;

import com.google.gson.JsonArray;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-06-28 20:08
 */
public class ExplodeJsonArray extends GenericUDTF {
    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {

        List<? extends StructField> allStructFieldRefs = argOIs.getAllStructFieldRefs();//拿到输入结构体中每个结构的引用
        //拿到这个集合之后，可以用来校验输入参数的个数是否是合法
        if(allStructFieldRefs.size() != 1){
            throw new UDFArgumentException("输入的参数个数只能为1");
        }

        //拿到第一个元素 -- 拿到对象检查器 -- 拿到参数类型
        //这里参数类型就是hive中的参数类型
        String typeName = allStructFieldRefs.get(0).getFieldObjectInspector().getTypeName();

        if (!"string".equals(typeName)){
            throw new UDFArgumentTypeException(0,"参数类型只能为String");
        }
        //设置返回值的列名和列的类型，可能有多列，所以用集合，并且列名和列的类型要一一对应（ArrayList是有序的）
        ArrayList<String> fieldNames = new ArrayList<>();
        ArrayList<ObjectInspector> fieldOIs = new ArrayList<>();

        fieldNames.add("items");//这个无所谓，因为后期炸开之后，都会自己设置列名，就是侧写表那个 as col_name
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);

        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames,fieldOIs);
    }

    //执行函数逻辑的方法
    @Override
    public void process(Object[] args) throws HiveException {
        String jsonArray = args[0].toString();
        //因为我们的目的就是将这个json数组每一个元素输出，这里string类型肯定不行，所以要转换为Json数组
        JSONArray array = new JSONArray(jsonArray);
        //遍历，扔出去
        for (int i = 0; i < array.length(); i++) {
            //因为可能返回多个列，所以要求forward必须为集合或者数组
            String[] str = new String[1];

            str[0] = array.getString(i);//json数组获取每一个数组元素

            forward(str);
        }
    }

    @Override
    public void close() throws HiveException {}
}

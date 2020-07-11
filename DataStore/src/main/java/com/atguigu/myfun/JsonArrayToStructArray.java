package com.atguigu.myfun;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-06-28 22:51
 */
public class JsonArrayToStructArray extends GenericUDF {
    @Override
    public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
        ArrayList<String> fieldNames = new ArrayList<>();
        ArrayList<ObjectInspector> fieldOIs = new ArrayList<>();

        if (arguments.length < 3) {
            throw new UDFArgumentException("至少需要三个参数");//对输入参数的个数判断
        }

        for (int i = 0; i < arguments.length; i++) {//对输入参数类型判断

            if (!"string".equals(arguments[i].getTypeName())) {
                throw new UDFArgumentTypeException(i, "第" + i + "个参数应该为String类型");
            }
        }

        for (int i = (arguments.length + 1) / 2; i < arguments.length; i++) {

            if (!(arguments[i] instanceof ConstantObjectInspector)) {//如果不是常量，则报错

                throw new UDFArgumentTypeException(i, "类型不匹配");
            }

            //如果传入的是常量，则可以获取到这个常量的值
            String field = ((ConstantObjectInspector) arguments[i]).getWritableConstantValue().toString();

            String[] list = field.split(":");
            fieldNames.add(list[0]);
            //通过上面的循环，最终返回结构体的指定的类型检查器
            switch (list[1]) {
                case "string":
                    fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
                    break;
                case "boolean":
                    fieldOIs.add(PrimitiveObjectInspectorFactory.javaBooleanObjectInspector);
                    break;
                case "tinyint":
                    fieldOIs.add(PrimitiveObjectInspectorFactory.javaByteObjectInspector);
                    break;
                case "smallint":
                    fieldOIs.add(PrimitiveObjectInspectorFactory.javaShortObjectInspector);
                    break;
                case "int":
                    fieldOIs.add(PrimitiveObjectInspectorFactory.javaIntObjectInspector);
                    break;
                case "bigint":
                    fieldOIs.add(PrimitiveObjectInspectorFactory.javaLongObjectInspector);
                    break;
                case "float":
                    fieldOIs.add(PrimitiveObjectInspectorFactory.javaFloatObjectInspector);
                    break;
                case "double":
                    fieldOIs.add(PrimitiveObjectInspectorFactory.javaDoubleObjectInspector);
                    break;
                default:
                    throw new UDFArgumentException("json_array_to_struct_array 不支持" + list[1] + "类型");
            }

        }

        return ObjectInspectorFactory.getStandardListObjectInspector(ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldOIs));
    }

    @Override
    public Object evaluate(DeferredObject[] arguments) throws HiveException {
        //evaluate中返回结构体，只需要返回struct中的value即可，即array或者list，因为initialize中已经规定了返回的列名
        //我们这里需要返回array<struct>，所以这里就需要返回二维数组，或者List<List>即可
        if(arguments[0].get() == null){//判断输入参数
            return null;
        }

        String strArray = arguments[0].get().toString();//获取第一个输入值
        JSONArray jsonArray = new JSONArray(strArray);//明确其为json数组，则封装为json数组
        ArrayList<Object> array = new ArrayList<>();//外层集合

        for (int i = 0; i < jsonArray.length(); i++) {//遍历这个json数组
            ArrayList<Object> struct = new ArrayList<>();//内层存放struct的value的数组

            JSONObject jsonObject = jsonArray.getJSONObject(i);//获取每一个json数组中的json对象
            for (int j = 1; j < (arguments.length + 1)/2 ; j++) {//遍历输入的参数的指定的列名
                String key = arguments[j].get().toString();//获取这个列名的字符串
                if(jsonObject.has(key)){ //判断json中有没有指定的这个key
                    struct.add(jsonObject.get(key));//获取这个值，添加到内层数组中
                }else {
                    struct.add(null);//保证不存在就为null，从而满足我们定义的输出结构
                }
            }

            array.add(struct);//内层集合放到外层
        }

        return array;//返回外层
    }

    //看执行计划explain的时候展示的字符串，通常返回函数名即可，不实现也没事
    @Override
    public String getDisplayString(String[] children) {
        //通用的返回方法
        return getStandardDisplayString("json_array_to_struct_array",children);
        

    }
}

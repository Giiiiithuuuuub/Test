package com.atguigu.myfun;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.AbstractPrimitiveJavaObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-06-29 18:13
 */
public class JsonArrayToStructArrayTest extends GenericUDF {
    @Override
    public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
        if (arguments.length < 3){
            throw new UDFArgumentException("至少需要三个参数");
        }

        for (int i = 0; i < arguments.length; i++) {
            if (!"string".equals(arguments[i].getTypeName())){
                throw new UDFArgumentTypeException(0,"第" + i + 1 + "个参数应该为string类型");
            }
        }

        ArrayList<String> fieldNames = new ArrayList<>();
        ArrayList<ObjectInspector> fieldOIs = new ArrayList<>();

        for (int i = (arguments.length + 1)/2; i < arguments.length; i++) {
            if (!(arguments[i] instanceof ConstantObjectInspector)){
                throw new UDFArgumentTypeException(i,"输入参数类型不匹配");
            }

            String field = ((ConstantObjectInspector) arguments[i]).getWritableConstantValue().toString();

            String[] split = field.split(":");
            fieldNames.add(split[0]);

            AbstractPrimitiveJavaObjectInspector primitiveJavaObjectInspector =
                    PrimitiveObjectInspectorFactory.getPrimitiveJavaObjectInspector(
                            TypeInfoFactory.getPrimitiveTypeInfo(split[1]));

            fieldOIs.add(primitiveJavaObjectInspector);



        }

        return ObjectInspectorFactory.getStandardListObjectInspector(ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames,fieldOIs));

    }

    @Override
    public Object evaluate(DeferredObject[] arguments) throws HiveException {

        if (arguments[0].get() == null){
            return null;
        }

        ArrayList<Object> array = new ArrayList<>();
        ArrayList<Object> struct = new ArrayList<>();

        String temp = arguments[0].get().toString();

        JSONArray jsonArray = new JSONArray(temp);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            for (int j = 0; j < (arguments.length + 1)/2; j++) {

                String key = arguments[j].get().toString();

                if (jsonObject.has(key)){
                    struct.add(jsonObject.get(key));
                }else {
                    struct.add(null);
                }
            }

            array.add(struct);
        }

        return array;
    }

    @Override
    public String getDisplayString(String[] children) {
        return getStandardDisplayString("json_array_to_struct_array",children);
    }
}

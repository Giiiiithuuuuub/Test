package com.atguigu.test.hive;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-28 21:05
 */
public class MyUDF extends GenericUDF {

    /**
    * @Description:    第一个方法主要是用来对传进来的参数进行一些判断，并对返回值进行约定
    * @author:         Yu HaiFeng
    * @email:          15548737663@163.com
    * @createDate:     2020/4/28 21:06
    * @param           objectInspectors
    * @return:         org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector
    */
    @Override
    public ObjectInspector initialize(ObjectInspector[] objectInspectors) throws UDFArgumentException {
        //对传进来的参数进行简单的校验
        if (objectInspectors.length != 1 || objectInspectors == null){
            throw new UDFArgumentLengthException("InputArgsIllegalException");
        }
        if (!objectInspectors[0].getCategory().equals(ObjectInspector.Category.PRIMITIVE)){
            throw new UDFArgumentTypeException(0,"InputArgsTypeMismatchException");
        }
        //因为是基本数据类型，所以通过基本数据类型的工厂类，约定返回Int类型的ObjectInspector类
        return PrimitiveObjectInspectorFactory.javaIntObjectInspector;
    }

    /**
    * @Description:    这个方法用来实现业务逻辑
    * @author:         Yu HaiFeng
    * @email:          15548737663@163.com
    * @createDate:     2020/4/28 21:08
    * @param deferredObjects
    * @return:         java.lang.Object
    */
    @Override
    public Object evaluate(DeferredObject[] deferredObjects) throws HiveException {
        Object o = deferredObjects[0].get();
        if (o == null) return 0;
        return o.toString().length();
    }

    /**
    * @Description:    这个方法主要是用于出错之后的备用，一般不写
    * @author:         Yu HaiFeng
    * @email:          15548737663@163.com
    * @createDate:     2020/4/28 21:08
    * @param strings
    * @return:         java.lang.String
    */
    @Override
    public String getDisplayString(String[] strings) {
        return "";
    }
}

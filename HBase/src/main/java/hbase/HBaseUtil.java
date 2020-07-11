package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-06-21 1:04
 */
public class HBaseUtil {

    public static Connection connection;

//    static {
//        try {
//            Configuration configuration = HBaseConfiguration.create();
//            configuration.set("hbase.zookeeper.quorum", "localhost108,localhost109,localhost110");
//
//            connection = ConnectionFactory.createConnection(configuration);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void createNameSpace(String nameSpace) {
        Admin admin = null;
        try {
            admin = connection.getAdmin();
            NamespaceDescriptor descriptor = NamespaceDescriptor.create(nameSpace).build();
            admin.createNamespace(descriptor);
        } catch (NamespaceExistException e) {
            System.err.println("命名空间已经存在");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (admin != null) {

                try {
                    admin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void createTable(String nameSpace, String tableName, String... families) throws IOException {
        //如果列族少于1，则直接结束
        if (families.length < 1) {
            System.out.println("Wrong!");
            return;
        }

        //这种try-with-resources语法，是因为如果需要关闭的资源实现了AutoClosed类或者子类，那么try结构结束之后，会自动关闭资源
        try (Admin admin = connection.getAdmin()) {

            if (admin.tableExists(TableName.valueOf(nameSpace, tableName))) {//判断表是否存在
                System.out.println("表存在");
                return;
            }
            //先获取builder对象
            TableDescriptorBuilder builder = TableDescriptorBuilder.newBuilder(TableName.valueOf(nameSpace, tableName));
            //放列族
            for (String family : families) {//放列族也需要列族Descriptor

                //需要列族名为byte数组，所以使用工具类转换
                ColumnFamilyDescriptorBuilder newBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(family));
                newBuilder.setMaxVersions(5);//这里通过列族的builder类，也可以设置列族的其他属性
                builder.setColumnFamily(newBuilder.build());//这里放列族的Descriptor
            }

            admin.createTable(builder.build());//表的Descriptor
        }

    }

    public void deleteTable(String nameSpace, String tableName) throws IOException {

        try (Admin admin = connection.getAdmin()) {

            if (!admin.tableExists(TableName.valueOf(tableName))) {
                System.out.println("表不存在");
                return;
            }
            //同shell客户端一样，想要删表，需要先下线
            admin.disableTable(TableName.valueOf(nameSpace, tableName));
            admin.deleteTable(TableName.valueOf(nameSpace, tableName));

        }
    }

    public void putCell(String nameSpace, String tableName, String rowKey, String family, String column, String value) throws IOException {
        try (Table table = connection.getTable(TableName.valueOf(nameSpace, tableName))) {

            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes(family), Bytes.toBytes(column), Bytes.toBytes(value));

            table.put(put);

        }
    }

    public static void getCell(String nameSpace, String tableName, String rowKey, String family, String column) {
        try (Table table = connection.getTable(TableName.valueOf(nameSpace, tableName))) {
            Get get = new Get(Bytes.toBytes(rowKey));//如果只添加rowKey，不添加column，则返回一整行
            get.addColumn(Bytes.toBytes(family),Bytes.toBytes(column));//返回一列信息
            get.addFamily(Bytes.toBytes(family));//这个方法是返回一个列族
            Result result = table.get(get);
            //遍历方式：rawCells,如果返回多列数据，则遍历每一个列
            Cell[] cells = result.rawCells();

            for (Cell cell : cells) {
                //这里如果用cell.getFamilyArray/getQualifierArray/getValueArray，其实返回值是一样的，返回的是整个Cell数据：列名、列族、值
                //正确的获取方法：用CellUtil工具类的clonexxx;
                System.out.println(Bytes.toString(CellUtil.cloneFamily(cell)));
            }

            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void scanRows(String nameSpace, String tableName, String startRow,String stopRow) throws IOException {
        Table table = connection.getTable(TableName.valueOf(nameSpace,tableName));
        ResultScanner scanner = null;
        try{
            Scan scan = new Scan();
            //withxxx之后返回的还是scan对象，所以可以练血withxxx
            Scan resutlScan = scan.withStartRow(Bytes.toBytes(startRow)).withStopRow(Bytes.toBytes(stopRow));
            scanner = table.getScanner(resutlScan);//resultScanner,其实相当于一个结果集
            //继续进行遍历:因为ResultScanner继承了Iterable接口，所以可以用迭代器，也就是可以用增强for
            for (Result result : scanner) {//这是一个result结果集
                Cell[] cells = result.rawCells();//每一个结果集获取cell集合

                for (Cell cell : cells) {//遍历每一个cell

                    System.out.println(Bytes.toString(CellUtil.cloneFamily(cell)));
                }
            }

        } finally {//其实scanner和table都继承了AutoClosed，可以使用try-with-resources方法进行自动关闭
            assert scanner != null; //这个用法相当于if(scanner != null)
                scanner.close();//因为scanner获取很多数据，这些数据不是一批发过来的，所以说相当于scanner也是一个连接，所以需要关闭

            table.close();
        }
    }

    public static void deleteCell(String nameSpace, String tableName, String rowKey, String family, String column){

        try(Table table = connection.getTable(TableName.valueOf(nameSpace,tableName))){
            Delete delete = new Delete(Bytes.toBytes(rowKey));
            Delete resultDelete = delete.addColumn(Bytes.toBytes(family), Bytes.toBytes(column));

            table.delete(resultDelete);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

//        getCell("default","student","1001","info","sex");

        String result = String.format("%02d",5);
        System.out.println(result);
    }

}

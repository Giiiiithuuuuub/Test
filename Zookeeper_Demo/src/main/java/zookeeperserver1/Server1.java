package zookeeperserver1;

import com.sun.tracing.dtrace.ArgsAttributes;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-22 19:11
 */
public class Server1 {

    private String connectString = "localhost103:2181,localhsot104:2181,localhost105:2181";
    private int sessionTimeOut = 10000;
    private ZooKeeper zkServer;
    private String parentZNode = "/servers";//用来将服务器信息注册到该节点

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        Server1 server = new Server1();
        //1. 初始化:创建zk对象
        server.init();
        //2. 判断节点是否存在，如果不存在就创建
        server.isExist();
        //3. 写入数据，将服务器注册信息都写入上述节点
        server.register(args);
        //4. 保证线程不结束
        Thread.sleep(Long.MAX_VALUE);
    }

    private void register(String...args) throws KeeperException, InterruptedException {
        zkServer.create(parentZNode + "/" + args[0],args[1].getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        System.out.println("===========" + args[0] + " is online now! ============" );
    }

    private void isExist() throws KeeperException, InterruptedException {
        Stat stat = zkServer.exists(parentZNode, false);
        if (stat == null){
            zkServer.create(parentZNode,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        }
    }

    private void init() throws IOException {
        zkServer = new ZooKeeper(connectString, sessionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
            }
        });
    }

}

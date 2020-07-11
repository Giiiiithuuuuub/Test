package zookeeperserver;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-22 9:15
 */
public class Server {
    private String connectionString = "localhost103:2181,localhsot104:2181,localhost105:2181";
    private int sessionTimeOut = 10000;
    private ZooKeeper zkClient = null;
    private String parentNode = "/servers";
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        //1. 初始化zk客户端对象
        server.init();
        //2. 判断zk中存储服务器信息的节点znode是否存在
        server.parentNodeExists();
        //3. 将服务器的信息写入到zk中
        server.writeServer(args);
        //4. 保持线程不结束
        Thread.sleep(Long.MAX_VALUE);
    }
    /**
    * @Description:
    * @author:         Yu HaiFeng
    * @email:          15548737663@163.com
    * @createDate:     2020/4/22 9:24
    * @param args      server的名字和信息
    * @return:         void
    */
    private void writeServer(String[] args) throws KeeperException, InterruptedException {
        zkClient.create(parentNode + "/"+ args[0],args[1].getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        System.out.println(args[0] +"  is online 。。。");
    }

    private void parentNodeExists() throws KeeperException, InterruptedException {
        Stat stat = zkClient.exists(parentNode, false);
        if (stat == null){
            //创建节点
            zkClient.create(parentNode,"servers".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        }
    }

    private void init() throws IOException {
        zkClient = new ZooKeeper(connectionString, sessionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
            }
        });
    }
}

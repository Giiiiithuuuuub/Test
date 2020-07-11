package zookeeperserver1;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-22 19:11
 */
public class Client1 {
    private String connectionString = "localhost103:2181,localhsot104:2181,localhost105:2181";
    private int sessionTimeOut = 10000;
    private ZooKeeper zkClient;
    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        Client1 client1 = new Client1();

        //1. 初始化客户端
        client1.init();
        //2. 从节点读数据（监听节点）
        client1.readZNode();
        //3. 保持线程不结束
        Thread.sleep(Long.MAX_VALUE);
    }

    private void readZNode() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/servers", new Watcher() {
            @Override
            //当在线的服务器发生变动后，会执行该方法，我们需要重新获取最新的服务器信息：再去读取一次,从而实现循环监听
            public void process(WatchedEvent event) {//这里的递归可以理解为是阻塞式的，只有当节点变动了，才会执行process方法，才会递归一次，
                                                    // 如果没变动，就会停在这儿，不会无限递归
                try {
                    readZNode();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //将监听结果实时打印到控制台
        System.out.println("Current on Line Servers :" + children);
    }

    private void init() throws IOException {
        zkClient = new ZooKeeper(connectionString, sessionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
            }
        });
    }
}

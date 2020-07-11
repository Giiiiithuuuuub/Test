package zookeeperserver;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-22 9:36
 */
public class Client {
    private String connectiongString = "localhost103:2181,localhost104:2181,localhost105:2181";
    private int sessionTimeOut = 10000;
    private ZooKeeper zkClient;
    private String parentNode = "/servers";

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        Client client = new Client();
        //1. 初始化zk客户端对象
        client.init();

        //2. 获取服务器的信息
        client.readServers();
        //3. 保持线程不结束，实时感知
        Thread.sleep(Long.MAX_VALUE);
    }

    private void readServers() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren(parentNode, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                //当在线的服务器发生变动后，会执行该方法，我们需要重新获取最新的服务器信息：再去读取一次,从而实现循环监听
                try {
                    readServers();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //将当前在线的服务器信息打印到控制台
        System.out.println("Current on Line Servers :" + children);
    }

    private void init() throws IOException {
        zkClient = new ZooKeeper(connectiongString, sessionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
            }
        });
    }
}

package zookeeper.test;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-22 6:49
 */
public class ZookeeperTest {
    String connect = "localhost103:2181";
    int sessionTimeout = 10000;
    ZooKeeper zooKeeper;

    @Before
    public void before() throws IOException {
         zooKeeper = new ZooKeeper(connect, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
            }
        });
    }

    @After
    public void after() throws InterruptedException {
        zooKeeper.close();
    }

    @Test
    public void testGetChild() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {//监听效果只管一次
                System.out.println("子节点发生变化。。。。。");
            }
        });
        for (String child : children) {
            System.out.println(child);
        }
        Thread.sleep(Long.MAX_VALUE);//用来休眠线程，从而使监听生效，否则方法都结束了监听器也就没用了
    }

    @Test
    public void testCreateChild() throws KeeperException, InterruptedException {
        //分别表示：路径，内容，权限，创建什么样的节点
        String s = zooKeeper.create("/atguigu/zook", "I am tired".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("s" + s);
    }

    @Test
    public void testGetData() throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists("/atguigu/java0000000000", false);
        if (stat == null){
            System.out.println("路径不存在");
        }else {
            byte[] data = zooKeeper.getData("/atguigu/java0000000000", new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("内容发生变化");
                }
            }, stat);
            System.out.println(new String(data));
            Thread.sleep(Long.MAX_VALUE);
        }
    }
    
    @Test
    public void testSetData() throws KeeperException, InterruptedException {
        Stat s = zooKeeper.exists("/atguigu/zook", false);
        Stat stat = zooKeeper.setData("/atguigu/zook", "I am IornMan".getBytes(), s.getVersion());
        System.out.println(stat);
    }

    @Test
    public void testDelete() throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists("/atguigu", false);

        if (stat == null){
            System.out.println("节点不存在");
        }else {
            zooKeeper.delete("/atguigu",stat.getVersion());//delete删除空节点，节点下面有内容可以，但是不能有节点
            System.out.println("删除成功");
            //org.apache.zookeeper.KeeperException$NotEmptyException: KeeperErrorCode = Directory not empty for /atguigu
        }
    }


}

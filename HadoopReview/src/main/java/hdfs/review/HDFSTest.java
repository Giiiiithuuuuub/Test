package hdfs.review;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-03 10:39
 */
public class HDFSTest {
    private FileSystem fs;

    @Before
    public void before() throws IOException, InterruptedException {
        URI uri = URI.create("hdfs://localhost103:9820");
        Configuration conf = new Configuration();
        fs = FileSystem.get(uri,conf,"atguigu");
    }

    @After
    public void after() throws IOException {
        fs.close();
    }

    @Test
    public void testMkdir() throws IOException {
        fs.mkdirs(new Path("/HDFSReviewTest"));
    }
    
    @Test
    public void testCopyFromLocal() throws IOException {
        fs.copyFromLocalFile(false,true,new Path("E:\\BigData\\Exercise\\inputwordcount\\hello.txt"),new Path("/HDFSReviewTest"));
    }

    @Test
    public void testList() throws IOException {
        RemoteIterator<LocatedFileStatus> iterator = fs.listFiles(new Path("/"), true);

        while (iterator.hasNext()){
            LocatedFileStatus next = iterator.next();

            System.out.println(next.getPath().getName());
            System.out.println(next.getPath());
            System.out.println(next.getPermission());
            System.out.println(next.getOwner());
            System.out.println(next.getGroup());
            BlockLocation[] blockLocations = next.getBlockLocations();
            for (BlockLocation blockLocation : blockLocations) {
                String[] hosts = blockLocation.getHosts();
                for (String host : hosts) {
                    System.out.println(host);
                }
            }
            System.out.println(next.getReplication());
            System.out.println(next.getLen());
            System.out.println("-------------------分割线----------------------");
        }
    }

    public void testListStatus(Path path) throws IOException, InterruptedException {
        before();
        FileStatus[] fileStatuses = fs.listStatus(path);

        for (FileStatus fileStatus : fileStatuses) {
            if (fileStatus.isFile()){
                System.out.println(fileStatus.getPath().getName());
            }else {
                testListStatus(fileStatus.getPath());
            }
        }

        after();
    }

    @Test
    public void testtestListStatusResult() throws IOException, InterruptedException {
        testListStatus(new Path("/"));

    }




}

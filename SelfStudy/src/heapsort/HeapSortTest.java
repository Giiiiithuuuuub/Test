package heapsort;

import java.util.Arrays;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-23 12:09
 */
public class HeapSortTest {
    public static void main(String[] args) {
        int[] tree = {2,5,3,1,10,4};
        heap_sort(tree);

        String string = Arrays.toString(tree);
        System.out.println(string);
    }

    /**
     * @Description: 封装的交换数组元素的方法
     * @author: Yu HaiFeng
     * @email: 15548737663@163.com
     * @createDate: 2020/4/23 12:50
     * @return: void
     */
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * @param tree 一个数组
     * @param n    代表这个树有多少个节点,从1计数
     * @param i    代表要对哪个节点做heapify操作，代表索引，从0计数
     * @Description: heapify方法
     * @author: Yu HaiFeng
     * @email: 15548737663@163.com
     * @createDate: 2020/4/23 12:22
     * @return: void
     */
    public static void heapify(int[] tree, int n, int i) {

        int c1 = 2 * i + 1;//左子节点
        int c2 = 2 * i + 2;//右子节点
        int parent = i;//父节点
        int maxC = 0;
        if (c2 <= n - 1) {
            maxC = tree[c1] > tree[c2] ? c1 : c2;//找到最大的子节点
        }else if (c1 <= n - 1){
            maxC = c1;
        }
        if (maxC != 0 && tree[parent] < tree[maxC]){

            swap(tree, parent, maxC);//如果父节点没有子节点大，进行heapify交换
            parent = maxC;//将父节点索引更新为之前的最大子节点
            heapify(tree, n, parent);//接着对被交换的子节点继续进行heapify
        }
    }

    /**
    * @Description:    将一个数组构建成一个完成的堆
    * @author:         Yu HaiFeng
    * @email:          15548737663@163.com
    * @createDate:     2020/4/23 13:04
    * @param tree      要构建的数组
    * @param n         总节点个数
    * @return:         void
    */
    public static void build_heap(int[] tree, int n){
        //已知最后一个节点的索引n- 1，则可计算父节点
        int parent = (n - 2)/2;//这里要考虑只有左子节点的特殊情况
        //然后从parent这个节点开始，倒着全部进行heapify
        for (int i = parent; i >=0;i--){
            heapify(tree,n,i);
        }
    }

    public static void heap_sort(int[] tree){
        int num = tree.length - 1;//最后一个索引

        build_heap(tree,num);

        for (int i = 0; i <= num; i++) {
            swap(tree,num - i,0);
            heapify(tree,num - i,0);
        }
    }
}

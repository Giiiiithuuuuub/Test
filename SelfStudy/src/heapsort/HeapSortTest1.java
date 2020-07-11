package heapsort;

import sun.applet.Main;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-23 13:42
 */
public class HeapSortTest1 {
    public static void main(String[] args) {

    }

    public static void swap(int[]tree ,int a,int b){
        int temp = tree[a];
        tree[a] = tree[b];
        tree[b] = temp;
    }

    public static void heapify(int[]tree,int n,int i){
        if(i >= n){//递归的出口
            return;
        }

        int c1 = 2 * i + 1;
        int c2 = 2 * i + 2;
        int max = i;
        if (c1 < n && tree[c1] > tree[max]){
            max = c1;
        }
        if (c2 < n && tree[c2] > tree[max]){
            max = c2;
        }

        if (max != i){
            swap(tree,max,i);
            heapify(tree,n,max);
        }
    }
}

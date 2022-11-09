/**
 * 仿照Python语言实现的Java版数组子集，组合和排列算法
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class MathTools {
    //计算排列的整数列表
    static List<List<Integer>> allPermutationList;
    static Stack<Integer> stack;
    //计算正整数的阶乘
    public static int factorial(int n) {
        // 负数及0的阶乘都设定为1
        if (n <= 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    //计算整数的全排列个数，即阶乘
    public static int permutationCount(int n) {
        return factorial(n);
    }

    //计算组合的个数
    public static int combinationCount(int n, int m) {
        return factorial(n) / (factorial(m) * factorial(n - m));
    }

    //整数列表转换为数组
    public static int[] list2Array(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    //整数数组转换为列表
    public static List<Integer> array2List(int[] array) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    //打印输出数组
    public static void outputArray(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                System.out.print(",");
            }
            System.out.print(array[i]);
        }
        System.out.println("]");
    }

    //所有元素的子集列表
    public static List<List<Integer>> subsets(List<Integer> list, int index) {
        //声明一个装子集的集合
        List<List<Integer>> allList = new ArrayList<List<Integer>>();
        //判断:如果传入的集合长度==传入的元素索引(实质上是要求子集的元素个数),即前面的所有元素都安排完了
        if (list.size() == index) {
            //添加一个空的集合
            allList.add(new ArrayList<Integer>());
        } else {
            //递归调用:从索引为0的元素开始将索引增加不断调用
            allList = subsets(list, index + 1);
            //获得当前索引的元素
            int item = list.get(index);
            //声明一个装所有(index-1)个元素的所有子集元素+当前索引元素的集合
            List<List<Integer>> subsets = new ArrayList<List<Integer>>();
            //遍历包含index-1的所有子集和的集合,将其中的子集输出
            for (List<Integer> subList : allList) {
                //声明一个新的数组来装(index-1)个元素的所有子集元素+当前索引index元素的集合
                List<Integer> newSubset = new ArrayList<Integer>();
                //先将(index-1)个元素的每一个子集添加到新的集合中
                newSubset.addAll(subList);
                //再将index位置的元素添加进去
                newSubset.add(item);
                //最后将新的子集添加到集合subsets中
                subsets.add(newSubset);
            }
            //最后将加入新的元素后的所有子集添加到包含(index-1)个元素的所有子集的集合当中当中
            allList.addAll(subsets);
        }
        return allList;
    }

    //所有元素的子集数组
    public static int[][] subsets(int[] array, int index) {
        List<Integer> list = array2List(array);
        List<List<Integer>> allList = subsets(list, 0);
        int[][] allArray = new int[allList.size()][allList.get(0).size()];
        for (int i = 0; i < allList.size(); i++) {
            int[] subArray = list2Array(allList.get(i));
            allArray[i] = subArray;
        }
        return allArray;
    }

    //从n个元素中取m个数的组合数组
    public static int[][] combinations(int[] array, int k) {
        List<Integer> list = array2List(array);
        List<List<Integer>> allList = subsets(list, 0);
        int[][] allArray = new int[combinationCount(array.length, k)][allList.get(0).size()];
        int count = 0;
        for (int i = 0; i < allList.size(); i++) {
            if (allList.get(i).size() == k) {
                int[] subArray = list2Array(allList.get(i));
                allArray[count++] = subArray;
            }
        }
        return allArray;
    }

    //交换数组的两个元素
    public static void swap(List<Integer> list, int a, int b) {
        int bValue = list.get(b);
        int aValue = list.remove(a);
        list.add(a, bValue);
        list.remove(b);
        list.add(b, aValue);
    }

    //列表元素中从k到m全排列
    public static void permutations(List<Integer> list, int k, int m) {
        if( k == m) {
            allPermutationList.add(list.stream().collect(Collectors.toList()));
        }
        else{
            for(int i = k; i <= m; i++) {
                swap(list, k, i);
                permutations(list, k+1 , m);
                swap(list, k, i);
            }
        }
    }

    //数组元素从k到m全排列
    public static int[][] permutations(int[] array, int k, int m){
        allPermutationList = new ArrayList<List<Integer>>();
        List<Integer> list = array2List(array);
        permutations(list, k, m);
        int[][] allArray = new int[allPermutationList.size()][allPermutationList.get(0).size()];
        for (int i = 0; i < allPermutationList.size(); i++) {
            int[] subArray = list2Array(allPermutationList.get(i));
            allArray[i] = subArray;
        }
        return allArray;
    }

    //列表全排列
    public static void allPermutations(List<Integer> list, Stack<Integer> stack) {
        //列表中的元素都已加入，返回结果
        if (list.size() == 0) {
            allPermutationList.add(stack.stream().collect(Collectors.toList()));
        } else {  //没有到树的叶子节点的时候，使用递归继续往下找。
            for (int i = 0; i < list.size(); i ++) {
                stack.push(list.get(i));
                list.remove(i);
                allPermutations(list, stack);
                list.add(i, stack.pop());
            }
        }
    }

    //数组全排列
    public static int[][] allPermutations(int[] array, Stack<Integer> stack){
        allPermutationList = new ArrayList<List<Integer>>();
        List<Integer> list = array2List(array);
        allPermutations(list, stack);
        int[][] allArray = new int[allPermutationList.size()][allPermutationList.get(0).size()];
        for (int i = 0; i < allPermutationList.size(); i++) {
            int[] subArray = list2Array(allPermutationList.get(i));
            allArray[i] = subArray;
        }
        return allArray;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        System.out.println(arrayList);

        //System.out.println(subsets(arrayList, 0));
        swap(arrayList, 0,3);
        System.out.println(arrayList);

        int[] array = new int[]{1, 2, 3, 4};
        outputArray(array);
        stack = new Stack<Integer>();
        int[][] allArray = allPermutations(array, stack);
        System.out.print("[");
        for (int i = 0; i < allArray.length; i++) {
            if (i > 0) {
                System.out.print(",");
            }
            outputArray(allArray[i]);
        }
        System.out.println("]");
    }
}

/**
 * 天池在线编程LeetCode公开题库Java版代码
 *
 * Author：dumbellyang
 * Email:yangdanbo@163.com
 * Weixin:dumbellyang
 * Date:2022-11-07
 *
 * 部分代码系参考或复制网友代码修改或从网友Python3版翻译而成，特此致谢
 *
 */
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.*;

class Solution {
    //1. 两数之和
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/tb09mi1apgpusvjf?spm=5176.15228502.0.0.175f79bfR5GqFe
    public int[] twoSum(int[] nums, int target) {
        if (nums.length >= 2) {
            for (int i = 0; i < nums.length - 1; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] + nums[j] == target) {
                        return new int[]{i, j};
                    }
                }
            }
        }
        return new int[]{-1, -1};
    }

    // java中不能直接输出数组的内容，所以还要写一个公用方法
    public void output(int[] solutions) {
        System.out.println("Solution:");
        System.out.print("[");
        for (int i = 0; i < solutions.length; i++) {
            if (i > 0) {
                System.out.print(",");
            }
            System.out.print(solutions[i]);
        }
        System.out.println("]");
    }

    //3. 无重复字符的最长子串
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/bi20zeeh72by5ocn?spm=5176.15228502.0.0.553779bfGSPQKl
    //执行测试用例通过，提交代码报答案错误
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0 || s.equals("")) {
            return 0;
        } else if (s.length() == 1) {
            return 1;
        } else {
            int lastMaxLength = 1;
            int maxLength = 1;
            Set<Character> set = new HashSet<Character>();
            set.add(s.charAt(0));
            for (int i = 1; i < s.length(); i++) {
                Character curChar = s.charAt(i);
                if (!set.contains(curChar)) {
                    set.add(curChar);
                    maxLength++;
                } else {
                    if (maxLength > lastMaxLength) {
                        lastMaxLength = maxLength;
                    }
                    set.clear();

                    set.add(curChar);
                    maxLength = 1;
                }
            }
            return maxLength > lastMaxLength ? maxLength : lastMaxLength;
        }
    }

    //14. 三数之和
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/pnopmkylog3uyrjd?spm=5176.15228502.0.0.3c4879bfu4HV2X
    //执行测试用例通过，提交代码报答案错误
    public boolean isEqual(List<Integer> a, List<Integer> b) {
        if (a.size() != b.size()) {
            return false;
        } else {
            return a.containsAll(b);
        }
    }

    public boolean isLarger(List<Integer> a, List<Integer> b) {
        return a.get(0) > b.get(0) || Objects.equals(a.get(0), b.get(0)) && a.get(1) > b.get(1) ||
                Objects.equals(a.get(0), b.get(0)) && Objects.equals(a.get(1), b.get(1)) && a.get(2) > b.get(2);
    }

    public List<Integer> buildSortedList(int a, int b, int c) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(a);
        if (b >= a) {
            list.add(b);
            if (c >= b) {
                list.add(c);
            } else if (c >= a) {
                list.add(1, c);
            } else {
                list.add(0, c);
            }
        } else {
            list.add(0, b);

            if (c >= a) {
                list.add(c);
            } else if (c >= b) {
                list.add(1, c);
            } else {
                list.add(0, c);
            }
        }
        return list;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 3) {
            return result;
        } else if (nums.length == 3) {
            if (nums[0] + nums[1] + nums[2] == 0) {
                List<Integer> subList = buildSortedList(nums[0], nums[1], nums[2]);

                result.add(subList);
                return result;
            } else {
                return result;
            }
        } else {
            for (int i = 0; i < nums.length - 2; i++) {
                for (int j = i + 1; j < nums.length - 1; j++) {
                    for (int k = j + 1; k < nums.length; k++) {
                        if (nums[i] + nums[j] + nums[k] == 0) {
                            List<Integer> subList = buildSortedList(nums[i], nums[j], nums[k]);

                            if (result.size() == 0) {
                                result.add(subList);
                            } else {
                                boolean isExisted = false;
                                int idx = 0;
                                for (List<Integer> b : result) {
                                    if (isEqual(subList, b)) {
                                        isExisted = true;
                                        break;
                                    } else if (isLarger(subList, b)) {
                                        idx++;
                                    }
                                }
                                if (false == isExisted) {
                                    result.add(idx, subList);
                                }
                            }
                        }
                    }
                }
            }
            return result;
        }
    }

    //15. 最接近的三数之和
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/wivezmol9fystagw?spm=5176.15228502.0.0.c98579bfSkIBJI
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        } else if (nums.length == 3) {
            return nums[0] + nums[1] + nums[2];
        } else {
            int sum = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length - 2; i++) {
                for (int j = i + 1; j < nums.length - 1; j++) {
                    for (int k = j + 1; k < nums.length; k++) {
                        if (java.lang.Math.abs(nums[i] + nums[j] + nums[k] - target) < java.lang.Math.abs(sum - target)) {
                            sum = nums[i] + nums[j] + nums[k];
                        }
                    }
                }
            }
            return sum;
        }
    }

    //18.删除链表的倒数第N个结点
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/hptcnuzyyl68fswf?spm=5176.15228502.0.0.346079bfM0Lrph
    //执行测试用例通过，提交代码报答案错误
    public ListNode removeNthFromEnd(ListNode head, int n) {
        List<ListNode> list = new ArrayList<ListNode>();
        list.add(head);
        ListNode p = head.next;
        while (p != null) {
            list.add(p);
            p = p.next;
        }

        if (n == 1 && list.size() == 1 || n >= list.size()) {
            return null;
        } else {
            ListNode removed = list.get(list.size() - n);
            ListNode pre = list.get(list.size() - n - 1);
            pre.next = removed.next;

            return list.get(0);
        }
    }

    //19.有效的括号
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/xd7eft4mjgewso6o?spm=5176.15228502.0.0.7d1579bf3VtION
    public boolean isValid(String s) {
        if (s.length() == 0 || s.length() > 10000) {
            return false;
        }
        List<Character> stack = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                stack.add(s.charAt(i));
            } else {
                if (stack.size() == 0) {
                    return false;
                }

                if (s.charAt(i) == ')') {
                    if (stack.lastIndexOf('(') != stack.size() - 1) {
                        return false;
                    } else {
                        stack.remove(stack.size() - 1);
                    }
                } else if (s.charAt(i) == '}') {
                    if (stack.lastIndexOf('{') != stack.size() - 1) {
                        return false;
                    } else {
                        stack.remove(stack.size() - 1);
                    }
                } else if (s.charAt(i) == ']') {
                    if (stack.lastIndexOf('[') != stack.size() - 1) {
                        return false;
                    } else {
                        stack.remove(stack.size() - 1);
                    }
                } else {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }

    //20.合并两个有序链表
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/unvlfxdhnzwwycol?spm=5176.15228502.0.0.7d1579bfTOM8MO
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode newListNode = new ListNode(-1);
        ListNode p = newListNode;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                p.next = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                p.next = new ListNode(list2.val);
                list2 = list2.next;
            }
            p = p.next;
        }
        if (list1 != null) {
            p.next = list1;
        }
        if (list2 != null) {
            p.next = list2;
        }
        return newListNode.next;
    }

    //25.删除有序数组中的重复项
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/d4vzjccmh23oz0sn?spm=5176.15228502.0.0.7d1579bfLnHyV0
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        int duplications = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] == nums[i - 1]) {
                for (int j = i - 1; j < nums.length - duplications - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                duplications++;
            }
        }
        return len - duplications;
    }

    //26.移除元素
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/aehayfm87hpoizhs?spm=5176.15228502.0.0.7d1579bfMazMQE
    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        int duplications = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == val) {
                for (int j = i; j < nums.length - duplications - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                duplications++;
            }
        }
        return len - duplications;
    }

    //30.搜索旋转排序数组
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/09bfut1prcd83wi2?spm=5176.15228502.0.0.7d1579bfEB5sdJ
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) {
                    return i;
                }
            }
            return -1;
        }
    }

    //33.有效的数独
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/ujkbi1ha5crf9wts?spm=5176.15228502.0.0.7d1579bfp28EfL
    public boolean isValidSudokuData(char[] data) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < data.length; i++) {
            if (data[i] >= '1' && data[i] <= '9') {
                if (set.contains(data[i])) {
                    return false;
                } else {
                    set.add(data[i]);
                }
            }
        }
        return true;
    }

    public boolean isValidSudoku(char[][] board) {
        for (int row = 0; row < 9; row++) {
            if (false == isValidSudokuData(board[row])) {
                return false;
            }
        }

        for (int col = 0; col < 9; col++) {
            char[] data = new char[9];
            for (int row = 0; row < 9; row++) {
                data[row] = board[row][col];
            }
            if (false == isValidSudokuData(data)) {
                return false;
            }
        }

        for (int rowArea = 0; rowArea < 3; rowArea++) {
            for (int colArea = 0; colArea < 3; colArea++) {
                char[] data = new char[9];

                for (int i = 0; i < 9; i++) {
                    data[i] = board[rowArea * 3 + i / 3][colArea * 3 + i % 3];
                }

                if (false == isValidSudokuData(data)) {
                    return false;
                }
            }
        }
        return true;
    }

    //46.最大子数组和
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/gtahxhmx1r6etpzj?spm=5176.15228502.0.0.7d1579bf0Klso7
    //执行测试用例通过，提交代码报答案错误
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        } else {
            int sum = 0;
            int subSum = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0) {
                    if (subSum != 0) {
                        subSum += nums[i];
                        if (subSum > 0) {
                            sum += subSum;
                            subSum = 0;
                        }
                    } else {
                        sum += nums[i];
                    }
                } else {
                    if (sum > 0) {
                        if (sum + nums[i] > 0) {
                            subSum += nums[i];
                        } else {
                            sum = 0;
                            subSum = 0;
                        }
                    }
                }
            }

            return subSum > 0 ? sum + subSum : sum;
        }
    }

    //53.旋转链表
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/jv8espkxwpdbowby?spm=5176.15228502.0.0.7d1579bfzz3Sch
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        List<ListNode> list = new ArrayList<ListNode>();
        list.add(head);
        ListNode p = head.next;
        while (p != null) {
            list.add(p);
            p = p.next;
        }

        if (list.size() == 1 || k % list.size() == 0) {
            return head;
        } else {
            int newHead = list.size() - k % list.size();
            list.get(list.size() - k % list.size() - 1).next = null;
            list.get(list.size() - 1).next = head;

            return list.get(newHead);
        }
    }

    //66.子集
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/wovfpppchwero2co?spm=5176.15228502.0.0.7d1579bfuJd8sq
    //执行测试用例通过，提交代码报答案错误
    public List<List<Integer>> subsetsList(List<Integer> list) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(list);

        if (list.size() > 1) {
            for (int i = 0; i < list.size(); i++) {
                List<Integer> subList = new ArrayList<Integer>();
                for (int j = 0; j < list.size(); j++) {
                    if (j != i) {
                        subList.add(list.get(j));
                    }
                }
                result.addAll(subsetsList(subList));
            }
        }
        return result;
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> empty = new ArrayList<Integer>();
        result.add(empty);

        List<Integer> curList = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            curList.add(nums[i]);
        }
        // 递归
        List<List<Integer>> allList = subsetsList(curList);
        List<Integer> sumList = new ArrayList<Integer>();
        // 去重
        for (List<Integer> list : allList) {
            Integer sum = 0;
            for (Integer i : list) {
                sum += i * i;
            }
            if (false == sumList.contains(sum)) {
                result.add(list);
                sumList.add(sum);
            }
        }
        return result;
    }

    //70. 删除排序链表中的重复元素 II
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/xahdotanskuosvsu?spm=5176.15228502.0.0.7d1579bfZL8bgZ
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode p = dummy, cur = head;
        while (cur != null && cur.next != null) {
            if (cur.next != null && cur.val != cur.next.val) {
                p.next = cur;
                p = p.next;
            } else {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
            }
            cur = cur.next;
        }
        p.next = cur;
        return dummy.next;
    }

    //71.删除排序链表中的重复元素
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/yoacnc8povsmm617?spm=5176.15228502.0.0.7d1579bfOe8V6o
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;

    }

    //73.合并两个有序数组
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/lx6nvsaogkbcybaa?spm=5176.15228502.0.0.7d1579bfOdkdb9
    //执行测试用例通过，提交代码报答案错误
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        if (m == 0) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
        } else {
            for (int i = 0; i < n; i++) {
                int cur = nums2[i];
                boolean isMerged = false;
                for (int j = 0; j < m + n; j++) {
                    if (isMerged) {
                        continue;
                    }
                    if (nums1[j] > cur || nums1[j] == 0) {
                        for (int k = m + n - 1; k > j; k--) {
                            nums1[k] = nums1[k - 1];
                        }
                        nums1[j] = cur;
                        isMerged = true;
                    }
                }
            }
        }
    }

    //78.二叉树的中序遍历
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/uq20sgfhciktiwcl?spm=5176.15228502.0.0.7d1579bfjo6DJ3
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        if (root != null) {
            if (root.left != null) {
                list.addAll(inorderTraversal(root.left));
            }
            list.add(root.val);
            if (root.right != null) {
                list.addAll(inorderTraversal(root.right));
            }
        }
        return list;
    }

    //85.对称二叉树
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/smgovhcnqtcrsloz?spm=5176.15228502.0.0.7d1579bfJQFlBp
    //执行测试用例通过，提交代码报答案错误及运行错误
    public boolean isSymmetric2(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null && rightNode != null || leftNode != null && rightNode == null) {
            return false;
        } else {
            if (leftNode.val != rightNode.val) {
                return false;
            } else {
                return isSymmetric2(leftNode.left, rightNode.right) && isSymmetric2(leftNode.right, rightNode.left);
            }
        }
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
            return true;
        } else {
            return isSymmetric2(root.left, root.right);
        }
    }

    //88.二叉树的最大深度
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/uamsse3zi2vsg7sm?spm=5176.15228502.0.0.7d1579bfPcsPPI
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    //89.从前序与中序遍历序列构造二叉树
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/il7nnjonzxtzjxt7?spm=5176.15228502.0.0.123a79bfcBL3sp
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if ((preorder == null || preorder.length == 0) &&
                (inorder == null || inorder.length == 0)) {
            return null;
        }
        //根据前序数组的第一个元素，就可以确定根节点
        TreeNode root = new TreeNode(preorder[0]);
        // 用preorder[0]去中序数组中查找对应的元素
        int mid_idx = -1;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root.val) {
                mid_idx = i;
                break;
            }
        }

        // 递归的处理前序数组的左边部分和中序数组的左边部分
        root.left = buildTree(Arrays.copyOfRange(preorder, 1, mid_idx + 1),
                              Arrays.copyOfRange(inorder, 0, mid_idx));
        // 递归处理前序数组右边部分和中序数组右边部分
        root.right = buildTree(Arrays.copyOfRange(preorder, mid_idx + 1, preorder.length),
                               Arrays.copyOfRange(inorder, mid_idx + 1, inorder.length));
        return root;
    }

    //90.从中序与后序遍历序列构造二叉树
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/vbne3ulq7locs7y8?spm=5176.15228502.0.0.123a79bfg91oww
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        int index = -1;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root.val) {
                index = i;
                break;
            }
        }
        root.left = buildTree2(Arrays.copyOfRange(inorder, 0, index),
                               Arrays.copyOfRange(postorder, 0, index));
        root.right = buildTree2(Arrays.copyOfRange(inorder, index + 1, inorder.length),
                                Arrays.copyOfRange(postorder, index, postorder.length - 1));

        return root;
    }

    //92.将有序数组转换为二叉搜索树
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/khmlb3lvp4jyxxm7?spm=5176.15228502.0.0.123a79bf5eq68p
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length);
    }

    private TreeNode helper(int[] nums, int left, int right) {
        if (left >= right) {
            return null;
        }
        int mid = (left + right) >> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid);
        root.right = helper(nums, mid + 1, right);
        return root;
    }

    //95.二叉树的最小深度
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/7mgbglyvtl9pi3eu?spm=5176.15228502.0.0.123a79bfOOZHSh
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return 1;
        } else {
            int leftDepth = 10000;
            int rightDepth = 10000;
            if (root.left != null) {
                leftDepth = minDepth(root.left) + 1;
            }
            if (root.right != null) {
                rightDepth = minDepth(root.right) + 1;
            }
            return Math.min(leftDepth, rightDepth);
        }
    }

    //96.路径总和
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/hg6yjn4lzctettgx?spm=5176.15228502.0.0.123a79bfZmEVWe
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        } else if (root.left == null && root.right == null) {
            if (root.val == targetSum) {
                return true;
            } else {
                return false;
            }
        } else {
            return hasPathSum(root.left, targetSum - root.val) ||
                    hasPathSum(root.right, targetSum - root.val);
        }
    }

    //104.买卖股票的最佳时机
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/uv7fotvgoxupupwp?spm=5176.15228502.0.0.123a79bfeOdLXd
    //执行测试用例通过，提交代码报答案错误
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int minPos = -1;
        int maxPrice = Integer.MIN_VALUE;
        int maxPos = -1;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
                minPos = i;
            }
            if (prices[i] > maxPrice) {
                maxPrice = prices[i];
                maxPos = i;
            }
        }
        if (minPos == prices.length - 1) {
            return 0;
        } else if (maxPos > minPos) {
            return maxPrice - minPrice;
        } else if (maxPos == minPos) {
            return 0;
        } else {
            maxPrice = Integer.MIN_VALUE;
            for (int i = minPos + 1; i < prices.length; i++) {
                if (prices[i] > maxPrice) {
                    maxPrice = prices[i];
                    ;
                }
            }
            return maxPrice - minPrice;
        }
    }

    //105.买卖股票的最佳时机II
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/roallxl8v6paeyr5?spm=5176.15228502.0.0.123a79bftMwktO
    public int maxProfit2(int[] prices) {
        return -1; // Java代码还没写。。。
    }

    //107.验证回文串
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/wgsaps9giaxxitna?spm=5176.15228502.0.0.123a79bfRySnqo
    public boolean isPalindrome(String s) {
        String regEx = "[a-zA-Z0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(s);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            sb.append(m.group());
        }

        String newStr = sb.toString().toLowerCase();
        for (int i = 0; i < newStr.length() / 2; i++) {
            if (newStr.charAt(i) != newStr.charAt(newStr.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public boolean validPalindrome(String s) {
        if (isPalindrome(s)) {
            return true;
        } else {
            for (int i = 0; i < s.length(); i++) {
                if (isPalindrome(s.replace(String.valueOf(s.charAt(i)), ""))) {
                    return true;
                }
            }
            return false;
        }
    }

    //114.只出现一次的数字
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/u0huybhfvm45ezvl?spm=5176.15228502.0.0.123a79bfORddEO
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; ) {
            if (i == nums.length - 1) {
                return nums[i];
            } else if (nums[i] != nums[i + 1]) {
                return nums[i];
            } else {
                i += 2;
            }
        }
        return -1;
    }

    //115.只出现一次的数字II
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/ivlew1cgd8e4u3pt?spm=5176.15228502.0.0.123a79bf3bjOIy
    public int singleNumber2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; ) {
            if (i == nums.length - 1) {
                return nums[i];
            } else if (nums[i] != nums[i + 1]) {
                return nums[i];
            } else {
                i += 3;
            }
        }
        return -1;
    }

    //118.环形链表
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/vedahxsx1b8jqnx4?spm=5176.15228502.0.0.123a79bfJbcCqi
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    //124.对链表进行插入排序
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/db7vjfolki5obklx?spm=5176.15228502.0.0.123a79bfpnoGxw
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1, head);
        ListNode last = head, cur = head.next;
        while (cur != null) {
            if (last.val <= cur.val) {
                last = last.next;
            } else {
                ListNode p = dummy;
                while (p.next.val <= cur.val) {
                    p = p.next;
                }
                last.next = cur.next;
                cur.next = p.next;
                p.next = cur;
            }
            cur = last.next;
        }
        return dummy.next;
    }

    //125.排序链表
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/4ro2fmd7uk5irb2f?spm=5176.15228502.0.0.123a79bfqAAaDH
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    private ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return null;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow;
        ListNode head1 = sortList(head, mid);
        ListNode head2 = sortList(mid, tail);
        return merge(head1, head2);
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy, p1 = head1, p2 = head2;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p.next = new ListNode(p1.val);
                p1 = p1.next;
            } else {
                p.next = new ListNode(p2.val);
                p2 = p2.next;
            }
            p = p.next;
        }
        if (p1 != null) {
            p.next = p1;
        } else {
            p.next = p2;
        }
        return dummy.next;
    }

    //126.逆波兰表达式求值
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/186c5gggr2eaxetm?spm=5176.15228502.0.0.123a79bflofUy8
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    stack.push(-stack.pop() + stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a / b);
                    break;
                default:
                    stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }

    //127.翻转字符串里的单词
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/r2frxq5r8blqzrdp?spm=5176.15228502.0.0.123a79bfk2kXWg
    public String reverseWords(String s) {
        String[] split = s.split(" ");
        StringBuilder reverse = new StringBuilder();
        int count = 0;
        for (int i = split.length - 1; i >= 0; i--) {
            if (split[i].length() > 0) {
                count += 1;
                if (count > 1) {
                    reverse.append(" ");
                }
                reverse.append(split[i]);
            }
        }
        return reverse.toString();
    }

    //130.最小栈
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/0f5efm1lgkc71qit?spm=5176.15228502.0.0.123a79bfJzk7fR
    //代码见MinStack.java

    //131.相交链表
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/scqretszaejgvugn?spm=5176.15228502.0.0.560979bfaVi0Rv
    //代码还未完成

    //135.Excel表列名称
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/d5i8ergo2ibym9iv?spm=5176.15228502.0.0.560979bf3xloSD
    public String convertToTitle(int columnNumber) {
        String column = "";
        while (columnNumber > 0) {
            int curCol = columnNumber % 26;
            if (curCol == 0) {
                curCol = 26;
            }
            column = (char) (64 + curCol) + column;
            columnNumber = (columnNumber - 1) / 26;
        }
        return column;
    }

    //136.多数元素
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/poadhl6o42bnlokn?spm=5176.15228502.0.0.560979bfPgf71N
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[(nums.length - 1) / 2];
    }

    //139.二叉搜索树迭代器
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/fjjnoehktyxlctbg?spm=5176.15228502.0.0.560979bfNRRMng
    //代码见BSTIterator.java

    //148.移除链表元素
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/vzj6xr0txtcnvj55?spm=5176.15228502.0.0.560979bfruIZyO
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        } else if (head.next == null) {
            if (head.val == val) {
                return null;
            } else {
                return head;
            }
        } else {
            ListNode newHead = new ListNode(-1);
            newHead.next = head;
            ListNode p = newHead;
            while (head != null) {
                if (head.val == val) {
                    p.next = head.next;
                } else {
                    p = head;
                }
                head = head.next;
            }
            return newHead.next;
        }
    }

    //151.反转链表
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/uftes6dzntjq0rtr?spm=5176.15228502.0.0.560979bfoXLwMG
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        } else if (head.next.next == null) {
            ListNode tail = head.next;
            head.next = null;
            tail.next = head;
            return tail;
        } else {
            ListNode remains = head.next;
            ListNode tail = reverseList(remains);
            ListNode p = tail;
            while (p.next != null) {
                p = p.next;
            }
            p.next = head;
            head.next = null;

            return tail;
        }
    }

    //160.存在重复元素
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/yul8zd4jroh3htcb?spm=5176.15228502.0.0.560979bf8mDYwX
    //执行测试用例通过，提交代码答案错误
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            if (set.contains(nums[i])) {
                return true;
            } else {
                set.add(nums[i]);
            }
        }
        return false;
    }

    //161.存在重复元素II
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/2dxe8bmzwpnqyxso?spm=5176.15228502.0.0.560979bfe2ipbb
    //执行测试用例通过，提交代码答案错误
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int pos = map.get(nums[i]);
                return pos - i <= k;
            } else {
                map.put(nums[i], i);
            }
        }
        return false;
    }

    //167.基本计算器II
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/4sfcxpjuwqla9vw8?spm=5176.15228502.0.0.560979bfP16BpA
    //执行测试用例通过，提交代码答案错误
    public int calculate(String s) {
        Stack<Integer> nums = new Stack<Integer>();
        s = s.replaceAll(" ", "");
        for (int i = 0; i < s.length() - 1; i++) {
            char curChar = s.charAt(i);
            if (Character.isDigit(curChar)) {
                int curNum = Character.getNumericValue(curChar);
                while ((i + 1 < s.length()) && Character.isDigit(s.charAt(i + 1))) {
                    curNum = curNum * 10 + Character.getNumericValue(s.charAt(i + 1));
                    i += 1;
                }
                nums.push(curNum);
            } else {
                int nextNum = Character.getNumericValue(s.charAt(i + 1));
                i++;
                while ((i + 1 < s.length()) && Character.isDigit(s.charAt(i + 1))) {
                    nextNum = nextNum * 10 + Character.getNumericValue(s.charAt(i + 1));
                    i += 1;
                }
                if (curChar == '+') {
                    nums.push(nextNum);
                } else if (curChar == '-') {
                    nums.push(-nextNum);
                } else if (curChar == '*') {
                    int num = nums.pop();
                    nums.push(num * nextNum);
                } else if (curChar == '/') {
                    int num = nums.pop();
                    nums.push(num / nextNum);
                }
            }
        }
        int result = 0;
        for (Integer num : nums) {
            result += num;
        }

        return result;
    }

    //170.二叉搜索树中第K小的元素
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/drfy3jrpkalumkua?spm=5176.15228502.0.0.560979bfONAd7f
    public List<Integer> getAllNodeValues(TreeNode node) {
        List<Integer> nums = new ArrayList<Integer>();
        if (node != null) {
            if (!nums.contains(node.val)) {
                nums.add(node.val);
            }
        }
        if (node.left != null) {
            nums.addAll(getAllNodeValues(node.left));
        }
        if (node.right != null) {
            nums.addAll(getAllNodeValues(node.right));
        }
        return nums;
    }

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> nums = getAllNodeValues(root);
        Collections.sort(nums);
        if (k <= nums.size()) {
            return nums.get(k - 1);
        } else {
            return -1;
        }
    }

    //171.2的幂
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/81xnysijoondtbwq?spm=5176.15228502.0.0.560979bfHWOm36
    public boolean isPowerOfTwo(int n) {
        if (n == 1 || n == 2) {
            return true;
        } else if (n > 2) {
            if (n % 2 == 1) {
                return false;
            }
            return isPowerOfTwo(n / 2);
        } else {
            return false;
        }
    }

    //181.只出现一次的数字III
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/ifhiana5hpqc9uyz?spm=5176.15228502.0.0.560979bf0N0T0j
    public int[] singleNumber3(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return nums;
        } else {
            Arrays.sort(nums);
            int count = 0;
            int[] result = new int[2];
            for (int i = 0; i < nums.length; i++) {
                if (i == nums.length - 1) {
                    result[count++] = nums[i];
                    if (count == 2) {
                        break;
                    }
                } else if (nums[i] == nums[i + 1]) {
                    i++;
                } else {
                    result[count++] = nums[i];
                    if (count == 2) {
                        break;
                    }
                }
            }
            return result;
        }
    }

    //224.字符串解码
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/bcadlk8qgwi5oi26?spm=5176.15228502.0.0.560979bfVmghU0
    public String decodeString(String s) {
        // 先拆成数组
        String[] array = handle(s);
        // 数字栈
        Stack<Integer> numStack = new Stack<>();
        // 符号栈
        Stack<String> stringStack = new Stack<>();
        // 字母、[]符号
        String ch = "qwertyuiopasdfghjklzxcvbnm[]";
        for (int i = 0; i < array.length; i++) {
            String str = array[i];
            // 符号
            if (ch.contains(str)) {
                // ]符号,弹出字符直到遇到[，同时弹出数字k
                if (str.equals("]")) {
                    handleMul(numStack, stringStack);
                } else {
                    stringStack.add(str);
                }
            } else {
                // 数字
                numStack.add(Integer.parseInt(str));
            }
        }
        StringBuilder back = new StringBuilder();
        while (!stringStack.isEmpty()) {
            back.append(stringStack.pop());
        }
        return back.reverse().toString();
    }

    // 弹出字符直到遇到[，同时弹出数字k
    private static void handleMul(Stack<Integer> numStack, Stack<String> stringStack) {
        // 倍数
        Integer pop = numStack.pop();
        StringBuilder str = new StringBuilder();
        while (true) {
            String ch = stringStack.pop();
            if (ch.equals("[")) {
                break;
            }
            str.append(ch);
        }
        // 需要k次的字符串
        StringBuilder kTimeStr = new StringBuilder(str.reverse().toString());
        // 累计k次的字符串
        StringBuilder totalStr = new StringBuilder();
        for (int i = 0; i < pop; i++) {
            totalStr.append(kTimeStr);
        }
        // 把字符压入字符栈
        String s = totalStr.toString();
        for (int i = 0; i < s.length(); i++) {
            stringStack.add(s.charAt(i) + "");
        }
    }

    // 先拆成数组
    public static String[] handle(String s) {
        // k个数
        int kCount = 0;
        // 数字个数，用于求出数组长度
        int numCount = 0;
        // k集合
        List<Integer> kList = new ArrayList<>(8);
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char currentCh = s.charAt(i);
            if (isNum(currentCh)) {
                str.append(currentCh);
                numCount++;
                // 当前符号是数字，并且上一个符号不是数字(排除0的情况，0没有上一个字符)
                if (i == 0 || !isNum(s.charAt(i - 1))) {
                    kCount++;
                }
            } else {
                String num = str.toString();
                // 不是数字
                if (num.length() != 0) {
                    kList.add(Integer.parseInt(num));
                    // 重置
                    str = new StringBuilder();
                }
            }
        }
        // 数组大小
        int arraySize = s.length() - numCount + kCount;
        String[] array = new String[arraySize];
        int index = 0;
        int numIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch < '0' || ch > '9') {
                array[index++] = ch + "";
                continue;
            }
            // 如果是数字，并且上一个不是数字，那么赋值k
            if (isNum(ch) && (i == 0 || !isNum(s.charAt(i - 1)))) {
                array[index++] = kList.get(numIndex++).toString();
            }
        }
        return array;
    }

    public static boolean isNum(char ch) {
        return ch >= '0' && ch <= '9';
    }

    //277.最长回文子序列
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/vv7o2js8dpgov2vi?spm=5176.15228502.0.0.560979bfnhFeSP
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        //dp[i][j]表示字符串中从i到j之间的最长回文子序列,i<j
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = 0;
            }
        }

        for (int i = n - 1; i > -1; i--) {
            dp[i][i] = 1; // 初始条件
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    //329.验证回文字符串Ⅱ
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/oqishxhcycspqheq?spm=5176.15228502.0.0.34d579bfzNj4OV
    //执行测试用例通过，提交代码答案错误
    public boolean isPalindrome2(String newStr) {
        for (int i = 0; i < newStr.length() / 2; i++) {
            if (newStr.charAt(i) != newStr.charAt(newStr.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public boolean validPalindrome2(String s) {
        if (isPalindrome2(s)) {
            return true;
        } else {
            for (int i = 0; i < s.length(); i++) {
                if (isPalindrome2(s.replace(String.valueOf(s.charAt(i)), ""))) {
                    return true;
                }
            }
            return false;
        }
    }

    //355.宝石与石头
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/y4fzhymgwtuntbsr?spm=5176.15228502.0.0.34d579bfbFZ1GC
    public int numJewelsInStones(String jewels, String stones) {
        int count = 0;
        for(int i = 0;i < stones.length();i ++) {
            if (jewels.indexOf(stones.charAt(i)) >= 0){
                count++;
            }
        }
        return count;
    }

    //360.二分查找
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/a5x3gdyeglz8dvrr?spm=5176.15228502.0.0.34d579bf5OEFBI
    public int search2(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;
        while(low <= high) {
            int mid = (low + high) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            if (target > nums[mid]) {
                low = mid + 1;
            }
            if (target < nums[mid]) {
                high = mid - 1;
            }
        }

        return -1;
    }

    //374.子域名访问计数
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/zxvzcidnk0vmtbsu?spm=5176.15228502.0.0.34d579bfsVlv0k
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> countMap = new HashMap<String, Integer>();
        for(String cp: cpdomains) {
            int pos = cp.indexOf(" ");
            int curCount = Integer.parseInt(cp.substring(0, pos));
            String[] curDomains = cp.substring(pos + 1).split("\\.");
            String curDomain = "";
            for(int i = curDomains.length - 1;i >= 0;i --) {
                if (curDomain.equals("")) {
                    curDomain = curDomains[i];
                } else {
                    curDomain = curDomains[i] + "." + curDomain;
                }
                if (countMap.containsKey(curDomain)) {
                    countMap.replace(curDomain, countMap.get(curDomain) + curCount);
                } else {
                    countMap.put(curDomain, curCount);
                }
            }
        }
        List<String> result = new ArrayList<String>();
        for(String key:countMap.keySet()) {
            result.add(countMap.get(key) + " " + key);
        }
        return result;
    }

    //387.比较含退格的字符串
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/jjpfs012mrtphtgu?spm=5176.15228502.0.0.34d579bfCUWZf0
    public String replaceBackspace(String str) {
        String newStr = "";
        for (int i = 0;i < str.length();i ++) {
            if (str.charAt(i) != '#') {
                newStr += str.charAt(i);
            } else {
                if (newStr.length() >= 1) {
                    newStr = newStr.substring(0, newStr.length() - 1);
                }
            }
        }
        return newStr;
    }

    public boolean backspaceCompare(String s, String t) {
        String newS = replaceBackspace(s);
        String newT = replaceBackspace(t);
        if (newS.length() != newT.length()) {
            return false;
        } else {
            for (int i = 0;i < newS.length();i ++) {
                if (newS.charAt(i) != newT.charAt(i)) {
                    return false;
                }
            }
        }

        return true;
    }

    //410.排序数组
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/n7nfra3pmqjqpyww?spm=5176.15228502.0.0.34d579bfFjg2Oa
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 1) {
            return nums;
        } else {
            for(int i = 1; i < nums.length; i++){
                //保存每次需要插入的那个数
                int swap = nums[i];
                int j;
                for(j = i; j > 0 && nums[j-1] > swap; j--){
                    //把大于需要插入的数往后移动。最后不大于temp的数就空出来j
                    nums[j] = nums[j-1];
                }
                //将需要插入的数放入这个位置
                nums[j] = swap;
            }
            return nums;
        }
    }

    //429.有序数组的平方
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/3tlxfwdolalefzxc?spm=5176.15228502.0.0.34d579bfoC59t8
    public int[] sortedSquares(int[] nums) {
        for(int i = 0;i < nums.length;i ++) {
            nums[i] = nums[i] * nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }

    //456.数组的相对排序
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/ogk9twmf2wjp3ydl?spm=5176.15228502.0.0.34d579bfRXc3yY
    //执行测试用例通过，提交代码答案错误
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] newArr = new int[arr1.length];
        int p = 0;
        for(int i = 0;i < arr2.length;i ++) {
            for(int j = 0;j < arr1.length;j ++) {
                if (arr1[j] == arr2[i]) {
                    newArr[p++] = arr1[j];
                }
            }
        }

        for(int i = 0;i < arr1.length;i ++) {
            boolean notExisted = true;
            for(int j = 0;j < arr2.length;j ++) {
                if (arr1[i] == arr2[j]) {
                    notExisted = false;
                    break;
                }
            }
            if (notExisted) {
                newArr[p++] = arr1[i];
            }
        }
        return newArr;
    }

    //459.最长公共子序列
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/q1tf6mtanr1nqdsi?spm=5176.15228502.0.0.34d579bfImtmYx
    //Java代码还没写

    //551.天池测试 - 01
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/pftqnt1xqwdzbjxb?spm=5176.15228502.0.0.34d579bfME84sa
    //测试数据

    //552.统计链表奇数节点
    //难度：简单
    //题目介绍：https://tianchi.aliyun.com/oj/problems/gx8aqftwdqvxyhwe?spm=5176.15228502.0.0.489f79bfFRdhS3
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public int numberEvenListNode(ListNode head) {
        int count = 0;
        while (head != null) {
            if (head.val % 2 == 1) {
                count ++;
            }
            head = head.next;
        }
        return count;
    }

    //553.光线反射
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/vwogaxfzgnskyjcr?spm=5176.15228502.0.0.489f79bfq4S4Zq
    public int getLength(String[] grid) {
        int n = grid.length;
        int m = grid[0].length();
        int length = 0;
        int x = 0;
        int y = 0;
        int direction = 0; // 0 DOWN 1 UP 2 LEFT 3 RIGHT
        while(x >= 0 && x < n && y >= 0 && y < m) {
            length ++;
            char curChar = grid[x].charAt(y);
            if (curChar == '.') {
                switch(direction) {
                    case 0: // DOWN
                        x += 1;
                        break;
                    case 1: // UP
                        x -= 1;
                        break;
                    case 2:  // LEFT
                        y -= 1;
                        break;
                    case 3: // RIGHT
                        y += 1;
                        break;
                    default:
                        x= -1;
                }
            } else if (curChar == 'L') {
                switch(direction) {
                    case 0: // DOWN
                        y += 1;
                        direction = 3; // RIGHT
                        break;
                    case 1: // UP
                        y -= 1;
                        direction = 2; // LEFT
                        break;
                    case 2:  // LEFT
                        x -= 1;
                        direction = 1; // UP
                        break;
                    case 3: // RIGHT
                        x += 1;
                        direction = 0; // DOWN
                        break;
                    default:
                        x = -1;
                }
            } else if (curChar == 'R') {
                switch(direction) {
                    case 0: // DOWN
                        y -= 1;
                        direction = 2; // LEFT
                        break;
                    case 1: // UP
                        y += 1;
                        direction = 3; // RIGHT
                        break;
                    case 2:  // LEFT
                        x += 1;
                        direction = 0; // DOWN
                        break;
                    case 3: // RIGHT
                        x -= 1;
                        direction = 1; // UP
                    default:
                        break;
                }
            }
        }
        return length;
    }

    //554.整理书架
    //难度：中等
    //题目介绍：https://tianchi.aliyun.com/oj/problems/mrktnfi7odgml6pr?spm=5176.15228502.0.0.489f79bf0mxc6Z
    //执行测试用例通过，提交代码答案错误
    public List<List<Integer>> combinations(List<Integer> list, int k) {
        if (k == 0 || list.isEmpty()) {//去除K大于list.size的情况。即取出长度不足K时清除此list
            return Collections.emptyList();
        }
        if (k == 1) {//递归调用最后分成的都是1个1个的，从这里面取出元素
            return list.stream().map(e -> Stream.of(e).collect(toList())).collect(toList());
        }
        Map<Boolean, List<Integer>> headAndTail = split(list, 1);
        List<Integer> head = headAndTail.get(true);
        List<Integer> tail = headAndTail.get(false);
        List<List<Integer>> c1 = combinations(tail, (k - 1)).stream().map(e -> {
            List<Integer> l = new ArrayList<>();
            l.addAll(head);
            l.addAll(e);
            return l;
        }).collect(toList());
        List<List<Integer>> c2 = combinations(tail, k);
        c1.addAll(c2);
        return c1;
    }

    /**
     *根据n将集合分成两组
     **/
    public Map<Boolean, List<Integer>> split(List<Integer> list, int n) {
        return IntStream
                .range(0, list.size())
                .mapToObj(i -> new AbstractMap.SimpleEntry<>(i, list.get(i)))
                .collect(partitioningBy(entry -> entry.getKey() < n, mapping(AbstractMap.SimpleEntry::getValue, toList())));
    }

    public boolean isLargerArray(List<Integer> minList, List<Integer> curList) {
        int listSize = Math.min(minList.size(), curList.size());
        for(int i = 0; i < listSize; i ++) {
            if (curList.get(i) > minList.get(i)) {
                return true;
            } else if (curList.get(i) < minList.get(i)) {
                return false;
            }
        }
        return false;
    }

    public int[] arrangeBookshelf(int[] order, int limit) {
        // 数字及出现位置
        Map<Integer, List<Integer>> mapPos = new HashMap<Integer, List<Integer>>();
        for(int i = 0;i < order.length;i ++) {
            if (mapPos.containsKey(order[i])) {
                List<Integer> list = mapPos.get(order[i]);
                list.add(i);
                mapPos.replace(order[i], list);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                mapPos.put(order[i], list);
            }
        }

        // 数字及需移去位置
        Map<Integer, List<List<Integer>>> removePos = new HashMap<Integer, List<List<Integer>>>();
        for(Integer key : mapPos.keySet()) {
            List<Integer> posList = mapPos.get(key);
            if (posList.size() > limit) {
                removePos.put(key, combinations(posList, posList.size() - limit));
            }
        }

        if (removePos.size() == 0) { // 如果不需要移除，直接返回
            return order;
        } else { // 需要移除，判断最小数组
            List<Integer> minOrder = new ArrayList<Integer>();
            List<Integer> allKeyRemoved = new ArrayList<Integer>();
            for(Integer key : removePos.keySet()) {
                List<Integer> curRemovedPos = new ArrayList<Integer>();
                for(List<Integer> posList : removePos.get(key)) {
                    List<Integer> removedOrder = new ArrayList<Integer>();
                    for(int i = 0;i < order.length;i ++) {
                        if (!posList.contains(i) && !allKeyRemoved.contains(i) ) {
                            removedOrder.add(order[i]);
                        }
                    }
                    if (minOrder.size() == 0) {
                        minOrder = removedOrder;
                        curRemovedPos = posList;
                    } else if (false == isLargerArray(minOrder, removedOrder)) {
                        minOrder = removedOrder;
                        curRemovedPos = posList;
                    }
                }
                allKeyRemoved.addAll(curRemovedPos);
            }
            int[] resultOrder = new int[minOrder.size()];
            for(int i = 0;i < resultOrder.length;i ++) {
                resultOrder[i] = minOrder.get(i);
            }
            return  resultOrder;
        }
    }

    //555.意外惊喜
    //难度：困难
    //题目介绍：https://tianchi.aliyun.com/oj/problems/zzxdpdrkgpp5rxwt
    //留待以后完成（本人算法功力尚浅，还需多多修炼，才能解此难题）
}
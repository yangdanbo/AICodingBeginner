##########################################
#天池在线编程LeetCode公开题库Python3版代码
#
# Author：dumbellyang
# Email:yangdanbo@163.com
# Weixin:dumbellyang
# Date:2022-11-07
#
# 部分代码系参考或复制网友代码修改或从网友Python3版翻译而成，特此致谢
##########################################################
import math
import re
import sys
from typing import List

class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    #1. 两数之和
    #难度：简单
    #题目介绍：https://tianchi.aliyun.com/oj/problems/tb09mi1apgpusvjf?spm=5176.15228502.0.0.175f79bfR5GqFe
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        for i in range(len(nums) -1):
            for j in range(i + 1,len(nums)):
                if target == nums[i] + nums[j]:
                    return [i,j]

    #3. 无重复字符的最长子串
    #难度：中等
    #题目介绍：https://tianchi.aliyun.com/oj/problems/bi20zeeh72by5ocn?spm=5176.15228502.0.0.553779bfGSPQKl
    #执行测试用例通过，提交代码报答案错误
    def lengthOfLongestSubstring(self, s: str) -> int:
        if s is None or len(s) == 0:
            return 0
        elif len(s) == 1:
            return 1
        else:
            set = []
            lastMaxLength = 1
            maxLength = 1
            set.append(s[0])
            for index in range(1,len(s)):
                if s[index] not in set:
                    set.append(s[index])
                    maxLength += 1
                else:
                    if maxLength > lastMaxLength:
                        lastMaxLength = maxLength
                    set = []
                    set.append(s[index])
                    maxLength = 1

            return max(maxLength, lastMaxLength)

    #14. 三数之和
    #难度：中等
    #题目介绍：https://tianchi.aliyun.com/oj/problems/pnopmkylog3uyrjd?spm=5176.15228502.0.0.3c4879bfu4HV2X
    #执行测试用例通过，提交代码报超时
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        result = []
        if nums is None or len(nums) < 3:
            return result
        elif len(nums) == 3:
            if nums[0] + nums[1] + nums[2] == 0:
                subList = [nums[0], nums[1], nums[2]]
                subList.sort()
                result.append(subList)
                return result
            else:
                return result
        else:
            for i in range(0, len(nums) - 2):
                for j in range(i + 1, len(nums) - 1):
                    for k in range(j + 1, len(nums)):
                        if nums[i] + nums[j] + nums[k] == 0:
                            subList = [nums[i], nums[j], nums[k]]
                            subList.sort()
                            if len(result) == 0:
                                result.append(subList)
                            else:
                                if subList not in result:
                                    result.append(subList)

            result.sort()
            return result

        #15. 最接近的三数之和
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/wivezmol9fystagw?spm=5176.15228502.0.0.c98579bfSkIBJI
        #执行测试用例通过，提交代码报超时
        def threeSumClosest(self, nums: List[int], target: int) -> int:
            if nums is None or len(nums) < 3:
                return 0
            elif len(nums) == 3:
                return nums[0] + nums[1] + nums[2]
            else:
                sum = sys.maxsize
                for i in range(0, len(nums) - 2):
                    for j in range(i + 1, len(nums) - 1):
                        for k in range(j + 1, len(nums)):
                            if abs(nums[i] + nums[j] + nums[k] - target) < abs(sum - target):
                                sum = nums[i] + nums[j] + nums[k]
                return sum

        #18.删除链表的倒数第N个结点
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/hptcnuzyyl68fswf?spm=5176.15228502.0.0.346079bfM0Lrph
        #执行测试用例通过，提交代码报答案错误
        def removeNthFromEnd(self, head: ListNode, n: int) -> ListNode:
            list = []
            list.append(head)
            p = head.next
            while p is not None:
                list.append(p)
                p = p.next

            if n >= len(list):
                return None
            else:
                removed = list[len(list) - n]
                pre = list[len(list) - n - 1]
                pre.next = removed.next

                return list[0]

        #19.有效的括号
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/xd7eft4mjgewso6o?spm=5176.15228502.0.0.7d1579bf3VtION
        def isValid(self, s: str) -> bool:
            if len(s) == 0 or len(s) > 10000:
                return False
            stack = []
            for c in s:
                if c == '(' or c == '{' or c == '[':
                    stack.append(c)
                else:
                    if len(stack) == 0:
                        return False

                    if c == ')':

                        if stack[-1] != '(':
                            return False
                        else:
                            stack.pop()
                    elif c == '}':
                        if stack[-1] != '{':
                            return False
                        else:
                            stack.pop()
                    elif c == ']':
                        if stack[-1] != '[':
                            return False
                        else:
                            stack.pop()
                    else:
                        return False

            return len(stack) == 0

        #20.合并两个有序链表
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/unvlfxdhnzwwycol?spm=5176.15228502.0.0.7d1579bfTOM8MO
        def mergeTwoLists(self, list1: Optional[ListNode], list2: Optional[ListNode]) -> Optional[ListNode]:
            if list1 is None and list2 is None:
                return None
            elif list2 is None:
                return list1
            elif list1 is None:
                return list2
            else:
                head = ListNode(-1)
                curr = head
                while list1 and list2:
                    if list1.val > list2.val:
                        curr.next = list2
                        list2 = list2.next
                        curr = curr.next
                    else:
                        curr.next = list1
                        list1 = list1.next
                        curr = curr.next
                if list1:
                    curr.next = list1
                if list2:
                    curr.next = list2
            return head.next

        #25.删除有序数组中的重复项
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/d4vzjccmh23oz0sn?spm=5176.15228502.0.0.7d1579bfLnHyV0
        def removeDuplicates(self, nums: List[int]) -> int:
            length = len(nums)
            duplications = 0
            for i in range(-1, -len(nums), -1):
                if nums[i] == nums[i - 1]:
                    for j in range(i - 1, - 1 - duplications):
                        nums[j] = nums[j + 1]
                    duplications += 1

            return length - duplications

        #26.移除元素
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/aehayfm87hpoizhs?spm=5176.15228502.0.0.7d1579bfMazMQE
        def removeElement(self, nums: List[int], val: int) -> int:
            length = len(nums)
            duplications = 0
            for i in range(-1, -len(nums) - 1, -1):
                if nums[i] == val:
                    for j in range(i, - 1 - duplications):
                        nums[j] = nums[j + 1]
                    duplications += 1

            return length - duplications

        #30.搜索旋转排序数组
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/09bfut1prcd83wi2?spm=5176.15228502.0.0.7d1579bfEB5sdJ
        def search(self, nums: List[int], target: int) -> int:
            if nums is None or len(nums) == 0:
                return -1
            else:
                for i in range(len(nums)):
                    if nums[i] == target:
                        return i
                return -1

        #33.有效的数独
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/ujkbi1ha5crf9wts?spm=5176.15228502.0.0.7d1579bfp28EfL
        def isValidSudoku(self, board: List[List[str]]) -> bool:
            for row in range(9):
                set = []
                for i in range(len(board[row])):
                    if board[row][i] != ".":
                        if int(board[row][i]) >= 1 and int(board[row][i]) <= 9:
                            if board[row][i] in set:
                                return False
                            else:
                                set.append(board[row][i])

            for col in range(9):
                data = []
                set = []
                for row in range(9):
                    data.append(board[row][col])

                for i in range(len(data)):
                    if data[i] != ".":
                        if int(data[i]) >= 1 and int(data[i]) <= 9:
                            if data[i] in set:
                                return False
                            else:
                                set.append(data[i])

            for rowArea in range(3):
                for colArea in range(3):
                    data = []
                    set = []
                    for i in range(9):
                        data.append(board[rowArea * 3 + int(i / 3)][colArea * 3 + i % 3])

                    for i in range(len(data)):
                        if data[i] != ".":
                            if int(data[i]) >= 1 and int(data[i]) <= 9:
                                if data[i] in set:
                                    return False
                                else:
                                    set.append(data[i])

            return True

        #46.最大子数组和
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/gtahxhmx1r6etpzj?spm=5176.15228502.0.0.7d1579bf0Klso7
        #执行测试用例通过，提交代码报答案错误
        def maxSubArray(self, nums: List[int]) -> int:
            if nums is None:
                return 0
            elif len(nums) == 1:
                return nums[0]
            else:
                sum = 0
                subSum = 0
                for i in range(0, len(nums)):
                    if nums[i] > 0:
                        if subSum != 0:
                            subSum += nums[i]
                            if subSum > 0:
                                sum += subSum
                                subSum = 0
                        else:
                            sum += nums[i]
                    else:
                        if sum > 0:
                            if sum + nums[i] > 0:
                                subSum += nums[i]
                            else:
                                sum = 0
                                subSum = 0
                if subSum > 0:
                    return sum + subSum
                else:
                    return sum

        #53.旋转链表
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/jv8espkxwpdbowby?spm=5176.15228502.0.0.7d1579bfzz3Sch
        def rotateRight(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
            if head is None:
                return None

            list = []
            list.append(head)
            p = head.next
            while p is not None:
                list.append(p)
                p = p.next

            if len(list) == 1 or k % len(list) == 0:
                return head
            else:
                newHead = len(list) - k % len(list)
                list[newHead - 1].next = None
                list[len(list) - 1].next = head

                return list[newHead]

        #66.子集
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/wovfpppchwero2co?spm=5176.15228502.0.0.7d1579bfuJd8sq
        def subsets(self, nums: List[int]) -> List[List[int]]:
            q = [[]]
            n = len(nums)
            for i in range(n):
                for j in range(len(q)):
                    q.append(q[j] + [nums[i]])
            return q

        #70. 删除排序链表中的重复元素 II
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/xahdotanskuosvsu?spm=5176.15228502.0.0.7d1579bfZL8bgZ
        def deleteDuplicates(self, head: ListNode) -> ListNode:
            dummy = ListNode(-1, head)
            p = dummy
            cur = head
            while cur is not None and cur.next is not None:
                if cur.next is not None and cur.val != cur.next.val:
                    p.next = cur
                    p = p.next
                else:
                    while cur.next is not None and cur.val == cur.next.val:
                        cur = cur.next

                cur = cur.next

            p.next = cur
            return dummy.next

        #71.删除排序链表中的重复元素
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/yoacnc8povsmm617?spm=5176.15228502.0.0.7d1579bfOe8V6o
        def deleteDuplicates(self, head: ListNode) -> ListNode:
            cur = head
            while cur is not None and cur.next is not None:
                if cur.next is not None and cur.val != cur.next.val:
                    cur = cur.next
                else:
                    while cur is not None and cur.next is not None and cur.val == cur.next.val:
                        cur.next = cur.next.next

            return head

        #73.合并两个有序数组
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/lx6nvsaogkbcybaa?spm=5176.15228502.0.0.7d1579bfOdkdb9
        #执行测试用例通过，提交代码报答案错误
        def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
            """
            Do not return anything, modify nums1 in-place instead.
            """
            if n == 0:
                pass
            elif m == 0 and n != 0:
                for i in range(n):
                    nums1[i] = nums2[i]
            else:
                for i in range(n):
                    cur = nums2[i]
                    isMerged = False
                    for j in range(n + m):
                        if isMerged:
                            continue

                        if nums1[j] > cur or nums1[j] == 0:
                            for k in range(m + n - 1, j, -1):
                                nums1[k] = nums1[k - 1]

                            nums1[j] = cur
                            isMerged = True

        #78.二叉树的中序遍历
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/uq20sgfhciktiwcl?spm=5176.15228502.0.0.7d1579bfjo6DJ3
        def inorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
            ans = []

            def dfs(root):
                if not root:
                    return
                dfs(root.left)
                ans.append(root.val)
                dfs(root.right)

            dfs(root)
            return ans

        #85.对称二叉树
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/smgovhcnqtcrsloz?spm=5176.15228502.0.0.7d1579bfJQFlBp
        def isSymmetric(self, root: TreeNode) -> bool:
            if not root:
                return True

            # 比较原则，(left_node.left,right_node.right)和(left_node.right,right_node.left)
            # 两种情况是不对称的：第一种：两个点中空一个；第二种：两个点值不相等
            # 当两个节点都为空时，已经到达了叶子节点，且包括叶子节点以及之前的节点都不满不对称的情况

            def compare(left_node, right_node):
                #  两个节点都为空
                if not (left_node or right_node):
                    return True
                #  两点空一个
                if not (left_node and right_node):
                    return False
                #  两个值不相等
                if left_node.val != right_node.val:
                    return False

                return compare(left_node.left, right_node.right) and compare(left_node.right, right_node.left)

            return compare(root.left, root.right)

        #88.二叉树的最大深度
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/uamsse3zi2vsg7sm?spm=5176.15228502.0.0.7d1579bfPcsPPI
        def maxDepth(self, root: Optional[TreeNode]) -> int:
            if not root:
                return 0
            return 1 + max(self.maxDepth(root.left), self.maxDepth(root.right))

        #89.从前序与中序遍历序列构造二叉树
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/il7nnjonzxtzjxt7?spm=5176.15228502.0.0.123a79bfcBL3sp
        def buildTree(self, preorder: List[int], inorder: List[int]) -> TreeNode:
            if not (preorder and inorder):
                return None
                # 根据前序数组的第一个元素，就可以确定根节点
            root = TreeNode(preorder[0])
            # 用preorder[0]去中序数组中查找对应的元素
            mid_idx = inorder.index(preorder[0])
            # 递归的处理前序数组的左边部分和中序数组的左边部分
            # 递归处理前序数组右边部分和中序数组右边部分
            root.left = self.buildTree(preorder[1:mid_idx + 1], inorder[:mid_idx])
            root.right = self.buildTree(preorder[mid_idx + 1:], inorder[mid_idx + 1:])
            return root

        #90.从中序与后序遍历序列构造二叉树
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/vbne3ulq7locs7y8?spm=5176.15228502.0.0.123a79bfg91oww
        def buildTree(self, inorder: List[int], postorder: List[int]) -> TreeNode:
            if not inorder:
                return None
            root = TreeNode(postorder[-1])
            index = inorder.index(root.val)
            root.left = self.buildTree(inorder[:index], postorder[:index])
            root.right = self.buildTree(inorder[index + 1:], postorder[index:-1])
            return root

        #92.将有序数组转换为二叉搜索树
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/khmlb3lvp4jyxxm7?spm=5176.15228502.0.0.123a79bf5eq68p
        def sortedArrayToBST(self, nums: List[int]) -> TreeNode:
            def helper(nums: List[int], left: int, right: int) -> TreeNode:
                if left >= right:
                    return None

                mid = (left + right) // 2
                root = TreeNode(nums[mid])
                root.left = helper(nums, left, mid)
                root.right = helper(nums, mid + 1, right)
                return root

            return helper(nums, 0, len(nums))

        #95.二叉树的最小深度
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/7mgbglyvtl9pi3eu?spm=5176.15228502.0.0.123a79bfOOZHSh
        def minDepth(self, root: TreeNode) -> int:
            if not root:
                return 0
            if not root.left and not root.right:
                return 1
            min_depth_left, min_depth_right = 10000000000, 1000000000000
            if root.left:
                min_depth_left = self.minDepth(root.left)
            if root.right:
                min_depth_right = self.minDepth(root.right)
            return 1 + min(min_depth_left, min_depth_right)

        #96.路径总和
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/hg6yjn4lzctettgx?spm=5176.15228502.0.0.123a79bfZmEVWe
        def hasPathSum(self, root: Optional[TreeNode], targetSum: int) -> bool:
            if not root:
                return False
            queue_node = [root]
            queue_val = [root.val]
            while queue_node:
                cur_node = queue_node.pop(0)
                cur_val = queue_val.pop(0)
                # 来到叶子结点
                if not cur_node.left and not cur_node.right:
                    if cur_val == targetSum:
                        return True

                if cur_node.left:
                    queue_node.append(cur_node.left)
                    queue_val.append(cur_val + cur_node.left.val)
                if cur_node.right:
                    queue_node.append(cur_node.right)
                    queue_val.append(cur_val + cur_node.right.val)
            return False

        #104.买卖股票的最佳时机
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/uv7fotvgoxupupwp?spm=5176.15228502.0.0.123a79bfeOdLXd
        def maxProfit(self, prices: List[int]) -> int:
            profit_0 = 0
            profit_1 = -prices[0]
            for i in range(1, len(prices)):
                profit_0 = max(profit_0, profit_1 + prices[i])
                profit_1 = max(profit_1, -prices[i])
            return profit_0

        #105.买卖股票的最佳时机II
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/roallxl8v6paeyr5?spm=5176.15228502.0.0.123a79bftMwktO
        def maxProfit2(self, prices: List[int]) -> int:
            profit_0 = 0
            profit_1 = -prices[0]
            for i in range(1, len(prices)):
                profit_0 = max(profit_0, profit_1 + prices[i])
                profit_1 = max(profit_1, profit_0 - prices[i])
            return profit_0

        #107.验证回文串
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/wgsaps9giaxxitna?spm=5176.15228502.0.0.123a79bfRySnqo
        def isPalindrome(self, s: str) -> bool:
            newStr = re.findall(r"[a-zA-Z0-9]", s.lower())
            for i in range(len(newStr) // 2):
                if newStr[i] != newStr[len(newStr) - 1 - i]:
                    return False
            return True

        #114.只出现一次的数字
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/u0huybhfvm45ezvl?spm=5176.15228502.0.0.123a79bfORddEO
        def singleNumber(self, nums: List[int]) -> int:
            nums.sort()
            for i in range(0, len(nums), 2):
                if i == len(nums) - 1:
                    return nums[i]
                if nums[i] != nums[i + 1]:
                    return nums[i]

            return -1

        #115.只出现一次的数字II
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/ivlew1cgd8e4u3pt?spm=5176.15228502.0.0.123a79bf3bjOIy
        def singleNumber2(self, nums: List[int]) -> int:
            nums.sort()
            for i in range(0, len(nums), 3):
                if i == len(nums) - 1:
                    return nums[i]
                if nums[i] != nums[i + 1]:
                    return nums[i]

            return -1

        #118.环形链表
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/vedahxsx1b8jqnx4?spm=5176.15228502.0.0.123a79bfJbcCqi
        def hasCycle(self, head: Optional[ListNode]) -> bool:
            slow = head
            fast = head
            while fast and fast.next:
                slow = slow.next
                fast = fast.next.next
                if slow == fast:
                    return True
            return False

        #124.对链表进行插入排序
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/db7vjfolki5obklx?spm=5176.15228502.0.0.123a79bfpnoGxw
        def insertionSortList(self, head: ListNode) -> ListNode:
            if not head:
                return head
            # 用于链接新列表
            new_head = ListNode(0)
            # cur 用于拿出元素
            cur = head
            # 对原列表进行排序
            while cur:
                # 标志位，标志是否遍历到new_head最后，还没把cur插入进去
                target = 0
                # 每次插入new_head都要从头开始
                temp_head = new_head
                # 将新值插入new_head
                while temp_head.next:
                    # 如果下一个为空则直接链接cur
                    if temp_head.next.val >= cur.val:
                        # 将temp_head下一个变成cur
                        next_next = temp_head.next
                        temp_head.next = cur
                        cur = cur.next
                        temp_head.next.next = next_next
                        target = 1
                        break
                        # temp_head下一个已经是变成cur，但是后面要接上temp_head.next（cur).next
                    temp_head = temp_head.next
                if target == 0:
                    temp_head.next = cur
                    cur = cur.next
                    temp_head.next.next = None
            return new_head.next

        #125.排序链表
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/4ro2fmd7uk5irb2f?spm=5176.15228502.0.0.123a79bfqAAaDH
        #执行测试用例通过，提交代码超时
        def sortList(self, head: Optional[ListNode]) -> Optional[ListNode]:
            # 寻找链表中点(奇数：中点；偶数：上中点)
            def find_mid(head):
                slow = head
                fast = head.next
                while fast.next and fast.next.next:
                    slow = slow.next  # 慢指针每次走一步
                    fast = fast.next.next  # 快指针每次走两步

                return slow

            # 合并两个链表，使其有序
            def mergeTwoLinkList(link1, link2):
                dummyHead = ListNode(-1)
                p = dummyHead
                while link1 and link2:
                    if link1.val < link2.val:
                        link1Next = link1.next
                        p.next = link1
                        link1 = link1Next
                    else:
                        link2Next = link2.next
                        p.next = link2
                        link2 = link2Next
                        p = p.next
                if link1:
                    p.next = link1
                if link2:
                    p.next = link2
                return dummyHead.next

            # 主函数入口
            def mergeSortLinkList(head):
                if not head or not head.next:
                    return head
                left_end = find_mid(head)
                mid = left_end.next
                # 切开
                left_end.next = None

                # 递归
                left = mergeSortLinkList(head)
                right = mergeSortLinkList(mid)

                return mergeTwoLinkList(left, right)

            return mergeSortLinkList(head)

        #126.逆波兰表达式求值
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/186c5gggr2eaxetm?spm=5176.15228502.0.0.123a79bflofUy8
        def evalRPN(self, tokens: List[str]) -> int:
            stack = []
            for ch in tokens:
                # 操作符
                if ch in '+-*/':
                    a = stack.pop()
                    b = stack.pop()
                    if ch == '+':
                        res = b + a
                    elif ch == '-':
                        res = b - a
                    elif ch == '*':
                        res = b * a
                    elif ch == '/':
                        res = int(b / a)  # Python负数除法有点怪
                    stack.append(res)
                # 操作数
                else:
                    stack.append(int(ch))

            return stack.pop()

        #127.翻转字符串里的单词
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/r2frxq5r8blqzrdp?spm=5176.15228502.0.0.123a79bfk2kXWg
        def reverseWords(self, s: str) -> str:
            split = s.split(" ")
            split.reverse()
            print(split)
            count = 0
            reverse = ""
            for i in range(len(split)):
                if len(split[i]) > 0:
                    count += 1
                    if count > 1:
                        reverse += " "

                    reverse += split[i]
            return reverse

        #130.最小栈
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/0f5efm1lgkc71qit?spm=5176.15228502.0.0.123a79bfJzk7fR
        class MinStack:
            def __init__(self):
                self.stack = []
                self.min_stack = [math.inf]

            def push(self, val: int) -> None:
                self.stack.append(val)
                self.min_stack.append(min(val, self.min_stack[-1]))

            def pop(self) -> None:
                self.stack.pop()
                self.min_stack.pop()

            def top(self) -> int:
                return self.stack[-1]

            def getMin(self) -> int:
                return self.min_stack[-1]

        # Your MinStack object will be instantiated and called as such:
        # obj = MinStack()
        # obj.push(val)
        # obj.pop()
        # param_3 = obj.top()
        # param_4 = obj.getMin()

        #131.相交链表
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/scqretszaejgvugn?spm=5176.15228502.0.0.560979bfaVi0Rv
        #执行测试用例通过，提交代码报答案错误
        def getIntersectionNode(self, headA: ListNode, headB: ListNode) -> ListNode:
            nodes = []
            p = headA;
            while p is not None:
                nodes.append(p.val)
                p = p.next

            p = headB
            while p is not None:
                if p.val in nodes:
                    return p

            return None

        #135.Excel表列名称
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/d5i8ergo2ibym9iv?spm=5176.15228502.0.0.560979bf3xloSD
        def convertToTitle(self, columnNumber: int) -> str:
            column = ""
            while columnNumber > 0:
                curCol = columnNumber % 26
                if curCol == 0:
                    curCol = 26

                column = chr((int)(64 + curCol)) + column
                columnNumber = (columnNumber - 1) // 26

            return column

        #136.多数元素
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/poadhl6o42bnlokn?spm=5176.15228502.0.0.560979bfPgf71N
        def majorityElement(self, nums: List[int]) -> int:
            nums.sort()
            return nums[len(nums) // 2]

        #139.二叉搜索树迭代器
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/fjjnoehktyxlctbg?spm=5176.15228502.0.0.560979bfNRRMng
        class BSTIterator:
            def __init__(self, root: TreeNode):
                self.root = root
                # 初始化保存一个不存在的数字，这里选择0，并没有选择无穷小
                self.ls = [0]
                self.bstIterator(self.root, self.ls)
                self.cnt = 0

            def next(self) -> int:
                self.cnt += 1
                res = self.ls[self.cnt]
                return res

            def hasNext(self) -> bool:
                if self.ls[self.cnt + 1:]:
                    return True
                return False

            def bstIterator(self, root, ls):
                # 值从小到大顺序是中序遍历的值，只需将中序遍历的值保存
                if not root:
                    return
                self.bstIterator(root.left, ls)
                ls.append(root.val)
                self.bstIterator(root.right, ls)

        # Your BSTIterator object will be instantiated and called as such:
        # obj = BSTIterator(root)
        # param_1 = obj.next()
        # param_2 = obj.hasNext()

        #148.移除链表元素
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/vzj6xr0txtcnvj55?spm=5176.15228502.0.0.560979bfruIZyO
        def removeElements(self, head: ListNode, val: int) -> ListNode:
            if not head:
                return None
            elif not head.next:
                if head.val == val:
                    return None
                else:
                    return head
            else:
                newHead = ListNode
                newHead.next = head
                p = newHead
                while head:
                    if head.val == val:
                        p.next = head.next
                    else:
                        p = head
                    head = head.next

                return newHead.next

        #151.反转链表
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/uftes6dzntjq0rtr?spm=5176.15228502.0.0.560979bfoXLwMG
        def reverseList(self, head: ListNode) -> ListNode:
            if not head or not head.next:
                return head
            elif not head.next.next:
                tail = head.next
                head.next = None
                tail.next = head
                return tail
            else:
                remains = head.next
                tail = self.reverseList(remains)
                p = tail
                while p.next:
                    p = p.next

                p.next = head
                head.next = None

                return tail

        #160.存在重复元素
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/yul8zd4jroh3htcb?spm=5176.15228502.0.0.560979bf8mDYwX
        #执行测试用例通过，提交代码答案错误
        def containsDuplicate(self, nums: List[int]) -> bool:
            if not nums or len(nums) == 1:
                return False
            else:
                nums.sort()
                for i in range(len(nums) - 2):
                    if nums[i] == nums[i + 1]:
                        return True
                return False

        #161.存在重复元素II
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/2dxe8bmzwpnqyxso?spm=5176.15228502.0.0.560979bfe2ipbb
        #执行测试用例通过，提交代码答案错误
        def containsNearbyDuplicate(self, nums: List[int], k: int) -> bool:
            if not nums or len(nums) == 1:
                return False
            else:
                dict = {}
                for i in range(len(nums)):
                    if nums[i] in dict:
                        pos = dict[nums[i]]
                        return i - pos >= k
                    else:
                        dict[nums[i]] = i
                return False

        #167.基本计算器II
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/4sfcxpjuwqla9vw8?spm=5176.15228502.0.0.560979bfP16BpA
        def calculate(self, s: str) -> int:
            n = len(s)
            stack = []
            preSign = '+'
            num = 0
            for i in range(n):
                if s[i] != ' ' and s[i].isdigit():
                    num = num * 10 + ord(s[i]) - ord('0')
                if i == n - 1 or s[i] in '+-*/':
                    if preSign == '+':
                        stack.append(num)
                    elif preSign == '-':
                        stack.append(-num)
                    elif preSign == '*':
                        stack.append(stack.pop() * num)
                    else:
                        stack.append(int(stack.pop() / num))
                    preSign = s[i]
                    num = 0
            return sum(stack)

        #170.二叉搜索树中第K小的元素
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/drfy3jrpkalumkua?spm=5176.15228502.0.0.560979bfONAd7f
        #执行测试用例通过，提交代码答案错误
        def kthSmallest(self, root: Optional[TreeNode], k: int) -> int:
            nums = []

            def DFS(root):
                if (not root):
                    return

            DFS(root.left)
            nums.append(root.val)
            DFS(root.right)
            DFS(root)
            return nums[k - 1]

        #171.2的幂
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/81xnysijoondtbwq?spm=5176.15228502.0.0.560979bfHWOm36
        def isPowerOfTwo(self, n: int) -> bool:
            if n == 1 or n == 2:
                return True
            elif n > 2:
                if n % 2 == 1:
                    return False
                return self.isPowerOfTwo(n // 2)
            else:
                return False

        #181.只出现一次的数字III
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/ifhiana5hpqc9uyz?spm=5176.15228502.0.0.560979bf0N0T0j
        def singleNumber3(self, nums: List[int]) -> List[int]:
            if not nums or len(nums) <= 2:
                return nums
            else:
                nums.sort()
                result = []
                for i in range(len(nums)):
                    if i == 0 and nums[i] != nums[i + 1]:
                        result.append(nums[i])
                        if len(result) == 2:
                            break
                    if i == len(nums) - 1 and nums[i] != nums[i - 1]:
                        result.append(nums[i])
                        if len(result) == 2:
                            break

                    if i > 0 and i < len(nums) - 1 and nums[i] != nums[i - 1] and nums[i] != nums[i + 1]:
                        result.append(nums[i])
                        if len(result) == 2:
                            break

                return result

        #224.字符串解码
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/bcadlk8qgwi5oi26?spm=5176.15228502.0.0.560979bfVmghU0
        def decodeString(self, s: str) -> str:
            stack = []
            for c in s:
                if c != "]":
                    stack.append(c)
                else:
                    tmp = ""
                    while stack[-1] != "[":
                        tmp = stack.pop(-1) + tmp
                    stack.pop(-1)
                    times = ""
                    while stack and stack[-1].isdigit():
                        times = stack.pop(-1) + times
                    times = int(times)
                    tmp = "".join(tmp for _ in range(times))
                    for t in tmp:
                        stack.append(t)
            return "".join(stack)

        #277.最长回文子序列
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/vv7o2js8dpgov2vi?spm=5176.15228502.0.0.560979bfnhFeSP
        def longestPalindromeSubseq(self, s: str) -> int:
            n = len(s)
            # dp[i][j]表示字符串中从i到j之间的最长回文子序列,i<j
            dp = [[0 for _ in range(n)] for _ in range(n)]
            for i in range(n - 1, -1, -1):
                dp[i][i] = 1  # 初始条件
                for j in range(i + 1, n):
                    if s[i] == s[j]:
                        dp[i][j] = dp[i + 1][j - 1] + 2
                    else:
                        dp[i][j] = max(dp[i + 1][j], dp[i][j - 1])
            return dp[0][n - 1]

        #329.验证回文字符串Ⅱ
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/oqishxhcycspqheq?spm=5176.15228502.0.0.34d579bfzNj4OV
        #执行测试用例通过，提交代码答案错误
        def validPalindrome(self, s: str) -> bool:
            def isPalindromStr(newStr: str) -> bool:
                for i in range(len(newStr) // 2):
                    if newStr[i] != newStr[len(newStr) - 1 - i]:
                        return False
                return True

            if isPalindromStr(s):
                return True
            else:
                for i in range(len(s)):
                    if isPalindromStr(s.replace(s[i], "")):
                        return True
            return False

        #355.宝石与石头
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/y4fzhymgwtuntbsr?spm=5176.15228502.0.0.34d579bfbFZ1GC
        def numJewelsInStones(self, jewels: str, stones: str) -> int:
            count = 0
            for i in range(len(stones)):
                if stones[i] in jewels:
                    count += 1
            return count

        #360.二分查找
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/a5x3gdyeglz8dvrr?spm=5176.15228502.0.0.34d579bf5OEFBI
        def search2(self, nums: List[int], target: int) -> int:
            low = 0
            high = len(nums) - 1
            while low <= high:
                mid = (low + high) // 2
                if target == nums[mid]:
                    return mid

                if target > nums[mid]:
                    low = mid + 1

                if target < nums[mid]:
                    high = mid - 1

            return -1

        #374.子域名访问计数
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/zxvzcidnk0vmtbsu?spm=5176.15228502.0.0.34d579bfsVlv0k
        def subdomainVisits(self, cpdomains: List[str]) -> List[str]:
            countDict = {}
            for cp in cpdomains:
                pos = cp.index(" ")
                curCount = int(cp[0:pos])
                curDomains = cp[pos + 1:len(cp)].split(".")
                domain = ""
                for curDomain in reversed(curDomains):
                    if domain == "":
                        domain = curDomain
                    else:
                        domain = curDomain + "." + domain

                    if domain in countDict:
                        countDict[domain] = countDict[domain] + curCount
                    else:
                        countDict[domain] = curCount

            result = []
            for key, value in countDict.items():
                result.append(str(value) + " " + key)

            return result

        #387.比较含退格的字符串
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/jjpfs012mrtphtgu?spm=5176.15228502.0.0.34d579bfCUWZf0
        #执行测试用例通过，提交代码答案错误
        def backspaceCompare(self, s: str, t: str) -> bool:
            def replaceBackspace(s: str) -> str:
                newStr = ""
                for i in range(len(s)):
                    if s[i] != '#':
                        newStr += s[i]
                    else:
                        if len(newStr) >= 1:
                            newStr = s[0: len(newStr) - 1]
                return newStr

            newS = replaceBackspace(s)
            newT = replaceBackspace(t)
            if len(newS) != len(newT):
                return False
            else:
                for i in range(len(newS)):
                    if newS[i] != newT[i]:
                        return False

                return True

        #410.排序数组
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/n7nfra3pmqjqpyww?spm=5176.15228502.0.0.34d579bfFjg2Oa
        def sortArray(self, nums: List[int]) -> List[int]:
            nums.sort()
            return nums

        #429.有序数组的平方
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/3tlxfwdolalefzxc?spm=5176.15228502.0.0.34d579bfoC59t8
        def sortedSquares(self, nums: List[int]) -> List[int]:
            nums = [x * x for x in nums]
            nums.sort()
            return nums

        #456.数组的相对排序
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/ogk9twmf2wjp3ydl?spm=5176.15228502.0.0.34d579bfRXc3yY
        #执行测试用例通过，提交代码答案错误
        def relativeSortArray(self, arr1: List[int], arr2: List[int]) -> List[int]:
            newArr = []
            for i in range(len(arr2)):
                for j in range(len(arr1)):
                    if arr1[j] == arr2[i]:
                        newArr.append(arr1[j])

            for i in range(len(arr1)):
                notExisted = True
                for j in range(len(arr2)):
                    if arr1[i] == arr2[j]:
                        notExisted = False
                        break

                if notExisted:
                    newArr.append(arr1[i])

            return newArr

        #459.最长公共子序列
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/q1tf6mtanr1nqdsi?spm=5176.15228502.0.0.34d579bfImtmYx
        def longestCommonSubsequence(self, text1: str, text2: str) -> int:
            n1 = len(text1)
            n2 = len(text2)
            dp = [[0] * (n2 + 1) for _ in range(n1 + 1)]  # dp[i][j]表示text1的前i个字符#与text2的前j个字符的最大公共子序列的长度
            for i in range(1, n1 + 1):
                for j in range(1, n2 + 1):
                    if text1[i - 1] == text2[j - 1]:
                        dp[i][j] = dp[i - 1][j - 1] + 1
                    else:
                        dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
            return dp[n1][n2]

        #551.天池测试 - 01
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/pftqnt1xqwdzbjxb?spm=5176.15228502.0.0.34d579bfME84sa
        #测试数据

        #552.统计链表奇数节点
        #难度：简单
        #题目介绍：https://tianchi.aliyun.com/oj/problems/gx8aqftwdqvxyhwe?spm=5176.15228502.0.0.489f79bfFRdhS3
        def numberEvenListNode(self, head: Optional[ListNode]) -> int:
            count = 0
            while head:
                if head.val % 2 == 1:
                    count += 1

                head = head.next

            return count

        #553.光线反射
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/vwogaxfzgnskyjcr?spm=5176.15228502.0.0.489f79bfq4S4Zq
        def getLength(self, grid: List[str]) -> int:
            n = len(grid)
            m = len(grid[0])
            length = 0
            x = 0
            y = 0
            direction = 0  # 0 DOWN 1 UP 2 LEFT 3 RIGHT
            while x >= 0 and x < n and y >= 0 and y < m:
                length += 1
                curChar = grid[x][y]
                if curChar == '.':
                    if direction == 0:  # DOWN
                        x += 1
                    elif direction == 1:  # UP
                        x -= 1
                    elif direction == 2:  # LEFT
                        y -= 1
                    elif direction == 3:  # RIGHT
                        y += 1
                    else:
                        x = -1
                elif curChar == 'L':
                    if direction == 0:  # DOWN
                        y += 1
                        direction = 3  # RIGHT
                    elif direction == 1:  # UP
                        y -= 1
                        direction = 2  # LEFT
                    elif direction == 2:  # LEFT
                        x -= 1
                        direction = 1  # UP
                    elif direction == 3:  # RIGHT
                        x += 1
                        direction = 0  # DOWN
                    else:
                        x = -1
                elif curChar == 'R':
                    if direction == 0:  # DOWN
                        y -= 1
                        direction = 2  # LEFT
                    elif direction == 1:  # UP
                        y += 1
                        direction = 3  # RIGHT
                    elif direction == 2:  # LEFT
                        x += 1
                        direction = 0  # DOWN
                    elif direction == 3:  # RIGHT
                        x -= 1
                        direction = 1  # UP
                    else:
                        x = -1

            return length

        #554.整理书架
        #难度：中等
        #题目介绍：https://tianchi.aliyun.com/oj/problems/mrktnfi7odgml6pr?spm=5176.15228502.0.0.489f79bf0mxc6Z
        #代码还没写

        #555.意外惊喜
        #难度：困难
        #题目介绍：https://tianchi.aliyun.com/oj/problems/zzxdpdrkgpp5rxwt
        #留待以后完成（本人算法及Python功力尚浅，解决此等问题还需继续修炼）


















































































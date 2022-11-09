#计算列表排列组合的Python方法
from typing import List
#整数阶乘
def factorial(n: int) -> int:
    if n <= 0:
        return 1
    else:
        return n * factorial(n - 1)

#全排列个数
def permutationCount(n:int) -> int:
    return factorial(n)

#n元素中取m个组合个数
def combinationCount(n:int, m:int) -> int:
    return factorial(n) // (factorial(m) * factorial(n-m))

# 子集列表
def subsets(nums: List[int]) -> List[List[int]]:
    q = [[]]
    n = len(nums)
    for i in range(n):
        for j in range(len(q)):
            q.append(q[j] + [nums[i]])
    return q

# n个数选m的组合列表
def combinations(nums: List[int], k: int) -> List[List[int]]:
    q = [[]]
    n = len(nums)
    for i in range(n):
        for j in range(len(q)):
            q.append(q[j] + [nums[i]])
    return [x for x in q if len(x) == k]

# 全排列
permList = []
def allPermutations(list, stack):
    # 列表中的元素都已加入，返回结果
    if not list:
        permList.append([x for x in stack])
    else:  # 没有到树的叶子节点的时候，使用递归继续往下找。
        for i in range(len(list)):
            stack.append(list[i])
            del list[i]
            allPermutations(list, stack)
            list.insert(i, stack.pop())

# 选择排列
permList2 = []
def permutations(n, begin, end):
    if begin >= end:
        permList2.append(n)
    else:
        i = begin
        for num in range(begin, end):
            n[num], n[i] = n[i], n[num]
            permutations(n, begin + 1, end)
            n[num], n[i] = n[i], n[num]

print(subsets([1, 2, 3, 4]), end="")
print(combinations([1, 2, 3, 4], 2), end="")
print(factorial(10))
print(permutationCount(3))
print(combinationCount(5,3))
allPermutations([1,2,3,4], [])
print(permList)
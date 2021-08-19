//https://leetcode.com/explore/challenge/card/august-leetcoding-challenge-2021/615/week-3-august-15th-august-21st/3903/
//Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such that the product of the sums of the subtrees is maximized.

//Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it modulo 109 + 7.

//Note that you need to maximize the answer before taking the mod and not after taking it.


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    class MyInteger{
        int val;
        public MyInteger(int a){
            val = a;
        }
    }
    
    public int maxProduct(TreeNode root) {
        //one dfs of bfs to find total
        long total = addAllValues(root);
        //one dfs to check every edge for product
        //monitor for max
        MyInteger sum = new MyInteger(0);
        long maxProduct = calculateProducts(root, total, sum);
        return (int)(maxProduct%1000000007);
    }
    
    public long addAllValues(TreeNode root) {
        if(root == null) return 0;
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        long total = 0;
        while(!stack.empty()){
            TreeNode currentNode = stack.pop();
            total += currentNode.val;
            if(currentNode.left != null)
                stack.push(currentNode.left);
            if(currentNode.right != null)
                stack.push(currentNode.right);
        }
        return total;
    }
    
    public long calculateProducts(TreeNode root, long total, MyInteger sum) {
        if(root == null) {
            sum = new MyInteger(0);
            return 0;
        }
        MyInteger sumLeft = new MyInteger(0);
        MyInteger sumRight = new MyInteger(0);
        long prod1 = calculateProducts(root.left, total, sumLeft);
        long prod2 = calculateProducts(root.right, total, sumRight);
        sum.val = (sumLeft.val+sumRight.val+root.val);
        long prod3 = sum.val * (total-sum.val);
        if(prod1 >= prod2 && prod1 >= prod3) return prod1;
        if(prod2 >= prod1 && prod2 >= prod3) return prod2;
        return prod3;
    }
}

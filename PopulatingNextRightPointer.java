/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        Queue<Node> queue = new LinkedList();
        queue.add(root);
        queue.add(null);
        Node prevNode = null;
        while(queue.size() != 0){
            Node cn = queue.poll();
            if(prevNode != null) prevNode.next = cn;
            prevNode = cn;
            if(cn == null){ 
                if(queue.size() != 0)
                    queue.add(null);
                continue;
            }
            if(cn.left != null) queue.add(cn.left);
            if(cn.right != null) queue.add(cn.right);
            
        }
        return root;
    }
}

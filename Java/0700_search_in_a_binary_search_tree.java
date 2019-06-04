/*
给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。

例如，给定二叉搜索树:
        4
       / \
      2   7
     / \
    1   3
   
    和值: 2, 你应该返回如下子树:
      2     
     / \   
    1   3

  	在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
*/

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class MySolution {
    private TreeNode res = null;
    
    public TreeNode searchBST(TreeNode root, int val) {
        search(root, val);
        return res;
    }
    
    private void search(TreeNode node, int val) {
        if (node == null) return;
        
        if (val == node.val)
            res = node;
        else if (val < node.val)
            search(node.left, val);
        else
            search(node.right, val);
    }
}

class Solution1 {
    public TreeNode searchBST(TreeNode root, int val) {
        while(root!=null) {
            if(root.val > val) {
                root = root.left;
            }else if(root.val < val) {
                root = root.right;
            }else{
                return root;
            }
        }
        return null;
    }
}

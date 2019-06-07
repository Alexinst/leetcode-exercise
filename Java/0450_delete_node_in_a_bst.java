class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution1 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        // 通过一个DummyHead，处理头节点的特殊情况
        TreeNode head = new TreeNode(0);
        head.left = root;

        TreeNode[] nodes = findNode(root, head,-1, key);
        if (nodes == null) return root;  // 二叉搜索树中不存在值key

        // nodes[1] 为key对应的节点，nodes[0]为该节点的父节点
        // 当前节点左子树为空，删除当前节点后，父节点指向当前节点右子树
        if (nodes[1].left == null){
            if(nodes[2].val == -1)
                nodes[0].left = nodes[1].right;
            else
                nodes[0].right = nodes[1].right;
        }

        else{
            //当前节点左子树不为空，删除当前节点后，父节点指向当前节点左子树，左子树最右节点指向当前节点右子树
            TreeNode rightest = findRightestNode(nodes[1].left);
            if(nodes[2].val == -1)  //当前节点在父节点的左边
                nodes[0].left = nodes[1].left;
            else
                nodes[0].right = nodes[1].left;
            rightest.right = nodes[1].right;
        }
        nodes[1].left = null;
        nodes[1].right = null;
        return head.left;
    }

    // 获取root及子树中值为key的节点，参数pos为0时表示:root为pre的左子树，为1时表示为右子树
    private TreeNode[] findNode(TreeNode root, TreeNode pre, int pos, int key){
        if(root == null) return null;

        if(root.val < key)
            return findNode(root.right, root, 1,  key);
        else if(root.val > key)
            return findNode(root.left, root, -1, key);

        TreeNode[] res = new TreeNode[3];
        res[0] = pre;
        res[1] = root;
        res[2] = new TreeNode(pos);
        return res;
    }

    private TreeNode findRightestNode(TreeNode root){
        if(root == null)
            return null;
        TreeNode node = root;
        while(node.right != null){
            node = node.right;
        }
        return node;
    }
}

class Solution2 {
    public TreeNode findMin(TreeNode node){
        while (node.left != null){
            node = node.left;
        }
        return node;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null){
            return root;
        }
        if (root.val < key){
            root.right = deleteNode(root.right,key);
        }else if (root.val > key){
            root.left = deleteNode(root.left,key);
        }else{
            if (root.left == null){
                return root.right;
            }else if (root.right == null){
                return root.left;
            }
            TreeNode min = findMin(root.right);
            root.val = min.val;
            root.right = deleteNode(root.right,root.val);
        }
        return root;
    }
}

class Solution3 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null){
            return null;
        }
        if(key<root.val){
            root.left=deleteNode(root.left,key);
            return root;
        }
        if(key>root.val){
            root.right=deleteNode(root.right,key);
            return root;
        }
        if(root.left==null){
            return root.right;
        }
        if(root.right==null){
            return root.left;
        }
        //找到右子树上的最小值
        TreeNode successor=findMin(root.right);
        //将找到的新根节点代替需要删除的节点
        successor.right=deleteMin(root.right);
        successor.left=root.left;
        return successor;
    }
    //找到二叉搜索树中数值最小的节点
    private TreeNode findMin(TreeNode node){
        if(node.left==null||node==null){
            return node;
        }
        return findMin(node.left);
    }
    private TreeNode deleteMin(TreeNode node){
        if(node==null){
            return null;
        }
        if(node.left==null){
            return node.right;
        }
        node.left=deleteMin(node.left);
        return node;
    }
}

package DSA;
import java.util.*;

class Node {
    Node left;
    Node right;
    int data;
}

class BST {

    public Node createNewNode(int k) {
        Node a = new Node();
        a.data = k;
        a.left = null;
        a.right = null;
        return a;
    }

    public Node insert(Node node, int val) {
        if (node == null) {
            return createNewNode(val);
        }
        if (val < node.data) {
            node.left = insert(node.left, val);
        } else if (val > node.data) {
            node.right = insert(node.right, val);
        }
        return node;
    }

    public Node delete(Node node, int val) {
        if (node == null) {
            System.out.println("tree is empty !");

            return null;
        }
        if (val < node.data) {
            node.left = delete(node.left, val);
        } else if (val > node.data) {
            node.right = delete(node.right, val);
        } else {
            if (node.left == null) {
                Node temp = node.right;
                return temp;
            } else if (node.right == null) {
                Node temp = node.left;
                return temp;
            }

            Node temp = MinVal(node.right);
            node.data = temp.data;
            node.right = delete(node.right, temp.data);
        }
        return node;

    }

    public void InOrder(Node node) {
        if (node == null) {
            return;
        }
        InOrder(node.left);
        System.out.print(node.data + " ");
        InOrder(node.right);

    }

    public void PreOrder(Node node) {
        if (node == null) {
            System.out.println("tree is empty !");
            return;
        }
        System.out.print(node.data + " ");
        InOrder(node.left);
        InOrder(node.right);

    }

    public void PostOrder(Node node) {
        if (node == null) {
            System.out.println("tree is empty !");
            return;
        }
        InOrder(node.left);
        InOrder(node.right);
        System.out.print(node.data + " ");

    }

    public int MaxVal(Node node) {
        if (node == null) {
            System.out.println("tree is empty !");
            return -1;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node.data;

    }

    public Node MinVal(Node node) {
        if (node == null) {
            System.out.println("tree is empty !");
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;

    }


// normal tree to bst
    void inorderBT(Node root , PriorityQueue<Integer> mh)
    {
        if(root == null)    return;
        inorderBT(root.left, mh);
        mh.add(root.data);
        inorderBT(root.right, mh);
    }
    void inorderBST(Node root, PriorityQueue<Integer> mh)
    {
        if(root == null) return;
        inorderBST(root.left, mh);
        root.data = mh.remove();
        inorderBST(root.right, mh);
    }

    Node binaryTreeToBST(Node root)
    {
        // Your code here
        PriorityQueue<Integer> mh = new PriorityQueue<Integer>();
        inorderBT(root, mh);
        inorderBST(root, mh);
        return root;
    }







    public int KthSmallestElement(Node root, int K)
    {
        // Write your code here

        List<Integer>list=new ArrayList<>();
        InOrder(root,list);
        Collections.sort(list);

        return ((K)<=list.size() && K>0)?list.get(K-1):-1;
    }

    public void InOrder(Node node,List<Integer> list) {
        if (node == null) {
            return;
        }
        InOrder(node.left,list);
        list.add(node.data);
        InOrder(node.right,list);

    }
    // Finding LCA for BST
    Node LCA(Node node, int n1, int n2)
    {
        // code here.
        if (node == null)
            return null;

        // If both n1 and n2 are smaller than root, then LCA lies in left
        if (node.data > n1 && node.data > n2)
            return LCA(node.left, n1, n2);

        // If both n1 and n2 are greater than root, then LCA lies in right
        if (node.data < n1 && node.data < n2)
            return LCA(node.right, n1, n2);

        return node;
    }
    int getCount(Node root,int l, int h)
    {
        //Your code here
        List <Integer> list= new ArrayList<Integer>();
        InOrder(root,list);
        int count =0;

        for (int ele:list){
            if (ele>=l && ele<=h)
                count++;
        }
        return count;
    }
    // normal binary tree
    Node lca(Node root, int n1,int n2)
    {
        // Your code here
        if (root==null) return null;
        if (root.data==n1 || root.data==n2) return root;
        Node lca1=lca(root.left,n1,n2);
        Node lca2=lca(root.right,n1,n2);
        if (lca1!=null && lca2!=null){
            return root;
        }
        if (lca1!=null){
            return lca1;
        }
        else
            return lca2;


    }

    //check the tree is is a subtree of another tree

    //helper function
    boolean areIdentical(Node root1, Node root2)
    {

        /* base cases */
        if (root1 == null && root2 == null)
            return true;

        if (root1 == null || root2 == null)
            return false;

        /* Check if the data of both roots is same and data of left and right
           subtrees are also same */
        return (root1.data == root2.data
                && areIdentical(root1.left, root2.left)
                && areIdentical(root1.right, root2.right));
    }

    /* This function returns true if S is a subtree of T, otherwise false */
    boolean isSubtree(Node T, Node S)
    {
        /* base cases */
        if (S == null)
            return true;

        if (T == null)
            return false;

        /* Check the tree with root as current node */
        if (areIdentical(T, S))
            return true;

        /* If the tree with root as current node doesn't match then
           try left and right subtrees one by one */
        return isSubtree(T.left, S)
                || isSubtree(T.right, S);
    }


}

public class BSTApp {

    public static void main(String[] args) {

        BST a = new BST();
        Node root = null;

        root = a.insert(root, 8);
        root = a.insert(root, 3);
        root = a.insert(root, 6);
        root = a.insert(root, 10);
        root = a.insert(root, 4);
        root = a.insert(root, 7);
        root = a.insert(root, 1);
        root = a.insert(root, 14);
        root = a.insert(root, 13);



        a.delete(root, 14);
        a.InOrder(root);
        System.out.println();
        a.PreOrder(root);
        System.out.println();
        a.PostOrder(root);
        System.out.println();
        System.out.println("Max Val=" + a.MaxVal(root));
        System.out.println("Min Val=" + a.MinVal(root).data);
        System.out.println("kth smallest = "+a.KthSmallestElement(root,5));
        System.out.println("in l and h = "+a.getCount(root,5,9));
        System.out.println(a.isSubtree(null,root));


    }

}
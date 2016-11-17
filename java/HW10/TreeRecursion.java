/* 
 * File: TreeRecursion.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 11/15/2016
 * Purpose: This class practices methods for interacting with Binary Search Trees
 */

public class TreeRecursion { 
    
    // These are the definitions and methods from the BinaryTreeCode link on the class web page
    
    private static class Node {
        int item;
        Node left;
        Node right;
        // number of items in this subtree
        
        public Node(int k) {
            left = null;
            right = null;
            item = k;
            
        }
        
        public Node(int k, Node l, Node r) {
            item = k;
            left = l;
            right = r;
            
        }
    }
    
    
    // Height of a binary tree is number of links in longest path, note that
    //     empty tree has height -1.
    
    private static int height(Node t) {
        if (t == null)
            return -1;
        else 
            return 1 + Math.max( height(t.left), height(t.right) ); 
    }
    
    
    // Recursively insert into tree
    
    private static Node insert(Node t, int item) {
        if (t == null)
            return new Node(item);
        else if (item < t.item) {
            t.left = insert(t.left, item);
            
            return t;
        } else if (item > t.item) {
            t.right = insert(t.right, item);
            
            return t;
        } else               // item is already here, do nothing
            return t;
    }
    
    // Just a utility method to insert into a tree
    
    private static Node insertArray(int[] A, Node r) {
        Node t = null;
        for(int i = 0; i < A.length; ++i)
            t = insert(t,A[i]);
        return t;  
    }
    
    
    
    // Recursively print out a diagram of the tree sideways
    
    private static void printIndentedTree(Node t) {
        System.out.println();
        printIndentedTreeAux(t, "");
        System.out.println();
    }
    
    private static void printIndentedTreeAux(Node t, String indent) {
        if (t != null) {
            printIndentedTreeAux(t.right, indent + "     "); // add five spaces to indent
            //     System.out.println(indent + t.item + "(" + t.N + ")");
            System.out.println(indent + t.item );
            printIndentedTreeAux(t.left, indent + "     "); // add five spaces to indent
        }
    }
    
    
    
    // These are the methods you must develop for HW 10 Problem B.2
    
    
    
    // return the number of nodes in the tree   (just for practice -- solved in lecture!)
    
    private static int size(Node t) {
        if (t == null)
            return 0;
        else
            return 1 + size(t.left) + size(t.right);
    }
    
    // print out a string representation of a binary tree using parentheses, infix style
    
    private static String treeToString(Node r) {
        if (r.left != null && r.right != null) {
            return "(" + treeToString(r.left) + " " + r.item + " " + treeToString(r.right) + ")";
        } else if (r.left == null && r.right != null) {
            return "( " + r.item + " " + treeToString(r.right) + ")";
        } else if (r.left != null && r.right == null) {
            return "(" + treeToString(r.left) + " " + r.item + " )";
        } else {
            return "( " + r.item + " )";
        }
    }
    
    // print out a string representation of a binary tree using parentheses, prefix style
    
    private static String treeToString2(Node r) {
        if (r.left != null && r.right != null) {
            return r.item + "(" + treeToString2(r.left) + "," + treeToString2(r.right) + ")";
        } else if (r.left == null && r.right != null) {
            return r.item + "(.," + treeToString2(r.right) + ")";
        } else if (r.left != null && r.right == null) {
            return r.item + "(" + treeToString2(r.left) + ",.)";
        } else {
            return r.item + "(.,.)";
        }
    }
    
    // Count the number of leaves in a binary tree
    
    private static int numLeaves(Node r) {
        if (r == null)
            return 0;
        else if(r.left == null && r.right == null)
            return 1;
        else
            return numLeaves(r.left) + numLeaves(r.right);
    }
    
    // reverse the tree by exchanging left and right pointers (you will modify the original tree)_
    
    public static Node reverse(Node r) {
        if (r == null) return null;
        reverseHelper(r);
        return r;
    }
    
    private static void reverseHelper(Node r) {
        if (r != null) {
            Node temp = r.right;
            r.right = r.left;
            r.left = temp;
            reverse(r.right);
            reverse(r.left);
        }   
    }
    
    // make a copy of a binary tree
    
    private static Node copy(Node r) {
        return copyHelper(r);
    }
    
    private static Node copyHelper(Node r) {
        if (r == null) return null;
        Node n = new Node(r.item, null, null);
        n.left = copyHelper(r.left);
        n.right = copyHelper(r.right);
        return n;
    }
    
    // return true if the binary tree satisfies the binary search tree property, false otherwise
    
    public static boolean isBST(Node r) {
        return isBSTHelper(r, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private static boolean isBSTHelper(Node r, int lo, int hi) {
        if (r == null) return true;
        if (r.item > lo && r.item < hi) return isBSTHelper(r.left, lo, r.item) && isBSTHelper(r.right, r.item, hi);
        return false;
    }
    
    // return true if r is a degenerate (i.e., "linked-list") binary tree, ie.,  in which all nodes have 0 or 1 child
    
    public static boolean isDegenerate(Node r) {
        if (r == null) return false;
        if (r.left != null && r.right == null) {
            return isDegenerate(r.left);
        } else if (r.left == null && r.right != null) {
            return isDegenerate(r.right);
        } else if (r.left == null && r.right == null) {
            return true;
        } else {
            return false;
        }
    }
    
    // A perfect binary tree is a perfect triangle; test by checking that all subtrees have same size
    
    public static boolean isPerfect(Node r) {
        if (r == null) return false;
        if (size(r.left) == size(r.right)) return true;
        return false;
    }
    
    // Determine if the two binary trees are structurally identical
    
    public static boolean equals(Node r, Node s) {
        if (r == null && s == null) return true;
        if ((r == null && s != null) || (r != null && s == null)) return false;
        if (r.item == s.item) return equals(r.left, s.left) && equals(r.right, s.right);
        return false;
    }
    
    // Unit Test for HW 10 B.2
    
    public static void main(String[] args) {
        
        
        
        System.out.println("Sample trees for testing tree methods for HW 10 B.2:"); 
        
        System.out.println("\nTree 0 is null.\n"); 
        Node root0 = null; 
        
        Node root1=null;
        
        int[] A = { 5, 2, 9, 4,1, 7, 12 };
        root1 = insertArray(A,root1);
        System.out.println("Tree 1:"); 
        printIndentedTree(root1);
        
        Node root2=null;
        
        int[] B = { 1, 2, 3, 6, 5, 4 };
        root2 = insertArray(B,root2);
        System.out.println("Tree 2:"); 
        printIndentedTree(root2);   
        
        Node root3=null;
        
        int[]C = { 1, 6, 2, 4, 3, 5 };
        root3 = insertArray(C,root3);
        System.out.println("Tree 3:"); 
        printIndentedTree(root3);     
        
        Node root4=null;
        
        int[] D = { 6, 2, 15, 9, 4, 1, 18, 3, 12, 7,5,10,13 };
        root4 = insertArray(D,root4);
        System.out.println("Tree 4:"); 
        printIndentedTree(root4);     
        
        //   Uncover one test at a time as you solve the problems
        
        System.out.println("\nPerformance Tests...."); 
        
        System.out.println("\nTesting size....");
        System.out.println("\n[0]: Should print out:\n" + D.length); 
        System.err.println(size(root4)); 
        
        System.out.println("\nTesting treeToString....");
        System.out.println("\n[1] Should print out:\n((( 1 ) 2 ( 4 )) 5 (( 7 ) 9 ( 12 )))"); 
        System.err.println(treeToString(root1)); 
        
        System.out.println("\n[2] Should print out:\n( 1 ( 2 ( 3 ((( 4 ) 5 ) 6 ))))"); 
        System.err.println(treeToString(root2)); 
        
        System.out.println("\nTesting treeToString2....");
        System.out.println("\n[3] Should print out:\n5(2(1(.,.),4(.,.)),9(7(.,.),12(.,.)))"); 
        System.err.println(treeToString2(root1)); 
        
        System.out.println("\n[4] Should print out:\n1(.,6(2(.,4(3(.,.),5(.,.))),.))"); 
        System.err.println(treeToString2(root3)); 
        
        System.out.println("\nTesting numLeaves ....");
        System.out.println("\n[5] Should print out:\n0"); 
        System.err.println(numLeaves(root0)); 
        
        System.out.println("\n[6] Should print out:\n7"); 
        System.err.println(numLeaves(root4)); 
        
        System.out.println("\nTesting isDegenerate ....");
        System.out.println("\n[7] Should print out:\nfalse"); 
        System.err.println(isDegenerate(root0)); 
        
        System.out.println("\n[8] Should print out:\ntrue"); 
        System.err.println(isDegenerate(root2)); 
        
        System.out.println("\n[9] Should print out:\nfalse"); 
        System.err.println(isDegenerate(root3));  
        
        System.out.println("\n[10] Should print out:\nfalse"); 
        System.err.println(isDegenerate(root4)); 
        
        System.out.println("\nTesting isPerfect ....");
        System.out.println("\n[11] Should print out:\nfalse"); 
        System.err.println(isPerfect(root3));  
        
        System.out.println("\n[12] Should print out:\ntrue"); 
        System.err.println(isPerfect(root1));        
        
        
        System.out.println("\nTesting isBST ....");
        
        // not BSTs 
        Node temp = new Node(4, new Node(5), null);
        Node temp2 = new Node(4, new Node(2, new Node(1), new Node(5)), new Node(10));
        
        System.out.println("\n[13] Should print out:\ntrue"); 
        System.err.println(isBST(root0)); 
        
        System.out.println("\n[14] Should print out:\ntrue"); 
        System.err.println(isBST(root4)); 
        
        System.out.println("\n[15] Should print out:\nfalse"); 
        System.err.println(isBST(temp)); 
        
        System.out.println("\n[16] Should print out:\nfalse"); 
        System.err.println(isBST(temp2));
        
        System.out.println("\n[17] Should print out:\nfalse"); 
        System.err.println(isBST(temp2)); 
        
        
        int[] A1 = { 9, 4,1, 7, 5, 2,  12 };
        System.out.println("\nTesting copy ....");
        root1 = insertArray(A,root1);
        
        temp = copy(root1);
        System.out.println("\n[18] Should print out:\n((( 1 ) 2 ( 4 )) 5 (( 7 ) 9 ( 12 )))");  
        System.err.println(treeToString(root1)); 
        
        System.out.println("\n[19] Should print out:\n((( 1 ) 2 ( 4 )) 5 (( 7 ) 9 ( 12 )))");  
        System.err.println(treeToString(temp)); 
        
        temp = insert(temp, 1000); 
        
        System.out.println("\n[20] Should print out:\n((( 1 ) 2 ( 4 )) 5 (( 7 ) 9 ( 12 ( 1000 ))))");  
        System.err.println(treeToString(temp)); 
        
        System.out.println("\n[21] Should print out:\n((( 1 ) 2 ( 4 )) 5 (( 7 ) 9 ( 12 )))");  
        System.err.println(treeToString(root1)); 
        
        System.out.println("\nTesting reverse ....");
        
        System.out.println("\n[22] Should print out:\n((( 12 ) 9 ( 7 )) 5 (( 4 ) 2 ( 1 )))");  
        System.err.println(treeToString(reverse(copy(root1)))); 
        
        System.out.println("\n[23] Should print out:\n((( 1 ) 2 ( 4 )) 5 (( 7 ) 9 ( 12 )))");  
        System.err.println(treeToString(reverse(reverse(root1)))); 
        
        System.out.println("\nTesting equals ....");
        
        System.out.println("\n[24] Should print out:\nfalse"); 
        System.err.println(equals(root1, root2)); 
        
        System.out.println("\n[25] Should print out:\nfalse"); 
        System.err.println(equals(root1, temp)); 
        
        int[] A2 = { 5, 2, 9, 1, 4, 12, 7 };
        temp = null; 
        temp = insertArray(A2,temp);
        
        System.out.println("\n[26] Should print out:\ntrue"); 
        System.err.println(equals(root1, temp)); 
        
        System.out.println("\n :-) "); 
        
        boolean IamDone = true; 
        System.out.println("\n[31] Should print out:\nHigh Five!"); 
        if(IamDone)
            System.err.println("High Five!");
        else
            System.err.println("Nope!");
    } 
}
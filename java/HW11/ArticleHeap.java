/* 
 * File: ArticleHeap.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 12/07/2016
 * Purpose: This class implements a max heap for articles based on cosine similarity
 */

class ArticleHeap {
    
    private final int SIZE = 10;       // initial length of array
    private int next = 0;              // limit of elements in array
    private Article[] A = new Article[SIZE];   // implements tree by storing elements in level order
    
    // standard resize to avoid overflow
    
    private void resize() {
        Article[] B = new Article[A.length*2];
        for(int i = 0; i < A.length; ++i)
            B[i] = A[i];
        A = B; 
    }
    
    // methods to move up and down tree as array
    
    private int parent(int i) { return (i-1) / 2; }
    private int lchild(int i) { return 2 * i + 1; }
    private int rchild(int i) { return 2 * i + 2; }
    
    private boolean isLeaf(int i) { return (lchild(i) >= next); }
    private boolean isRoot(int i) { return i == 0; }
    
    // standard swap, using indices in array
    private void swap(int i, int j) {
        Article temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    
    // basic data structure methods
    
    public boolean isEmpty() {
        return (next == 0);
    }
    
    public int size() {
        return (next);
    }
    
    // insert an integer into array at next available location
    //    and fix any violations of heap property on path up to root
    
    public void insert(Article k) {
        if(size() == A.length) resize(); 
        A[next] = k; 
        
        int i = next;
        int p = parent(i); 
        while(!isRoot(i) && A[i].compareCS(A[p]) > 0) {
            swap(i,p);
            i = p;
            p = parent(i); 
        }
        
        ++next;
    }
    
    
    // Remove top (maximum) element, and replace with last element in level
    //    order; fix any violations of heap property on a path downwards
    
    public Article getMax() throws HeapUnderflowException {
        if ((next - 1) < 0) throw new HeapUnderflowException("Error: Heap underflow!");
        --next;
        swap(0,next);                // swap root with last element
        int i = 0;                   // i is location of new key as it moves down tree
        
        // while there is a maximum child and element out of order, swap with max child
        int mc = maxChild(i); 
        while(!isLeaf(i) && A[i].compareCS(A[mc]) < 0) { 
            swap(i,mc);
            i = mc; 
            mc = maxChild(i);
        }
        
        ///     printHeapAsTree(); 
        
        return A[next];
    }
    
    // return index of maximum child of i or -1 if i is a leaf node (no children)
    
    int maxChild(int i) {
        if(lchild(i) >= next)
            return -1;
        if(rchild(i) >= next)
            return lchild(i);
        else if(A[lchild(i)].compareCS(A[rchild(i)]) > 0)
            return lchild(i);
        else
            return rchild(i); 
    }
    
    // Apply heapsort to the array A. To use, fill A with keys and then call heapsort
    
    public  void heapSort() {
        next = 0;
        for(int i = 0; i < A.length; ++i)      // turn A into a heap
            insert(A[i]);
        for(int i = 0; i < A.length; ++i) {     // delete root A.length times, which swaps max into
            try {
                getMax();                           //  right side of the array
            } catch (HeapUnderflowException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    // debug method
    
    private void printHeap() {
        for(int i = 0; i < A.length; ++i)
            System.out.print(A[i] + " ");
        System.out.println("\t next = " + next);
    }
    
    private void printHeapAsTree() {
        printHeapTreeHelper(0, ""); 
        System.out.println(); 
    }
    
    private void printHeapTreeHelper(int i, String indent) {
        if(i < next) {
            
            printHeapTreeHelper(rchild(i), indent + "   "); 
            System.out.println(indent + A[i]);
            printHeapTreeHelper(lchild(i), indent + "   "); 
        }
    }
    
    // Unit Test
    
    public static  void main(String [] args) {
        
        ArticleHeap H = new ArticleHeap(); 
        
        Article a = new Article("a","hsafg");
        Article s = new Article("s","gwrec hfd");
        Article d = new Article("d","dhfdsfh");
        Article f = new Article("f","grsgare");
        Article g = new Article("g","h453e5hrga");
        Article h = new Article("h","sdfhga35q8u");
        Article j = new Article("j","tyumuy");
        Article k = new Article("k","jrh6tw6tyhj");
        Article l = new Article("l","grfdg54");
        
        a.putCS(0.0);
        k.putCS(0.054);
        d.putCS(0.09);
        f.putCS(0.23);
        l.putCS(0.34);
        h.putCS(0.56);
        j.putCS(0.78);
        s.putCS(0.94);
        g.putCS(1.0);
        
        // test basic methods
        Article[] S = { a, s, d, f, g, h, j, k, l};
        System.out.println("Insert articles and print heap after each insertion:"); 
        for(int i = 0; i < S.length; ++i) {
            H.insert(S[i]);
            //H.printHeapAsTree(); 
        } 
        
        
        System.out.println("getMax() and print out, until empty:\n");
        while(!H.isEmpty()) {
            try {
                Article q = H.getMax();
                System.out.println("Max: " + q.getTitle() + ", CS: " + q.getCS());
            } catch (HeapUnderflowException e) {
                System.out.println(e.getMessage());
            }
            //H.printHeapAsTree(); 
        }
        
        System.out.println("These should throw exceptions:\n");
        try {
            Article q = H.getMax();
            System.out.println("Max: " + q.getTitle() + ", CS: " + q.getCS());
        } catch (HeapUnderflowException e) {
            System.out.println(e.getMessage());
        }
        try {
            Article q = H.getMax();
            System.out.println("Max: " + q.getTitle() + ", CS: " + q.getCS());
        } catch (HeapUnderflowException e) {
            System.out.println(e.getMessage());
        }
        try {
            Article q = H.getMax();
            System.out.println("Max: " + q.getTitle() + ", CS: " + q.getCS());
        } catch (HeapUnderflowException e) {
            System.out.println(e.getMessage());
        }
    }
}

class HeapUnderflowException extends Exception {
    public HeapUnderflowException(String text) {
        super(text);
    }
}
public class Test {
    public static void main(String[] args) {
        Node head = new Node(4, new Node(3, new Node( 7, new Node(9, new Node( 5, new Node(-1,null))))));
        
        Node p = head;
        Node q = head.next;
        System.out.println("(1) " + p.item + " " + q.item); 
        
        p = q.next;
        q = p.next.next;
        System.out.println("(2) " + p.item + " " + q.item); 
        
        Node r = head.next;
        p = r.next;
        q = head.next.next;
        if(r.next == q) {
            q = p.next;
        }
        if(r.next.item == p.item) {
            p = p.next;
        }
        System.out.println("(3) " + p.item + " " + q.item);        
        
        
        p = head;
        p = p.next;
        p = p.next;
        q = p.next;
        q = q.next;
        p = q.next;
        System.out.println("(4) " + p.item + " " + q.item);
        
        p = head; 
        while(p.item < 8) {
            q = p;
            p = p.next;
        }
        System.out.println("(5) " + p.item + " " + q.item);
        
        q.item = head.next.item;
        p.next.item = q.next.item;
        
        System.out.print("(6)  head -> ");                     // this prints out the whole list
        for(Node s = head; s != null; s = s.next)
            System.out.print(s.item + " -> " );
        System.out.println(".");
        
        // get the original list back
        
        head = new Node(4, new Node(3, new Node( 7, new Node(9, new Node( 5, new Node(-1,null))))));
        p = head.next.next;
        q = p.next;
        p.next = q.next;
        
        System.out.print("(7)  head -> ");                   // this prints out the whole list
        for(Node s = head; s != null; s = s.next)
            System.out.print(s.item + " -> " );
        System.out.println(".");
        
        // get the original list back
        
        head = new Node(4, new Node(3, new Node( 7, new Node(9, new Node( 5, new Node(-1,null))))));
        p = head;
        q = p.next.next;
        p.next = q.next;
        p = p.next.next;
        
        System.out.print("(8)  head -> ");                   // this prints out the whole list
        for(Node s = head; s != null; s = s.next)
            System.out.print(s.item + " -> " );
        System.out.println(".");
        
        // get the original list back
        
        head = new Node(4, new Node(3, new Node( 7, new Node(9, new Node( 5, new Node(-1,null))))));
        p = head;
        while(p != null) {
            q = p.next;
            p.next = q.next;
            p = p.next;
        }
        
        System.out.print("(9)  head -> ");                   // this prints out the whole list
        for(Node s = head; s != null; s = s.next)
            System.out.print(s.item + " -> " );
        System.out.println(".");
        
        // get the original list back
        
        head = new Node(4, new Node(3, new Node( 7, new Node(9, new Node( 5, new Node(-1,null))))));
        
        p = head.next;
        q = head;
        while ( p != null ) {
            r = q;         
            q = p;        
            p = p.next;   
            q.next = r;
        }
        head.next = null; 
        head = q;
        
        System.out.print("(10)  head -> ");                   // this prints out the whole list
        for(Node s = head; s != null; s = s.next)
            System.out.print(s.item + " -> " );
        System.out.println(".");
        
    }
}
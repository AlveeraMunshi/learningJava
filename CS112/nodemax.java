import org.w3c.dom.Node;

public class nodemax {
    public static int max(Node head){ 
        if(head <= head.next)
            return max(head.next);
        
    }
    public static void main(String[] args)
    {

    }
}

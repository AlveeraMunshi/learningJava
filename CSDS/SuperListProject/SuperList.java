package SuperListProject;

public class SuperList<E> {
    private ListNode<E> root, end;
    private int size = 0;
    public SuperList()
    {
        // TODO Auto-generated constructor stub
    }
    public boolean isEmpty()
    {
        return size == 0;
    }
    public void push(E item)
    {
        add(item);
    }
    public void push(int position, E item)
    {
        add(position, item);
    }
    public void add(E item)
    {
        if (root == null) //if list empty
        {
            //root and end are the same
            root = new ListNode(item);
            end = new ListNode(item);
        }
        else //if list isn't empty
        {
            //new node is added to end
            ListNode<E> temp = new ListNode(item);
            end.setNext(temp); //current end needs to have a new next
            end = temp; //new end is the new node
        }
        size++; //size increases
    }
    public void add(int position, E item)
    {
        ListNode<E> temp = new ListNode(item); //given value is stored in a new node
        if (position < 0 || position > size) //if position is out of bounds
        {
            throw new IndexOutOfBoundsException();
        }
        if (isEmpty())
        {
            add(item);
        }
        else if (position == 0) //if position is at the beginning
        {
            root.setPrev(temp); //temp precedes current root
            temp.setNext(root); //current root follows temp
            root = temp; //temp is now the root
            root.setPrev(null); //root does not have a previous
            size++; //size increases
        }
        else if (position == size) //if position is at the end
        {
            temp.setPrev(end); //temp precedes current end
            end.setNext(temp); //current end follows temp
            end = temp; //temp is now the end
            end.setNext(null); //end does not have a next
            size++; //size increases
        }
        else //if position is in the middle
        {
            ListNode<E> current = root; //start at first node
            for (int x = 0; x < position; x++)
            {
                current = current.getNext(); //move to next node
            }
            ListNode<E> currentprev = current.getPrev(); //saves current preceding node
            currentprev.setNext(temp); //temp follows current preceding node
            temp.setPrev(currentprev); //temp is preceded by current's previous
            current.setPrev(temp); //temp precedes current node
            temp.setNext(current); //current node follows temp
            //value following current is unchanged
            size++; //size increases
        }
    }
    public void remove(int position)
    {
        if (position < 0 || position >= size) //if position is out of bounds
        {
            throw new IndexOutOfBoundsException();
        }
        if (position == 0) //if position is at the beginning
        {
            root = root.getNext(); //root is now the next node
            root.setPrev(null); //root does not have a previous
            size--; //size decreases
        }
        else if (position == size - 1) //if position is at the end
        {
            end = end.getPrev(); //end is now the previous node
            end.setNext(null); //end does not have a next
            size--; //size decreases
        }
        else //if position is in the middle
        {
            ListNode<E> current = root; //start at first node
            for (int x = 0; x < position; x++) //find value at intended position
            {
                current = current.getNext(); //move to next node
            }
            current.getPrev().setNext(current.getNext()); //node before current follows node after current
            current.getNext().setPrev(current.getPrev()); //node after current precedes node before current
            size--; //size decreases
        }
    }
    public void pop()
    {
        remove(size - 1);
    }
    public void poll()
    {
        remove(0);
    }
    public void clear()
    {
        root = null;
        end = null;
        size = 0;
    }
    public void set(E value, int position)
    {
        if (position < 0 || position >= size) //if position is out of bounds
        {
            throw new IndexOutOfBoundsException();
        }
        else //if position is in bounds
        {
            ListNode<E> temp = root; //start at first node
            for (int x = 0; x < position; x++) //find value at intended position
            {
                temp = temp.getNext(); //next node
            }
            temp.setValue(value);
        }
    }
    public E get(int position)
    {
        if (position < 0 || position >= size) //if position is out of bounds
        {
            throw new IndexOutOfBoundsException();
        }
        else //if position is in bounds
        {
            ListNode<E> temp = root; //start at first node
            for (int x = 0; x < position; x++) //find value at intended position
            {
                temp = temp.getNext(); //next node
            }
            return temp.getValue();
        }
    }
    public int size()
    {
        return size;
    }
    public String toString()
    {
        String output = "";
        for (int x = 0; x < size; x++)
        {
            output += get(x) + " ";
        }
        return output;
    }
    public class ListNode<E>{
        private E value;
        private ListNode<E> next, prev;
        public ListNode(Object data)
        {
            this.value = value;
            this.next = null;
        }
        public E getValue()
        {
            return value;
        }
        public void setValue(E value)
        {
            this.value = value;
        }
        public ListNode<E> getNext()
        {
            return next;
        }
        public void setNext(ListNode<E> next)
        {
            this.next = next;
        }
        public ListNode<E> getPrev()
        {
            return prev;
        }
        public void setPrev(ListNode<E> prev)
        {
            this.prev = prev;
        }
        public String toString()
        {
            return value.toString();
        }
        public boolean hasPrev()
        {
            return prev != null;
        }
        public boolean hasNext()
        {
            return next != null;
        }
        
    }
    public static void main (String[]args)
    {
        SuperList<Object> app = new SuperList<Object>();
    }
}


package SuperListProject;

import java.util.EmptyStackException;

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
    public void add(E item)
    {
        ListNode<E> temp = new ListNode(item);

        if (isEmpty()) //if list empty
        {
            //root and end are the same
            root = temp;
            end = root;

        }
        else //if list isn't empty
        {
            //new node is added to end
            temp.setPrev(end); //temp precedes current end
            end.setNext(temp); //current end follows temp
            end = temp; //temp is now the end
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
        if (position == size) //if position is at the end
        {
            add(item); //add to end
        }
        else if (position == 0) //if position is at the beginning
        {
            root.setPrev(temp); //temp precedes current root
            temp.setNext(root); //current root follows temp
            root = temp; //temp is now the root
            size++; //size increases
        }
        else //if position is in the middle
        {
            ListNode<E> current = root; //start at first node
            for (int x = 0; x < position; x++)
            {
                current = current.getNext(); //move to next node
            }
            try
            {
                ListNode<E> currentprev = current.getPrev(); //saves current preceding node
                currentprev.setNext(temp); //temp follows current preceding node
                temp.setPrev(currentprev); //temp is preceded by current's previous
                current.setPrev(temp); //temp precedes current node
                temp.setNext(current); //current node follows temp
                //value following current is unchanged
            }
            catch(Exception e)
            {
            }
            size++; //size increases
        }
    }
    public E remove(int position)
    {
        if (position < 0 || position >= size) //if position is out of bounds
        {
            throw new IndexOutOfBoundsException();
        }
        if (size == 1)
        {
            E temp = root.getValue(); // save value
            clear(); //clear list
            return temp;
        }
        else if (position == size - 1) //if position is at the end
        {
            E temp = end.getValue(); //saves value of end
            end = end.getPrev(); //end is now the previous node
            end.setNext(null); //end does not have a next
            size--; //size decreases
            return temp; //returns value of end
        }
        else if (position == 0) //if position is at the beginning
        {
            E temp = root.getValue(); //saves value of root
            root = root.getNext(); //root is now the next node
            root.setPrev(null); //root does not have a previous
            size--; //size decreases
            return temp; //returns value of root
        }
        else //if position is in the middle
        {
            ListNode<E> current = root; //start at first node
            for (int x = 0; x < position; x++) //find value at intended position
            {
                current = current.getNext(); //move to next node
            }
            E temp = current.getValue(); //saves value of current node
            current.getPrev().setNext(current.getNext()); //node before current follows node after current
            current.getNext().setPrev(current.getPrev()); //node after current precedes node before current
            size--; //size decreases
            return temp; //returns value of current node
        }
    }
    public E pop()
    {
        E temp = end.getValue(); //saves value of end
        if (temp == null) //if list is empty
        {
            throw new EmptyStackException();
        }
        
        remove(size - 1); //removes end
        return temp; //returns value of end
    }
    public E poll()
    {
        if(size==0)
            return null;

        E temp = root.getValue(); //saves value of root
        remove(0); //removes root
        return temp; //returns value of root

    }
    public E stackPeek()
    {
        if (end == null) //if list is empty
        {
            throw new EmptyStackException();
        }
        else
        {
            return end.getValue(); //returns value of end
        }
    }
    public E queuePeek()
    {
        if (root == null) //if list is empty
        {
            throw new EmptyStackException();
        }
        else
        {
            return root.getValue(); //returns value of root
        }
    }
    public void clear()
    {
        root = null;
        end = null;
        size = 0;
    }
    public void set(int position, E value)
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
    public boolean contains(E value)
    {
        ListNode<E> temp = root; //start at first node
        for (int x = 0; x < size; x++) //find value at intended position
        {
            if (temp.getValue().equals(value) || temp.getValue() == value) //if value is found
            {
                return true;
            }
            temp = temp.getNext(); //next node
        }
        return false;
    }
    public int size()
    {
        return size;
    }
    public String toString()
    {
        String output = "[";
        ListNode<E> temp=root;
        for (int x = 0; x < size; x++)
        {
            if (temp != null)
                output += temp.getValue();
            if(x<size-1)
                output += ", ";
            temp=temp.getNext();
        }
        output += "]";
        return output;
    }
    public class ListNode<E>{
        private E value;
        private ListNode<E> next, prev;
        public ListNode(E value)
        {
            this.value = value;
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


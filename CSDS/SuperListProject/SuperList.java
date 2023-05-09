package SuperListProject;

import java.util.EmptyStackException;

public class SuperList<E> {
    private Listprev<E> root, end;
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
        Listprev<E> temp = new Listprev(item);

        if (isEmpty()) //if list empty
        {
            //root and end are the same
            root = temp;
            end = root;

        }
        else //if list isn't empty
        {
            //new prev is added to end
            temp.setPrev(end); //temp precedes curr end
            end.setNext(temp); //curr end follows temp
            end = temp; //temp is now the end
        }
        size++; //size increases
    }
    public void add(int position, E item)
    {
        Listprev<E> temp = new Listprev(item); //given val is stored in a new prev
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
            root.setPrev(temp); //temp precedes curr root
            temp.setNext(root); //curr root follows temp
            root = temp; //temp is now the root
            size++; //size increases
        }
        else //if position is in the middle
        {
            Listprev<E> curr = root; //start at first prev
            for (int x = 0; x < position; x++)
            {
                curr = curr.getNext(); //move to next prev
            }
            try
            {
                Listprev<E> currprev = curr.getPrev(); //saves curr preceding prev
                currprev.setNext(temp); //temp follows curr preceding prev
                temp.setPrev(currprev); //temp is preceded by curr's previous
                curr.setPrev(temp); //temp precedes curr prev
                temp.setNext(curr); //curr prev follows temp
                //val following curr is unchanged
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
            E temp = root.getValue(); // save val
            clear(); //clear list
            return temp;
        }
        else if (position == size - 1) //if position is at the end
        {
            E temp = end.getValue(); //saves val of end
            end = end.getPrev(); //end is now the previous prev
            end.setNext(null); //end does not have a next
            size--; //size decreases
            return temp; //returns val of end
        }
        else if (position == 0) //if position is at the beginning
        {
            E temp = root.getValue(); //saves val of root
            root = root.getNext(); //root is now the next prev
            root.setPrev(null); //root does not have a previous
            size--; //size decreases
            return temp; //returns val of root
        }
        else //if position is in the middle
        {
            Listprev<E> curr = root; //start at first prev
            for (int x = 0; x < position; x++) //find val at intended position
            {
                curr = curr.getNext(); //move to next prev
            }
            E temp = curr.getValue(); //saves val of curr prev
            curr.getPrev().setNext(curr.getNext()); //prev before curr follows prev after curr
            curr.getNext().setPrev(curr.getPrev()); //prev after curr precedes prev before curr
            size--; //size decreases
            return temp; //returns val of curr prev
        }
    }
    public E pop()
    {
        E temp = end.getValue(); //saves val of end
        if (temp == null) //if list is empty
        {
            throw new EmptyStackException();
        }
        
        remove(size - 1); //removes end
        return temp; //returns val of end
    }
    public E poll()
    {
        if(size==0)
            return null;

        E temp = root.getValue(); //saves val of root
        remove(0); //removes root
        return temp; //returns val of root

    }
    public E stackPeek()
    {
        if (end == null) //if list is empty
        {
            throw new EmptyStackException();
        }
        else
        {
            return end.getValue(); //returns val of end
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
            return root.getValue(); //returns val of root
        }
    }
    public void clear()
    {
        root = null;
        end = null;
        size = 0;
    }
    public void set(int position, E val)
    {
        if (position < 0 || position >= size) //if position is out of bounds
        {
            throw new IndexOutOfBoundsException();
        }
        else //if position is in bounds
        {
            Listprev<E> temp = root; //start at first prev
            for (int x = 0; x < position; x++) //find val at intended position
            {
                temp = temp.getNext(); //next prev
            }
            temp.setval(val);
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
            Listprev<E> temp = root; //start at first prev
            for (int x = 0; x < position; x++) //find val at intended position
            {
                temp = temp.getNext(); //next prev
            }
            return temp.getValue();
        }
    }
    public boolean contains(E val)
    {
        Listprev<E> temp = root; //start at first prev
        for (int x = 0; x < size; x++) //find val at intended position
        {
            if (temp.getValue().equals(val) || temp.getValue() == val) //if val is found
            {
                return true;
            }
            temp = temp.getNext(); //next prev
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
        Listprev<E> temp=root;
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
    public class Listprev<E>{
        private E val;
        private Listprev<E> next, prev;
        public Listprev(E val)
        {
            this.val = val;
        }
        public E getValue()
        {
            return val;
        }
        public void setval(E val)
        {
            this.val = val;
        }
        public Listprev<E> getNext()
        {
            return next;
        }
        public void setNext(Listprev<E> next)
        {
            this.next = next;
        }
        public Listprev<E> getPrev()
        {
            return prev;
        }
        public void setPrev(Listprev<E> prev)
        {
            this.prev = prev;
        }
        public String toString()
        {
            return val.toString();
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


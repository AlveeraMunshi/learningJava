package Sets.Trees;

import javax.swing.tree.TreeNode;

public class TreeSet<E extends Comparable<E>> 
{
    TreeNode<E> root;
    int size;
    String output;
    TreeNode<E> parent;
    int time = 1;

    public TreeSet()
    {
        root = null;
    }
    public void add(E val) //actually called from main
    {
        TreeNode<E> temp = new TreeNode<E>(val);
        if (size == 0 || root == null) 
        {
            root = temp;
            size = 1;
        } 
        else 
        {
            add(root, temp); //compare to first prev
        }
    }

    public void add(TreeNode<E> e, TreeNode<E> val) //recursive
    {
        if (e.compareTo(val) > 0) // if existing is greater than new
        { 
            if (e.getLeft() == null) //if end of tree
            { 
                e.setLeft(val); //left of existing is new
                size++;
            } 
            else 
            {
                add(e.getLeft(), val); //compare new to curr left
            }
        } 
        else if (e.compareTo(val) < 0) // if existing is less than new
        { 
            if (e.getRight() == null) //if end of tree
            { 
                e.setRight(val); //right of existing is new
                size++;
            } 
            else 
            {
                add(e.getRight(), val); //compare new to curr right
            }
        }
        else
        {
            System.out.println("Duplicate value");
        }
    }

    public void remove(E val) //actually called from main
    { 
        if (root.getValue().equals(val) && root.getLeft() == null && root.getRight() == null) //if root is only node
        { 
            size = 0;
            root = null; //remove root, clear tree
        } 
        else 
        {
            remove(root, null, val); //compare value to root
        }
    }

    public void remove(TreeNode<E> curr, TreeNode<E> prev, E val) //recursive
    { 
        if (curr != null) //if tree is not empty
        {
            if (val.compareTo(curr.getValue()) > 0) //if val is greater than curr
            { 
                prev = curr;
                remove(curr.getRight(), prev, val); //compare val to curr right
            } 
            else if (val.compareTo(curr.getValue()) < 0) //if val is less than curr
            { 
                prev = curr;
                remove(curr.getLeft(), prev, val); //compare val to curr left
            } 
            else //if val is equal to curr
            { 
                if (curr.getLeft() == null && curr.getRight() == null) //if curr has no children
                { 
                    if (prev.getLeft() == curr) //if curr is left child
                    { 
                        prev.setLeft(null); //remove curr
                        size--;
                        //recursion ends
                    } 
                    else if (prev.getRight() == curr) //if curr is right child
                    { 
                        prev.setRight(null); //remove curr
                        size--;
                        //recursion ends
                    }
                } 
                else if (curr.getLeft() != null && curr.getRight() == null) //if curr has left child but not right
                { 
                    if (prev.getLeft() == curr) //if curr is left child of previous
                    { 
                        prev.setLeft(curr.getLeft()); //replace curr with curr left
                        size--;
                        //recursion ends
                    } 
                    else if (prev.getRight() == curr) //if curr is right child of previous
                    { 
                        prev.setRight(curr.getLeft()); //replace curr with curr left
                        size--;
                        //recursion ends
                    }
                } 
                else if (curr.getLeft() == null && curr.getRight() != null) //if curr has right child but not left
                { 
                    if (prev.getLeft() == curr) //if curr is left child of previous
                    { 
                        prev.setLeft(curr.getRight()); //replace curr with curr right
                        size--;
                        //recursion ends
                    } 
                    else if (prev.getRight() == curr) //if curr is right child of previous
                    { 
                        prev.setRight(curr.getRight()); //replace curr with curr right
                        size--;
                        //recursion ends
                    }
                } 
                else if (curr.getLeft() != null && curr.getRight() != null) //if curr has two children
                { 
                    TreeNode<E> left = curr.getLeft(); //store left child
                    TreeNode<E> right = curr.getRight(); //store right child

                    if (left.compareTo(right) > 0) //if left child is greater than right child
                    { 
                        if (prev.getLeft() == curr) //if curr is left child of previous
                        { 
                            prev.setLeft(left); //replace curr with left child
                            size--;
                            //recursion ends
                        } 
                        else if (prev.getRight() == curr) //if curr is right child of previous
                        { 
                            prev.setRight(left); //replace curr with left child
                            size--;
                            //recursion ends
                        }
                    } 
                    else if (left.compareTo(right) < 0) //if left child is less than right child
                    { 
                        if (prev.getLeft() == curr) //if curr is left child of previous
                        { 
                            prev.setLeft(right); //replace curr with right child
                            size--;
                            //recursion ends
                        } 
                        else if (prev.getRight() == curr) //if curr is right child of previous
                        { 
                            prev.setRight(right); //replace curr with right child
                            size--;
                            //recursion ends
                        }
                    }
                }
            }
        }
    }

    public String preOrder(TreeNode<E> temp) {

        if(size == 0) {
            return "[]";
        }

        if (temp != null) {
            output += temp.getValue() + ", ";
            preOrder(temp.getLeft());
            preOrder(temp.getRight());
        }

        return output.substring(0, output.length() - 2) + "]";
    }

    public String preOrder() {
        output = "[";
        return preOrder(root);
    }

    public String inOrder(TreeNode<E> temp) {

        if(size == 0) {
            return "[]";
        }

        if (temp != null) {
            inOrder(temp.getLeft());
            output += temp.getValue() + ", ";
            inOrder(temp.getRight());
        }

        return output.substring(0, output.length() - 1) + "]";
    }

    public String inOrder() {
        output = "[";
        return inOrder(root);
    }

    public String postOrder(TreeNode<E> temp) {

        if(size == 0) {
            return "[]";
        }

        if (temp != null) {
            postOrder(temp.getLeft());
            postOrder(temp.getRight());
            output += temp.getValue() + ", ";
        }

        return output.substring(0, output.length() - 1) + "]";
    }

    public String postOrder() {
        output = "[";
        return postOrder(root);
    }

    public void rotateLeft(){
        if(root.getRight() != null) {
            rotateLeft(root.getRight());
        }
    }

    public void rotateLeft(TreeNode<E> val) {
        if(val.getLeft() == null)
            root.setRight(null);
        else 
            root.setRight(val.getLeft());
        
        val.setLeft(root);
        root = val;

    }

    public void rotateRight(){
        if(root.getLeft() != null) {
            rotateRight(root.getLeft());
        }
    }

    public void rotateRight(TreeNode<E> val) {
        if(val.getRight() == null)
            root.setLeft(null);
        else 
            root.setLeft(val.getRight());
        
        val.setRight(root);
        root = val;
    }

    public int size() {
        return size;
    }

    public class TreeNode<E extends Comparable<E>>
    {
        E val;
        TreeNode<E> left;
        TreeNode<E> right;
        TreeNode(E val)
        {
            this.val = val;
            left = null;
            right = null;
        }
        public E getValue()
        {
            return val;
        }
        public TreeNode<E> getRight()
        {
            return right;
        }
        public TreeNode<E> getLeft()
        {
            return left;
        }
        public void setRight(TreeNode<E> right)
        {
            this.right = right;
        }
        public void setLeft(TreeNode<E> left)
        {
            this.left = left;
        }
        public void setval(E val)
        {
            this.val = val;
        }
        public String toString()
        {
            return val.toString();
        }
        public int compareTo(TreeNode<E> other)
        {
            return val.compareTo(other.getValue());
    }
}

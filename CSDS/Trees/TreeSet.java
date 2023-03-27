package Trees;

public class TreeSet<E extends Comparable<E>> {
    TreeNode<E> root;
    int size;
    public TreeSet()
    {
        root = null;
    }
    public void add(E value)
    {
        if (contains(value))
        {
            return; // if the value is already in the tree, do nothing
        }
        else if (root == null) // if the root is null, add the value to the root
        {
            root = new TreeNode<E>(value);
        }
        else
        {
            TreeNode<E> current = root;
            while(true)
            {
                if(value.compareTo(current.getValue()) < 0) // if value is less than current, go left
                {
                    if(current.getLeft() == null) // if left is null, add new node
                    {
                        current.setLeft(new TreeNode<E>(value));
                        break;
                    }
                    else // else set current to the value on the left
                    {
                        current = current.left;
                    }
                }
                else // if value is greater than current, go right
                {
                    if(current.getRight() == null) // if right is null, add new node
                    {
                        current.setRight(new TreeNode<E>(value));
                        break;
                    }
                    else // else set current to the value on the right
                    {
                        current = current.right;
                    }
                }
            }
        }
    }
    public boolean contains(E value)
    {
        TreeNode<E> current = root;
        while(current != null)
        {
            if(value.compareTo(current.getValue()) < 0) // if value is less than current, go left
            {
                current = current.left;
            }
            else if(value.compareTo(current.getValue()) > 0) // if value is greater than current, go right
            {
                current = current.right;
            }
            else // if value is equal to current, return true
            {
                return true;
            }
        }
        return false;
    }
    public E remove(E value)
    {
        if (root.getValue().equals(value) && root.getLeft() == null && root.getRight() == null)
        {
            root = null;
            size = 0;
            return value;
        }
        else
        {
            //recursive
            recursiveRemove(root, value);
        }
    }
    public TreeNode<E> recursiveRemove(TreeNode<E> current, E value)
    {
        if(value.compareTo(current.getValue()) < 0) // if value is less than current, go left
        {
            current.setLeft(recursiveRemove(current.getRight(), value)); // send node on right
        }
        else if(value.compareTo(current.getValue()) > 0) // if value is greater than current, go right
        {
            current.setRight(recursiveRemove(current.getRight(), value)); // send node on right
        }
        else // if value is equal to current, return true
        {
            if (current.getLeft() == null)
            {
                return current.getRight();
            }
            else if (current.getRight() == null)
            {
                return current.getLeft();
            }
            else
            {
                current.setValue(minValue(current.getRight()));
                current.setRight(recursiveRemove(current.getRight(), current.getValue()));
            }
        }

    }
    public E minValue(TreeNode<E> node)
    {
        E minv = node.getValue();
        while (node.getLeft() != null)
        {
            minv = node.getLeft().getValue();
            node = node.getLeft();
        }
        return minv;
    }
    public class TreeNode<E extends Comparable<E>>
    {
        E value;
        TreeNode<E> left;
        TreeNode<E> right;
        TreeNode(E value)
        {
            this.value = value;
            left = null;
            right = null;
        }
        public E getValue()
        {
            return value;
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
        public void setValue(E value)
        {
            this.value = value;
        }
        public String toString()
        {
            return value.toString();
        }
    }
}

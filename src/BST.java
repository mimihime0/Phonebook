public class BST<T>
{
    BSTNode<T> root, current;
    private int size;

    public BST() {
        root = current = null;
    }

    public BST(BSTNode<T> root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public BSTNode<T> getRoot() {
        return root;
    }

    public int size() {
        return size;
    }

    public void findRoot()
    {
        current=root;
    }

    public T retrieve() {
        return current.getData();
    }



    public boolean findKey(String key)
    {
        BSTNode<T> p = root;
        if(isEmpty())
            return false;
        while(p != null) {
            current= p;
            if(key.compareToIgnoreCase(p.getKey())==0) {
                return true;
            }
            else if (key.compareToIgnoreCase(p.getKey()) < 0) {
                p = p.getLeft();
            } else {
                p = p.getRight();
            }
        }
        return false;
    }
    public BSTNode<T> findKeyNode(String key) {
        BSTNode<T> p = root;
        if (isEmpty())
            return null;
        while (p != null) {
            if (key.compareToIgnoreCase(p.getKey()) == 0) {
                return p;
            } else if (key.compareToIgnoreCase(p.getKey()) < 0) {
                p = p.getLeft();
            } else {
                p = p.getRight();
            }
        }
        return null;
    }

    public boolean add(String key,T value)
    {
        if (root==null)
        {
            current=root=new BSTNode<T>(key,value);
            return true;
        }

        BSTNode<T> p=current;
        if (findKey(key))
        {
            current=p;
            return false;
        }

        BSTNode<T> temp=new BSTNode<>(key,value);
        if (key.compareToIgnoreCase(current.getKey())<0)
            current.setLeft(temp);
        else
            current.setRight(temp);
        current=temp;
        return true;
    }



//    public boolean add(String k, T type) {
//        BSTNode<T> p, q = current;
//        if (! findKey(k)) {//haven't found key
//            System.out.println("Haven't found key");
//            current = q; //
//            return false; // key already in the BST
//        }
//        p = new BSTNode<T>(k, type);
//        if(isEmpty()) {//we are going to add this one element
//            System.out.println("only one element");
//            root = current = p;
//            return true;
//        } else {
//            System.out.println("It's not empty");
//// current is pointing to parent of the new key
//            if (k.compareTo(current.getKey()) < 0)
//                current.setLeft(p);
//            else
//                current.setRight(p);
//            current = p;
//            return true;
//        }
//    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(BSTNode<T> node) {
        if (node != null) {
            printInOrder(node.getLeft());
            System.out.println(node.getData());
            //System.out.println(node.key + ": " + node..getData());
            printInOrder(node.getRight());
        }

    }
}

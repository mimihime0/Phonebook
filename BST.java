public class BST<T> {
    private BSTNode<T> root;
    private int size;

    public BST() {
        root = null;
        size = 0;
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

    public T search(String key) {
        BSTNode<T> node = findNodeRecursive(root, key);
        return (node != null) ? node.getData() : null;
    }

    public BSTNode<T> findNode(String key) {
         return findNodeRecursive(root, key);
    }


    private BSTNode<T> findNodeRecursive(BSTNode<T> node, String key) {
        if (node == null) {
            return null; 
        }

        int compare = key.compareToIgnoreCase(node.getKey());

        if (compare == 0) {
            return node; 
        } else if (compare < 0) {
            return findNodeRecursive(node.getLeft(), key); 
        } else {
            return findNodeRecursive(node.getRight(), key); 
        }
    }

    public boolean containsKey(String key) {
        return findNodeRecursive(root, key) != null;
    }


    public boolean add(String key, T value) {
        if (key == null || value == null) return false;

        if (containsKey(key)) {
            return false;
        }

        root = addRecursive(root, key, value);
        size++;
        return true;
    }

    private BSTNode<T> addRecursive(BSTNode<T> node, String key, T value) {
        if (node == null) {
            return new BSTNode<>(key, value); 
        }

        int compare = key.compareToIgnoreCase(node.getKey());

        if (compare < 0) {
            node.setLeft(addRecursive(node.getLeft(), key, value));
        } else if (compare > 0) {
            node.setRight(addRecursive(node.getRight(), key, value));
        }

        return node; 
    }


    public boolean remove(String key) {
        if (key == null || !containsKey(key)) {
            return false;
        }

        root = removeRecursive(root, key);
        size--;
        return true;
    }


    private BSTNode<T> removeRecursive(BSTNode<T> node, String key) {
        if (node == null) {
            return null; 
        }

        int compare = key.compareToIgnoreCase(node.getKey());

        if (compare < 0) {
            node.setLeft(removeRecursive(node.getLeft(), key));
        } else if (compare > 0) {
            node.setRight(removeRecursive(node.getRight(), key));
        } else {

            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }

            else if (node.getLeft() == null) {
                return node.getRight(); 
            } else if (node.getRight() == null) {
                return node.getLeft(); 
            }

            else {

                BSTNode<T> successor = findMin(node.getRight());

                node.setKey(successor.getKey());
                node.setData(successor.getData());

                node.setRight(removeRecursive(node.getRight(), successor.getKey()));
            }
        }
        return node; 
    }

    private BSTNode<T> findMin(BSTNode<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public void printInOrder() {
        printInOrderRecursive(root);
    }

    private void printInOrderRecursive(BSTNode<T> node) {
        if (node != null) {
            printInOrderRecursive(node.getLeft());

            System.out.println(node.getData());
            System.out.println("----------"); 
            printInOrderRecursive(node.getRight());
        }
    }

    public java.util.List<T> getAllDataInOrder() {
        java.util.List<T> list = new java.util.ArrayList<>();
        getAllDataInOrderRecursive(root, list);
        return list;
    }

    private void getAllDataInOrderRecursive(BSTNode<T> node, java.util.List<T> list) {
        if (node != null) {
            getAllDataInOrderRecursive(node.getLeft(), list);
            list.add(node.getData());
            getAllDataInOrderRecursive(node.getRight(), list);
        }
    }
}

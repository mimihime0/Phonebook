public class BSTNode <T> {
    private String key; 
    private T data;   
    private BSTNode<T> left, right;

    public BSTNode(String k, T val) {
        key = k;
        data = val;
        left = right = null;
    }

    // Getters
    public T getData() {
        return data;
    }

    public String getKey() {
        return key;
    }

    public BSTNode<T> getLeft() {
        return left;
    }

    public BSTNode<T> getRight() {
        return right;
    }

    // Setters
    public void setLeft(BSTNode<T> left) {
        this.left = left;
    }

    public void setRight(BSTNode<T> right) {
        this.right = right;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setData(T data) {
        this.data = data;
    }
}

import java.nio.BufferUnderflowException;

public class BinarySearchTree {
    private binaryTree root;

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(binaryTree x, binaryTree start) {
        if (start == null)
            return false;
        if (x.value < start.value) {
            return contains(x, start.left);
        } else if (x.value > start.value) {
            return contains(x, start.right);
        } else {
            return true;
        }
    }

    public int findMin() {
        if (root == null) throw new BufferUnderflowException();
        return findMinChild(root).getValue();
    }

    public binaryTree findMinChild(binaryTree node) {
        if (node.left == null) {
            return node;
        }
        return findMinChild(node.left);
    }

    public int findMax() {
        if (root == null) throw new BufferUnderflowException();
        return finMaxChild(root).getValue();
    }

    public binaryTree finMaxChild(binaryTree node) {
        if (node.right == null) {
            return node;
        }
        return findMinChild(node.right);
    }

    public binaryTree insert(binaryTree x,binaryTree t){
        if (t == null)
            return new binaryTree(x.value,null,null);
        if (x.value < t.value) {
            t.left = insert(x,t.left);
        } else if (x.value > t.value) {
            t.right = insert(x,t.right);
        } else {

        }
        return t;
    }


    class binaryTree {
        int value;
        binaryTree left;
        binaryTree right;

        public binaryTree(int value, binaryTree left, binaryTree right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public boolean  hasChild(){
            return true;
        }


        public int getValue() {
            return this.value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public binaryTree getLeft() {
            return left;
        }

        public void setLeft(binaryTree left) {
            this.left = left;
        }

        public binaryTree getRight() {
            return right;
        }

        public void setRight(binaryTree right) {
            this.right = right;
        }
    }
}

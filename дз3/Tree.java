public class Tree<T extends Comparable<T>> {
    private TreeNode<T> root;
    private int height;

    public Tree() {
        this.root = null;
        this.height = 0;
    }

    public Tree(TreeNode<T> root) {
        this.root = root;
        this.height = calculateHeight(root);
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int calculateHeight(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(calculateHeight(node.getLeft()), calculateHeight(node.getRight()));
    }

    public void add(T value) {
        if (root == null) {
            root = new TreeNode<>(value);
            height = 1;
        } else {
            add(root, value);
        }
    }

    private void add(TreeNode<T> current, T value) {
        if (current.getValue().compareTo(value) > 0) {
            if (current.getLeft() == null) {
                current.setLeft(new TreeNode<>(value));
                height = calculateHeight(root);
            } else {
                add(current.getLeft(), value);
            }
        } else if (current.getValue().compareTo(value) <= 0) {
            if (current.getRight() == null) {
                current.setRight(new TreeNode<>(value));
                height = calculateHeight(root);
            } else {
                add(current.getRight(), value);
            }
        }
    }

    public T get(T value) {
        if (this.root == null) {
            return null;
        }
        return get(root, value);
    }

    private T get(TreeNode<T> node, T value) {
        if (node == null) {
            return null;
        }
        int cmp = value.compareTo(node.getValue());
        if (cmp < 0) {
            return get(node.getLeft(), value);
        } else if (cmp > 0) {
            return get(node.getRight(), value);
        } else {
            return node.getValue();
        }
    }

    private TreeNode<T> findMin(TreeNode<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public void remove(T value) {
        root = remove(root, value);
        height = calculateHeight(root);
    }

    private TreeNode<T> remove(TreeNode<T> node, T value) {
        if (node == null) {
            return null;
        }
        int cmp = value.compareTo(node.getValue());
        if (cmp < 0) {
            node.setLeft(remove(node.getLeft(), value));
        } else if (cmp > 0) {
            node.setRight(remove(node.getRight(), value));
        } else {
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            } else if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                TreeNode<T> minNode = findMin(node.getRight());
                node.setValue(minNode.getValue());
                node.setRight(remove(node.getRight(), minNode.getValue()));
            }
        }
        return node;
    }

    public void preOrder() {
        preOrder(root);
        System.out.println();
    }

    private void preOrder(TreeNode<T> node) {
        if (node != null) {
            System.out.print(node.getValue() + " ");
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(TreeNode<T> node) {
        if (node != null) {
            inOrder(node.getLeft());
            System.out.print(node.getValue() + " ");
            inOrder(node.getRight());
        }
    }

    public void postOrder() {
        postOrder(root);
        System.out.println();
    }
    

    private void postOrder(TreeNode<T> node) {
        if (node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.println(node.getValue() + " ");
        }
    }
 }
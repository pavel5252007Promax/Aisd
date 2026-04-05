public class TwoThreeTree {
    private Node root;
    private int size;

    private static class Node {
        int[] keys = new int[2]; //макс 2
        Node[] children = new Node[3]; // макс 3
        int keyCount; // текущее кол-во ключей (1 или 2)

        boolean isLeaf() {
            return children[0] == null;
        }

        boolean canGive() {
            return keyCount == 2;
        }

        boolean canTake() {
            return keyCount == 1;
        }

        int max() {
            if (isLeaf()) {
                return keys[keyCount - 1];
            }
            return children[keyCount].max();
        }

        int min() {
            if (isLeaf()) {
                return keys[0];
            }
            return children[0].min();
        }
    }

    public TwoThreeTree() {
        root = null;
        size = 0;
    }

    private static class DeleteResult {
        Node node;
        boolean underFlow;
        DeleteResult(Node node, boolean underFlow) {
            this.node = node;
            this.underFlow = underFlow;
        }
    }

    private static class SplitInfo {
        Node left;
        Node right;
        int key;
        SplitInfo(Node left, Node right, int key) {
            this.left = left;
            this.right = right;
            this.key = key;
        }
    }

    public void insert(int key) {
        if (root == null) {
            root = new Node();
            root.keys[0] = key;
            root.keyCount = 1;
            size++;
            return;
        }

        SplitInfo split = insert(root, key);
        if (split != null) {
            Node newRoot = new Node();
            newRoot.keys[0] = split.key;
            newRoot.children[0] = split.left;
            newRoot.children[1] = split.right;
            newRoot.keyCount = 1;
            root = newRoot;
        }
        size++;
    }

    private SplitInfo insert(Node node, int key) {
        if (node.isLeaf()) {
            if (node.keyCount == 1) {
                if (key < node.keys[0]) {
                    node.keys[1] = node.keys[0];
                    node.keys[0] = key;
                } else {
                    node.keys[1] = key;
                }
                node.keyCount = 2;
                return null;
            } else {
                int a = node.keys[0];
                int b = node.keys[1];
                int c = key;
                if (a > c) {
                    int t = a;
                    a = c;
                    c = t;
                }
                if (b > c) {
                    int t = b;
                    b = c;
                    c = t;
                }
                if (a > b) {
                    int t = a;
                    a = b;
                    b = t;
                }
                Node left = new Node();
                left.keys[0] = a;
                left.keyCount = 1;
                Node right = new Node();
                right.keys[0] = c;
                right.keyCount = 1;

                return new SplitInfo(left, right, b);
            }
        } else {
            int childIndex;
            if (key < node.keys[0]) {
                childIndex = 0;
            } else if (node.keyCount == 1 || key < node.keys[1]) {
                childIndex = 1;
            } else {
                childIndex = 2;
            }

            SplitInfo childSplit = insert(node.children[childIndex], key);
            if (childSplit == null) {
                return null;
            }
            int promotedKey = childSplit.key;
            Node leftNode = childSplit.left;
            Node rightNode = childSplit.right;
            if (node.keyCount == 1) {
                if (promotedKey < node.keys[0]) {
                    node.keys[1] = node.keys[0];
                    node.keys[0] = promotedKey;
                    node.children[2] = node.children[1];
                    node.children[1] = rightNode;
                    node.children[0] = leftNode;
                } else {
                    node.keys[1] = promotedKey;
                    node.children[2] = rightNode;
                    node.children[1] = leftNode;
                }
                node.keyCount = 2;
                return null;
            } else {
                int a = node.keys[0];
                int b = node.keys[1];
                int c = promotedKey;
                Node ch0 = node.children[0];
                Node ch1 = node.children[1];
                Node ch2 = node.children[2];
                Node ch3;

                if (promotedKey < a) {
                    ch3 = ch2;
                    ch2 = ch1;
                    ch1 = rightNode;
                    ch0 = leftNode;
                } else if (promotedKey < b) {
                    ch3 = ch2;
                    ch2 = rightNode;
                    ch1 = leftNode;
                } else {
                    ch3 = rightNode;
                    ch2 = leftNode;
                }

                Node left = new Node();
                Node right = new Node();

                int median;

                if (promotedKey < a) {
                    median = a;
                    left = new Node();
                    left.keys[0] = promotedKey;
                    left.keyCount = 1;
                    left.children[0] = leftNode;
                    left.children[1] = rightNode;
                    right = new Node();
                    right.keys[0] = b;
                    right.keyCount = 1;
                    right.children[0] = ch1;
                    right.children[1] = ch2;
                } else if (promotedKey < b) {
                    median = b;
                    left = new Node();
                    left.keys[0] = a;
                    left.keyCount = 1;
                    left.children[0] = ch0;
                    left.children[1] = leftNode;
                    right = new Node();
                    right.keys[0] = promotedKey;
                    right.keyCount = 1;
                    right.children[0] = rightNode;
                    right.children[1] = ch2;
                } else {
                    median = c;
                    left = new Node();
                    left.keys[0] = a;
                    left.keyCount = 1;
                    left.children[0] = ch0;
                    left.children[1] = ch1;
                    right = new Node();
                    right.keys[0] = b;
                    right.keyCount = 1;
                    right.children[0] = leftNode;
                    right.children[1] = rightNode;
                }
                return new SplitInfo(left, right, median);
            }
        }
    }

    public boolean search(int key) {
        return search(root, key);
    }

    public boolean search(Node node, int key) {
        if (node == null) {
            return false;
        }

        if (node.isLeaf()) {
            for (int i = 0; i < node.keyCount; i++) {
                if (key == node.keys[i]) {
                    return true;
                }
            }
            return false;
        }

        if (key < node.keys[0]) {
            return search(node.children[0], key);
        } else if (node.keyCount == 1 || key < node.keys[1]) {
            return search(node.children[1], key);
        } else {
            return search(node.children[2], key);
        }
    }

    public boolean delete(int key) {
        if (root == null || !search(key)) {
            return false;
        }

        DeleteResult res = delete(root, key);
        root = res.node;
        if (res.underFlow) {
            if (root.keyCount == 0 && !root.isLeaf()) {
                root = root.children[0];
            } else {
                root = null;
            }
        }
        size--;
        return true;
    }

    private DeleteResult delete(Node node, int key) {
        if (node.isLeaf()) {
            int newCount = 0;
            for (int i = 0; i < node.keyCount; i++) {
                if (node.keys[i] != key) {
                    node.keys[newCount++] = node.keys[i];
                }
            }
            node.keyCount = newCount;
            return new DeleteResult(node, node.keyCount == 0);
        } else {
            int childIdx = 0;
            if (key < node.keys[0]) {
                childIdx = 0;
            } else if (node.keyCount == 1 || key < node.keys[1]) {
                childIdx = 1;
            } else {
                childIdx = 2;
            }

            if ((childIdx == 0 && key == node.keys[0]) || (childIdx == 1 && node.keyCount == 2 && key == node.keys[1])) {
                Node leftSubtree = node.children[childIdx];
                int replacement = leftSubtree.max();
                DeleteResult leftRes = delete(leftSubtree, replacement);
                node.children[childIdx] = leftRes.node;

                if (childIdx == 0) {
                    node.keys[0] = replacement;
                } else {
                    node.keys[1] = replacement;
                }

                if (leftRes.underFlow) {
                    return handleUnderFlow(node, childIdx);
                } else {
                    return new DeleteResult(node, false);
                }
            } else {
                DeleteResult childRes = delete(node.children[childIdx], key);
                node.children[childIdx] = childRes.node;

                if (childRes.underFlow) {
                    return handleUnderFlow(node, childIdx);
                } else {
                    return new DeleteResult(node, false);
                }
            }
        }
    }

    private DeleteResult handleUnderFlow(Node parent, int childIdx) {
        Node leftSibling = childIdx > 0 ? parent.children[childIdx - 1] : null;
        Node rightSibling = childIdx < parent.keyCount ? parent.children[childIdx + 1] : null;

        if (leftSibling != null && leftSibling.canGive()) {
            redistributeFromLeft(parent, childIdx, leftSibling);
            return new DeleteResult(parent, false);
        }

        if (rightSibling != null && rightSibling.canGive()) {
            redistributeFromRight(parent, childIdx, rightSibling);
            return new DeleteResult(parent, false);
        }

        if (leftSibling != null) {
            mergeWithleft(parent, childIdx, leftSibling);
        } else if (rightSibling != null) {
            mergeWithRight(parent, childIdx, rightSibling);
        }
        return new DeleteResult(parent, parent.keyCount == 0);
    }

    private void redistributeFromLeft(Node parent, int childIdx, Node leftSibling) {
        Node child = parent.children[childIdx];
        if (child.isLeaf()) {
            child.keys[1] = child.keys[0];
            child.keys[0] = parent.keys[childIdx - 1];
            child.keyCount = 2;
            parent.keys[childIdx - 1] = leftSibling.keys[1];
            leftSibling.keyCount = 1;
        } else {
            child.keys[1] = child.keys[0];
            child.children[2] = child.children[1];
            child.children[1] = child.children[0];
            child.keys[0] = parent.keys[childIdx - 1];
            child.children[0] = leftSibling.children[leftSibling.keyCount];
            parent.keys[childIdx - 1] = leftSibling.keys[leftSibling.keyCount - 1];
            child.keyCount = 2;
            leftSibling.keyCount--;
        }
    }

    private void redistributeFromRight(Node parent, int childIdx, Node rightSibling) {
        Node child = parent.children[childIdx];
        if (child.isLeaf()) {
            child.keys[1] = parent.keys[childIdx];
            child.keyCount = 2;
            parent.keys[childIdx] = rightSibling.keys[0];
            rightSibling.keys[0] = rightSibling.keys[1];
            rightSibling.keyCount = 1;
        } else {
            child.keys[1] = parent.keys[childIdx];
            child.children[2] = rightSibling.children[0];
            child.keyCount = 2;
            parent.keys[childIdx] = rightSibling.keys[0];
            rightSibling.keys[0] = rightSibling.keys[1];
            rightSibling.children[0] = rightSibling.children[1];
            rightSibling.children[1] = rightSibling.children[2];
            rightSibling.keyCount--;
        }
    }

    private void mergeWithleft(Node parent, int childIdx, Node leftSibling) {
        Node child = parent.children[childIdx];
        int parentKey = parent.keys[childIdx - 1];
        if (child.isLeaf()) {
            leftSibling.keys[1] = parentKey;
            leftSibling.keyCount = 2;
        } else {
            leftSibling.keys[1] = parentKey;
            leftSibling.children[2] = child.children[0];
            leftSibling.keyCount = 2;
        }

        for (int i = childIdx - 1; i < parent.keyCount - 1; i++) {
            parent.keys[i] = parent.keys[i + 1];
            parent.children[i + 1] = parent.children[i + 2];
        }
        parent.children[parent.keyCount] = null;
        parent.keyCount--;
    }

    private void mergeWithRight(Node parent, int childIdx, Node rightSibling) {
        Node child = parent.children[childIdx];
        int parentKey = parent.keys[childIdx];
        if (child.isLeaf()) {
            rightSibling.keys[1] = rightSibling.keys[0];
            rightSibling.keys[0] = parentKey;
            rightSibling.keyCount = 2;
        } else {
            rightSibling.keys[1] = rightSibling.keys[0];
            rightSibling.keys[0] = parentKey;
            rightSibling.children[2] = rightSibling.children[1];
            rightSibling.children[1] = rightSibling.children[0];
            rightSibling.children[0] = child.children[0];
            rightSibling.keyCount = 2;
        }

        for (int i = childIdx; i < parent.keyCount - 1; i++) {
            parent.keys[i] = parent.keys[i+1];
            parent.children[i + 1] = parent.children[i + 2];
        }
        parent.children[parent.keyCount] = null;
        parent.keyCount--;
    }

    public int size() {
        return size;
    }

    public void print() {
        print(root, 0);
    }

    private void print(Node node, int level) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < level; i++) {
            System.out.print(" ");
        }
        for (int i = 0; i < node.keyCount; i++) {
            System.out.print(node.keys[i] + " ");
        }
        System.out.println();
        if (!node.isLeaf()) {
            for (int i = 0; i <= node.keyCount; i++) {
                print(node.children[i], level + 1);
            }
        }
    }
}
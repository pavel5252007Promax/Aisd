public class task4 {
    public static void main(String[] args) {
        Tree<Integer> t = new Tree<>();
        t.add(4);
        t.add(1);
        t.add(9);
        t.add(26);
        t.add(58);
        t.add(52);
        t.add(2);
        

        calc(t.getRoot());
    }

    public static int calc(TreeNode<Integer> n) {
        if (n.getLeft() != null && n.getRight() != null) {
            int l = calc(n.getLeft());
            int r = calc(n.getRight());
            int s = l + r + n.getValue();
            System.out.println(s);
            return s;
        }

        if (n.getLeft() != null) {
            int l = calc(n.getLeft());
            int s = l + n.getValue();
            System.out.println(s);
            return s;
        }

        if (n.getRight() != null) {
            int r = calc(n.getRight());
            int s = r + n.getValue();
            System.out.println(s);
            return s;
        }

        int v = n.getValue();
        System.out.println(v);
        return v;
    }
}
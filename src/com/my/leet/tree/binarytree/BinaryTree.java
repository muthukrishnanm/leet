package com.my.leet.tree.binarytree;

import com.my.leet.tree.binarytree.bst.BST;

public class BinaryTree {
    protected final Node root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public void displayInorder() {
        displayTree(root);
    }

    public void displayPostOrder() {
        displayTreePostOrder(root);
    }

    private static void displayTree(Node node) {
        if (node != null) {
            displayTree(node.getLeft());
            System.out.print(node.getValue() + " ");
            displayTree(node.getRight());
        }
    }

    private static void displayTreePostOrder(Node node) {
        if (node != null) {
            displayTreePostOrder(node.getLeft());
            displayTreePostOrder(node.getRight());
            System.out.print(node.getValue() + " ");
        }
    }

    public boolean isBst() {
        return isBstStatic(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isBstStatic(Node node, int min, int max) {
        if (node.getLeft() != null) {
            return isBstStatic(node.getLeft(), min, node.getValue());
        }
        if (node.getRight() != null) {
            return isBstStatic(node.getRight(), node.getValue(), max);
        }
        return node.getValue() > min && node.getValue() < max;
    }

    public boolean isTreeBalanced() {
        return isTreeBalancedStatic(root);
    }

    private static boolean isTreeBalancedStatic(Node node) {
        if (node == null) {
            return true;
        }
        int leftHeight = height(node.getLeft());
        int rightHeight = height(node.getRight());
        return Math.abs(leftHeight - rightHeight) <= 1 && isTreeBalancedStatic(node.getLeft()) && isTreeBalancedStatic(node.getRight());
    }

    private static int height(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    public static void main(String[] args) {
        Node n780 = new Node(null, null, 780);
        Node n980 = new Node(null, null, 980);
        Node n1000 = new Node(n780, n980, 1000);
        Node n900 = new Node(null, null, 900);
        Node n150 = new Node(n900, n1000, 150);
        Node n700 = new Node(null, null, 700);
        Node n800 = new Node(null, null, 800);
        Node n200 = new Node(n700, n800, 200);
        Node root = new Node(n150, n200, 100);

        BinaryTree binaryTree = new BinaryTree(root);
        binaryTree.displayInorder();

        System.out.println("Is BST = " + binaryTree.isBst());


        BST bst = new BST(new Node(null, null, 100));
        bst.insert(50);
        bst.insert(150);
        bst.insert(25);
        bst.insert(75);
        bst.insert(125);
        bst.insert(175);
        bst.insert(12);
        bst.insert(37);

        bst.displayInorder();
        System.out.println("Is BST = " + bst.isBst());
        System.out.println("Is balanced Tree = " + bst.isTreeBalanced());


        Node n6000 = new Node(null, null, 6000);
        Node n5000 = new Node(null, null, 5000);
        Node n4000 = new Node(n6000, n5000, 4000);
        Node n2000 = new Node(null, null, 2000);
        Node n1200 = new Node(n2000, n4000, 1200);
        Node n10000 = new Node(null, null, 10000);
        Node n1000_1 = new Node(n10000, n1200, 10000);
        BinaryTree binaryTree1 = new BinaryTree(n1000_1);
        System.out.println("Is balanced Tree = " + binaryTree1.isTreeBalanced());

    }
}

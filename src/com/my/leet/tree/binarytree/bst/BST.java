package com.my.leet.tree.binarytree.bst;

import com.my.leet.tree.binarytree.BinaryTree;
import com.my.leet.tree.binarytree.Node;

import java.util.Stack;

public class BST extends BinaryTree {
    private static class Index {
        private int index;

        public Index(int index) {
            this.index = index;
        }

        public void decrement() {
            this.index = this.index - 1;
        }

        public int getIndex() {
            return index;
        }
    }

    public BST(Node root) {
        super(root);
    }

    public void insert(int value) {
        Node next = root;
        Node previous = null;
        boolean isLeft = false;
        while (next != null) {
            int currentValue = next.getValue();
            previous = next;
            if (currentValue > value) {
                next = next.getLeft();
                isLeft = true;
            } else {
                next = next.getRight();
                isLeft = false;
            }
        }
        if (isLeft) {
            previous.setLeft(new Node(null, null, value));
            return;
        }
        previous.setRight(new Node(null, null, value));
    }

    public void delete(int value) {
        Node deleteNode = root;
        Node previous = null;
        boolean isLeft = false;
        while (deleteNode != null) {
            int currentValue = deleteNode.getValue();
            if (currentValue == value) {
                break;
            } else if (currentValue > value) {
                previous = deleteNode;
                deleteNode = deleteNode.getLeft();
                isLeft = true;
            } else {
                previous = deleteNode;
                deleteNode = deleteNode.getRight();
                isLeft = false;
            }
        }
        if (deleteNode == null) {
            throw new RuntimeException("Cannot find the node to delete");
        }

        //No child
        if (deleteNode.getLeft() == null && deleteNode.getRight() == null) {
            if (isLeft) {
                previous.setLeft(null);
            } else {
                previous.setRight(null);
            }
            return;
        }

        //Only right child
        if ((deleteNode.getLeft() == null && deleteNode.getRight() != null)) {
            if (isLeft) {
                previous.setLeft(deleteNode.getRight());
            } else {
                previous.setRight(deleteNode.getRight());
            }
            return;
        }

        //Only left child
        if (deleteNode.getLeft() != null && deleteNode.getRight() == null) {
            if (isLeft) {
                previous.setLeft(deleteNode.getLeft());
            } else {
                previous.setRight(deleteNode.getLeft());
            }
            return;
        }

        if (deleteNode.getLeft() != null && deleteNode.getRight() != null) {
            Node successor = deleteNode.getRight();
            Node successorPrevious = null;
            while (successor.getLeft() != null) {
                successorPrevious = successor;
                successor = successor.getLeft();
            }
            if (successor.getRight() != null && successor != deleteNode.getRight()) {
                successorPrevious.setLeft(successor.getRight());
                successor.setRight(null);
            } else {
                successorPrevious.setLeft(null);
            }
            if (isLeft) {
                previous.setLeft(successor);
            } else {
                previous.setRight(successor);
            }
            successor.setRight(deleteNode.getRight());
            successor.setLeft(deleteNode.getLeft());
        }


    }

    public static BST fromPostOrder(int[] postOrder) {
        int size = postOrder.length;
        Index index = new Index(size - 1);
        Node node = fromPostOrderStatic(postOrder, index, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return new BST(node);
    }

    private static Node fromPostOrderStatic(int[] postOrder, Index index, int min, int max) {
        if (index.getIndex() < 0) {
            return null;
        }
        Node root = null;
        int key = postOrder[index.getIndex()];
        if (key > min && key < max) {
            root = new Node(null, null, key);
            index.decrement();
            if (index.getIndex() > 0) {
                root.setLeft(fromPostOrderStatic(postOrder, index, min, key));
                root.setRight(fromPostOrderStatic(postOrder, index, key, max));
            }

        }
        return root;
    }

    private static BST fromPostOrder1(int[] postOrder) {
        int n = postOrder.length;
        Node root = new Node(null, null, postOrder[n - 1]);
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        for (int i = n - 2; i >= 0; i--) {
            int element = postOrder[i];
            Node thisNode = new Node(null, null, element);
            Node temp = null;
            while (!stack.isEmpty() && element < stack.peek().getValue()) {
                temp = stack.pop();
            }
            if (temp == null) {
                stack.peek().setRight(thisNode);
            } else {
                temp.setLeft(thisNode);
            }
            stack.push(thisNode);
        }
        return new BST(root);
    }

    private static int sum = 0;
    public static void transformToGreaterSumTree(Node node) {
        if (node == null) {
            return;
        }
        transformToGreaterSumTree(node.getRight());
        int temp = node.getValue();
        node.setValue(sum);
        sum = sum + temp;
        transformToGreaterSumTree(node.getLeft());
    }

    public static void main(String[] args) {
//        BST bst = new BST(new Node(null, null, 10));
//        bst.insert(3);
//        bst.insert(14);
//        bst.insert(1);
//        bst.insert(6);
//        bst.insert(13);
//        bst.insert(4);
//        bst.insert(7);
//        System.out.println("TREE:");
//        //bst.displayInorder(); //in order
//        bst.displayPostOrder();
//
////        bst.delete(3);
////        System.out.println("\nAfter deletion of 3:");
////        bst.display();

// Construct BST from post order
//        int[] postOrder = new int[]{1, 7, 5, 50, 40, 10};
//        BST bst = fromPostOrder1(postOrder);
//        bst.displayInorder();


        // Construct greater sum tree from BST
        Node root = new Node(null, null, 10);

        Node n15 = new Node(null, null, 15);
        Node n20 = new Node(null, null, 20);
        Node n12 = new Node(null, null, 12);
        n15.setRight(n20);
        n15.setLeft(n12);
        root.setRight(n15);

        Node n5 = new Node(null, null, 5);
        Node n2 = new Node(null, null, 2);
        Node n7 = new Node(null, null, 7);
        n5.setRight(n7);
        n5.setLeft(n2);
        root.setLeft(n5);

        transformToGreaterSumTree(root);
        BinaryTree binaryTree = new BinaryTree(root);
        binaryTree.displayInorder();
    }
}

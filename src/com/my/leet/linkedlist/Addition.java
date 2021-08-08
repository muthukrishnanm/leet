package com.my.leet.linkedlist;

public class Addition {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode x = l1, y = l2;
        int a, b;
        int carry = 0;
        int sum = 0;
        ListNode node = new ListNode(0);
        ListNode head = node;
        while (x != null || y != null) {
            a = 0;
            b = 0;
            if (x != null) {
                a = x.val;
                x = x.next;
            }
            if (y != null) {
                b = y.val;
                y = y.next;
            }
            sum = a + b + carry;
            carry = sum / 10;
            sum = sum % 10;
            node.next = new ListNode(sum);
            node = node.next;
        }
        if(carry!=0){
            node.next = new ListNode(carry);
        }
        return head.next;
    }

    public static void main(String[] args) {
        Addition a = new Addition();
//        ListNode f = new ListNode(2, new ListNode(4, new ListNode(3)));
//        ListNode s = new ListNode(5, new ListNode(6, new ListNode(4)));
//        ListNode f = new ListNode(2, new ListNode(4, new ListNode(3)));
//        ListNode s = new ListNode(5, new ListNode(6));
        ListNode f = new ListNode(5, null);
        ListNode s = new ListNode(5, null);
        ListNode result = a.addTwoNumbers(f, s);
        while (result != null) {
            System.out.print(result.val);
            result = result.next;
        }
    }
}

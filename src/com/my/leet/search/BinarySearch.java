package com.my.leet.search;

public class BinarySearch {
    public int runBinarySearchRecursively(
            int[] sortedArray, int key, int low, int high) {
        int middle = (low + high) / 2;

        if (high < low) {
            return -1;
        }

        if (key == sortedArray[middle]) {
            return middle;
        } else if (key < sortedArray[middle]) {
            return runBinarySearchRecursively(
                    sortedArray, key, low, middle - 1);
        } else {
            return runBinarySearchRecursively(
                    sortedArray, key, middle + 1, high);
        }
    }

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int[] array = {1,3,5,7,10,11,16,20,23,30,34,60};
        int position = binarySearch.runBinarySearchRecursively(array, 13, 0, 11);
        System.out.println(position);
    }
}

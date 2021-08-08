package com.my.leet.matrix;

public class ElementFinder {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int position = binarySearch(matrix, 0, (m * n) - 1, target, m, n);
        if (position != -1) {
            return true;
        }
        return false;
    }

    public boolean searchMatrix1(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        return binarySearch1(matrix, target, 0, m*n-1, m, n);
    }

    public int binarySearch(int[][] matrix, int left, int right, int target, int m, int n) {
        if (right >= 1) {
            int mid = (left + right) / 2;
            int midm = (mid / m);
            int midn = (mid % n);
            int midValue = matrix[midm][midn];
            if (midValue > target) {
                return binarySearch(matrix, left, mid, target, m, n);
            }
            if (midValue < target) {
                return binarySearch(matrix, mid + 1, right, target, m, n);
            }
            if (midValue == target) {
                return mid;
            }
        }
        return -1;
    }

    public boolean binarySearch1(int[][] matrix, int target, int left, int right, int m, int n) {
        int middle = (left + right) / 2;
        if (left > right) {
            return false;
        }
        int middleValue = getMidValue(matrix, middle, m, n);
        if (middleValue == target) {
            return true;
        }
        if (middleValue > target) {
            return binarySearch1(matrix, target, left, middle-1, m, n);
        }
        return binarySearch1(matrix, target, middle+1, right, m, n);
    }

    public int getMidValue(int[][] matrix, int middle, int m, int n) {
        int midm = (middle / n);
        int midn = (middle % n);
        return matrix[midm][midn];
    }

    public static void main(String[] args) {
        ElementFinder elementFinder = new ElementFinder();
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        System.out.println(elementFinder.searchMatrix1(matrix, 77));
    }
}

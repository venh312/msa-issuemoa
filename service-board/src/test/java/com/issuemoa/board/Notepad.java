package com.issuemoa.board;

public class Notepad {
    public int pivotIndex(int[] nums) {
        int pivot = nums.length / 2;

        int leftSum = 0;
        for (int i = 0; i < pivot; i++) {
            leftSum += Math.abs(nums[i]);
        }
        int rightSum = 0;
        for (int i = pivot+1; i < nums.length; i++) {
            rightSum += Math.abs(nums[i]);
        }

        if (leftSum == rightSum) return pivot;
        else if (leftSum > rightSum) {
            while (pivot != 0) {
                pivot--;
                leftSum = 0;
                for (int i = 0; i < pivot; i++) {
                    leftSum += nums[i];
                }
                rightSum = 0;
                for (int i = pivot+1; i < nums.length; i++) {
                    rightSum += nums[i];
                }
                if (leftSum == rightSum) return pivot;
            }
            return -1;
        } else {
            while (pivot == nums.length) {
                pivot++;
                leftSum = 0;
                for (int i = 0; i < pivot; i++) {
                    leftSum += nums[i];
                }
                rightSum = 0;
                for (int i = pivot+1; i < nums.length; i++) {
                    rightSum += nums[i];
                }
                if (leftSum == rightSum) return pivot;
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        Notepad notepad = new Notepad();
        //System.out.println(notepad.pivotIndex(new int[]{1,7,3,6,5,6}));
        //System.out.println(notepad.pivotIndex(new int[]{1,2,3}));
        System.out.println(notepad.pivotIndex(new int[]{-1,-1,-1,-1,-1,0}));
    }
}


/*
This class is the primary class compiled and executed when trying out different algorithms for the various problems.

 */

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3};
        int[] b = new int[]{4, 5, 6};
        NMaxCombinations(a, b, 3, 2);
    }


    /**
     * You have a binary tree t. Your task is to find the largest value in each row of this tree. In a tree, a row is a
     * set of nodes that have equal depth. For example, a row with depth 0 is a tree root, a row with depth 1 is composed
     * of the root's children, etc.
     * Return an array in which the first element is the largest value in the row with depth 0, the second element is the
     * largest value in the row with depth 1, the third element is the largest element in the row with depth 2, etc.
     */
    class Tree<T> {
        T value;
        Tree<T> left, right;

        public Tree(T t) {
            value = t;
        }
    }

    private int[] largestValueInTreeRows(Tree<Integer> t) {
        Queue<Tree<Integer>> queue = new ArrayDeque<>();
        List<Integer> valuesAtDepth = new ArrayList<>();

        //Add the initial node
        if (t != null) {
            queue.add(t);
        }

        //Set the number of elements at the 0 depth
        int queueSizeAtDepth = queue.size();

        while (!queue.isEmpty()) {
            //Set the curMax to the smallest value by default
            int curMax = Integer.MIN_VALUE;

            //Go through all the elements at the current depth
            for (int i = 0; i < queueSizeAtDepth; i++) {
                Tree<Integer> node = queue.remove();
                curMax = Math.max(curMax, node.value);
                //Add the children but these elements won't be run in the loop since they are past the queueSizeAtDepth
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            //Add the local max
            valuesAtDepth.add(curMax);
            //Set the loop for the next iteration to run the number of elements in the queue which should be the
            //number of elements at the specified depth
            queueSizeAtDepth = queue.size();
        }

        //Copy over list to array
        int[] values = new int[valuesAtDepth.size()];
        int counter = 0;
        for (Integer i : valuesAtDepth) values[counter++] = i;

        return values;
    }

    /**
     * You are climbing a staircase that has n steps. You can take the steps either 1 or 2 at a time.
     * Calculate how many distinct ways you can climb to the top of the staircase.
     */
    int climbingStairs(int n) {

        if (n == 1) return 1;

        int[] numberOfWays = new int[n + 1];

        numberOfWays[1] = 1;
        numberOfWays[2] = 2;

        for (int i = 3; i <= n; i++) {
            //The numberOfWays when one step away plus the numberOfWays when two steps away
            numberOfWays[i] = numberOfWays[i - 1] + numberOfWays[i - 2];
        }


        return numberOfWays[n];
    }


    /**
     * You are planning to rob houses on a specific street, and you know that every house on the street has a certain
     * amount of money hidden. The only thing stopping you from robbing all of them in one night is that adjacent
     * houses on the street have a connected security system. The system will automatically trigger an alarm if two
     * adjacent houses are broken into on the same night.
     * Given a list of non-negative integers nums representing the amount of money hidden in each house,
     * determine the maximum amount of money you can rob in one night without triggering an alarm.
     */

    int houseRobber(int[] nums) {

        //Only the first house can be robbed
        if (nums.length == 1) return nums[0];

        //If there are two houses, rob the one with the most money
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int[][] maxSoFar = new int[nums.length + 1][2];
        for (int i = 1; i <= nums.length; i++) {
            maxSoFar[i][0] = Math.max(maxSoFar[i][0], maxSoFar[i - 1][1]); //If you don't rob it
            maxSoFar[i][1] = nums[i - 1] + maxSoFar[i - 1][0]; //If you do rob it
        }

        return Math.max(maxSoFar[nums.length][0], maxSoFar[nums.length][1]);
    }


    /**
     * Given a sorted integer array that does not contain any duplicates,
     * return a summary of the number ranges it contains.
     */
    String[] composeRanges(int[] nums) {

        //If the parameter is empty
        if (nums.length == 0) return new String[0];

        //Set the starting value and ending value to the same initial element
        int starting = nums[0];
        int end = nums[0];

        List<String> answer = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < nums.length; i++) {
            if ((nums[i] - end) != 1) { //If the difference between the end and current element is not 1
                if (starting == end) {
                    sb.append(starting); //append just the starting if the two values are the same
                } else {
                    sb.append(starting + "->" + end); // else append the starting arrow and ending
                }
                starting = nums[i]; //reassign the starting
                end = nums[i]; //reassign the ending
                answer.add(sb.toString()); //add the string to the answer
                sb = new StringBuilder(); //reset the string builder
            } else {
                end = nums[i]; //move the end
            }
        }

        /**
         * Take care of the last possible string in cause the first if statement is never triggered
         */
        if (starting == end) {
            sb.append(starting);
        } else {
            sb.append(starting + "->" + end);
        }
        answer.add(sb.toString());


        //Convert list to array
        String[] answerArray = new String[answer.size()];
        int counter = 0;
        for (String s : answer) {
            answerArray[counter++] = s;
        }
        return answerArray;
    }


    /**
     * Given an array of integers, write a function that determines whether the array contains any duplicates.
     * Your function should return true if any element appears at least twice in the array, and it should return
     * false if every element is distinct.
     */
    boolean containsDuplicates(int[] a) {
        Set<Integer> aset = new HashSet<>();
        for (Integer x : a) {
            if (aset.contains(x)) return true;
            aset.add(x);
        }
        return false;
    }

    /**
     * You have two integer arrays, a and b, and an integer target value v. Determine whether there is a pair of
     * numbers, where one number is taken from a and the other from b, that can be added together to get a sum of v.
     * Return true if such a pair exists, otherwise return false.
     */
    boolean sumOfTwo(int[] a, int[] b, int v) {
        //O(a*b)
//        for(int i = 0; i<a.length; i++){
//            if(arrayContains(b, v-a[i])) return true;
//        }
//        return false;
        Set<Integer> set = new HashSet<>();
        for (Integer i : a) {
            set.add(v - i);
        }
        for (Integer i : b) {
            if (set.contains(i)) return true;
        }
        return false;
    }

    boolean arrayContains(int[] b, int x) {
        for (Integer i : b) if (x == i) return true;
        return false;
    }


    /**
     * You have an array of integers nums and an array queries, where queries[i] is a pair of indices (0-based).
     * Find the sum of the elements in nums from the indices at queries[i][0] to queries[i][1] (inclusive) for each
     * query, then add all of the sums for all the queries together. Return that number modulo 109 + 7.
     */
    int sumInRange(int[] nums, int[][] queries) {

//        int totalSum = 0;
//
//        //O(queries*nums)
//        for(int i = 0; i<queries.length; i++){
//            for(int x = queries[i][0]; x <= queries[i][1]; x++){
//                totalSum += nums[x];
//            }
//        }
//
//        if(totalSum < 0){
//            totalSum = totalSum + 1000000007;
//        }
//
//        return totalSum % 1000000007;


        Map<Integer, Integer> sumMap = new HashMap<>();

        int totalSum = 0;

        //Keep track of all the sums from the beginning to the current idx
        /*
        ex)
        0->0
        0->1
        0->2
        0->3
        0->4
        0->5
         */
        for (int i = 1; i < nums.length; i++) {
            totalSum += nums[i];
            sumMap.put(i, totalSum % 1000000007);

        }

        totalSum = 0;

        for (int i = 0; i < queries.length; i++) {
            //If it starts at 0 just add on the sum from 0->y as dictated by the map
            if (queries[i][0] == 0) totalSum = totalSum % 1000000007 + sumMap.get(queries[i][1]) % 1000000007;
                //if it starts at a non zero idx, add on the 0->y and subtract 0->x
            /*
            ex)
            3->5 = (0->5) - (0->2)
            (0->2) comes from 0->(3-1)
            If you have just sumMap.get(queries[i][0]), you get the following incorrect equation.
            3->5 != (0->5) - (0->3)
            The subtraction operation is inclusive
             */
            else
                totalSum = totalSum % 1000000007 + (sumMap.get(queries[i][1]) - sumMap.get(queries[i][0] - 1)) % 1000000007;
        }


        totalSum += 1000000007;

        return totalSum % 1000000007;

    }


    /**
     * Given an array of integers, find the maximum possible sum you can get from one of its contiguous subarrays.
     * The subarray from which this sum comes must contain at least 1 element.
     */
    int arrayMaxConsecutiveSum2(int[] inputArray) {

        /*

        O(n^2)

         */
//        int maxSum = 0;
//        HashMap<Integer, Integer> map = new HashMap<>();
//
//        for (int i = 0; i < inputArray.length; i++) {
//            maxSum += inputArray[i];
//            map.put(i, maxSum);
//        }
//
//        int tempSum = 0;
//        for (int i = 0; i < inputArray.length; i++) {
//            for (int j = i; j < inputArray.length; j++) {
//                if (i == 0) tempSum = map.get(j);
//                else tempSum = map.get(j) - map.get(i - 1);
//                maxSum = Math.max(maxSum, tempSum);
//            }
//        }
//
//        for (int i = 0; i < inputArray.length; i++) {
//            maxSum = Math.max(maxSum, inputArray[i]);
//        }
//
//        return maxSum;


        //Assign max to the first element as the default
        int max = inputArray[0];
        //Assign cur to the max as the beginning
        int cur = max;
        //Starting at 1 loop through the array and run the following operations
        for (int i = 1; i < inputArray.length; i++) {
            //Set the cur to either the current element or the addition of the element to the current max
            cur = Math.max(inputArray[i], inputArray[i] + cur);
            //Set the max to the max between the cur and the new max
            max = Math.max(cur, max);
        }
        return max;

    }


    /**
     * Given an integer, , traverse its digits (1,2,...,n) and determine how many digits evenly divide
     * (i.e.: count the number of times  divided by each digit i has a remainder of ). Print the number of evenly
     * divisible digits.
     * Note: Each digit is considered to be unique, so each occurrence of the same evenly divisible digit should be
     * counted (i.e.: for , the answer is ).
     */
    public static int findDigits(int n) {
        //Using O(n) space
//        char[] nCopy = String.valueOf(n).toCharArray();
//        int ans = 0;
//        for(int i = nCopy.length - 1; i>=0; i--){
//            int onesPlace = nCopy[i] - '0';
//            if(onesPlace == 0) continue;
//            if(n%onesPlace == 0) ans++;
//        }
//
//        return ans;
        int nCopy = n;
        int ans = 0;
        while (nCopy > 0) {
            if (nCopy % 10 == 0) {
            } else if (n % (nCopy % 10) == 0) ans++;
            nCopy /= 10;
        }
        return ans;
    }

    /**
     * Karl has an array of  integers defined as . In one operation, he can delete any element from the array.
     * Karl wants all the elements of the array to be equal to one another. To do this, he must delete zero or more
     * elements from the array. Find and print the minimum number of deletion operations Karl must perform so that all
     * the array's elements are equal.
     */
    static int equalizeArray(int[] arr) {
        //Get the most common element in the array
        //Return array.size - count of most common
        Map<Integer, Integer> arrCounter = new HashMap<>();
        for (Integer x : arr) {
            if (arrCounter.containsKey(x)) arrCounter.put(x, arrCounter.get(x) + 1);
            else arrCounter.put(x, 1);
        }

        int maxValue = 1;
        for (Integer x : arr) {
            if (arrCounter.get(x) > maxValue) maxValue = Math.max(maxValue, arrCounter.get(x));
        }

        return arr.length - maxValue;
    }

    /**
     * Given a string , find the number of "unordered anagrammatic pairs" of substrings. In other words,
     * find the number of unordered pairs of substrings of  that are anagrams of each other.
     * <p>
     * Two strings are anagrams of each other if the letters of one string can be rearranged to form the other string.
     * <p>
     * Input Format
     * First line contains , the number of testcases. Each testcase consists of string  in one line.
     */
    static int sherlockAndAnagrams(String s) {
        // Complete this function
//        Set<char[]> stringSet = new HashSet<>();
//        int count = 0;
//        for(int i = 0; i<=s.length(); i++){
//            for(int j=i; j<=s.length(); j++){
//                String substring = s.substring(i, j);
//                char[] subStringArray = substring.toCharArray();
//                Arrays.sort(subStringArray);
//                if(stringSet.contains(subStringArray)) count++;
//                stringSet.add(subStringArray);
//            }
//        }
        return -1;
    }

    /**
     * Given a list of numbers, can you find the median?
     */
    static int findMedian(int[] arr) {
        //Sort the array then find the middle
        Arrays.sort(arr);
        int middle = arr.length / 2;
        if (arr.length % 2 == 0) {
            return (arr[middle] + arr[middle - 1]) / 2;
        } else {
            return arr[middle];
        }
    }

    /**
     * Given 3 arrays (A, B, C) which are sorted in ascending order, we are required to merge them together in
     * ascending order and output the array D.
     */
    static int[] merge(int[] a, int[] b, int[] c) {
        int[] d = new int[a.length + b.length + c.length];

        int[] aAndb = new int[a.length + b.length];

        int iterA = 0, iterB = 0, iterC = 0, iterAAndB = 0, iterD = 0;

        while (iterA < a.length && iterB < b.length) {
            if (a[iterA] < b[iterB]) {
                aAndb[iterC++] = a[iterA++];
            } else {
                aAndb[iterC++] = b[iterB++];
            }
        }

        while (iterA < a.length) {
            aAndb[iterC++] = a[iterA++];
        }

        while (iterB < b.length) aAndb[iterC++] = b[iterB++];

        iterC = 0;

        while (iterC < c.length && iterAAndB < aAndb.length) {
            if (c[iterC] < aAndb[iterAAndB]) {
                d[iterD++] = c[iterC++];
            } else {
                d[iterD++] = aAndb[iterAAndB++];
            }
        }

        while (iterC < c.length) {
            d[iterD++] = c[iterC++];
        }

        while (iterAAndB < aAndb.length) d[iterD++] = aAndb[iterAAndB++];

        return d;
    }


    /**
     * Given two equally sized arrays (A, B) and N (size of both arrays).
     * A sum combination is made by adding one element from array A and another element of array B.
     * Display the maximum K valid sum combinations from all the possible sum combinations.
     */
    static void NMaxCombinations(int[] A, int[] B, int n, int k) {
        //Uses O(n) space
//        PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1)); //compare o2 to o1 because we want the larger on top
//        for(int i = 0; i<A.length; i++){
//            for(int j = 0; j<B.length; j++){
//                minHeap.add(A[i]+B[j]);
//            }
//        }
//        while(k > 0){
//            System.out.println(minHeap.poll());
//            k--;
//        }

        //sort the arrays
        Arrays.sort(A);
        Arrays.sort(B);
        //biggest elements at the end
        int iterA = A.length - 1, iterB = B.length - 1;
        System.out.println(A[iterA] + B[iterB]); //the biggest possible sum
        k--;
        while (k > 0) {
            //check which sum is greater and decrement the iterators appropriately
            if ((A[iterA - 1] + B[iterB]) > (A[iterA] + B[iterB - 1])) {
                System.out.println((A[iterA-- - 1] + B[iterB]));
            } else {
                System.out.println((A[iterA] + B[iterB-- - 1]));
            }
            k--;
        }

    }
}

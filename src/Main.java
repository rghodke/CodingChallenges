
/*
This class is the primary class compiled and executed when trying out different algorithms for the various problems.

 */

import java.util.*;

public class Main {

    public static void main(String[] args) {
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
        char[] sArray = s.toCharArray();
        int count = 0;
        //Get the different substring pairs
        for (int i = 1; i < sArray.length; i++) {
            for (int j = 0; j < sArray.length - i + 1; j++) {
                String s1 = s.substring(j, i + j);
                for (int k = j + 1; k < sArray.length - i + 1; k++) {
                    String s2 = s.substring(k, i + k);
                    //Check if they are anagrams
                    if (checkIfAnagram(s1, s2))
                        count++;
                }
            }
        }
        return count;
    }

    static boolean checkIfAnagram(String a, String b) {
        if (a.length() != b.length()) return false;
        char[] aArray = a.toCharArray();
        char[] bArray = b.toCharArray();
        Map<Character, Integer> aCount = new HashMap<>(), bCount = new HashMap<>();
        //Add letters and their counts
        for (int i = 0; i < aArray.length; i++) {
            if (aCount.containsKey(aArray[i])) aCount.put(aArray[i], aCount.get(aArray[i]) + 1);
            else {
                aCount.put(aArray[i], 1);
            }
        }
        for (int i = 0; i < bArray.length; i++) {
            if (bCount.containsKey(bArray[i])) bCount.put(bArray[i], bCount.get(bArray[i]) + 1);
            else {
                bCount.put(bArray[i], 1);
            }
        }
        //Check if same number
        for (int i = 0; i < bArray.length; i++) {
            char letter = bArray[i];
            int bCountOfLetter = bCount.get(letter);
            if (!aCount.containsKey(letter)) return false;
            int aCountOfLetter = aCount.get(letter);
            if (aCountOfLetter != bCountOfLetter) return false;
        }

        return true;
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

    /**
     * Given a string str of size n. The problem is to print all the palindromic permutations of str in
     * alphabetic order if possible else print “-1”.
     */
    public static int printAlphabeticPermutation(String str) {
        TreeSet<String> ansSet = new TreeSet<>();
        permute(str, 0, str.length() - 1, ansSet);
        if (ansSet.isEmpty()) return -1;
        return ansSet.size();
    }


    private static void permute(String str, int i, int j, TreeSet<String> set) {
        if (i == j) {
            if (checkIfPalindrome(str)) {
                set.add(str);
            }
        } else {
            for (int x = i; x <= j; x++) {
                str = swap(str, i, x);
                permute(str, i + 1, j, set);
                str = swap(str, i, x);
            }
        }
    }

    private static String swap(String str, int i, int j) {
        char[] strArray = str.toCharArray();
        char temp = strArray[i];
        strArray[i] = strArray[j];
        strArray[j] = temp;
        return String.valueOf(strArray);
    }

    private static boolean checkIfPalindrome(String str) {
        //01234 length/2 = 2.5 -> length/2 - 1
        //0123 //length/2 = 2 -> length/2 - 1
        char[] strArray = str.toCharArray();
        for (int i = 0; i < (strArray.length / 2); i++) {
            if (strArray[i] != strArray[strArray.length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Given an array of N distinct elements where elements are between 1 and N both inclusive,
     * check if it is stack-sortable or not. An array A[] is said to be stack sortable if it can be stored in
     * another array B[], using a temporary stack S.
     * <p>
     * The operations that are allowed on array are:
     * <p>
     * Remove the starting element of array A[] and push it into the stack.
     * Remove the top element of the stack S and append it to the end of array B.
     * If all the element of A[] can be moved to B[] by performing these operations such that array B is sorted in ascending order, then array A[] is stack sortable.
     */
    public static boolean isArrayStackSortable(int[] A) {
        //use a stack to sort A into B
        //check if the stack.peek is one greater than B, if so then pop
        //we can only push an element on the stack if the stack is empty or if the element we are going to push
        // is one greater than the peek on the stack
        return false;
    }


    /**
     * Given an array of N integers with duplicates allowed. All elements are ranked from 1 to N if they are distinct.
     * If there are say x repeated elements of a particular value then each element should be assigned a rank equal to
     * the arithmetic mean of x consecutive ranks.
     */
    public static double[] rankTheArray(int[] data) {
        double[] ans = new double[data.length];
        for (int x = 0; x < data.length; x++) {
            ans[x] = data[x];
        }
        Arrays.sort(data); //O(n log n)
        Map<Integer, List<Integer>> idxMap = new HashMap<>();
        for (int i = 0; i < data.length; i++) {
            List<Integer> idxList = new ArrayList<>();
            idxMap.putIfAbsent(data[i], idxList);
            idxList = idxMap.get(data[i]);
            idxList.add(i + 1);
            idxMap.put(data[i], idxList);
        }
        for (int i = 0; i < ans.length; i++) {
            ans[i] = averageOfList(idxMap.get((int) (ans[i])));
        }
        return ans;
    }

    private static double averageOfList(List<Integer> data) {
        double sum = 0;
        for (int x : data) {
            sum += x;
        }
        return sum / data.size();
    }

    /**
     * Given two positive integers n and k. The problem is to check whether the bit at position k from the
     * right in the binary representation of n is set (‘1’) or unset (‘0’).
     * Constraints: 1 <= k <= number of bits in the binary representation of n.
     */
    public static String setOrUnset(int n, int k) {
        /*
        String x = Integer.toBinaryString(n);
        int pos = x.length() - k;
        if(x.charAt(pos) == '1') return "set";
        else return "unset";
        */
        n = n >> (k - 1);
        if ((n & 1) == 1) return "set";
        else return "unset";
    }

    /**
     * Given n non-negative integers a_1, a_2, ..., a_n  where each represents a point at coordinate  (i, a_i) . ‘ n ‘
     * vertical lines are drawn such that the two endpoints of line i is at  (i, a_i)  and (i, 0).
     * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
     * <p>
     * The program should return an integer which corresponds to the maximum area of water that can be contained
     * ( maximum area instead of maximum volume sounds weird but this is 2D plane we are working with for simplicity ).
     */

    public static class ContainerObject {
        int length;
        int width;
        int area;

        ContainerObject(int length, int width) {
            this.length = length;
            this.width = width;
            this.area = this.length * this.width;
        }
    }

    public static int areaOfWater(int[] xCoor) {
        /*
        //Find the max distance multiplied with the max heights to create the biggest container
        List<ContainerObject> containerObjectList = new ArrayList<>();
        for(int i = 0; i<xCoor.length; i++){
            for(int j = i; j<xCoor.length; j++){
                int line1 = xCoor[i];
                int line2 = xCoor[j];
                //Use min and not max because the walls of the water are limited by the height of the smaller line
                containerObjectList.add(new ContainerObject(Math.min(line1, line2), j-i));
            }
        }

        Collections.sort(containerObjectList, new Comparator<ContainerObject>() {
            @Override
            public int compare(ContainerObject o1, ContainerObject o2) {
                return Integer.compare(o2.area, o1.area);
            }
        });

        return containerObjectList.get(0).area;
        */
        int left = 0;
        int right = xCoor.length - 1;
        int area = 0;
        while (left < right) {
            area = Math.max(area, Math.min(xCoor[left], xCoor[right]) * (right - left));
        }
        //We move the left ptr if the value at the left ptr is less than that of the right
        //To maximize the left value
        if (xCoor[left] < xCoor[right]) {
            left++;
        } else if (xCoor[right] <= xCoor[left]) { //if right value <= left value to maximize the right value
            right--;
        }
        return area;
    }

    /**
     * Given n boxes containing some chocolates arranged in a row. There are k number of students. The problem is
     * to distribute maximum number of chocolates equally among k students by selecting a consecutive sequence of
     * boxes from the given lot. Consider the boxes are arranged in a row with numbers from 1 to n from left to right.
     * We have to select a group of boxes which are in consecutive order that could provide maximum number of
     * chocolates equally to all the k students. An array arr[] is given representing the row arrangement of the
     * boxes and arr[i] represents number of chocolates in that box at position ‘i’.
     */
    public static int maxNumOfChocolates(int arr[], int n, int k) {
//        int sum = 0;
//
//        for(int i = 0; i<n; i++){
//            for(int j = 0; j < n; j++){
//                int tempSum = sum(arr, i, j);
//                if(tempSum % k == 0){
//                    sum = Math.max(sum, tempSum);
//                }
//            }
//        }

        Map<Integer, Integer> remainders = new HashMap<>();

        int[] sums = new int[n];
        sums[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sums[i] = sums[i - 1] + arr[i];
        }


        int maxNum = 0;
        for (int i = 0; i < sums.length; i++) {
            System.out.println(sums[i]);
            if (sums[i] % k == 0) {
                maxNum = Math.max(maxNum, sums[i]);
            } else if (!remainders.containsKey(sums[i] % k)) {
                remainders.put((sums[i] % k), i);
            } else {
                //sums[remainders.get[sums[i] % k ] will be at the ends of the subArray because the elements
                //are added in the above for loop
                if (maxNum < (sums[i] - sums[remainders.get(sums[i] % k)])) {
                    maxNum = sums[i] - sums[remainders.get(sums[i] % k)];
                }
            }

        }

        return maxNum / k;
    }

    public static int sum(int data[], int i, int j) {
        int sum = 0;
        while (i < j) {
            sum += data[i++];
        }
        return sum;
    }

    /**
     * Given an array of n positive elements, find the maximum AND value and the pair of elements generating
     * the maximum AND value from the array AND is bitwise & operator.
     */
    public static int maxANDValue(int[] data) {
        int max = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                max = Math.max(max, data[i] & data[j]);
            }
        }
        return max;
    }

    /**
     * You are given two string str1 and str2 of same length. In a single shift you can rotate one string (str2)
     * by 1 element such that its 1st element becomes the last and second one becomes the first like “abcd” will
     * change to “bcda” after one shift operation. You have to find the minimum shift operation required to get
     * common prefix of maximum length from str1 and str2.
     */
    public static String minimumShiftOperation(String s1, String s2) {
        /*
        O(n^2)
         */
//        int prefixLength = 0;
//        for(int i = 0; i<s2.length(); i++){
//            int rotatedPrefixLength = 0;
//            s2 = rotateString(s2);
//            for(int j = 0; j<s2.length(); j++){
//                if(s1.charAt(j) == s2.charAt(j)){
//                    rotatedPrefixLength++;
//                }
//            }
//            prefixLength = Math.max(rotatedPrefixLength, prefixLength);
//        }
//        return s1.substring(0, prefixLength);


        //Rather than rotate a string multiple times, you can append it to itself and look for the s1 pattern in s2
        // instead. Once we have the indexOf the prefix, we can calculate the shifts required

        //runKMPSearch(s1, s2+s2);
        //O(n)
        return KMPAlgo(s2 + s2, s1);
    }

    private static String rotateString(String s2) {
        StringBuilder sb = new StringBuilder();
        sb.append(s2.charAt(s2.length() - 1));
        sb.append(s2.substring(0, s2.length() - 1));
        return sb.toString();
    }

    /**
     * KMP Algorithm
     */
    public static String KMPAlgo(String s1, String s2) {

        int pos = 0, len = 0;

        //preprocessing
        int[] p = new int[s1.length() + 1];
        int k = 0;
        p[1] = 0;

        for (int i = 2; i <= s2.length(); i++) {
            while (k > 0 && s1.charAt(k) != s1.charAt(i - 1)) k = p[k];
            if (s1.charAt(k) == s1.charAt(i - 1)) k++;
            p[i] = k;
        }

        //find longest prefix
        for (int j = 0, i = 0; i < s1.length(); i++) {
            while (j > 0 && s1.charAt(j) != s2.charAt(i)) j = p[j];
            if (s1.charAt(j) == s2.charAt(i)) j++;

            //for new pos with longer prefix in s2
            if (j > len) {
                len = j;
                pos = i - j + 1;
            }
        }

        return s1.substring(0, len);
    }

    /**
     * Given a positive number N. We need to find number(s) such that sum of digits of those numbers to themselves
     * is equal to N. If no such number is possible print -1.
     */
    public static Set<Integer> findNumDigit(int n) {
        long valueOfLeft, valueOfEight;
        Set<Integer> ans = new HashSet<>();
        for (int i = 0; i <= 100; i++) {
            valueOfLeft = Math.abs(n - i) + sumOfDigits(Math.abs(n - i));
            valueOfEight = n + i + sumOfDigits(n + i);

            if (valueOfEight == n) {
                ans.add(n + i);
            }
            if (valueOfLeft == n) {
                ans.add(Math.abs(n - i));
            }
        }
        return ans;
    }

    private static int sumOfDigits(int k) {
        int sum = 0;
        while (k > 0) {
            sum += k % 10;
            k /= 10;
        }
        return sum;
    }

    /**
     * Implement an algo to check if string has all unique character
     */
    public static boolean allUnique(String str1) {

        Set<Character> strChar = new HashSet<>();
        for (int i = 0; i < str1.length(); i++) {
            if (strChar.contains(str1.charAt(i))) return false;
            else {
                strChar.add(str1.charAt(i));
            }
        }
        return true;
        //If no additional DS, you can sort or you can do two for loops
    }

    /**
     * Given two strings, write a method to decide if one is a permutation of the other.
     */
    public static boolean isPermutation(String s1, String s2) {
        char[] s1Array = s1.toCharArray();
        char[] s2Array = s2.toCharArray();
        Arrays.sort(s1Array);
        Arrays.sort(s2Array);
        return (Arrays.compare(s1Array, s2Array) == 0);
        //Sort the two strings, check if =
        //Count the # of chars and see if they are equal size and number of occurances
    }

    /**
     * Write a method to replace all spaces in a string with '%20'. You may assume that the string has sufficient
     * space at the end to hold the additional characters, and that you are given the "true" length of the string.
     * (Note: If implementing in Java, please use a character array so that you can perform this operation in place.)
     */
    public static String replaceSpaceWith20(char[] s1) {
        String string = new String(s1);
        string = string.replaceAll(" ", "%20");
        return string;

//        StringBuilder sb = new StringBuilder();
//        for(Character c : s1){
//            if(c == ' '){
//                sb.append("%20");
//            }else{
//                sb.append(c);
//            }
//        }
//        return sb.toString();
    }

    /**
     * Given a string, write a function to check if it is a permutation of a palindrome. A palindrome is a word or
     * phrase that is the same forwards and backwards. A permutation is a rearrangement of letters. The palindrome
     * does not need to be limited to just dictionary words.
     */
    public static boolean isPermutationOfPalindrome(String s1) {
        //Get count of all unique chars
        //If odd length, it should have only 1 odd count and the rest are even
        //If its even length, it should have only even counts
        s1 = s1.toLowerCase();
        Map<Character, Integer> charCount = new HashMap<>();
        int countWithoutSpace = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == ' ') continue;
            charCount.putIfAbsent(s1.charAt(i), 0);
            charCount.put(s1.charAt(i), charCount.get(s1.charAt(i)) + 1);
            countWithoutSpace++;
        }

        boolean oddLength = false;

        if (countWithoutSpace % 2 == 1) oddLength = true;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == ' ') continue;
            int count = charCount.get(s1.charAt(i));
            System.out.println("s1.charAt(i) " + s1.charAt(i) + "count " + count);
            if (count % 2 == 1) {
                if (oddLength) {
                    oddLength = false;
                } else if (!oddLength) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * There are three types of edits that can be performed on strings: insert a character, remove a character,
     * or replace a character. Given two strings, write a function to check if they are one edit (or zero edits) away.
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean isOneAway(String s1, String s2) {
        //Check if s1 and s2 have the same character except for one character

//        boolean isOneEditCheck = false;
//
//        String shorterString = s1.length() < s2.length() ? s1 : s2;
//        String longerString = s1.length() < s2.length() ? s2 : s1;
//
//        for(int i = 0; i<shorterString.length(); i++){
//            if(longerString.indexOf(shorterString.charAt(i)) == -1){
//                if(!isOneEditCheck) isOneEditCheck = true;
//                else if(isOneEditCheck) return false;
//            }else{
//                longerString = longerString.replace(""+shorterString.charAt(i), "");
//            }
//        }
//
//        if(isOneEditCheck && longerString.length() == 1) return true;
//        else if(isOneEditCheck && longerString.length() > 1){
//            return false;
//        }
//        return true;

        String shorterString = s1.length() < s2.length() ? s1 : s2;
        String longerString = s1.length() < s2.length() ? s2 : s1;

        boolean isOneAwayCheck = false;
        int s1Iterator = 0;
        int s2Iterator = 0;

        while (s1Iterator < shorterString.length() && s2Iterator < longerString.length()) {
            if (shorterString.charAt(s1Iterator) != longerString.charAt(s2Iterator)) {
                if (isOneAwayCheck) return false;
                if (!isOneAwayCheck) isOneAwayCheck = true;
                if (shorterString.length() == longerString.length()) s1Iterator++;
            } else {
                s1Iterator++;
            }
            s2Iterator++;
        }
        return true;
    }

    /**
     * String Compression: Implement a method to perform basic string compression using the counts of repeated
     * characters. For example, the string aabcccccaaa would become a2blc5a3. If the "compressed" string would not
     * become smaller than the original string, your method should return the original string. You can assume the
     * string has only uppercase and lowercase letters (a -z).
     */
    public static String compressString(String s1) {
        StringBuilder sb = new StringBuilder();
        char checkChar = s1.charAt(0);
        int occurance = 1;
        for (int i = 1; i < s1.length(); i++) {
            if (s1.charAt(i) == checkChar) {
                occurance++;
            } else {
                sb.append(checkChar);
                sb.append(occurance);
                checkChar = s1.charAt(i);
                occurance = 1;
            }
        }
        sb.append(checkChar);
        sb.append(occurance);
        if (sb.toString().length() >= s1.length()) {
            return s1;
        } else {
            return sb.toString();
        }
    }

    /**
     * Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes,
     * write a method to rotate the image by 90 degrees. Can you do this in place?
     */
    public static boolean rotateNxNMatrix(int[][] matrix) {
        if (matrix == null || matrix.length != matrix[0].length) return false;
        int n = matrix.length;
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;
            for (int j = first; j < last; j++) {
                int offset = j - first;
                int temp = matrix[first][j];
                matrix[first][j] = matrix[last - offset][first];
                matrix[last - offset][first] = matrix[last][last - offset];
                matrix[last][last - offset] = matrix[j][last];
                matrix[j][last] = temp;
            }
        }
        return true;
    }

    /**
     * Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are
     * set to 0.
     */
    public static boolean setRowColumnTo0(int[][] data) {
//        boolean[] row = new boolean[data.length];
//        boolean[] column = new boolean[data[0].length];
//
//        for(int i = 0; i<data.length; i++){
//            for(int j = 0; j<data.length; j++){
//                if(data[i][j] == 0){
//                    row[i] = true;
//                    column[j] = true;
//                }
//            }
//        }
//
//        for(int i = 0; i<row.length; i++){
//            if(row[i]) zeroRow(data, i);
//        }
//
//        for(int i = 0; i<column.length; i++){
//            if(column[i]) zeroColumn(data, i);
//        }


        boolean rowHasZero = false, colHasZero = false;

        for (int j = 0; j < data[0].length; j++) {
            if (data[0][j] == 0) {
                rowHasZero = true;
                break;
            }
        }

        for (int i = 0; i < data.length; i++) {
            if (data[i][0] == 0) {
                colHasZero = true;
                break;
            }
        }

        for (int i = 1; i < data.length; i++) {
            for (int j = 1; j < data[0].length; j++) {
                if (data[i][j] == 0) {
                    data[i][0] = 0;
                    data[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < data.length; i++) {
            if (data[i][0] == 0) zeroRow(data, i);
        }

        for (int j = 1; j < data[0].length; j++) {
            if (data[0][j] == 0) zeroColumn(data, j);
        }

        if (rowHasZero) zeroRow(data, 0);
        if (colHasZero) zeroColumn(data, 0);

        return true;
    }

    private static void zeroRow(int[][] data, int row) {
        for (int i = 0; i < data[row].length; i++) {
            data[row][i] = 0;
        }
    }

    private static void zeroColumn(int[][] data, int col) {
        for (int j = 0; j < data.length; j++) {
            data[j][col] = 0;
        }
    }

    /**
     * String Rotation: Assume you have a method isSubstring which checks if one word is a substring of another.
     * Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using only one call to isSubString
     * (e.g., "waterbottle" is a rotation of"erbottlewat").
     */
    public static boolean isRotation(String s1, String s2) {
        s2 = s2 + s2;
        return s2.contains(s1);
    }

    /**
     * Remove Dups: Write code to remove duplicates from an unsorted linked list
     */

    static class Node {
        Node next = null;
        int data;

        public Node(int data) {
            this.data = data;
        }

        void appendToTail(int data) {
            Node after = new Node(data);
            Node n = this;
            while (n.next != null) {
                n = n.next;
            }
            n.next = after;
        }
    }

    public Node removeDups(Node data) {
//        Set<Integer> dataSet = new HashSet<>();
//
//        Node headPtr = new Node(-1);
//        headPtr.next = data;
//
//        while(data.next != null){
//            if(dataSet.contains(data.data)){
//                data.data = data.next.data;
//                data.next = data.next.next;
//            }else{
//                dataSet.add(data.data);
//            }
//            data = data.next;
//        }
//
//        return headPtr.next;

        //No additional space allowed
        Node headPtr = new Node(-1);
        headPtr.next = data;

        Node current = data;

        while (current != null) {
            Node iter = current;
            while (iter.next != null) {
                if (iter.next.data == current.data) {
                    iter.next = iter.next.next;
                } else {
                    iter = iter.next;
                }
            }
            current = current.next;
        }

        return headPtr.next;

    }

    /**
     * Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.
     */
    public Node findKthToLast(Node head, int k) {
        Node iter1 = head;
        Node iter2 = head;

        //Move forward k spaces
        for (int i = 0; i < k; i++) {
            if (iter1 == null) return null;
            iter1 = iter1.next;
        }

        //Move forward the rest of the space leaving the remainder which is k away from the end
        while (iter1 != null) {
            iter1 = iter1.next;
            iter2 = iter2.next;
        }

        return iter2;
    }

    /**
     * Implement an algorithm to delete a node in the middle (i.e., any node but the first and last node, not
     * necessarily the exact middle) of a singly linked list, given only access to that node.
     */
    public void deleteNodeMid(Node data) {
        if (data == null || data.next == null) return;
        data.data = data.next.data;
        data.next = data.next.next;
    }


    /**
     * Partition: Write code to partition a linked list around a value x, such that all nodes less than x come before
     * all nodes greater than or equal to x. If xis contained within the list the values of x only need to be after the
     * elements less than x (see below). The partition element x can appear anywhere in the "right partition"; it does
     * not need to appear between the left and right partitions.
     */
    public Node partitionAroundX(Node head, int x) {
//        Node leftList = new Node(-1);
//        Node leftIter = leftList;
//        Node rightList = new Node(-1);
//        Node rightIter = rightList;
//        Node iter = head;
//        while(iter != null){
//            if(iter.data < x){
//                leftIter.next = new Node(iter.data);
//                leftIter = leftIter.next;
//            }
//            if(iter.data >= x){
//                rightIter.next = new Node(iter.data);
//                rightIter = rightIter.next;
//            }
//        }
//
//        rightIter = rightList.next;
//
//        leftIter.next = rightIter;
//
//        return leftList.next;

        Node left = head;
        Node right = head;

        while (head != null) {
            Node next = head.next;
            if (head.data < x) {
                head.next = left;
                left = head;
            } else {
                right.next = head;
                right = head;
            }
            head = next;
        }
        right.next = null;

        return left;
    }

    /**
     * Sum Lists: You have two numbers represented by a linked list, where each node contains a single digit. The digits
     * are stored in reverse order, such that the 1 's digit is at the head of the list. Write a function that adds the
     * two numbers and returns the sum as a linked list.
     */
    public Node addLists(Node num1, Node num2, int carry) {
        if (num1 == null && num2 == null && carry == 0) return null;
        Node result = new Node(-1);
        int value = carry;
        if (num1 != null) value += num1.data;
        if (num2 != null) value += num2.data;
        result.data = value % 10;
        if (num1 != null || num2 != null) {
            Node next = addLists(num1 == null ? null : num1.next, num2 == null ? null : num2.next, value / 10);
            result.next = next;
        }
        return result;
    }


    /**
     * Palindrome: Implement a function to check if a linked list is a palindrome.
     */
    public static boolean isListPalindrome(Node head) {
        //Reverse the list
        //Traverse it and check for equality
        Node reverse = reverseList(head);
        return ListsAreEqual(head, reverse);
    }

    private static Node reverseList(Node node) {
        Node head = null;
        while (node != null) {
            Node n = new Node(node.data);
            n.next = head;
            head = n;
            node = node.next;
        }
        return head;
    }

    private static boolean ListsAreEqual(Node one, Node two) {
        while (one != null && two != null) {
            if (one.data != two.data) return false;
            one = one.next;
            two = two.next;
        }
        return one == null && two == null;
    }

    /**
     * Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the
     * intersecting node. Note that the intersection is defined based on reference, not value. That is, if the kth
     * node of the first linked list is the exact same node (by reference) as the jth node of the second linked list,
     * then they are intersecting.
     */
    public static boolean twoListsOverlap(Node n1, Node n2) {
        //find lengths
        int lengthN1 = findLength(n1);
        int lengthN2 = findLength(n2);

        //specify shorter/longer
        Node shorter = lengthN1 < lengthN2 ? n1 : n2;
        Node longer = lengthN1 < lengthN2 ? n2 : n1;

        //Traverse longer list to be the same size as shorter
        int k = Math.abs(lengthN1 - lengthN2);
        while (k > 0) {
            longer = longer.next;
        }

        //iterate through
        while (shorter != null && longer != null) {
            if (shorter == longer) return true;
            shorter = shorter.next;
            longer = longer.next;
        }

        return false;
    }

    private static int findLength(Node n1) {
        int length = 0;
        while (n1 != null) {
            length++;
            n1 = n1.next;
        }
        return length;
    }

    /**
     * Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the beginning
     * of the loop. DEFINITION Circular linked list: A (corrupt) linked list in which a node's next pointer points
     * to an earlier node, so as to make a loop in the linked list.
     */
    private static Node circularListStart(Node n1) {
        Node slow = n1;
        Node fast = n1;
        while (fast.next != null && fast != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }

        if (fast == null || fast.next == null) return null; //no loop

        slow = n1;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    /**
     * Given a sorted (increasing order) array with unique integer elements, write an algorithm to create a binary
     * search tree with minimal height.
     */
    static class TreeNode {

        int data;
        TreeNode left, right;

        public TreeNode(int data) {
            this.data = data;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }

    /**
     * Given a sorted (increasing order) array with unique integer elements, write an algorithm to create a
     * binary search tree with minimal height.
     */
    public TreeNode createMinBST(int[] data, int start, int end) {
        if (data.length == 0) return null;
        if (data.length == 1) return new TreeNode(data[0]);
        if (end < start) return null;
        int mid = (end - start) / 2;
        TreeNode root = new TreeNode(data[mid]);
        root.setLeft(createMinBST(data, start, mid - 1));
        root.setRight(createMinBST(data, mid + 1, end));
        return root;
    }

    /**
     * Route Between Nodes: Given a directed graph, design an algorithm to find out whether there is a route between
     * two nodes.
     */
    public enum State {
        Unvisited, Visiting, Visited
    }

    class GraphNode {
        int data;
        List<GraphNode> children;
        public State state;

        public GraphNode(int data) {
            this.data = data;
            children = new ArrayList<>();
            state = State.Unvisited;
        }

        public void addChild(GraphNode n) {
            children.add(n);
        }

        public List<GraphNode> getChildren() {
            return children;
        }
    }

    private static boolean pathExists(GraphNode start, GraphNode end) {
        Queue<GraphNode> nodesToBeVisited = new LinkedList<>();
        start.state = State.Visiting;
        nodesToBeVisited.add(start);
        while (!nodesToBeVisited.isEmpty()) {
            GraphNode node = nodesToBeVisited.poll();
            if (node == end) return true;
            for (GraphNode child : node.children) {
                if (child.state == State.Unvisited) {
                    child.state = State.Visiting;
                    nodesToBeVisited.add(child);
                }
            }
            node.state = State.Visited;
        }
        return false;
    }

    /**
     * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
     * <p>
     * For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6
     */
    public int largestProductSubarray(int[] data) {
        int max[] = new int[data.length];
        int min[] = new int[data.length];
        max[0] = data[0];
        min[0] = data[0];
        int result = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] > 0) {
                max[i] = Math.max(max[i - 1] * data[i], data[i]);
                min[i] = Math.min(max[i - 1] * data[i], data[i]);
            } else {
                max[i] = Math.max(min[i - 1] * data[i], data[i]); //Get the biggest negative * current negative element
                min[i] = Math.min(max[i - 1] * data[i], data[i]); //The biggest positive * current negative element
            }

            result = Math.max(result, max[i]);
        }
        return result;
    }

    /**
     * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of
     * which the sum ≥ s. If there isn't one, return 0 instead.
     * <p>
     * For example, given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal length of 2 under the problem constraint.
     */
    public static int lengthOfSmallestSubArrayThatAddsUpToK(int[] data, int k) {
        int leftIter = 0, rightIter = 0, sum = 0;
        int minLength = Integer.MAX_VALUE;
        while (leftIter < data.length) {
            if (sum < k) {
                sum += data[rightIter++];
            } else {
                minLength = Math.min(minLength, rightIter - leftIter);
                sum -= data[leftIter++];
            }
        }
        while (sum >= k) {
            minLength = Math.min(minLength, rightIter - leftIter);
            sum -= data[leftIter++];
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }


    /**
     * List of Depths: Given a binary tree, design an algorithm which creates a linked list of all the nodes at each
     * depth (e.g., if you have a tree with depth D, you'll have D linked lists).
     */

    public static List<LinkedList<TreeNode>> generateListAtDepth(TreeNode node) {
        List<LinkedList<TreeNode>> nodeList = new ArrayList<>();
        generateLinkedListAtDepth(node, nodeList, 0);
        return nodeList;
    }

    public static void generateLinkedListAtDepth(TreeNode node, List<LinkedList<TreeNode>> depthList, int depth) {
        if (node == null) return;
        LinkedList<TreeNode> nodeLinkedList;
        if (depth == depthList.size()) {
            nodeLinkedList = new LinkedList<>();
            depthList.add(nodeLinkedList);
        } else {
            nodeLinkedList = depthList.get(depth);
        }
        nodeLinkedList.add(node);
        generateLinkedListAtDepth(node.left, depthList, depth + 1);
        generateLinkedListAtDepth(node.right, depthList, depth + 1);
    }

    /**
     * Check Balanced: Implement a function to check if a binary tree is balanced. For the purposes of this question,
     * a balanced tree is defined to be a tree such that the heights of the two subtrees of any node never differ
     * by more than one.
     */

    public static boolean isBalanced(TreeNode node) {
        return checkHeight(node) != Integer.MIN_VALUE;
    }

    public static int checkHeight(TreeNode node) {
        boolean result = true;
        if (node == null) return -1;
        int leftHeight = checkHeight(node.left);
        if (leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        int rightHeight = checkHeight(node.right);
        if (rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        int heightDiff = Math.abs(leftHeight - rightHeight);
        if (heightDiff > 1) {
            return Integer.MIN_VALUE;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    /**
     * Validate BST: Implement a function to check if a binary tree is a binary search tree.
     */
    public static boolean isBST(TreeNode node) {
        boolean ans = true;
        if (node == null) return true;
        TreeNode left = node.left, right = node.right;
        if (left != null) {
            ans &= left.data <= node.data;
        }
        if (right != null) {
            ans &= right.data > node.data;
        }
        return ans && isBST(node.left) && isBST(node.right);
    }

    /**
     * Insertion: You are given two 32-bit numbers, N and M , and two bit positions, i and j. Write a method to insert
     * Minto N such that M starts at bit j and ends at bit i. You can assume that the bits j through i have enough space
     * to fit all of M. That is, if M = 10011, you can assume that there are at least 5 bits between j and i. You would
     * not, for example, have j = 3 and i = 2, because M could not fully fit between bit 3 and bit 2.
     */
    public static int updateNums(int a, int b, int i, int j) {
        int allOnes = ~0;

        int left = allOnes << j + 1;

        int right = (1 << i) - 1;

        int mask = left | right;

        a = a & mask;

        b = b << i;

        return a | b;
    }

    /**
     * Binary to String: Given a real number between 0 and 1 (e.g., 0.72) that is passed in as a double, print the
     * binary representation. If the number cannot be represented accurately in binary with at most 32
     * characters, print"ERROR:'
     */
    public static String printDecimalBinaryRepresentation(double dec) {
        double x = .5;
        StringBuilder sb = new StringBuilder();
        sb.append('.');
        while (dec > 0) {
            if (sb.length() > 32) {
                return "ERROR";
            }
            if (dec >= x) {
                sb.append(1);
                dec = dec - x;
            } else {
                sb.append(0);
            }
            x /= 2;

        }
        return sb.toString();
    }

    /**
     * Flip Bit to Win: You have an integer and you can flip exactly one bit from a Oto a 1. Write code to find
     * the length of the longest sequence of 1 s you could create.
     */
    public int lengthOfLongest1WithFlip(int x) {
//        String binaryStr = Integer.toBinaryString(x);
//        int startOfLongestOne = binaryStr.indexOf('1');
//        if (startOfLongestOne == -1){
//            return 1;
//        }
//        int endOfLongestOne = startOfLongestOne;
//        int curLength = 0;
//        int maxLength = 1;
//        for( int i = startOfLongestOne+1; i < binaryStr.length(); i++){
//            if (binaryStr.charAt(i) == '1'){
//                endOfLongestOne = i;
//                curLength++;
//            }else{
//                startOfLongestOne = i;
//                curLength = 0;
//            }
//            maxLength = Math.max(maxLength, endOfLongestOne - startOfLongestOne);
//        }
//
//
//        if (maxLength == 32){
//            return maxLength; //if it can't be flipped, all 1's
//        }
//        return maxLength + 1; //if it can be flipped
        if (x == ~0) return Integer.BYTES * 8;
        int curLength = 0;
        int maxLength = 0;
        int prevLength = 0;
        while (x > 0) {
            if ((x & 1) == 1) curLength++;
            if ((x & 1) == 0) {
                //keep track of scenarios like 11101110(we can swap the middle 0 to one)
                prevLength = ((x & 2) == 0) ? 0 : curLength;
                curLength = 0;
            }
            x = x >>> 1;
            maxLength = Math.max(maxLength, prevLength + curLength);
        }
        return maxLength + 1;
    }

    /**
     * Triple Step: A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3 steps at a
     * time. Implement a method to count how many possible ways the child can run up the stairs
     */
    public static int printLargerAndSmaller(int n) {
        int[] cache = new int[n + 1];
        cache[0] = 0;
        cache[1] = 1; //1 step
        cache[2] = 2; //1+1 and 2
        cache[3] = 4; //111,21,12,3
        for (int i = 4; i <= n; i++) {
            cache[i] = cache[i - 1] + cache[i - 2] + cache[i - 3];
        }
        return cache[n];
    }

    /**
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     * <p>
     * The robot can only move either down or right at any point in time. The robot is trying to reach the
     * bottom-right corner of the grid (marked 'Finish' in the diagram below).
     * <p>
     * How many possible unique paths are there?
     */
    public static int findUniquePaths(int m, int n) {
        int[][] paths = new int[m][n];
        for (int i = 0; i < m; i++) {
            paths[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            paths[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
            }
        }

        return paths[m - 1][n - 1];

    }


    /**
     * Robot in a Grid: Imagine a robot sitting on the upper left corner of grid with r rows and c columns. The robot
     * can only move in two directions, right and down, but certain cells are "off limits" such that the robot cannot
     * step on them. Design an algorithm to find a path for the robot from the top left to the bottom right.
     */

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static List<Point> robotTraversal(boolean[][] matrix) {
        List<List<Point>> answerList = new ArrayList<>();
        robotTraversalHelper(matrix, 0, 0, answerList, new ArrayList<Point>());
        return answerList.get(0);
    }

    private static void robotTraversalHelper(boolean[][] matrix, int i, int j, List<List<Point>> answerList, ArrayList<Point> points) {
        if (i == matrix.length - 1 && j == matrix[matrix.length - 1].length - 1) {
            Point p = new Point(i, j);
            points.add(p);
            answerList.add(new ArrayList<>(points));
            return;
        } else if (i < matrix.length && j < matrix[i].length) {
            if (!matrix[i][j]) {
                return;
            } else {
                Point p = new Point(i, j);
                points.add(p);
                robotTraversalHelper(matrix, i + 1, j, answerList, points);
                robotTraversalHelper(matrix, i, j + 1, answerList, points);
                points.remove(p);
            }
        }
    }

    /**
     * Magic Index: A magic index in an array A[ 1 .•. n-1] is defined to be an index such that A[ i] i. Given a
     * sorted array of distinct integers, write a method to find a magic index, if one exists, in array A.
     */
    public static int findMagicIdx(int[] data) {
//        for (int i = 0; i<data.length; i++){
//            if (data[i] == i) return i;
//        }
//        int left = 0;
//        int right = data.length - 1;
//
//        int ans = -1;
//
//        while (left<=right){
//            int m = right - (right-left)/2;
//            if (data[m] == m){
//              ans = m;
//              return ans;
//            }
//            else if (data[m] > m){
//                right = m;
//            }
//            else if (data[m]<m){
//                left = m;
//            }
//        }
//
//        return ans;
        return findMagicHelper(data, 0, data.length - 1);
    }

    public static int findMagicHelper(int data[], int left, int right) {
        if (right < left) return -1;

        int mid = right - (right - left) / 2;
        int midVal = data[mid];
        if (midVal == mid) return mid;

        int leftIdx = Math.min(mid - 1, midVal);
        int start = findMagicHelper(data, left, leftIdx);
        if (start >= 0) return start;

        int rightIdx = Math.max(mid + 1, midVal);
        int end = findMagicHelper(data, rightIdx, right);
        return end;
    }

    /**
     * Power Set: Write a method to return all subsets of a set.
     */
    public List<List<Integer>> powerSet(List<Integer> data) {
        return null;
    }

    /**
     * Sorted Merge: You are given two sorted arrays, A and B, where A has a large enough buffer at the end to hold B.
     * Write a method to merge B into A in sorted order.
     */
    public static int[] mergeArrays(int[] a, int[] b, int lastA, int lastB) {
        int idxA = lastA - 1;
        int idxB = lastB - 1;
        int idxAB = lastA + lastB - 1;

        //Rather than checking and assigning from the front and having to shift, assign from end
        while (idxB >= 0) {
            if (idxA >= 0 && a[idxA] > b[idxB]) {
                a[idxAB--] = a[idxA--];
            } else {
                b[idxAB--] = b[idxB--];
            }
        }
        //This isn't needed because we are using the a array thus they should already be in place
        while (idxA >= 0) {
            a[idxAB--] = a[idxA];
        }
        return a;
    }

    /**
     * Group Anagrams: Write a method to sort an array so that all tne anagrams are next to each other
     */
    public static String[] sortAnagramString(String[] anagramArray) {
        for (int i = 0; i < anagramArray.length; i++) {
            char[] chars = anagramArray[i].toCharArray();
            Arrays.sort(chars);
            anagramArray[i] = String.valueOf(chars);
        }

        Comparator<String> comparator = Comparator.naturalOrder();

        Arrays.sort(anagramArray, comparator);

        return anagramArray;
    }

    /**
     * Search in Rotated Array: Given a sorted array of n integers that has been rotated an unknown number of times,
     * write code to find an element in the array. You may assume that the array was originally sorted in increasing order.
     */
    public static int searchSortedArray(int[] data, int x) {
        int left = 0;
        int right = data.length - 1;
        while (left <= right) {
            int mid = right - (right - left) / 2;
            if (data[mid] == x) return mid;
            //left half is ordered
            if (data[left] < data[mid]) {
                if (x >= data[left] && x < data[mid]) { //if x exists in the ordered array
                    right = mid - 1;
                } else { //if x is not in the sorted array, its in the other half
                    left = mid + 1;
                }
            } else if (data[mid] < data[left]) { //right half is ordered
                if (x > data[mid] && x <= data[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else if (data[mid] == data[left]) { //all repeats on left
                if (data[mid] != data[right]) {
                    left = mid + 1;
                } else { //if both sides show up as all repeats, we have to do a standard O(n) search
                    for (int i = 0; i < data.length; i++) {
                        if (data[i] == x) return i;
                    }
                }
            }
        }

        return -1;
    }


    /**
     * Sorted Search, No Size: You are given an array-like data structure Listy which lacks a size method. It does,
     * however, have an elementAt ( i) method that returns the element at index i in 0( 1) time. If i is beyond the
     * bounds of the data structure, it returns -1. (For this reason, the data structure only supports positive
     * integers.) Given a Listy which contains sorted, positive integers, find the index at which an element x occurs.
     * If x occurs multiple times, you may return any index.
     */
    public static int searchListy(List<Integer> listy, int x) {
        int size = 1;

        while (listy.get(size) != null && listy.get(size) < x) {
            size = size * 2;
        }

        int left = 0, right = size;

        while (left <= right) {
            int m = right - (right - left) / 2;
            if (listy.get(m) == x) return m;
            else if (listy.get(m) > x) right = m - 1;
            else if (listy.get(m) < x) left = m - 1;
        }

        return -1;
    }

    /**
     * Given an array arr[] of size n containing integers. The problem is to find the length of the longest
     * sub-array having sum equal to the given value k.
     */
    public static int[] subArrayAddingUpToK(int[] data, int k) {
//        int rightIter = 0;
//        int leftIter = 0;
//        int maxLen = Integer.MIN_VALUE;
//        int start = 0, end = 0;
//        int sum = 0;
//        while (rightIter < data.length){
//            sum += data[rightIter++];
//            if (sum == k){
//                start = leftIter;
//                end = rightIter;
//                maxLen = Math.max(maxLen, rightIter-leftIter);
//            }
//        }
//
//        while (leftIter < data.length){
//            sum -= data[leftIter++];
//            if (sum == k){
//                maxLen = Math.max(maxLen, rightIter - leftIter);
//            }
//        }
//
//        return maxLen == Integer.MIN_VALUE ? null : Arrays.copyOfRange(data, start, end);

        Map<Integer, Integer> sumToIdxMap = new HashMap<>();

        int sum = 0;
        int maxLength = -1;
        int start = 0, end = 0;

        for (int i = 0; i < data.length; i++) {
            sum += data[i];
            if (sum == k) {
                maxLength = Math.max(maxLength, i + 1);
                end = i;
            }
            sumToIdxMap.putIfAbsent(sum, i);

            //if the map has what you are missing
            if (sumToIdxMap.containsKey(sum - k)) { //what you need to get to k if your sum is over
                maxLength = Math.max(maxLength, i - sumToIdxMap.get(sum - k)); //curidx - idx of subarray to get rid of whats over k
                start = sumToIdxMap.get(sum - k);
                end = i;
            }
        }

        return maxLength == -1 ? null : Arrays.copyOfRange(data, start, end);
    }

    /**
     * Give a positive integer n, find modular multiplicative inverse of all integer from 1 to n with
     * respect to a big prime number, say, ‘prime’.
     * <p>
     * The modular multiplicative inverse of a is an integer ‘x’ such that.
     * <p>
     * a x ≡ 1 (mod prime)
     */
    public static int[] modInverse(int a, int prime) {
        int dp[] = new int[a + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= a; i++) {
            dp[i] = dp[prime % i] * (prime - prime / i) % prime;
        }
        return dp;
    }

    /**
     * Given two numbers, the task is to use alternative bits within two numbers to create result. We take first bits
     * of second number, then second bit of the first number, third bit of second number and take the fourth bit of a
     * first number and so on and generate a number with it.
     */
    public static String constructNumber(int a, int b) {
//        StringBuilder sb = new StringBuilder();
//
//        String aStr = Integer.toBinaryString(a);
//        String bStr = Integer.toBinaryString(b);
//
//        boolean isB = true;
//        int aIter = aStr.length()-1, bIter = bStr.length() - 1;
//        while(aIter >= 0 && bIter >= 0){
//            if(isB){
//                sb.append(bStr.charAt(bIter));
//            }
//            else{
//                sb.append(aStr.charAt(aIter));
//            }
//            aIter--;
//            bIter--;
//            isB = !isB;
//        }
//        return sb.reverse().toString();

        int oddDigitsOfB = 0;
        boolean isOddDigit = true;
        for (int iter = b; iter > 0; iter >>= 1) {
            if (isOddDigit) {
                oddDigitsOfB |= b & 1;
            }
            oddDigitsOfB = oddDigitsOfB << 1;
            isOddDigit = !isOddDigit;
        }

        oddDigitsOfB >>>= 1;

        int evenDigitsOfA = 0;
        boolean isEvenDigit = false;
        for (int iter = a; iter > 0; iter >>= 1) {
            if (isEvenDigit) {
                evenDigitsOfA |= iter & 1;
            }
            evenDigitsOfA = evenDigitsOfA << 1;
            isEvenDigit = !isEvenDigit;
        }

        int ans = (oddDigitsOfB | evenDigitsOfA);
        return Integer.toBinaryString(ans);

    }

    /**
     * Given an Array of Integers and an Integer value k, find out k sub-arrays(may be overlapping) which
     * have k maximum sums.
     */
    public static void findMaxKSums(int[] data, int k) {
        PriorityQueue<Integer> maxSum = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));

        for (int i = 0; i < data.length; i++) {
            for (int j = i; j < data.length; j++) {
                maxSum.add(sumArray(data, i, j));
            }
        }

//        for (int x : data) maxSum.add(x);
//        addMaxSumHelper(maxSum, data, 0, data.length-1);
////        while (!maxSum.isEmpty()){
//            System.out.println(maxSum.poll());
//        }
        while (k-- > 0) {
            System.out.println(maxSum.poll());
        }
    }

    private static Integer sumArray(int[] data, int i, int j) {
        int sum = 0;
        for (; i <= j; i++) {
            sum += data[i];
        }
        return sum;
    }

    private static void addMaxSumHelper(PriorityQueue<Integer> maxSum, int[] data, int l, int h) {
        if (l == h) {
            maxSum.add(data[l]);
            return;
        }
        int m = (l + h) / 2;
        addMaxSumHelper(maxSum, data, l, m);
        addMaxSumHelper(maxSum, data, m + 1, h);
        System.out.println("l " + l + " h " + h + " maxSum " + findSumOfSubArray(data, l, h));
        maxSum.add(findSumOfSubArray(data, l, h));
        return;
    }

    private static int findSumOfSubArray(int[] data, int l, int h) {
        int sum = data[l];
        int curMax = data[l];
        for (int i = l + 1; i <= h; i++) {
            sum = Math.max(data[i], sum + data[i]);
            curMax = Math.max(sum, curMax);
        }
        return curMax;
    }

    /**
     * Given a string s that may have duplicate characters. Find out the lexicographic rank of s. s may consist
     * lower as well as upper case letters. We consider the lexicographic order of characters as their order of ACCII
     * value. Hence the lexicographical order of characters will be ‘A’, ‘B’, ‘C’, …, ‘Y’, ‘Z’, ‘a’, ‘b’, ‘c’, …, ‘y’, ‘z’.
     */
    public static int findLexographicalOrder(String s) {
        Set<String> allPermuations = new HashSet<>();
        char[] sortedSArr = s.toCharArray();
        Arrays.sort(sortedSArr);
        String sortedS = new String(sortedSArr);
        allPermuations = findAllPermutations(sortedS);
        List<String> allPermutationsList = new ArrayList<>(allPermuations);
        Collections.sort(allPermutationsList, Comparator.naturalOrder());
        for (int i = 0; i < allPermutationsList.size(); i++) {
            if (allPermutationsList.get(i).equals(s)) return i + 1;
        }
        return -1;
    }

    private static Set<String> findAllPermutations(String s) {
        Set<String> permList = new HashSet<>();
        findAllPermutationsHelper(s, 0, 1, permList);
        return permList;
    }

    private static void findAllPermutationsHelper(String s, int i, int j, Set<String> permList) {
        if (j >= s.length() || i >= s.length()) return;
        if (j < i) return;
        permList.add(s);
        findAllPermutationsHelper(s, i, j + 1, permList);
        findAllPermutationsHelper(s, i + 1, j, permList);
        String sSwap = swap(s, i, j);
        findAllPermutationsHelper(sSwap, i, j + 1, permList);
        findAllPermutationsHelper(sSwap, i + 1, j, permList);
    }

    /**
     * XNOR gives the reverse of XOR if binary bit.
     */
    public static int findXNOR(int a, int b) {

        // if num2 is greater then
        // we swap this number in num1
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }

        a = togglebit(a);

        return a ^ b;

    }

    private static int togglebit(int n) {
        if (n == 0)
            return 1;

        int i = n;

        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;

        return i ^ n;
    }


    /**
     * This problem is to rotate a given array to the right by n steps.
     * <p>
     * For example:
     * <p>
     * Given [1, 2, 3] and n = 1, you should return [3, 1, 2]
     * <p>
     * Each step, the last element in the array is moved to the front of the array, and the rest are shifted right.
     * <p>
     * Another example:
     * <p>
     * Given [1, 2, 3, 4, 5] and n = 3, you should return [3, 4, 5, 1, 2]
     */
    public static int[] rotateArrayRight(int[] data, int r) {
//        while (k-- > 0){
//            data = rotateArrayRightOnce(data);
//        }
//        return data;
        int n = data.length;
        int i, j, k, temp;

        for (i = 0; i < gcd(r, n); i++) {
            temp = data[i];
            j = i;
            while (true) {
                k = j + r;
                if (k >= n) {
                    k -= n;
                }
                if (k == i) {
                    break;
                }
                data[j] = data[k];
                j = k;
            }
            data[j] = temp;
        }
        return data;
    }

    private static int[] rotateArrayRightOnce(int[] data) {
        /*
        int[] rotated = new int[data.length];
        for(int i = data.length - 1; i > 0; i--){
            rotated[i] = data[i-1];
        }
        rotated[0] = data[data.length - 1];
        return rotated;
        */
        //Instead of making new array, use the original array and have a temp variable for thr only element you need;
        int temp = data[data.length - 1];
        //Populate in reverse
        for (int i = data.length - 1; i > 0; i--) {
            data[i] = data[i - 1];
        }
        data[0] = temp;
        return data;
    }

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    /**
     * LCS Problem Statement: Given two sequences, find the length of longest subsequence present in both of them.
     * A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous. For
     * example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”. So a string of length
     * n has 2^n different possible subsequences.
     */
    public static String findLongestSubseq(String s1, String s2) {
        Map<Character, Integer> charCount = new HashMap<>();
        for (Character c : s1.toCharArray()) {
            charCount.putIfAbsent(c, 0);
            charCount.put(c, charCount.get(c) + 1);
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : s2.toCharArray()) {
            if (charCount.containsKey(c)) {
                if (charCount.get(c) > 0)
                    sb.append(c);
                charCount.put(c, charCount.get(c) - 1);
            }
        }
        return sb.toString();
    }

    /**
     * Remove duplicates from a sorted array
     */
    public static int[] removeDupsSortedArr(int[] data) {
        int writeIdx = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[writeIdx] != data[i]) {
                data[++writeIdx] = data[i];
            }
        }
        return Arrays.copyOfRange(data, 0, writeIdx + 1);
    }

    /**
     * Buy and Sell a Stock Once
     */
    public double buySellOnce(double[] data) {
        //If you have the condition that you can only operate with prev elements, assign as you traverse the array
        double curMax = 0;
        double curMin = Double.MAX_VALUE;
        double maxProfit = 0;
        for (double price : data) {
            maxProfit = Math.max(maxProfit, price - curMin);
            curMin = Math.min(price, curMax);
        }
        return maxProfit;
    }

    /**
     * Write a program that takes an array of integers and finds the length of longest subarray whose entries are equal
     */
    public static int lengthOfLongestEqualSubArray(int[] data) {
//        int length = 1;
//        for (int i = 0; i < data.length; i++){
//            int curLength = 1;
//            for (int j = i + 1; j < data.length; j++){
//                if(data[i] == data[j]){
//                    curLength++;
//                    length = Math.max(length, curLength);
//                }
//            }
//        }
//        return length;
        int curMaxLength = 1;
        int length = 1;
        int elem = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] == elem) {
                length++;
                curMaxLength = Math.max(curMaxLength, length);
            } else {
                elem = data[i];
                length = 1;
            }
        }
        return curMaxLength;
    }

    /**
     * Buy and sell twice
     */
    public static double buyAndSellStockTwice(double[] prices) {
        //O(n) time and space
        double[] profit = new double[prices.length];
        Arrays.fill(profit, 0);
        double max_price = prices[prices.length - 1];
        for (int i = prices.length - 2; i < prices.length; i++) {
            //We can only subtract a bigger idx from a smaller idx so we get the max price while iterating and
            //subtract appropriately
            max_price = Math.max(max_price, prices[i]);
            profit[i] = Math.max(profit[i + 1], profit[i] + max_price - prices[i]);
        }

        double min_price = prices[0];
        for (int i = 1; i < prices.length; i++) {
            //We can only subtract backwards so assign min_price as we iterate and then operate
            min_price = Math.min(min_price, prices[i]);
            profit[i] = Math.max(profit[i - 1], profit[i] + prices[i] - min_price);
        }

        return profit[prices.length - 1];
    }


    /**
     * Given an integer n, find the closest integer (not including itself), which is a palindrome.
     * <p>
     * The 'closest' is defined as absolute difference minimized between two integers.
     */
    public static int findClosetPalindrome(int n) {

        String nStr = Integer.toString(n);
        int idx = 0;
        boolean isOdd = nStr.length() % 2 != 0;
        StringBuilder sb = new StringBuilder();
        while (idx < nStr.length() / 2) {
            sb.append(nStr.charAt(idx++));
        }
        String prefixStr = sb.reverse().toString();
        sb.reverse();
        if (isOdd) {
            sb.append(nStr.charAt(idx));
        }
        sb.append(prefixStr);
        return Integer.parseInt(sb.toString());


//        String nStr = Integer.toString(n);
//        //O(n)
//        List<Character> closetPalindromeList = new ArrayList<>(nStr.length());
//        boolean isOdd = nStr.length() % 2 != 0;
//        for(int i = 0; i<nStr.length(); i++) closetPalindromeList.add('~');
//        int idx = 0;
//        //O(n)
//        while(idx != nStr.length()/2){
//            closetPalindromeList.add(idx, nStr.charAt(idx));
//            closetPalindromeList.add(nStr.length() - 1 - idx, nStr.charAt(idx));
//            idx++;
//        }
//
//        if (isOdd){
//            closetPalindromeList.add(idx, nStr.charAt((nStr.length()/2)));
//        }
//
//        List<Character> finalArray = new ArrayList<>();
//        for (Character c : closetPalindromeList){
//            if (c != '~') finalArray.add(c);
//        }
//        char[] digitsArray = new char[finalArray.size()];
//        for (int i = 0; i<finalArray.size(); i++){
//            digitsArray[i] = finalArray.get(i);
//        }
//        String s = new String(digitsArray);
//        return Integer.parseInt(s);
//

//        int closestPalindrome = n;
//
//        for(int i = n+1; i<Integer.MAX_VALUE; i++){
//            if(isPalindrome(i)) {
//                closestPalindrome = i;
//                break;
//            }
//        }
//
//        int difference = Math.abs(closestPalindrome - n);
//
//        for (int i = n+1; i>Integer.MIN_VALUE; i--){
//            if (isPalindrome(i)){
//                int differenceLower = Math.abs(i - n);
//                if (differenceLower < difference){
//                    closestPalindrome = i;
//                }
//                break;
//            }
//        }
//
//        return closestPalindrome;
    }

    private static boolean isPalindrome(int num) {
        String numStr = String.valueOf(num);
        for (int i = 0; i < numStr.length() / 2; i++) {
            if (numStr.charAt(i) != numStr.charAt(numStr.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Search rotated array
     */
    public static int binarySearchRotated(int[] data, int target) {
        //Instead of rotating the array back to orginal form, we can mathematically do the same via modulo and knowing
        //how much the array was rotated
        //rotated idx is the ((normal idx) + (rotated amount)) modulo (length)
        //data[] = 1,2,3,4,5,6,7
        //dataRotate[] = 4,5,6,7,1,2,3
        //normal idx = (end + start) / 2 => (6 + 0)/2 => 3;
        // data[3] = 4; This is the middle of the unrotated array
        // dataRotate[3] = 7; this is the end of the unsorted array and this is an abstract data point but...
        //rotatedIdx = ((normal idx) + (amount rotated)) % (length) = (3 + 4) % 7 = 7 % 7 => 0
        //This is the same as saying take your idx and subtract the amount rotated but the modulo operator help with if
        // the subtraction or addition goes below 0 or over the length
        //If you want the idx 20, you really want 20 % data.length => 20 % 7 => 6; data[20] => data[6]
        //dataRotated[rotatedIdx] => dataRotated[0] = 4 = data[normalIdx];
        //We then continue our binary search operation as normal using rotatedIdx but we modify the normalIdx

        //1) Find the smallest idx to find out how much the array was rotated
        int start = 0, end = data.length - 1;
        while (start < end) {
            int mid = (end + start) / 2;
            if (data[mid] > data[end]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        int rotatedAmount = start;
        start = 0;
        end = data.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int realMid = (mid + rotatedAmount) % data.length;
            if (data[realMid] == target) return realMid;
            if (data[realMid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }

    /**
     * Find the median of two sorted arrays
     */
    public static int medianOfTwoSortedArrays(int[] dataA, int[] dataB) {
        //O(n) space and time
        int[] dataCombined = combineSortedArrays(dataA, dataB);
        if (dataCombined.length % 2 != 0) {
            return dataCombined[dataCombined.length / 2];
        } else {
            return (dataCombined[dataCombined.length / 2] + dataCombined[(dataCombined.length / 2) - 1]) / 2;
        }
    }

    private static int[] combineSortedArrays(int[] dataA, int[] dataB) {
        int[] temp = new int[dataA.length + dataB.length];
        int i = 0, j = 0, k = 0;
        while (i < dataA.length && j < dataB.length) {
            if (dataA[i] < dataB[j]) {
                temp[k++] = dataA[i++];
            } else {
                temp[k++] = dataB[j++];
            }
        }
        while (i < dataA.length) {
            temp[k++] = dataA[i++];
        }
        while (j < dataB.length) {
            temp[k++] = dataB[j++];
        }
        return temp;
    }

    /**
     * Find smallest negative number idx in sorted array closet to the positive number
     * ex) -4,-3,-2,-1,-1,-1,0,1,2,3,4
     * return 5 because -1 @ idx 5 is only 1 away from 0
     */
    private static int findSmallestNegativeNumberHighestIdx(int[] data) {
        int start = 0;
        int end = data.length - 1;
        int ansIdx = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (data[mid] < 0) {
                ansIdx = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return ansIdx;
    }

    /**
     * Find smallest negative number idx in sorted array closet to the positive number
     * ex) -4,-3,-2,-1,-1,-1,0,1,2,3,4
     * return 5 because -1 @ idx 3 is only 1 away from 0
     */
    private static int findSmallestNegativeNumberLowestIdx(int[] data) {
        //Find number closet to 0
        int start = 0;
        int end = data.length - 1;
        int ansIdx = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (data[mid] < 0) {
                ansIdx = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        //Find earliest occurance of data[ansIdx]
        int target = data[ansIdx];
        start = 0;
        end = data.length - 1;
        ansIdx = -1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (data[mid] >= target) {
                end = mid - 1;
                ansIdx = mid;
            } else {
                start = mid + 1;
            }
        }

        return ansIdx;
    }

    /**
     * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
     * <p>
     * For "(()", the longest valid parentheses substring is "()", which has length = 2.
     * <p>
     * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
     */
    public static int findLongestValidParenthesis(String expr) {
        /*
        Stack<Character> stack = new Stack<>();
        int length = 0;
        int maxLength = -1;
        int i = expr.indexOf('(');
        if (i == -1) return -1;
        for (; i<expr.length(); i++){
            Character charAtIdx = expr.charAt(i);
            if (charAtIdx.equals(')')){
                if (stack.empty()){
                    maxLength = Math.max(maxLength, length);
                    length = 0;
                }
                else if (stack.peek() == '('){
                    stack.pop();
                    length++;
                    length++;
                    maxLength = Math.max(maxLength, length);
                }
            }
            if (charAtIdx.equals('(')){
                stack.push('(');
            }
        }

        while(!stack.isEmpty()){
            maxLength--;
            maxLength--;
            stack.pop();
        }
        return maxLength;
        */
        int left = 0, right = 0, maxLength = 0;
        //left scan
        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '(') {
                left++;
            } else if (expr.charAt(i) == ')') right++;
            if (left == right) {
                maxLength = Math.max(maxLength, 2 * right);
            } else if (right > left) { //if there are too many closing parenthesis then reset the algo for a new string
                left = right = 0; //reset all calc
            }
        }
        //right scan
        left = right = 0;
        for (int i = expr.length() - 1; i >= 0; i--) {
            if (expr.charAt(i) == '(') {
                left++;
            } else if (expr.charAt(i) == ')') right++;
            if (left == right) {
                maxLength = Math.max(maxLength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxLength;
    }

    /**
     * Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B)
     * is defined as the absolute difference between A and B.
     */
    public static Integer findKthSmallestDistanceBetweenPairs(int[] data, int k) {
//        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
//        Set<Integer> set = new HashSet<>();
//        for (int i = 0; i<data.length; i++){
//            for (int j = 0; j<data.length; j++){
//                    set.add(Math.abs(data[i] - data[j]));
//            }
//        }
//        priorityQueue.addAll(set);
//        while(k-- > 1){
//            priorityQueue.poll();
//        }
//        return priorityQueue.poll();
        Arrays.sort(data);

        //find low
        int low = data[1] - data[0];
        for (int i = 1; i < data.length - 1; i++) {
            low = Math.min(low, data[i + 1] - data[i]);
        }

        //find high
        int high = data[data.length - 1] - data[0]; //max diff

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (countPairs(data, mid) < k) low = mid + 1;
            else {
                high = mid;
            }
        }

        return low;
    }

    private static int countPairs(int[] data, int mid) {
        int n = data.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int j = i;
            while (j < n && data[j] - data[i] < mid) j++;
            res = res + j - i - 1; // res = res + number of pairs whose diff is < mid minus the starting idx and one for length
        }
        return res;
    }

    /**
     * Boyer-moore algo
     */
    public static int indexOfCustom(String string, String pattern) {
        int ansIdx = -1;
        Map<Character, Integer> patternMapping = new HashMap<>();
        //for every letter in alphabet default skip is length of pattern
        for (int i = 0; i < 26; i++) {
            patternMapping.put((char) ('a' + i), pattern.length());
        }
        //for every letter in pattern ,put how many letters you can shift
        for (int i = 0; i < pattern.length() - 1; i++) {
            patternMapping.put(pattern.charAt(i), pattern.length() - 1 - i);
        }
        int i = 0;
        int skip = 1;
        for (i = 0; i <= string.length() - pattern.length(); i += skip) {
            skip = 1;
            for (int j = pattern.length() - 1; j >= 0; j--) {
                if (pattern.charAt(j) == string.charAt(i + j)) {
                    if (j == 0) {
                        ansIdx = i;
                    }
                } else if (pattern.charAt(j) != string.charAt(i + j)) {
                    skip = patternMapping.get(string.charAt(i));
                    break;
                }
            }
            System.out.println("i " + i + " skip " + skip);
        }
        return ansIdx;
    }

    /**
     * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it.
     * Find and return the shortest palindrome you can find by performing this transformation.
     */
    public static String generateShortestPalindrome(String s) {
        //O(n^2)
        if (isStringPalindrome(s)) return s;
        StringBuilder sb = new StringBuilder(s);
        int iter = s.length() - 1;
        while (!isStringPalindrome(sb.toString())) {//O(n)
            System.out.println(sb.toString());
            sb.insert(0, s.charAt(iter--));//O(n)
        }
        return sb.toString();
    }

    private static boolean isStringPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    private static int[] mergeSort(int[] data, int l, int r) {
        if (l < r) {
            int mid = l + (r - l) / 2;
            mergeSort(data, l, mid);
            mergeSort(data, mid + 1, r);
            mergeSortHelper(data, l, mid, r);
        }
        return data;
    }

    private static void mergeSortHelper(int[] data, int l, int m, int r) {
        //sizes of the two arrays
        int n1 = m - l + 1; //+1 for the middle
        int n2 = r - m;

        //The arrays of the two halfs
        int[] n1Data = new int[n1];
        int[] n2Data = new int[n2];

        //Iterators of the array
        int i = 0, j = 0, k = l;

        //Populate the arrays
        for (i = 0; i < n1; i++) {
            n1Data[i] = data[l + i]; //covers mid
        }
        for (j = 0; j < n2; j++) {
            n2Data[j] = data[m + 1 + j]; //+1 for mid offset
        }

        //reset the iterators
        i = 0;
        j = 0;

        //populate the array
        while (i < n1 && j < n2) {
            if (n1Data[i] < n2Data[j]) {
                data[k++] = n1Data[i++];
            } else {
                data[k++] = n2Data[j++];
            }
        }

        //copy over the rest of the remainder
        while (i < n1) {
            data[k++] = n1Data[i++];
        }

        while (j < n2) {
            data[k++] = n2Data[j++];
        }
    }

    private static int[] quickSort(int[] data, int low, int high) {

        if (low < high) {
            int pivot = quickSortHelper(data, low, high);
            quickSort(data, low, pivot - 1);
            quickSort(data, pivot + 1, high);
        }
        return data;
    }

    private static int quickSortHelper(int[] data, int low, int high) {
        int pivot = data[high];
        int idxPrior = low - 1;
        for (int i = low; i < high; i++) {
            if (data[i] <= pivot) {
                idxPrior++;
                arraySwap(data, i, idxPrior);
            }
        }
        arraySwap(data, idxPrior + 1, high);
        return idxPrior + 1;
    }

    private static void arraySwap(int[] data, int i, int idxPrior) {
        int temp = data[i];
        data[i] = data[idxPrior];
        data[idxPrior] = temp;
    }

    /**
     * Generate all possible mnemonic for a phone numbers
     * ex) 222-222-2222 -> aaa-aaa-aaaa, aaa-aaa-aaab, aaa-aaa-aaac, ..., baa-aaa-aaaa...,caa-aaa-aaaa
     */
    public static List<String> generateMnemonics(String number) {
        String[] mneonics = new String[]{"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};
        List<String> set = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        generateMnemonicsHelper(number, 0, sb, set, mneonics);
        return set;
    }

    public static void generateMnemonicsHelper(String number, int numIdx, StringBuilder sb, List<String> set, String[] mneonics) {
        if (sb.toString().length() > number.length()) {
            return;
        }
        if (sb.toString().length() == number.length()) {
            set.add(sb.toString());
            return;
        }

        for (int i = numIdx; i < number.length(); i++) {
            int charAtI = (number.charAt(i)) - '0';
            for (int j = 0; j < mneonics[charAtI].length(); j++) {
                char selectedChar = mneonics[charAtI].charAt(j);
                sb.append(selectedChar);
                generateMnemonicsHelper(number, i + 1, sb, set, mneonics);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    /**
     * Construct a stack via an array
     */
    static class Stack {
        int size;
        int pushIdx;
        int popIdx;
        int[] data;

        public Stack() {
            size = 3;
            pushIdx = 0;
            popIdx = pushIdx;
            data = new int[size];
        }

        public boolean push(int x) {
            if (pushIdx == data.length - 1) {
                doubleDataArray();
            }
            data[pushIdx++] = x;
            popIdx = pushIdx;
            return true;
        }

        public int pop() {
            if (popIdx == 0) return -1;
            int dataPopped = data[popIdx-- - 1];
            return dataPopped;
        }

        public void doubleDataArray() {
            size = size * 2;
            int[] newData = new int[size];
            for (int i = 0; i < data.length; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
    }

    static class QueueWithArray {
        int[] data;
        int size;
        int enqIdx;
        int deqIdx;

        public QueueWithArray() {
            size = 10;
            data = new int[size];
            enqIdx = 0;
            deqIdx = 0;
        }

        public boolean enqueue(int insertThis) {
            if (enqIdx > data.length) return false;
            data[enqIdx] = insertThis;
            enqIdx = (enqIdx + 1) % data.length;
            return true;
        }

        public int dequeue() {
            int deqItem = data[deqIdx];
            deqIdx = (deqIdx + 1) % data.length;
            return deqItem;
        }
    }

    /**
     * Given two integers n and k, find how many different arrays consist of numbers from 1 to n such that there are
     * exactly k inverse pairs.
     * <p>
     * We define an inverse pair as following: For ith and jth element in the array, if i < j and a[i] > a[j] then
     * it's an inverse pair; Otherwise, it's not.
     * <p>
     * Since the answer may be very large, the answer should be modulo 109 + 7.
     */
    public static int kInversePairs(int n, int k) {
        int[] dp = new int[k + 1];
        int M = Integer.parseInt(Double.toString(Math.pow(10, 9))) + 7;
        for (int i = 1; i <= n; i++) {
            int[] temp = new int[k + 1];
            temp[0] = 1;
            for (int j = 1; j <= k; j++) {
                int val = (dp[j] + M - ((j - i) >= 0 ? dp[j - i] : 0)) % M;
                temp[j] = (temp[j - 1] + val) % M;
            }
            dp = temp;
        }
        return ((dp[k] + M - (k > 0 ? dp[k - 1] : 0)) % M);
    }


}

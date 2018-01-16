
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
        Node head= null;
        while (node != null) {
            Node n = new Node(node.data);
            n.next = head;
            head = n;
            node = node.next;
        }
        return head;
        }

    private static boolean ListsAreEqual(Node one, Node two){
         while (one != null && two != null) {
             if (one.data != two.data) return false;
             one = one.next;
             two = two.next;
         }
         return one== null && two== null;
    }

    /**
     * Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the
     * intersecting node. Note that the intersection is defined based on reference, not value. That is, if the kth
     * node of the first linked list is the exact same node (by reference) as the jth node of the second linked list,
     * then they are intersecting.
     */
    public static boolean twoListsOverlap(Node n1, Node n2){
        //find lengths
        int lengthN1 = findLength(n1);
        int lengthN2 = findLength(n2);

        //specify shorter/longer
        Node shorter = lengthN1 < lengthN2 ? n1 : n2;
        Node longer = lengthN1 < lengthN2 ? n2 : n1;

        //Traverse longer list to be the same size as shorter
        int k = Math.abs(lengthN1 - lengthN2);
        while(k>0){
            longer = longer.next;
        }

        //iterate through
        while(shorter != null && longer != null){
            if (shorter == longer) return true;
            shorter = shorter.next;
            longer = longer.next;
        }

        return false;
    }

    private static int findLength(Node n1) {
        int length = 0;
        while(n1 != null){
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
    private static Node circularListStart(Node n1){
        Node slow = n1;
        Node fast = n1;
        while (fast.next != null && fast != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }

        if (fast == null || fast.next == null) return null; //no loop

        slow = n1;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    /**
     * Given a sorted (increasing order) array with unique integer elements, write an algorithm to create a binary
     * search tree with minimal height.
     */
    class TreeNode{

        int data;
        TreeNode left, right;

        public TreeNode(int data){
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
    public TreeNode createMinBST(int[] data, int start, int end){
        if (data.length == 0) return null;
        if(data.length == 1) return new TreeNode(data[0]);
        if (end < start) return null;
        int mid = (end-start)/2;
        TreeNode root = new TreeNode(data[mid]);
        root.setLeft(createMinBST(data, start, mid-1));
        root.setRight(createMinBST(data, mid+1, end));
        return root;
    }

    /**
     * Route Between Nodes: Given a directed graph, design an algorithm to find out whether there is a route between
     * two nodes.
     */
    public enum State{
        Unvisited, Visiting, Visited
    }

    class GraphNode{
        int data;
        List<GraphNode> children;
        public State state;

        public GraphNode(int data){
            this.data = data;
            children = new ArrayList<>();
            state = State.Unvisited;
        }

        public void addChild(GraphNode n){
            children.add(n);
        }
        
        public List<GraphNode> getChildren() {
            return children;
        }
    }
    
    private static boolean pathExists(GraphNode start, GraphNode end){
        Queue<GraphNode> nodesToBeVisited = new LinkedList<>();
        start.state = State.Visiting;
        nodesToBeVisited.add(start);
        while(!nodesToBeVisited.isEmpty()){
            GraphNode node = nodesToBeVisited.poll();
            if (node == end) return true;
            for (GraphNode child : node.children){
                if (child.state == State.Unvisited){
                    child.state = State.Visiting;
                    nodesToBeVisited.add(child);
                }
            }
            node.state = State.Visited;
        }
        return false;



    }
}

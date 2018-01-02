
/*
This class is the primary class compiled and executed when trying out different algorithms for the various problems.

 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {

    }


    /**
     *
     You have a binary tree t. Your task is to find the largest value in each row of this tree. In a tree, a row is a
     set of nodes that have equal depth. For example, a row with depth 0 is a tree root, a row with depth 1 is composed
     of the root's children, etc.
     Return an array in which the first element is the largest value in the row with depth 0, the second element is the
     largest value in the row with depth 1, the third element is the largest element in the row with depth 2, etc.
     *
     */
    class Tree<T>{
        T value;
        Tree<T> left, right;

        public Tree(T t){
            value = t;
        }
    }

    private int[] largestValueInTreeRows(Tree<Integer> t) {
        Queue<Tree<Integer>> queue = new ArrayDeque<>();
        List<Integer> valuesAtDepth = new ArrayList<>();

        //Add the initial node
        if(t != null){
            queue.add(t);
        }

        //Set the number of elements at the 0 depth
        int queueSizeAtDepth = queue.size();

        while(!queue.isEmpty()){
            //Set the curMax to the smallest value by default
            int curMax = Integer.MIN_VALUE;

            //Go through all the elements at the current depth
            for(int i = 0; i<queueSizeAtDepth; i++){
                Tree<Integer> node = queue.remove();
                curMax = Math.max(curMax, node.value);
                //Add the children but these elements won't be run in the loop since they are past the queueSizeAtDepth
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
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
        for(Integer i : valuesAtDepth) values[counter++] = i;

        return values;
    }

    /**
     *
     * You are climbing a staircase that has n steps. You can take the steps either 1 or 2 at a time.
     * Calculate how many distinct ways you can climb to the top of the staircase.
     *
     */
    int climbingStairs(int n){

        if(n == 1) return 1;

        int[] numberOfWays = new int[n+1];

        numberOfWays[1] = 1;
        numberOfWays[2] = 2;

        for(int i = 3; i<=n; i++){
            //The numberOfWays when one step away plus the numberOfWays when two steps away
            numberOfWays[i] = numberOfWays[i-1] + numberOfWays[i-2];
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

    int houseRobber(int[] nums){

        //Only the first house can be robbed
        if(nums.length == 1) return nums[0];

        //If there are two houses, rob the one with the most money
        if(nums.length == 2) return Math.max(nums[0], nums[1]);

        int[][] maxSoFar = new int[nums.length+1][2];
        for(int i = 1; i<=nums.length; i++){
            maxSoFar[i][0] = Math.max(maxSoFar[i][0],maxSoFar[i-1][1]); //If you don't rob it
            maxSoFar[i][1] = nums[i-1] + maxSoFar[i-1][0]; //If you do rob it
        }

        return Math.max(maxSoFar[nums.length][0], maxSoFar[nums.length][1]);
    }

}

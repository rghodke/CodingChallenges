
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
            numberOfWays[i] = numberOfWays[i-1] + numberOfWays[i-2];
        }


        return numberOfWays[n];
    }

}

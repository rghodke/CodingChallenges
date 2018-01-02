
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

    /*
    You have a binary tree t. Your task is to find the largest value in each row of this tree. In a tree, a row is a
    set of nodes that have equal depth. For example, a row with depth 0 is a tree root, a row with depth 1 is composed
    of the root's children, etc.
    Return an array in which the first element is the largest value in the row with depth 0, the second element is the
    largest value in the row with depth 1, the third element is the largest element in the row with depth 2, etc.
     */

    class Tree<T>{
        T value;
        Tree<T> left, right;

        public Tree(T t){
            value = t;
        }
    }

    class TreeNodeWithDepth{
        Tree<Integer> t;
        int depth;

        public TreeNodeWithDepth(Tree<Integer> t, int depth){
            this.t = t;
            this.depth = depth;
        }
    }

    private int[] largestValueInTreeRows(Tree<Integer> t) {
        Queue<Tree<Integer>> queue = new ArrayDeque<>();

        List<TreeNodeWithDepth> listOfNodesAtDepth = new ArrayList<>();

        int depth = 0;

        if(t != null){
            queue.add(t);
            listOfNodesAtDepth.add(new TreeNodeWithDepth(t, depth));
        }

        while(!queue.isEmpty()){
            Tree<Integer> node = queue.remove();
            depth++;
            if(node.left != null){
                queue.add(node.left);
                listOfNodesAtDepth.add(new TreeNodeWithDepth(node.left, depth));
            }
            if(node.right != null){
                queue.add(node.right);
                listOfNodesAtDepth.add(new TreeNodeWithDepth(node.right, depth));
            }
        }

        List<List<Integer>> listOfNodesValues = new ArrayList<>();

        for(int i = 0; i<depth; i++){
            List<Integer> tempList = new ArrayList<Integer>();
            listOfNodesValues.add(tempList);
        }

        for(TreeNodeWithDepth node : listOfNodesAtDepth){
            List<Integer> listOfNodesAtNodeDepth = listOfNodesValues.get(node.depth);
            listOfNodesAtNodeDepth.add(node.t.value);
            listOfNodesValues.set(node.depth, listOfNodesAtNodeDepth);
        }

        List<Integer> answer = new ArrayList<>();
        for(List<Integer> l : listOfNodesValues){
            int max = l.get(0);
            for(Integer i : l){
                max = Math.max(max, i);
            }
            answer.add(max);
        }

        int[] answerArray = new int[answer.size()];
        int iterator = 0;

        for(Integer i : answer){
            answerArray[iterator++] = i;
        }

        return answerArray;

    }

}

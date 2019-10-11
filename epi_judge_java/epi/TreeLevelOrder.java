package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
public class TreeLevelOrder {
  @EpiTest(testDataFile = "tree_level_order.tsv")
  public static List<List<Integer>> binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
    List<List<Integer>> levels = new LinkedList<>();
    Deque<BinaryTreeNode<Integer>> toVisit = new LinkedList<>();
    toVisit.add(tree);
    while (!toVisit.isEmpty()) {
      Deque<BinaryTreeNode<Integer>> visiting = toVisit;
      List<Integer> level = new LinkedList<>();
      toVisit = new LinkedList<>();
      while (!visiting.isEmpty()) {
        BinaryTreeNode<Integer> current = visiting.remove();
        if (current != null) {
          level.add(current.data);
          toVisit.add(current.left);
          toVisit.add(current.right);
        }
      }
      if (!level.isEmpty()) {
        levels.add(level);
      }
    }
    return levels;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeLevelOrder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

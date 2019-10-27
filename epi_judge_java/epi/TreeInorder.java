package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

import java.util.List;
public class TreeInorder {

  private static class NodeAndState {
    public BinaryTreeNode<Integer> node;
    public Boolean leftSubtreeTraversed;

    public NodeAndState(BinaryTreeNode<Integer> node,
                        Boolean leftSubtreeTraversed) {
      this.node = node;
      this.leftSubtreeTraversed = leftSubtreeTraversed;
    }
  }

  @EpiTest(testDataFile = "tree_inorder.tsv")
  public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {
    List<Integer> list = new LinkedList<>();
    Deque<BinaryTreeNode<Integer>> stack = new ArrayDeque<>();
    BinaryTreeNode<Integer> current = tree;
    while (current != null || !stack.isEmpty()) {
      if (current == null) {
        current = stack.pop();
        list.add(current.data);
        current = current.right;
      } else {
        stack.push(current);
        current = current.left;
      }
    }
    return list;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

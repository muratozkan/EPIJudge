package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;
public class TreeFromPreorderWithNull {

  static class NodeWithIndex {
    BinaryTreeNode<Integer> node;
    int index;

    NodeWithIndex(BinaryTreeNode<Integer> node, int index) {
      this.node = node;
      this.index = index;
    }
  }

  private static NodeWithIndex recursiveConstruct(List<Integer> pre, int pi) {
    NodeWithIndex node = new NodeWithIndex(null, 1);
    if (pre.get(pi) != null) {
      int d = pre.get(pi);
      node.node = new BinaryTreeNode<>(d);
      if (pre.get(pi + 1) == null && pre.get(pi + 2) == null) {
        node.index = 3;
        return node;
      }
      NodeWithIndex left = recursiveConstruct(pre, pi + 1);
      NodeWithIndex right = recursiveConstruct(pre, pi + left.index + 1);
      node.node.left = left.node;
      node.node.right = right.node;
      node.index = left.index + right.index + 1;
    }
    return node;
  }

  public static BinaryTreeNode<Integer> reconstructPreorder(List<Integer> preorder) {
    return recursiveConstruct(preorder, 0).node;
  }
  @EpiTest(testDataFile = "tree_from_preorder_with_null.tsv")
  public static BinaryTreeNode<Integer>
  reconstructPreorderWrapper(TimedExecutor executor, List<String> strings)
      throws Exception {
    List<Integer> ints = new ArrayList<>();
    for (String s : strings) {
      if (s.equals("null")) {
        ints.add(null);
      } else {
        ints.add(Integer.parseInt(s));
      }
    }

    return executor.run(() -> reconstructPreorder(ints));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderWithNull.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

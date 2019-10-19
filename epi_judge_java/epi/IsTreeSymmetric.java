package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Objects;

public class IsTreeSymmetric {

  private static boolean symmetricWalk(BinaryTreeNode<Integer> left, BinaryTreeNode<Integer> right) {
    if (left == null && right == null) {
      return true;
    }
    if (left != null && right != null) {
      return Objects.equals(left.data, right.data) && symmetricWalk(left.left, right.right) && symmetricWalk(left.right, right.left);
    }
    return false;
  }

  @EpiTest(testDataFile = "is_tree_symmetric.tsv")
  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    return tree == null || symmetricWalk(tree.left, tree.right);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {

  static class BalanceHeight {
    boolean balanced;
    int height;

    BalanceHeight(boolean balanced, int height) {
      this.balanced = balanced;
      this.height = height;
    }
  }

  private static BalanceHeight checkHeight(BinaryTreeNode<Integer> root) {
    BalanceHeight current = new BalanceHeight(true, -1);
    if (root == null) {
      return current;
    }
    BalanceHeight left, right;
    left = checkHeight(root.left);
    if (!left.balanced) {
      return left;
    }
    right = checkHeight(root.right);
    if (!right.balanced) {
      return right;
    }

    current.balanced = Math.abs(left.height - right.height) <= 1;
    current.height = Math.max(right.height, left.height) + 1;
    return current;
  }

  @EpiTest(testDataFile = "is_tree_balanced.tsv")
  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    return checkHeight(tree).balanced;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

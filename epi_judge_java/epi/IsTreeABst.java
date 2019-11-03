package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeABst {

  private static boolean recursiveCheck(BinaryTreeNode<Integer> tree, int min, int max) {
    if (tree == null) {
      return true;
    }
    if (tree.data < min || tree.data > max) {
      return false;
    }
    return recursiveCheck(tree.left, min, tree.data) &&
            recursiveCheck(tree.right, tree.data, max);
  }

  @EpiTest(testDataFile = "is_tree_a_bst.tsv")
  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    return recursiveCheck(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

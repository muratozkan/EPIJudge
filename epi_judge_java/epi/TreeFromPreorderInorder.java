package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class TreeFromPreorderInorder {

  private static BinaryTreeNode<Integer> recursiveConstruct(List<Integer> pre, List<Integer> in, int pi, int pH, int inL, int inH) {
    BinaryTreeNode<Integer> root = null;
    if (pi < pH && inL < inH) {
      int d = pre.get(pi);
      int ri = in.subList(inL, inH).indexOf(d);
      BinaryTreeNode<Integer> left = recursiveConstruct(pre, in, pi + 1, pi + ri + 1, inL, inL + ri);
      BinaryTreeNode<Integer> right = recursiveConstruct(pre, in, pi + ri + 1, pH, inL + ri + 1, inH);
      root = new BinaryTreeNode<>(d, left, right);
    }
    return root;
  }

  @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")
  public static BinaryTreeNode<Integer>
  binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {
    return recursiveConstruct(preorder, inorder, 0, preorder.size(), 0, inorder.size());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

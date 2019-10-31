package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class TreeWithParentInorder {
  @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")

  public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
    List<Integer> list = new ArrayList<>();
    BinaryTree<Integer> prev = null, curr = tree;
    while (curr != null) {
      if (curr.right != null && curr.right == prev) {
        prev = curr;
        curr = curr.parent;
      } else if (curr.left != null && curr.left != prev) {
        prev = curr;
        curr = curr.left;
      } else {
        list.add(curr.data);
        if (curr.right != null && curr.right != prev) {
          prev = curr;
          curr = curr.right;
        } else {
          if (curr.parent == null) {
            curr = null;
          } else{
            prev = (curr.parent.left == curr) ? curr : curr.parent;
            curr = prev.parent;
          }
        }
      }
    }
    return list;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeWithParentInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

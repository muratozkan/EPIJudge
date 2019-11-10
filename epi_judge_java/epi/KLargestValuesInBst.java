package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class KLargestValuesInBst {
  @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")
  public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {
    List<Integer> result = new ArrayList<>(k);
    reverseInOrder(tree, k, result);
    return result;
  }

  private static void reverseInOrder(BstNode<Integer> tree, int k, List<Integer> list) {
    if (tree != null && list.size() < k) {
      reverseInOrder(tree.right, k, list);
      if (list.size() < k) {
        list.add(tree.data);
        reverseInOrder(tree.left, k, list);
      }
    }
  }

  @EpiTestComparator
  public static boolean comp(List<Integer> expected, List<Integer> result) {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KLargestValuesInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

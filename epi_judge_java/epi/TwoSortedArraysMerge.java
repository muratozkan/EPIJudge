package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class TwoSortedArraysMerge {

  public static void mergeTwoSortedArrays(List<Integer> A, int m,
                                          List<Integer> B, int n) {
    int al = A.size() - m;
    Collections.rotate(A, al);
    int wi = 0, i = 0, j = 0;
    while (wi < m + n) {
      int ai = (i < m) ? A.get(al + i) : Integer.MAX_VALUE;
      int bj = (j < n) ? B.get(j) : Integer.MAX_VALUE;
      if (ai <= bj) {
        Collections.swap(A, wi, al + i);
        i ++;
      } else {
        A.set(wi, bj);
        j++;
      }
      wi++;
    }
  }
  @EpiTest(testDataFile = "two_sorted_arrays_merge.tsv")
  public static List<Integer>
  mergeTwoSortedArraysWrapper(List<Integer> A, int m, List<Integer> B, int n) {
    mergeTwoSortedArrays(A, m, B, n);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TwoSortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class SearchRowColSortedMatrix {
  @EpiTest(testDataFile = "search_row_col_sorted_matrix.tsv")

  public static boolean matrixSearch(List<List<Integer>> A, int x) {
    int m = 0, n = A.get(0).size() - 1;
    while (m < A.size() && n >= 0) {
      int t = A.get(m).get(n);
      if (t == x) {
        return true;
      }
      if (t > x) {
        n -= 1;
      } else {
        m += 1;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchRowColSortedMatrix.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

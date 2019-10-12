package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;
public class IsValidSudoku {

  private static int subGridIx(int row, int col) {
    return (row / 3) * 3 + (col / 3);
  }

  // check whether nth bit is set.
  private static int checkUpdate(int bs, int v) {
    if (((bs >>> v) & 1) == 1) {
      return -1; // already set
    }
    return (1 << v) | bs;
  }

  @EpiTest(testDataFile = "is_valid_sudoku.tsv")
  // Check if a partially filled matrix has any conflicts.
  public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
    List<Integer> colC = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0);
    List<Integer> sGC = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0);

    for (int r = 0; r < 9; r++) {
      int rowC = 0;
      for (int c = 0; c < 9; c++) {
        int v = partialAssignment.get(c).get(r);
        if (v != 0) {
          rowC = checkUpdate(rowC, v);
          if (rowC == -1) {
            return false;
          }
          int cColC = checkUpdate(colC.get(c), v);
          if (cColC == -1) {
            return false;
          }
          colC.set(c, cColC);

          int cSGC = checkUpdate(sGC.get(subGridIx(r, c)), v);
          if (cSGC == -1) {
            return false;
          }
          sGC.set(subGridIx(r, c), cSGC);
        }
      }
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidSudoku.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

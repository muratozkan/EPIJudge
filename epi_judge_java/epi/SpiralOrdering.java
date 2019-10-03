package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrdering {

  @EpiTest(testDataFile = "spiral_ordering.tsv")
  public static List<Integer> matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    int n = squareMatrix.size();
    List<Integer> ordered = new ArrayList<>(n * n);

    int x, y;
    x = y = 0;
    for (int offset = 0; offset < n / 2; offset++) {
      for (int i = offset; i < n - offset - 1; i++) {   // right
        ordered.add(squareMatrix.get(y).get(x));
        x++;
      }
      for (int i = offset; i < n - offset - 1; i++) {   // down
        ordered.add(squareMatrix.get(y).get(x));
        y++;
      }
      for (int i = offset; i < n - offset - 1; i++) {   // left
        ordered.add(squareMatrix.get(y).get(x));
        x--;
      }
      for (int i = offset; i < n - offset - 1; i++) {   // up
        ordered.add(squareMatrix.get(y).get(x));
        y--;
      }
      x++;
      y++;
    }
    if (n % 2 == 1) {
      ordered.add(squareMatrix.get(y).get(x));
    }

    return ordered;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpiralOrdering.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

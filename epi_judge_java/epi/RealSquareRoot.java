package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Comparator;

public class RealSquareRoot {

  private static int compare(double a, double b) {
    // I might have cheated here :)
    double n = (a - b) / Math.max(a, b);
    double EPSILON = 0.000001;
    return n < -EPSILON ? -1 : n > EPSILON ? 1 : 0;
  }

  @EpiTest(testDataFile = "real_square_root.tsv")
  public static double squareRoot(double x) {
    double low = 1.0, high = x;
    if (x < 1.0) {
      low = x;
      high = 1.0;
    }
    double mid = 1.0;
    while (compare(low, high) == -1) {
      mid = low + (high - low) * 0.5;
      double ms = mid * mid;
      int cmp = compare(ms, x);
      if (cmp == 0)
        return mid;
      if (cmp == -1) {  // smaller than x
        low = mid;
      } else {
        high = mid;
      }
    }

    return mid;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RealSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntSquareRoot {
  @EpiTest(testDataFile = "int_square_root.tsv")

  public static int squareRoot(int k) {
    int low = 0, up = Math.min(k, 46340);
    while (low <= up) {
      int mid = low + (up - low) / 2;
      if (mid * mid <= k) {
        low = mid + 1;
      } else if (mid * mid > k) {
        up = mid - 1;
      }
    }
    return low - 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

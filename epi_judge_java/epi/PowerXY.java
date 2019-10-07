package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PowerXY {
  @EpiTest(testDataFile = "power_x_y.tsv")
  public static double power(double x, int y) {
    double d = 1.0D;
    if (y < 0) {
      x = 1 / x;
      y = -y;
    }
    while (y != 0) {
      if ((y & 1) == 1) {
        d *= x;
      }
      x *= x;
      y >>>= 1;
    }
    return d;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PowerXY.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

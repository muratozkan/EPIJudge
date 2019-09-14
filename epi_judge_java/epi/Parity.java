package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class Parity {
  /* parity is 1 if number of 1's is odd, 0 otherwise */
  @EpiTest(testDataFile = "parity.tsv")
  public static short parityBruteForce(long x) {
    short parity = 0;
    while (x != 0) {
      parity ^= (x & 1);
      x >>>= 1;
    }
    return parity;
  }

  @EpiTest(testDataFile = "parity.tsv")
  public static short parityBetter(long x) {
    short parity = 0;
    while (x != 0) {
      parity ^= 1;
      x &= (x - 1);
    }
    return parity;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

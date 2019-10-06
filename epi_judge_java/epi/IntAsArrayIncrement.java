package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {
    int carry = 1;
    for (int i = A.size() - 1; i >= 0; i--) {
      int d = A.get(i) + carry;
      A.set(i, d % 10);
      carry = d / 10;
    }
    if (carry == 1) {
      A.add(0);       // only way to have a 1 carry over is to have a 0 all the way, so we're adding a 0 to the LSD
      A.set(0, 1);
    }
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

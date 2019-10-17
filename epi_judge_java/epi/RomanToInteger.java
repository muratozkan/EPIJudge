package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Map;

public class RomanToInteger {
  @EpiTest(testDataFile = "roman_to_integer.tsv")

  public static int romanToInteger(String s) {
    Map<Character, Integer> rToI = Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000);
    int n = 0, last = 10000;
    for (int i = 0; i < s.length(); i++) {
      int cur = rToI.get(s.charAt(i));
      n += cur;
      if (cur > last) { // exception
        n -= (2 * last);
      } else {
        last = cur;
      }
    }
    return n;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RomanToInteger.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

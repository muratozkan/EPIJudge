package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsNumberPalindromic {
  @EpiTest(testDataFile = "is_number_palindromic.tsv")
  public static boolean isPalindromeNumber(int x) {
    if (x <= 0) {
      return x == 0;
    }
    // we don't want to check the string version to avoid O(n) space complexity
    int n = (int) Math.log10(x);
    int p = (int) Math.pow(10, n);
    int d = x;
    for (; n > 0; n -= 2) {
      int msd = d / p;
      int lsd = d % 10;
      if (msd != lsd) {
        return false;
      }
      d = (d % p) / 10;
      p /= 100;
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsNumberPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

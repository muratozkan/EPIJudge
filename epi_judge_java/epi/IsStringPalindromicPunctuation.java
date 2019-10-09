package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsStringPalindromicPunctuation {

  @EpiTest(testDataFile = "is_string_palindromic_punctuation.tsv")
  public static boolean isPalindrome(String s) {
    int b = 0;
    int e = s.length() - 1;
    while (e > b) {
      char ec = s.charAt(e);
      if (!Character.isLetterOrDigit(ec)) {
        e --;
        continue;
      }
      char bc = s.charAt(b);
      if (!Character.isLetterOrDigit(bc)) {
        b ++;
        continue;
      }
      if (Character.toLowerCase(bc) != Character.toLowerCase(ec)) {
        return false;
      }
      e --;
      b ++;
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPalindromicPunctuation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class StringIntegerInterconversion {

  public static String intToString(int x) {
    boolean n = (x < 0);
    StringBuilder sb = new StringBuilder(11);
    do {
      int a = x / 10;
      int b = x - (a * 10);
      sb.append(Math.abs(b));
      x = a;
    } while (x != 0);
    if (n) {
      sb.append("-");
    }
    return sb.reverse().toString();
  }
  public static int stringToInt(String s) {
    int x = 0;
    int p = 1;
    int ei = s.charAt(0) == '-' ? 1 : 0;
    for (int i = s.length() - 1; i >= ei; i--) {
      x += (s.charAt(i) - '0') * p;
      p *= 10;
    }
    return x * (ei == 0 ? 1 : -1);
  }
  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (Integer.parseInt(intToString(x)) != x) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

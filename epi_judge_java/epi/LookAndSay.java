package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class LookAndSay {

  @EpiTest(testDataFile = "look_and_say.tsv")
  public static String lookAndSay(int n) {
    String s = "1";
    for (int i = 1; i < n; i++) {
      s = iterateLookAndSay(s);
    }
    return s;
  }

  private static String iterateLookAndSay(String ls) {
    StringBuilder sb = new StringBuilder();
    int c = 0;
    char d = ls.charAt(0);
    for (int i = 0; i < ls.length(); i++) {
      char ci = ls.charAt(i);
      if (d == ci) {
        c ++;
      } else {
        sb.append(c).append(d);
        c = 1;
        d = ci;
      }
    }
    sb.append(c).append(d);
    return sb.toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LookAndSay.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

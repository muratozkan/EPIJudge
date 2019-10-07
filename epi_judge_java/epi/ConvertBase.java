package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ConvertBase {
  @EpiTest(testDataFile = "convert_base.tsv")

  public static String convertBase(String numAsString, int b1, int b2) {
    boolean negative = numAsString.charAt(0) == '-';
    int number = 0;
    for (int i = (negative ? 1 : 0); i < numAsString.length(); i++) {
      int d = numAsString.charAt(i) >= 'A' ? (numAsString.charAt(i) - 'A' + 10) : numAsString.charAt(i) - '0';
      number = (number * b1) + d;
    }

    StringBuilder builder = new StringBuilder();
    do {
      int d = number % b2;
      number = number / b2;
      builder.append((char) (d >= 10 ? 'A' + d - 10 : '0' + d));
    } while (number != 0);
    if (negative) {
      builder.append('-');
    }
    return builder.reverse().toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ConvertBase.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

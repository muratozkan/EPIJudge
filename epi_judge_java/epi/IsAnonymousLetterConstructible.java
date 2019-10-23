package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class IsAnonymousLetterConstructible {
  @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")

  public static boolean isLetterConstructibleFromMagazine(String letterText,
                                                          String magazineText) {
    Map<Character, Integer> mc = new HashMap<>();
    for (int i = 0; i < magazineText.length(); i++) {
      int c = mc.getOrDefault(magazineText.charAt(i), 0);
      mc.put(magazineText.charAt(i), c + 1);
    }

    for (int i = 0; i < letterText.length(); i++) {
      int c = mc.getOrDefault(letterText.charAt(i), 0);
      if (c == 0) {
        return false;
      }
      mc.put(letterText.charAt(i), c - 1);
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

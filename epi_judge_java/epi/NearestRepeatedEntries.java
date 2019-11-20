package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NearestRepeatedEntries {
  @EpiTest(testDataFile = "nearest_repeated_entries.tsv")
  public static int findNearestRepetition(List<String> paragraph) {
    int minDist = Integer.MAX_VALUE;
    Map<String, Integer> map = new HashMap<>();
    for (int i = 0; i < paragraph.size(); i++) {
      String s = paragraph.get(i);
      if (map.containsKey(s)) {
        int pi = map.get(s);
        if (minDist > i - pi) {
          minDist = i - pi;
        }
      }
      map.put(s, i);
    }
    return minDist == Integer.MAX_VALUE ? -1 : minDist;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NearestRepeatedEntries.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

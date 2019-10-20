package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class SearchFirstKey {
  @EpiTest(testDataFile = "search_first_key.tsv")
  public static int searchFirstOfK(List<Integer> A, int k) {
    int end = A.size() - 1;
    int start = 0;
    int ci = -1;            // candidate index
    while (start <= end) {
      int m = (end + start) / 2;
      if (A.get(m) > k) {
        end = m - 1;
      } else if (A.get(m) < k) {
        start = m + 1;
      } else {
        ci = m;
        end = m - 1;
      }
    }
    return ci;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

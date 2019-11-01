package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class SearchShiftedSortedArray {
  @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")

  public static int searchSmallest(List<Integer> A) {
    int li = 0, ui = A.size() - 1;
    int low = A.get(ui);

    while (li < ui) {
      int med = (ui - li) / 2 + li;
      if (A.get(med) > A.get(med + 1)) {
        return med + 1;
      }
      if (A.get(med) > low) {
        li = med;
      } else {
        ui = med;
      }
    }

    return 0;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchShiftedSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

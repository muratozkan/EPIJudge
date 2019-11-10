package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.BiPredicate;
public class KLargestInHeap {

  private static class IndexAndValue {
    int index;
    int value;

    IndexAndValue(int index, int value) {
      this.index = index;
      this.value = value;
    }
  }

  @EpiTest(testDataFile = "k_largest_in_heap.tsv")
  public static List<Integer> kLargestInBinaryHeap(List<Integer> A, int k) {
    if (k <= 0) {
      return List.of();
    }

    PriorityQueue<IndexAndValue> heap = new PriorityQueue<>(Comparator.comparing(iav -> - iav.value));
    heap.add(new IndexAndValue(0, A.get(0)));
    List<Integer> results = new ArrayList<>(k);
    while (results.size() < k) {
      IndexAndValue iav = heap.remove();
      results.add(iav.value);
      int li = 2 * iav.index + 1, ri = 2 * iav.index + 2;
      if (A.size() > li) {
        heap.add(new IndexAndValue(li, A.get(li)));
      }
      if (A.size() > ri) {
        heap.add(new IndexAndValue(ri, A.get(ri)));
      }
    }

    return results;
  }
  @EpiTestComparator
  public static boolean comp(List<Integer> expected, List<Integer> result) {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KLargestInHeap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

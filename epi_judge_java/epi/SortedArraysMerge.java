package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class SortedArraysMerge {

  private static class MinRef {
    Integer value;
    int aI;
    int i;

    MinRef(Integer value, int aI, int i) {
      this.value = value;
      this.aI = aI;
      this.i = i;
    }
  }

  @EpiTest(testDataFile = "sorted_arrays_merge.tsv")
  public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
    PriorityQueue<MinRef> minHeap = new PriorityQueue<>(sortedArrays.size(), Comparator.comparingInt(r -> r.value));
    List<Integer> sorted = new LinkedList<>();
    for (int i = 0; i < sortedArrays.size(); i++) {
      List<Integer> a = sortedArrays.get(i);
      if (!a.isEmpty())
        minHeap.add(new MinRef(a.get(0), i, 0));
    }
    while (!minHeap.isEmpty()) {
      MinRef min = minHeap.remove();
      if (sortedArrays.get(min.aI).size() > (min.i + 1)) {
        minHeap.add(new MinRef(sortedArrays.get(min.aI).get(min.i + 1), min.aI, min.i + 1));
      }
      sorted.add(min.value);
    }
    return sorted;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

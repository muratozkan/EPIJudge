package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class SortAlmostSortedArray {

  public static List<Integer>
  sortApproximatelySortedData(Iterator<Integer> sequence, int k) {
    int size = k + 1;
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(size, Comparator.naturalOrder());
    List<Integer> list = new ArrayList<>();
    while (sequence.hasNext()) {
      if (minHeap.size() < size) {
        minHeap.add(sequence.next());
      }
      if (minHeap.size() == size) {
        list.add(minHeap.poll());
      }
    }
    while (!minHeap.isEmpty()) {
      list.add(minHeap.poll());
    }
    return list;
  }
  @EpiTest(testDataFile = "sort_almost_sorted_array.tsv")
  public static List<Integer>
  sortApproximatelySortedDataWrapper(List<Integer> sequence, int k) {
    return sortApproximatelySortedData(sequence.iterator(), k);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortAlmostSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

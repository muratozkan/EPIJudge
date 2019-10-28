package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class KthLargestInArray {
  // The numbering starts from one, i.e., if A = [3,1,-1,2] then
  // findKthLargest(1, A) returns 3, findKthLargest(2, A) returns 2,
  // findKthLargest(3, A) returns 1, and findKthLargest(4, A) returns -1.
  @EpiTest(testDataFile = "kth_largest_in_array.tsv")
  public static int findKthLargest(int k, List<Integer> A) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, Comparator.naturalOrder());
    // O(nlog(k)) since this method sorts the heap. Space complexity: O(k)
    for (Integer val : A) {
      int min = Integer.MIN_VALUE;
      if (minHeap.size() == k) {
        min = minHeap.remove();
      }
      minHeap.add(Math.max(min, val));
    }
    return minHeap.remove();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthLargestInArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

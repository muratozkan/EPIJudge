package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class KthLargestInArray {
  // The numbering starts from one, i.e., if A = [3,1,-1,2] then
  // findKthLargest(1, A) returns 3, findKthLargest(2, A) returns 2,
  // findKthLargest(3, A) returns 1, and findKthLargest(4, A) returns -1.
  @EpiTest(testDataFile = "kth_largest_in_array.tsv")
  public static int findKthLargest(int k, List<Integer> A) {
    Random random = new Random();
    int p;  // current partition index
    int si = 0, li = A.size() - 1;
    do {
      p = partition(A, si, li, si + random.nextInt(li - si + 1));
      if (p > (k - 1)) {
        li = p - 1;
      } else if (p < (k - 1)) {
        si = p + 1;
      }
    } while (p != (k - 1));

    return A.get(p);
  }


  private static int partition(List<Integer> A, int start, int end, int pivotIndex) {
    int pivotVal = A.get(pivotIndex);
    int cutOff = start;
    Collections.swap(A, pivotIndex, end); // put pivot to last slot so we can find it later
    for (int i = start; i < end; i++) {
      if (A.get(i) >= pivotVal) {
        Collections.swap(A, i, cutOff);
        cutOff += 1;
      }
    }
    Collections.swap(A, cutOff, end);
    return cutOff;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthLargestInArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

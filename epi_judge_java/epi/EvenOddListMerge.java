package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class EvenOddListMerge {
  @EpiTest(testDataFile = "even_odd_list_merge.tsv")
  public static ListNode<Integer> evenOddMerge(ListNode<Integer> L) {
    ListNode<Integer> sorted, iter;
    sorted = L;
    iter = sorted == null ? null : sorted.next;
    while (iter != null) {
      ListNode<Integer> currentTail = iter.next;
      if (currentTail != null) {
        iter.next = currentTail.next;
        currentTail.next = sorted.next;
        sorted.next = currentTail;
        sorted = sorted.next;
      }
      iter = iter.next;
    }

    return L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvenOddListMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {
    ListNode<Integer> h = new ListNode<>(-100, L);
    ListNode<Integer> rH = new ListNode<>(-200, null);
    ListNode<Integer> prev = h, curr = h.next;  // iterating main list
    ListNode<Integer> sH, sL = null;   // sublist head and last element

    int i = 1;
    for (;i < start; i++) {
      prev = curr;
      curr = curr.next;
    }
    sH = prev;
    for (; i <= finish; i++) {
      prev = curr;
      curr = curr.next;
      if (rH.next == null) {
        sL = prev;
      }
      ListNode<Integer> t = rH.next;
      rH.next = prev;
      prev.next = t;
    }
    if (sH != null && sL != null) {
      sH.next = rH.next;
      sL.next = curr;
    }

    return h.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

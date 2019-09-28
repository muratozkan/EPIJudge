package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> i1,
                                                      ListNode<Integer> i2) {
    ListNode<Integer> m = new ListNode<>(Integer.MIN_VALUE, null);
    ListNode<Integer> mi = m;
    while (i1 != null && i2 != null) {
      ListNode<Integer> c;
      if (i2.data < i1.data) {
        c = i2;
        i2 = i2.next;
      } else {
        c = i1;
        i1 = i1.next;
      }
      mi.next = c;
      mi = mi.next;
    }
    mi.next = (i1 == null) ? i2 : i1;
    return m.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

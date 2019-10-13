package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class DoTerminatedListsOverlap {

  public static ListNode<Integer> overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {
    ListNode<Integer> i0 = new ListNode<>(Integer.MIN_VALUE, l0);
    ListNode<Integer> i1 = new ListNode<>(Integer.MIN_VALUE, l1);
    int len0 = 0, len1 = 0;
    while (i0.next != null) {
      i0 = i0.next;
      len0 ++;
    }
    while (i1.next != null) {
      i1 = i1.next;
      len1 ++;
    }

    if (i0 == i1) {
      // the last nodes are equal, so these lists are overlapping. In the longer list, iterate (lenL - lenS) times to
      // find the beginning.
      if (len0 >= len1) {
        i0 = new ListNode<>(Integer.MIN_VALUE, l0);
        i1 = new ListNode<>(Integer.MIN_VALUE, l1);
      } else {
        i0 = new ListNode<>(Integer.MIN_VALUE, l1);
        i1 = new ListNode<>(Integer.MIN_VALUE, l0);
      }
      for (int i = 0; i < Math.abs(len0 - len1); i++) {
        i0 = i0.next;
      }
      for (int i = 0; i < Math.min(len0, len1); i++) {
        if (i0 == i1) {
          return i0;
        }
        i0 = i0.next;
        i1 = i1.next;
      }
    }

    return null;
  }

  @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
  public static void
  overlappingNoCycleListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                                 ListNode<Integer> l1, ListNode<Integer> common)
      throws Exception {
    if (common != null) {
      if (l0 != null) {
        ListNode<Integer> i = l0;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l0 = common;
      }

      if (l1 != null) {
        ListNode<Integer> i = l1;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l1 = common;
      }
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingNoCycleLists(finalL0, finalL1));

    if (result != common) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoTerminatedListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

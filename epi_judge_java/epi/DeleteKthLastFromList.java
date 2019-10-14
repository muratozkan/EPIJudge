package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class DeleteKthLastFromList {
  @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")
  // Assumes L has at least k nodes, deletes the k-th last node in L.
  public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
    ListNode<Integer> lastI = new ListNode<>(Integer.MIN_VALUE, L);
    ListNode<Integer> remI = new ListNode<>(Integer.MIN_VALUE, L);

    for (int i = 0; i < k; i++) {
      lastI = lastI.next;
    }

    while (lastI.next != null) {
      lastI = lastI.next;
      remI = remI.next;
    }
    // remI.next is the node to delete now
    ListNode<Integer> toDelete = remI.next;
    remI.next = toDelete == null ? null : toDelete.next;

    // if deleting the head node, the list is the remaining
    return toDelete == L ? remI.next : L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DeleteKthLastFromList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

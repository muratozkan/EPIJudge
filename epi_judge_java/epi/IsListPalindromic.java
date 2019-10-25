package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsListPalindromic {

  private static ListNode<Integer> reverse(ListNode<Integer> head) {
    ListNode<Integer> next, prev = null, current = head;
    while (current != null) {
      next = current.next;
      current.next = prev;
      prev = current;
      current = next;
    }
    return prev;
  }

  @EpiTest(testDataFile = "is_list_palindromic.tsv")

  public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {
    int k = -1;
    ListNode<Integer> it, head = new ListNode<>(Integer.MIN_VALUE, L);
    it = head;
    while (it.next != null) {
      it = it.next;
      k ++;
    }
    if (k == 0 || k == 1) {
      return true;
    }

    it = head;
    for (int i = 0; i < k / 2 ; i++) {
      it = it.next;
    }
    ListNode<Integer> rHead = reverse(it), rit;
    it = head.next;
    rit = rHead;
    while (it != null && rit != null) {
      if (!it.data.equals(rit.data)) {
        return false;
      }
      it = it.next;
      rit = rit.next;
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsListPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

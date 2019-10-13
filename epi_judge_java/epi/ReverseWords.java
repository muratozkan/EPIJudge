package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

public class ReverseWords {

  public static void reverseWords(char[] input) {
    int n = input.length;
    reverse(input, 0, n - 1);

    int start = 0;
    for(int spaceIx = 0; spaceIx <= n; spaceIx++) {
      if (spaceIx < n && input[spaceIx] != ' ') {
        continue;
      }
      reverse(input, start, spaceIx - 1);
      start = spaceIx + 1;
    }
  }

  static void reverse(char[] array, int start, int end) {
    while (start < end) {
      char c = array[start];
      array[start] = array[end];
      array[end] = c;
      start ++;
      end --;
    }
  }

  @EpiTest(testDataFile = "reverse_words.tsv")
  public static String reverseWordsWrapper(TimedExecutor executor, String s)
      throws Exception {
    char[] sCopy = s.toCharArray();

    executor.run(() -> reverseWords(sCopy));

    return String.valueOf(sCopy);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseWords.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

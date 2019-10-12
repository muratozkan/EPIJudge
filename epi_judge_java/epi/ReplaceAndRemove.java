package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class ReplaceAndRemove {

  public static int replaceAndRemove(int size, char[] s) {
    int wi = 0, aC = 0;
    for (int ri = 0; ri < size; ri++) {
      if (s[ri] != 'b') {
        s[wi] = s[ri];
        wi++;
      }
      if (s[ri] == 'a') {
        aC++;
      }
    }
    int ri = wi - 1;
    wi += aC - 1;
    size = wi + 1;
    while (ri >= 0) {
      if (s[ri] != 'a') {
        s[wi] = s[ri];
      } else {
        s[wi] = 'd';
        wi --;
        s[wi] = 'd';
      }
      wi --;
      ri --;
    }
    return size;
  }
  @EpiTest(testDataFile = "replace_and_remove.tsv")
  public static List<String>
  replaceAndRemoveWrapper(TimedExecutor executor, Integer size, List<String> s)
      throws Exception {
    char[] sCopy = new char[s.size()];
    for (int i = 0; i < size; ++i) {
      if (!s.get(i).isEmpty()) {
        sCopy[i] = s.get(i).charAt(0);
      }
    }

    Integer resSize = executor.run(() -> replaceAndRemove(size, sCopy));

    List<String> result = new ArrayList<>();
    for (int i = 0; i < resSize; ++i) {
      result.add(Character.toString(sCopy[i]));
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReplaceAndRemove.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

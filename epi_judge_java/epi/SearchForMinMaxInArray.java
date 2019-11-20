package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class SearchForMinMaxInArray {
  @EpiUserType(ctorParams = {Integer.class, Integer.class})

  public static class MinMax {
    public Integer smallest;
    public Integer largest;

    public MinMax(Integer smallest, Integer largest) {
      this.smallest = smallest;
      this.largest = largest;
    }

    private static MinMax minMax(Integer a, Integer b) {
      return Integer.compare(b, a) < 0 ? new MinMax(b, a) : new MinMax(a, b);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      MinMax minMax = (MinMax)o;

      if (!smallest.equals(minMax.smallest)) {
        return false;
      }
      return largest.equals(minMax.largest);
    }

    @Override
    public String toString() {
      return "min: " + smallest + ", max: " + largest;
    }
  }

  @EpiTest(testDataFile = "search_for_min_max_in_array.tsv")
  public static MinMax findMinMax(List<Integer> A) {
    if (A.size() == 1) {
      return new MinMax(A.get(0), A.get(0));
    }

    for (int i = 0; i < A.size(); i += 2) {
      int s = i + 1;
      if (s == A.size()) {
        s = i / 2;
      }
      int li = i;
      if (A.get(i) > A.get(s)) {
        li = s;
      }
      if (i == 0 && i != li) {  // first comparison and i > s
        Collections.swap(A, i, s);
      } else if (i > 0) {
        Collections.swap(A, i / 2, li);
      }

    }
    int min = A.stream().limit(A.size() / 2 + 1).reduce(Integer.MAX_VALUE, (a, b) -> b < a ? b : a);
    int max = A.stream().skip(A.size() / 2).reduce(Integer.MIN_VALUE, (a, b) -> b > a ? b : a);

    return new MinMax(min, max);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchForMinMaxInArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class OnlineMedian {
  public static List<Double> onlineMedian(Iterator<Integer> sequence) {
    PriorityQueue<Integer> min = new PriorityQueue<>();
    PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());
    List<Double> list = new ArrayList<>();
    while (sequence.hasNext()) {
      Integer next = sequence.next();
      min.add(next);
      max.add(min.remove());
      if (max.size() - min.size() > 1) {
        min.add(max.remove());
      }
      double median = max.peek();
      if (min.size() == max.size()) {
         median =  (double) (min.peek() + max.peek()) / 2;
      }
      list.add(median);
    }

    return list;
  }
  @EpiTest(testDataFile = "online_median.tsv")
  public static List<Double> onlineMedianWrapper(List<Integer> sequence) {
    return onlineMedian(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "OnlineMedian.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

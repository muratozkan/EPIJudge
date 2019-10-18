package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.function.BiFunction;

public class EvaluateRpn {
  @EpiTest(testDataFile = "evaluate_rpn.tsv")
  public static int eval(String expression) {
    Map<String, BiFunction<Integer, Integer, Integer>> OPS = Map.of(
            "+", Integer::sum,
            "-", (b, a) -> a - b,
            "*", (b, a) -> a * b,
            "/", (b, a) -> a / b);
    Deque<Integer> stack = new ArrayDeque<>();
    for (String s : expression.split(",")) {
      if (s.length() == 1 && OPS.containsKey(s)) {
        stack.push(OPS.get(s).apply(stack.pop(),  stack.pop()));
      } else {
        stack.push(Integer.valueOf(s));
      }
    }
    return stack.pop();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvaluateRpn.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

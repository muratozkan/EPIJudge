package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class IntAsArrayMultiply {
  @EpiTest(testDataFile = "int_as_array_multiply.tsv")
  public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
    List<Integer> result = new ArrayList<>(Collections.nCopies(num1.size() + num2.size(), 0));
    int nm = (num1.get(0) * num2.get(0)) < 0 ? -1 : 1;
    num1.set(0, Math.abs(num1.get(0)));
    num2.set(0, Math.abs(num2.get(0)));
    int carry = 0;
    for (int i = num1.size() - 1; i >= 0; i--) {
      for (int j = num2.size() - 1; j >= 0; j--) {
        int t = result.get(i + j + 1) + num1.get(i) * num2.get(j) + carry;
        result.set(i + j + 1, t % 10);
        carry = t / 10;
      }
      result.set(i, carry);
      carry = 0;
    }
    int fnz = 0;
    while (result.get(fnz) == 0 && fnz < result.size() - 1){
      fnz ++;
    }
    result = result.subList(fnz, result.size());
    result.set(0, result.get(0) * nm);
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

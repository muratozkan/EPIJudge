package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class DirectoryPathNormalization {
  @EpiTest(testDataFile = "directory_path_normalization.tsv")

  public static String shortestEquivalentPath(String path) {
    Deque<String> stack = new ArrayDeque<>();
    String outPath = "";
    if (path.startsWith("/")) {
      outPath = "/";
    }
    for (String name : path.split("/")) {
      if ("..".equals(name)) {
        String prev = stack.peek();
        if (prev == null || "..".equals(prev)) {
          stack.push(name);
        } else {
          stack.pop();
        }
      } else if (!".".equals(name) && !name.isEmpty()) {
        stack.push(name);
      }
    }
    List<String> list = new ArrayList<>();
    stack.forEach(s -> list.add(0, s));
    return outPath + String.join("/", list);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DirectoryPathNormalization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

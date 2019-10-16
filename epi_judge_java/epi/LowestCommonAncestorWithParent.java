package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestorWithParent {

  private static int depth(BinaryTree<Integer> tree) {
    int d = 0;
    while(tree.parent != null) {
      tree = tree.parent;
      d ++;
    }
    return d;
  }

  public static BinaryTree<Integer> lca(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {
    BinaryTree<Integer> d, s;
    int r0 = depth(node0), r1 = depth(node1);
    if (r0 >= r1) {
      d = node0;
      s = node1;
    } else {
      d = node1;
      s = node0;
    }
    for (int i = 0; i < Math.abs(r0 - r1); i++) {
      d = d.parent;
    }

    while (d != s) {
      d = d.parent;
      s = s.parent;
    }

    return d;
  }
  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
                               Integer key0, Integer key1) throws Exception {
    BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTree<Integer> result = executor.run(() -> lca(node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestorWithParent.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.BitSet;
import java.util.List;
public class AbsentValueArray {

  private static final int NUM_BUCKETS = 1 << 16;
  private static final int BUCKET_CAPACITY = 1 << 16;

  public static int findMissingElement(Iterable<Integer> stream) {
    int[] buckets = new int[NUM_BUCKETS];
    stream.iterator().forEachRemaining(i -> buckets[i >>> 16]++);

    int bucket = -1;
    for (int i = 0; i < NUM_BUCKETS; i++) {
      if (buckets[i] < BUCKET_CAPACITY) {
        bucket = i;
        break;
      }
    }
    BitSet bitSet = new BitSet(BUCKET_CAPACITY);
    for (int i : stream) {
      if (i >>> 16 == bucket) {
        bitSet.set(i & 0xFFFF);
      }
    }

    for (int i = 0; i < BUCKET_CAPACITY; i++) {
      if (!bitSet.get(i)) {
        return (bucket << 16) | i;
      }
    }
    return -1;
  }
  @EpiTest(testDataFile = "absent_value_array.tsv")
  public static void findMissingElementWrapper(List<Integer> stream)
      throws Exception {
    try {
      int res = findMissingElement(stream);
      if (stream.stream().filter(a -> a.equals(res)).findFirst().isPresent()) {
        throw new TestFailure(String.valueOf(res) + " appears in stream");
      }
    } catch (IllegalArgumentException e) {
      throw new TestFailure("Unexpected no missing element exception");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "AbsentValueArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

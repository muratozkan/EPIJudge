package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseBits {

  static final int precomputed[] = { 0x0, 0x8, 0x4, 0xC, 0x2, 0xA, 0x6, 0xE, 0x1, 0x9, 0x5, 0xD, 0x3, 0xB, 0x7, 0xF };

  @EpiTest(testDataFile = "reverse_bits.tsv")
  public static long reverseBits(long x) {
    long res = 0L;

    final int mask = 0xF;
    final int ms = 4;

    for (int i = 0; i < 16; i++) {
      res <<= ms;
      res |= precomputed[(int) x & mask];
      x >>>= ms;
    }
    System.out.println(Long.toBinaryString(res));
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

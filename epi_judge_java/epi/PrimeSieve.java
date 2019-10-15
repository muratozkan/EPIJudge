package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class PrimeSieve {

  private static List<Boolean> primeSieve(int n) {
    List<Boolean> isPrime = new ArrayList<>(Collections.nCopies(n + 1, true));
    isPrime.set(0, false);
    isPrime.set(1, false);
    for (int p = 2; p <= n; p++) {
      if (isPrime.get(p)) {
        for (int m = 2; m <= n / p; m++) {
          isPrime.set(p * m, false);    // mark multiples of p as non-prime
        }
      }
    }
    return isPrime;
  }

  @EpiTest(testDataFile = "prime_sieve.tsv")
  // Given n, return all primes up to and including n.
  public static List<Integer> generatePrimes(int n) {
    List<Integer> primes = new ArrayList<>();
    List<Boolean> isPrime = primeSieve(n);
    for (int i = 0; i <= n; i++) {
      if (isPrime.get(i)) {
        primes.add(i);
      }
    }
    return primes;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

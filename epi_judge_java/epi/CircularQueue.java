package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.Arrays;
import java.util.List;
public class CircularQueue {

  public static class Queue {
    private Integer[] array;
    private int start = 0, end = 0, size = 0;
    private int capacity;
    public Queue(int capacity) {
      this.capacity = capacity;
      array = new Integer[this.capacity];
    }

    private void grow() {
      array = Arrays.copyOf(array, capacity * 2);
      if (start == end) {
        for (int i = 0; i < end; i++) {
          array[capacity + i] = array[i];
          array[i] = null;
        }
        end = capacity + end;
      }
      capacity *= 2;
    }

    public void enqueue(Integer x) {
      if (size + 1 > array.length) {
        grow();
      }

      array[end] = x;
      size ++;
      end = (end + 1) % array.length;
    }

    public Integer dequeue() {
      Integer i = array[start];
      array[start] = null;
      size --;
      start = (start + 1) % array.length;
      return i;
    }

    public int size() {
      return size;
    }
    @Override
    public String toString() {
      return Arrays.toString(array);
    }
  }
  @EpiUserType(ctorParams = {String.class, int.class})
  public static class QueueOp {
    public String op;
    public int arg;

    public QueueOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }

    @Override
    public String toString() {
      return op;
    }
  }

  @EpiTest(testDataFile = "circular_queue.tsv")
  public static void queueTester(List<QueueOp> ops) throws TestFailure {
    Queue q = new Queue(1);
    int opIdx = 0;
    for (QueueOp op : ops) {
      switch (op.op) {
      case "Queue":
        q = new Queue(op.arg);
        break;
      case "enqueue":
        q.enqueue(op.arg);
        break;
      case "dequeue":
        int result = q.dequeue();
        if (result != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, result);
        }
        break;
      case "size":
        int s = q.size();
        if (s != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, s);
        }
        break;
      }
      opIdx++;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CircularQueue.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

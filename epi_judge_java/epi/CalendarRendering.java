package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CalendarRendering {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Event {
    public int start, finish;

    public Event(int start, int finish) {
      this.start = start;
      this.finish = finish;
    }

    @Override
    public String toString() {
      return "[" + start + ", " + finish + "]";
    }
  }

  private static class Endpoint {
    public int time;
    public boolean isStart;

    Endpoint(int time, boolean isStart) {
      this.time = time;
      this.isStart = isStart;
    }

    @Override
    public String toString() {
      return "[" + time + ", " + (isStart ? "S" : "E") + "]";
    }
  }

  @EpiTest(testDataFile = "calendar_rendering.tsv")
  public static int findMaxSimultaneousEvents(List<Event> A) {
   List<Endpoint> endpoints = A.stream()
           .flatMap(e -> Stream.of(new Endpoint(e.start, true), new Endpoint(e.finish, false)))
           .sorted((e1, e2) -> {
             if (e1.time != e2.time) {
               return Integer.compare(e1.time, e2.time);
             }

             return e1.isStart ? (e2.isStart ? 0 : -1) : (e2.isStart ? 1 : 0);
           })
           .collect(Collectors.toList());

   int max = 0, current = 0;
   for (Endpoint e : endpoints) {
     if (e.isStart) {
       current ++;
       max = Math.max(current, max);
     } else {
       current --;
     }
   }
   return max;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CalendarRendering.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

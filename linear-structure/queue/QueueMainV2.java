package queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QueueMainV2 {

  public static void main(String[] args) {
    System.out.println(getJosephusPermutation(5, 2));
    System.out.println(getJosephusPermutation(7, 3));

    Queue<Integer> queue = new ArrayDeque<>();
    queue.iterator();

  }

  private static List<Integer> getJosephusPermutation(int n, int k) {
//    Queue<Integer> queue = new LinkedList<>();
//    MyQueue<Integer> queue = new MyQueue<>();
    MyArrQueue<Integer> queue = new MyArrQueue<>();
    for (int i = 1; i <= n; i++) {
      queue.add(i);
    }

    List<Integer> result = new ArrayList<>();

    while (!queue.isEmpty()) {
      for (int i = 0; i < k - 1; i++) {
        queue.add(queue.poll());
      }
      result.add(queue.poll());
    }

    return result;
  }
}

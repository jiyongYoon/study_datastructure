package queue;

import java.util.LinkedList;
import java.util.Queue;

public class QueueMainV1 {

  public static void main(String[] args){
    System.out.println(findLastCard(4)); // 4
    System.out.println(findLastCard(7)); // 6
    System.out.println(findLastCard(9)); // 2
  }

  private static int findLastCard(int n) {
    //    Queue<Integer> queue = new LinkedList<>();
//    MyQueue<Integer> queue = new MyQueue<>();
    MyArrQueue<Integer> queue = new MyArrQueue<>();
    for (int i = 1; i <= n; i++) {
      queue.add(i);
    }

    while (queue.size() != 1) {
      System.out.println(queue);
      queue.poll();
      if (queue.size() > 1) {
        Integer poll = queue.poll();
        queue.add(poll);
      }
    }

    return queue.poll();
  }
}

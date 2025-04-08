package queue;

import java.util.List;
import linked_list.MyLinkedList;

// MyLinkedList가 List 인터페이스를 구현했다면, Queue도 List 인터페이스를 상속한 인터페이스가 되면 되겠다.
public class MyQueue<T> extends MyLinkedList<T> {

  public MyQueue() {
    super();
  }

  public MyQueue(List<T> elements) {
    super(elements);
  }

  public void offer(T data) {
    this.add(data);
  }

  public T poll() {
    if (this.size == 0) {
      return null;
    }

    T data = this.get(0);
    this.size--;
    this.head = this.getNode(1);
    return data;
  }

  public T peek() {
    if (this.size == 0) {
      return null;
    }

    return this.get(0);
  }

  public boolean isEmpty() {
    return this.size == 0;
  }
}

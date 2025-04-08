package queue;

// 배열을 이용한 원형 큐
public class MyArrQueue<T> {
  private final int DEFAULT_SIZE = 3;
  private T[] arr;
  private int front; // 첫 데이터 위치 앞
  private int rear;  // 마지막 데이터 위치
  private int capacity; // 실제 사용 가능한 크기 (arr.length - 1)

  @SuppressWarnings("unchecked")
  public MyArrQueue() {
    this.capacity = DEFAULT_SIZE;
    this.arr = (T[]) new Object[DEFAULT_SIZE + 1]; // 맨 앞 한 칸은 비운다
    this.front = 0;
    this.rear = 0;
  }

  @SuppressWarnings("unchecked")
  public MyArrQueue(int size) {
    if (size < 1) {
      throw new IllegalArgumentException("Queue size must be at least 1");
    }
    this.capacity = size;
    this.arr = (T[]) new Object[size + 1]; // 원형 큐를 위해 +1
    this.front = 0;
    this.rear = 0;
  }

  public int size() {
    if (isEmpty()) {
      return 0;
    }
    return (rear - front + arr.length) % arr.length;
  }

  public boolean isEmpty() {
    return front == rear;
  }

  public boolean isFull() {
    return (rear + 1) % arr.length == front;
  }

  public T poll() {
    if (isEmpty()) {
      return null;
    }

    front = (front + 1) % arr.length;
    T item = arr[front];
    arr[front] = null; // 메모리 누수 방지
    return item;
  }

  public T peek() {
    if (isEmpty()) {
      return null;
    }

    return arr[(front + 1) % arr.length];
  }

  @Override
  public String toString() {
    if (isEmpty()) {
      return "[]";
    }
    StringBuilder sb = new StringBuilder("[");
    int index = (front + 1) % arr.length;
    while (index != rear) {
      sb.append(arr[index]).append(", ");
      index = (index + 1) % arr.length;
    }
    sb.append(arr[rear]);
    sb.append("]");
    return sb.toString();
  }

  @SuppressWarnings("unchecked")
  public void add(T data) {
    if (isFull()) {
      // 배열 확장
      int newCapacity = capacity + DEFAULT_SIZE;
      T[] newArr = (T[]) new Object[newCapacity + 1];
      int count = 0;
      int index = (front + 1) % arr.length;

      // 기존 요소 복사
      while (index != rear) {
        newArr[count++] = arr[index];
        index = (index + 1) % arr.length;
      }
      newArr[count++] = arr[rear];

      // 새 요소 추가
      newArr[count] = data;

      // 포인터 재설정
      front = newCapacity; // front는 배열 끝을 가리킴
      rear = count;
      arr = newArr;
      capacity = newCapacity;
    } else {
      rear = (rear + 1) % arr.length;
      arr[rear] = data;
    }
  }

  public void printStatus() {
    System.out.println(String.format("-- queue state: queue: %s, size: %d, front: %d, rear: %d, capacity: %d",
        this.toString(), this.size(), this.front, this.rear, this.capacity));
  }
}
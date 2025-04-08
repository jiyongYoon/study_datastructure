package queue;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JavaMyArrQueueTest {
  private MyArrQueue<Integer> queue;

  @BeforeEach
  void setUp() {
    queue = new MyArrQueue<>();
  }

  @Test
  void testDefaultConstructor() {
    assertThat(queue.isEmpty()).isTrue();
    assertThat(queue.size()).isEqualTo(0);
    assertThat(queue.toString()).isEqualTo("[]");
  }

  @Test
  void testCustomSizeConstructor() {
    MyArrQueue<Integer> customQueue = new MyArrQueue<>(5);

    assertThat(customQueue.isEmpty()).isTrue();
    assertThat(customQueue.size()).isEqualTo(0);
    assertThat(customQueue.toString()).isEqualTo("[]");
  }

  @Test
  void testInvalidSizeConstructor() {
    assertThatThrownBy(() -> new MyArrQueue<>(0))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Queue size must be at least 1");
  }

  @Test
  void testAdd() {
    queue.add(1);
    queue.add(2);
    queue.add(3);

    assertThat(queue.size()).isEqualTo(3);
    assertThat(queue.toString()).isEqualTo("[1, 2, 3]");
    assertThat(queue.isEmpty()).isFalse();
    assertThat(queue.isFull()).isTrue();
  }

  @Test
  void testAddWithExpansion() {
    queue.add(1);
    queue.add(2);
    queue.add(3);
    queue.add(4); // 확장 발생

    assertThat(queue.size()).isEqualTo(4);
    assertThat(queue.toString()).isEqualTo("[1, 2, 3, 4]");
    assertThat(queue.isFull()).isFalse();
  }

  @Test
  void testPoll() {
    queue.add(1);
    queue.add(2);
    queue.add(3);

    assertThat(queue.poll()).isEqualTo(1);
    assertThat(queue.size()).isEqualTo(2);
    assertThat(queue.toString()).isEqualTo("[2, 3]");

    assertThat(queue.poll()).isEqualTo(2);
    assertThat(queue.poll()).isEqualTo(3);
    assertThat(queue.poll()).isNull();
    assertThat(queue.isEmpty()).isTrue();
  }

  @Test
  void testPeek() {
    queue.add(1);
    queue.add(2);

    assertThat(queue.peek()).isEqualTo(1);
    assertThat(queue.size()).isEqualTo(2); // 크기 변하지 않음
    assertThat(queue.toString()).isEqualTo("[1, 2]");
  }

  @Test
  void testPeekAndPollEmptyQueue() {
    assertThat(queue.peek()).isNull();
    assertThat(queue.poll()).isNull();
    assertThat(queue.isEmpty()).isTrue();
  }

  @Test
  void testCircularBehavior() {
    queue.add(1);
    queue.add(2);
    queue.poll(); // front 이동
    queue.add(3); // rear가 원형으로 이동

    assertThat(queue.size()).isEqualTo(2);
    assertThat(queue.toString()).isEqualTo("[2, 3]");
    assertThat(queue.peek()).isEqualTo(2);
  }

  @Test
  void testFullQueueExpansion() {
    MyArrQueue<Integer> smallQueue = new MyArrQueue<>(2);
    smallQueue.add(1);
    smallQueue.add(2); // 가득 참
    smallQueue.add(3); // 확장

    assertThat(smallQueue.size()).isEqualTo(3);
    assertThat(smallQueue.toString()).isEqualTo("[1, 2, 3]");
    assertThat(smallQueue.poll()).isEqualTo(1);
    assertThat(smallQueue.size()).isEqualTo(2);
  }

  @Test
  void testQueueOperationsSequence() {
    queue.add(1);
    queue.add(2);
    assertThat(queue.poll()).isEqualTo(1);
    queue.add(3);
    queue.add(4); // 확장 발생

    assertThat(queue.size()).isEqualTo(3);
    assertThat(queue.peek()).isEqualTo(2);
    assertThat(queue.poll()).isEqualTo(2);
    assertThat(queue.poll()).isEqualTo(3);
    assertThat(queue.poll()).isEqualTo(4);
    assertThat(queue.isEmpty()).isTrue();
  }

  @Test
  void testToString() {
    queue.add(1);
    assertThat(queue.toString()).isEqualTo("[1]");

    queue.add(2);
    assertThat(queue.toString()).isEqualTo("[1, 2]");

    queue.poll();
    queue.poll();
    assertThat(queue.toString()).isEqualTo("[]");
  }
}

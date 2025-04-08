package queue;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JavaMyQueueTest {
  private MyQueue<Integer> queue;

  @BeforeEach
  void setUp() {
    queue = new MyQueue<>();
  }

  @Test
  void testDefaultConstructor() {
    assertThat(queue.isEmpty()).isTrue();
    assertThat(queue.size()).isEqualTo(0);
    assertThat(queue.toString()).isEqualTo("[]");
  }

  @Test
  void testListConstructor() {
    MyQueue<Integer> queueWithList = new MyQueue<>(Arrays.asList(1, 2, 3));

    assertThat(queueWithList.size()).isEqualTo(3);
    assertThat(queueWithList.peek()).isEqualTo(1);
    assertThat(queueWithList.toString()).isEqualTo("[1, 2, 3]");
  }

  @Test
  void testOffer() {
    queue.offer(1);
    queue.offer(2);
    queue.offer(3);

    assertThat(queue.size()).isEqualTo(3);
    assertThat(queue.peek()).isEqualTo(1);
    assertThat(queue.toString()).isEqualTo("[1, 2, 3]");
  }

  @Test
  void testPoll() {
    queue.offer(1);
    queue.offer(2);
    queue.offer(3);

    assertThat(queue.poll()).isEqualTo(1);
    assertThat(queue.size()).isEqualTo(2);
    assertThat(queue.peek()).isEqualTo(2);
    assertThat(queue.toString()).isEqualTo("[2, 3]");

    queue.poll();
    queue.poll();
    assertThat(queue.poll()).isNull();
    assertThat(queue.isEmpty()).isTrue();
  }

  @Test
  void testPeek() {
    queue.offer(1);
    queue.offer(2);

    assertThat(queue.peek()).isEqualTo(1);
    assertThat(queue.size()).isEqualTo(2);  // 크기 변하지 않음
    assertThat(queue.toString()).isEqualTo("[1, 2]");
  }

  @Test
  void testPeekAndPollEmptyQueue() {
    assertThat(queue.peek()).isNull();
    assertThat(queue.poll()).isNull();
    assertThat(queue.isEmpty()).isTrue();
  }

  @Test
  void testQueueOperationsSequence() {
    queue.offer(1);
    queue.offer(2);
    assertThat(queue.poll()).isEqualTo(1);
    queue.offer(3);

    assertThat(queue.size()).isEqualTo(2);
    assertThat(queue.peek()).isEqualTo(2);
    assertThat(queue.poll()).isEqualTo(2);
    assertThat(queue.poll()).isEqualTo(3);
    assertThat(queue.isEmpty()).isTrue();
  }

  @Test
  void testToString() {
    queue.offer(1);
    assertThat(queue.toString()).isEqualTo("[1]");

    queue.offer(2);
    assertThat(queue.toString()).isEqualTo("[1, 2]");

    queue.poll();
    queue.poll();
    assertThat(queue.toString()).isEqualTo("[]");
  }
}

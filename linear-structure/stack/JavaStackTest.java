package stack;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JavaStackTest {
  private MyStack<Integer> myStack;

  @BeforeEach
  void setUp() {
    myStack = new MyStack<>();
  }

  @Test
  void testDefaultConstructor() {
    MyStack<String> stack = new MyStack<>();
    assertThat(stack.size()).isEqualTo(0);
    assertThat(stack.isEmpty()).isTrue();
    assertThat(stack.toString()).isEqualTo("[]");
  }

  @Test
  void testPush() {
    myStack.push(1);
    myStack.push(2);
    myStack.push(3);

    assertThat(myStack.size()).isEqualTo(3);
    assertThat(myStack.peek()).isEqualTo(3);
    assertThat(myStack.toString()).isEqualTo("[1, 2, 3]");
  }

  @Test
  void testPushBeyondDefaultSize() {
    // Default size is 100, push 101 elements
    for (int i = 0; i < 101; i++) {
      myStack.push(i);
    }

    assertThat(myStack.size()).isEqualTo(101);
    assertThat(myStack.peek()).isEqualTo(100);
  }

  @Test
  void testPeek() {
    myStack.push(1);
    myStack.push(2);

    assertThat(myStack.peek()).isEqualTo(2);
    assertThat(myStack.size()).isEqualTo(2); // Size unchanged
  }

  @Test
  void testPeekEmptyStack() {
    assertThatThrownBy(() -> myStack.peek())
        .isInstanceOf(MyStack.MyStackEmptyStackException.class)
        .hasMessage("Stack이 비어있습니다.");
  }

  @Test
  void testPop() {
    myStack.push(1);
    myStack.push(2);
    myStack.push(3);

    assertThat(myStack.pop()).isEqualTo(3);
    assertThat(myStack.size()).isEqualTo(2);
    assertThat(myStack.peek()).isEqualTo(2);
    assertThat(myStack.toString()).isEqualTo("[1, 2]");
  }

  @Test
  void testPopEmptyStack() {
    assertThatThrownBy(() -> myStack.pop())
        .isInstanceOf(MyStack.MyStackEmptyStackException.class)
        .hasMessage("Stack이 비어있습니다.");
  }

  @Test
  void testSize() {
    assertThat(myStack.size()).isEqualTo(0);
    myStack.push(1);
    assertThat(myStack.size()).isEqualTo(1);
    myStack.pop();
    assertThat(myStack.size()).isEqualTo(0);
  }

  @Test
  void testIsEmpty() {
    assertThat(myStack.isEmpty()).isTrue();
    myStack.push(1);
    assertThat(myStack.isEmpty()).isFalse();
    myStack.pop();
    assertThat(myStack.isEmpty()).isTrue();
  }

  @Test
  void testToString() {
    myStack.push(1);
    assertThat(myStack.toString()).isEqualTo("[1]");

    myStack.push(2);
    assertThat(myStack.toString()).isEqualTo("[1, 2]");

    myStack.pop();
    myStack.pop();
    assertThat(myStack.toString()).isEqualTo("[]");
  }

  @Test
  void testStackOperationsSequence() {
    myStack.push(1);
    myStack.push(2);
    assertThat(myStack.pop()).isEqualTo(2);
    myStack.push(3);
    myStack.push(4);

    assertThat(myStack.size()).isEqualTo(3);
    assertThat(myStack.pop()).isEqualTo(4);
    assertThat(myStack.pop()).isEqualTo(3);
    assertThat(myStack.pop()).isEqualTo(1);
    assertThat(myStack.isEmpty()).isTrue();
  }
}

package linked_list;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JavaLinkedListTest {
  private MyLinkedList<Integer> myLinkedList;

  @BeforeEach
  void setUp() {
    myLinkedList = new MyLinkedList<>();
    myLinkedList.add(1);
    myLinkedList.add(2);
    myLinkedList.add(3);
  }

  @Test
  void testDefaultConstructor() {
    MyLinkedList<String> list = new MyLinkedList<>();
    assertThat(list.size()).isEqualTo(0);
    assertThat(list.toString()).isEqualTo("[]");
  }

  @Test
  void testListConstructor() {
    List<Integer> input = Arrays.asList(1, 2, 3);
    MyLinkedList<Integer> list = new MyLinkedList<>(input);

    assertThat(list.size()).isEqualTo(3);
    assertThat(list.get(0)).isEqualTo(1);
    assertThat(list.get(1)).isEqualTo(2);
    assertThat(list.get(2)).isEqualTo(3);
  }

  @Test
  void testAdd() {
    MyLinkedList<Integer> list = new MyLinkedList<>();
    list.add(5);
    list.add(10);

    assertThat(list.size()).isEqualTo(2);
    assertThat(list.get(0)).isEqualTo(5);
    assertThat(list.get(1)).isEqualTo(10);
    assertThat(list.toString()).isEqualTo("[5, 10]");
  }

  @Test
  void testAddWithIndex() {
    myLinkedList.add(1, 99);

    assertThat(myLinkedList.size()).isEqualTo(4);
    assertThat(myLinkedList.get(0)).isEqualTo(1);
    assertThat(myLinkedList.get(1)).isEqualTo(99);
    assertThat(myLinkedList.get(2)).isEqualTo(2);
    assertThat(myLinkedList.get(3)).isEqualTo(3);
  }

  @Test
  void testAddWithIndexInvalid() {
    assertThatThrownBy(() -> myLinkedList.add(-1, 5))
        .isInstanceOf(MyLinkedList.MyLinkedListIndexOutOfBoundsException.class)
        .hasMessage("인덱스가 범위를 벗어났습니다.");

    assertThatThrownBy(() -> myLinkedList.add(4, 5))
        .isInstanceOf(MyLinkedList.MyLinkedListIndexOutOfBoundsException.class)
        .hasMessage("인덱스가 범위를 벗어났습니다.");
  }

  @Test
  void testGet() {
    assertThat(myLinkedList.get(0)).isEqualTo(1);
    assertThat(myLinkedList.get(1)).isEqualTo(2);
    assertThat(myLinkedList.get(2)).isEqualTo(3);
  }

  @Test
  void testGetInvalidIndex() {
    assertThatThrownBy(() -> myLinkedList.get(-1))
        .isInstanceOf(MyLinkedList.MyLinkedListIndexOutOfBoundsException.class)
        .hasMessage("인덱스가 범위를 벗어났습니다.");

    assertThatThrownBy(() -> myLinkedList.get(3))
        .isInstanceOf(MyLinkedList.MyLinkedListIndexOutOfBoundsException.class)
        .hasMessage("인덱스가 범위를 벗어났습니다.");
  }

  @Test
  void testContains() {
    assertThat(myLinkedList.contains(2)).isTrue();
    assertThat(myLinkedList.contains(5)).isFalse();
    assertThat(new MyLinkedList<Integer>().contains(1)).isFalse();
  }

  @Test
  void testSubList() {
    MyLinkedList<Integer> subList = myLinkedList.subList(1, 3);

    assertThat(subList.size()).isEqualTo(2);
    assertThat(subList.get(0)).isEqualTo(2);
    assertThat(subList.get(1)).isEqualTo(3);
  }

  @Test
  void testSubListInvalidRange() {
    assertThatThrownBy(() -> myLinkedList.subList(-1, 2))
        .isInstanceOf(MyLinkedList.MyLinkedListIndexOutOfBoundsException.class)
        .hasMessage("인덱스가 범위를 벗어났습니다.");

    assertThatThrownBy(() -> myLinkedList.subList(0, 4))
        .isInstanceOf(MyLinkedList.MyLinkedListIndexOutOfBoundsException.class)
        .hasMessage("인덱스가 범위를 벗어났습니다.");

    assertThatThrownBy(() -> myLinkedList.subList(2, 1))
        .isInstanceOf(MyLinkedList.MyLinkedListIllegalArgumentException.class)
        .hasMessageContaining("fromIndex(2) > toIndex(1)");
  }

  @Test
  void testToString() {
    assertThat(myLinkedList.toString()).isEqualTo("[1, 2, 3]");

    MyLinkedList<Integer> single = new MyLinkedList<>();
    single.add(1);
    assertThat(single.toString()).isEqualTo("[1]");

    MyLinkedList<Integer> empty = new MyLinkedList<>();
    assertThat(empty.toString()).isEqualTo("[]");
  }

  @Test
  void testIterator() {
    Iterator<Integer> iterator = myLinkedList.iterator();

    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.next()).isEqualTo(1);
    assertThat(iterator.next()).isEqualTo(2);
    assertThat(iterator.next()).isEqualTo(3);
    assertThat(iterator.hasNext()).isFalse();

    assertThatThrownBy(iterator::next)
        .isInstanceOf(java.util.NoSuchElementException.class)
        .hasMessage("No more elements");
  }
}

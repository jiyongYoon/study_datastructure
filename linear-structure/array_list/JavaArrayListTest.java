package array_list;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JavaArrayListTest {

  private MyArrayList<Integer> myArrayList;

  @BeforeEach
  void setUp() {
    myArrayList = new MyArrayList<>();
    myArrayList.add(1);
    myArrayList.add(2);
    myArrayList.add(3);
  }

  @Test
  void testDefaultConstructor() {
    MyArrayList<String> list = new MyArrayList<>();
    assertThat(list.size()).isEqualTo(0);
    assertThat(list.isEmpty()).isTrue();
  }

  @Test
  void testListConstructor() {
    List<Integer> input = Arrays.asList(1, 2, 3);
    MyArrayList<Integer> list = new MyArrayList<>(input);

    assertThat(list.size()).isEqualTo(3);
    assertThat(list.get(0)).isEqualTo(1);
    assertThat(list.get(1)).isEqualTo(2);
    assertThat(list.get(2)).isEqualTo(3);
  }

  @Test
  void testAdd() {
    MyArrayList<Integer> list = new MyArrayList<>();
    list.add(5);
    list.add(10);

    assertThat(list.size()).isEqualTo(2);
    assertThat(list.get(0)).isEqualTo(5);
    assertThat(list.get(1)).isEqualTo(10);
  }

  @Test
  void testAddWithIndex() {
    myArrayList.add(1, 99);

    assertThat(myArrayList.size()).isEqualTo(4);
    assertThat(myArrayList.get(0)).isEqualTo(1);
    assertThat(myArrayList.get(1)).isEqualTo(99);
    assertThat(myArrayList.get(2)).isEqualTo(2);
    assertThat(myArrayList.get(3)).isEqualTo(3);
  }

  @Test
  void testAddAll() {
    List<Integer> toAdd = Arrays.asList(4, 5, 6);
    myArrayList.addAll(toAdd);

    assertThat(myArrayList.size()).isEqualTo(6);
    assertThat(myArrayList.get(3)).isEqualTo(4);
    assertThat(myArrayList.get(4)).isEqualTo(5);
    assertThat(myArrayList.get(5)).isEqualTo(6);
  }

  @Test
  void testIndexOf() {
    myArrayList.add(2); // [1, 2, 3, 2]

    assertThat(myArrayList.indexOf(2)).isEqualTo(1);
    assertThat(myArrayList.indexOf(5)).isEqualTo(-1);
  }

  @Test
  void testLastIndexOf() {
    myArrayList.add(2); // [1, 2, 3, 2]

    assertThat(myArrayList.lastIndexOf(2)).isEqualTo(3);
    assertThat(myArrayList.lastIndexOf(5)).isEqualTo(-1);
  }

  @Test
  void testGet() {
    assertThat(myArrayList.get(0)).isEqualTo(1);
    assertThat(myArrayList.get(2)).isEqualTo(3);
  }

  @Test
  void testContains() {
    assertThat(myArrayList.contains(2)).isTrue();
    assertThat(myArrayList.contains(5)).isFalse();
  }

  @Test
  void testIsEmpty() {
    MyArrayList<Integer> emptyList = new MyArrayList<>();
    assertThat(emptyList.isEmpty()).isTrue();
    assertThat(myArrayList.isEmpty()).isFalse();
  }

  @Test
  void testSubList() {
    MyArrayList<Integer> subList = myArrayList.subList(1, 3);

    assertThat(subList.size()).isEqualTo(2);
    assertThat(subList.get(0)).isEqualTo(2);
    assertThat(subList.get(1)).isEqualTo(3);
  }

  @Test
  void testSubListInvalidRange() {
    assertThatThrownBy(() -> myArrayList.subList(-1, 2))
        .isInstanceOf(MyArrayList.MyArrayListIndexOutOfBoundsException.class)
        .hasMessage("인덱스가 범위를 벗어났습니다.");

    assertThatThrownBy(() -> myArrayList.subList(0, 4))
        .isInstanceOf(MyArrayList.MyArrayListIndexOutOfBoundsException.class)
        .hasMessage("인덱스가 범위를 벗어났습니다.");

    assertThatThrownBy(() -> myArrayList.subList(2, 1))
        .isInstanceOf(MyArrayList.MyArrayListIllegalArgumentException.class)
        .hasMessageContaining("fromIndex(2) > toIndex(1)");
  }

  @Test
  void testToString() {
    assertThat(myArrayList.toString()).isEqualTo("[1, 2, 3]");

    MyArrayList<Integer> single = new MyArrayList<>();
    single.add(1);
    assertThat(single.toString()).isEqualTo("[1]");
  }

  @Test
  void testIterator() {
    Iterator<Integer> iterator = myArrayList.iterator();

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

package array;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JavaArrayTest {
  private MyArray myArray;

  @BeforeEach
  void setUp() {
    int[] initialValues = {1, 2, 3, 4, 5};
    myArray = new MyArray(initialValues);
  }

  @Test
  void testConstructorWithArray() {
    int[] input = {1, 2, 3};
    MyArray array = new MyArray(input);

    assertThat(array.length).isEqualTo(3);
    assertThat(array.arr).containsExactly(1, 2, 3);
    // 입력 배열이 수정되어도 원본이 유지되는지 확인
    input[0] = 99;
    assertThat(array.arr[0]).isEqualTo(1);
  }

  @Test
  void testConstructorWithSize() {
    MyArray array = new MyArray(3);

    assertThat(array.length).isEqualTo(3);
    assertThat(array.arr).hasSize(3);
    assertThat(array.arr).containsOnly(0); // 기본값 0으로 채워짐
  }

  @Test
  void testGetValidIndex() {
    assertThat(myArray.get(0)).isEqualTo(1);
    assertThat(myArray.get(2)).isEqualTo(3);
    assertThat(myArray.get(4)).isEqualTo(5);
  }

  @Test
  void testGetInvalidIndex() {
    assertThatThrownBy(() -> myArray.get(-1))
        .isInstanceOf(MyArray.MyArrayIndexOutOfBoundsException.class)
        .hasMessage("인덱스가 벗어났습니다.");

    assertThatThrownBy(() -> myArray.get(5))
        .isInstanceOf(MyArray.MyArrayIndexOutOfBoundsException.class)
        .hasMessage("인덱스가 벗어났습니다.");
  }

  @Test
  void testAssignValidIndex() {
    myArray.assign(1, 10);
    assertThat(myArray.get(1)).isEqualTo(10);

    myArray.assign(0, 99);
    assertThat(myArray.get(0)).isEqualTo(99);
  }

  @Test
  void testAssignInvalidIndex() {
    assertThatThrownBy(() -> myArray.assign(-1, 10))
        .isInstanceOf(MyArray.MyArrayIndexOutOfBoundsException.class)
        .hasMessage("인덱스가 벗어났습니다.");

    assertThatThrownBy(() -> myArray.assign(5, 10))
        .isInstanceOf(MyArray.MyArrayIndexOutOfBoundsException.class)
        .hasMessage("인덱스가 벗어났습니다.");
  }

  @Test
  void testToString() {
    assertThat(myArray.toString()).isEqualTo("[1, 2, 3, 4, 5]");

    // 크기가 1인 경우 테스트
    MyArray singleElement = new MyArray(new int[] {7});
    assertThat(singleElement.toString()).isEqualTo("[7]");

    // 빈 배열 테스트
    MyArray empty = new MyArray(0);
    assertThat(empty.toString()).isEqualTo("[]");
  }
}

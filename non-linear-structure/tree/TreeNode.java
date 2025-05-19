package tree;

public class TreeNode<T> {

  T value;
  TreeNode<T> left;
  TreeNode<T> right;

  public TreeNode(T value) {
    this(value, null, null);
  }

  // TODO 생성자 오버로딩 - 자식노드가 있는 노드 초기화
  public TreeNode(T value, TreeNode<T> left, TreeNode<T> right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  // TODO 컨벤션이 있는 이유와 누가 봐도 알 수 있는 작업인지, setLeft(), setRight()
  public void linkLeft(TreeNode<T> left) {
    this.left = left;
  }

  public void linkRight(TreeNode<T> right) {
    this.right = right;
  }

  // TODO 비트연산으로 left, right 연산시 false가 나오게 하는 케이스 확인 -> 적용해보기
  public boolean isLeaf() {
    return this.left == null && this.right == null;
  }

  // 접근 제어자와 캡슐화 정도에 대한 고민 필요
  public boolean hasOnlyLeft() {
    return this.left != null && this.right == null;
  }

  public boolean hasOnlyRight() {
    return this.left == null && this.right != null;
  }

  public boolean hasLeftAndRight() {
    return this.left != null && this.right != null;
  }
}

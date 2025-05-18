package tree;

public class TreeNode<T> {

  T value;
  TreeNode<T> left;
  TreeNode<T> right;

  public TreeNode(T value) {
    this.value = value;
  }

  public void linkLeft(TreeNode<T> left) {
    this.left = left;
  }

  public void linkRight(TreeNode<T> right) {
    this.right = right;
  }

  public void deleteLeft() {
    this.left = null;
  }

  public void deleteRight() {
    this.right = null;
  }

  public boolean isLeaf() {
    return this.left == null && this.right == null;
  }

  public boolean isOnlyLeft() {
    return this.left != null && this.right == null;
  }

  public boolean isOnlyRight() {
    return this.left == null && this.right != null;
  }

  public boolean isLeftAndRight() {
    return this.left != null && this.right != null;
  }
}

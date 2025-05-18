package tree;


public class MyBinarySearchTree<T extends Comparable<T>> {

  private TreeNode<T> root;

  public MyBinarySearchTree() {
    this.root = null;
  }

  public MyBinarySearchTree(TreeNode<T> root) {
    this.root = root;
  }

  public boolean isExists(TreeNode<T> value) {
    return isExistsRecursive(this.root, value.value);
  }

  private boolean isExistsRecursive(TreeNode<T> current, T value) {
    if (current == null) {
      return false;
    }
    int compare = current.value.compareTo(value);
    if (compare == 0) {
      return true;
    } else if (compare > 0) {
      return isExistsRecursive(current.left, value);
    } else {
      return isExistsRecursive(current.right, value);
    }
  }

  public void insertNode(TreeNode<T> insertNode) {
    if (this.root == null) {
      this.root = insertNode;
      return;
    }

    insertNodeRecursive(root, insertNode);
  }

  private void insertNodeRecursive(TreeNode<T> current, TreeNode<T> insert) {
    int compare = current.value.compareTo(insert.value);
    if (compare == 0) {
      throw new MyTreeDuplicateException();
    } else {
      if (current.isLeaf()) {
        if (compare > 0) {
          current.linkLeft(insert);
        } else {
          current.linkRight(insert);
        }
      } else {
        if (compare > 0) {
          if (current.left == null) {
            current.left = insert;
          } else {
            insertNodeRecursive(current.left, insert);
          }
        } else {
          if (current.right == null) {
            current.right = insert;
          } else {
            insertNodeRecursive(current.right, insert);
          }
        }
      }
    }
  }

  public void deleteNode(TreeNode<T> deleteNode) {
    if (this.root == null) {
      return;
    }

    deleteNodeRecursive(null, root, false, deleteNode);
  }

  private void deleteNodeRecursive(TreeNode<T> parent, TreeNode<T> current, boolean isCurrentLeft, TreeNode<T> deleteNode) {
    int compareTo = current.value.compareTo(deleteNode.value);
    // current가 삭제 대상일 때
    if (compareTo == 0) {
      // 삭제 대상이 리프일때
      if (current.isLeaf()) {
        if (parent == null) { // root node 일 때
          this.root = null;
        } else if (isCurrentLeft) {
          parent.linkLeft(null);
        } else {
          parent.linkRight(null);
        }
      }
      // 삭제 대상 자식 노드가 왼쪽에만 있는 경우
      else if (current.isOnlyLeft()) {
        if (parent == null) { // root node 일 때
          this.root = current.left;
        } else if (isCurrentLeft) {
          parent.linkLeft(current.left);
        } else {
          parent.linkRight(current.left);
        }
      }
      // 삭제 대상 자식 노드가 오른쪽에만 있는 경우
      else if (current.isOnlyRight()) {
        if (parent == null) {
          this.root = current.right;
        } else if (isCurrentLeft) {
          parent.linkLeft(current.right);
        } else {
          parent.linkRight(current.right);
        }
      }
      // 삭제 대상 노드에 자식 노드가 두개 있는 경우 -> "왼쪽 서브트리"에서 선택하기
      else if (current.isLeftAndRight()) {
        TreeNode<T> biggestNode = current.left;
        TreeNode<T> biggestNodeParent = current;
        boolean isBiggestNodeLeft = true;
        while (biggestNode.right != null) {
          biggestNodeParent = biggestNode;
          biggestNode = biggestNode.right;
          isBiggestNodeLeft = false;
        }
        // 현재 노드의 값을 가장 큰 노드의 값으로 교체
        current.value = biggestNode.value;
        // 가장 큰 노드를 삭제 (가장 큰 노드는 리프이거나 왼쪽 자식만 가지고 있음)
        if (biggestNode.isLeaf()) {
          if (isBiggestNodeLeft) {
            biggestNodeParent.linkLeft(null);
          } else {
            biggestNodeParent.linkRight(null);
          }
        } else if (biggestNode.isOnlyLeft()) {
          if (isBiggestNodeLeft) {
            biggestNodeParent.linkLeft(biggestNode.left);
          } else {
            biggestNodeParent.linkRight(biggestNode.left);
          }
        }
      }
    }
    // current가 삭제 대상이 아닐 때
    else if (compareTo > 0) {
      deleteNodeRecursive(current, current.left, true, deleteNode);
    } else {
      deleteNodeRecursive(current, current.right, false, deleteNode);
    }
  }

  public void printTree() {
    if (root == null) {
      System.out.println("Tree is empty");
      return;
    }

    int maxDepth = getMaxDepth(root);
    int maxWidth = (int) Math.pow(2, maxDepth); // 최대 너비 계산 (조정)
    // 각 레벨의 노드와 위치를 저장
    java.util.List<TreeNode<T>> nodes = new java.util.ArrayList<>();
    java.util.List<Integer> positions = new java.util.ArrayList<>();
    nodes.add(root);
    positions.add(maxWidth / 2); // 루트 노드의 중앙 위치

    for (int level = 0; level < maxDepth; level++) {
      java.util.List<TreeNode<T>> nextNodes = new java.util.ArrayList<>();
      java.util.List<Integer> nextPositions = new java.util.ArrayList<>();
      // 현재 레벨의 출력 준비 (노드 값)
      StringBuilder valueLine = new StringBuilder();
      StringBuilder connectorLine = new StringBuilder();
      // 최대 너비만큼 공백 초기화
      for (int i = 0; i < maxWidth; i++) {
        valueLine.append(" ");
        connectorLine.append(" ");
      }
      // 현재 레벨의 노드 출력
      for (int i = 0; i < nodes.size(); i++) {
        TreeNode<T> node = nodes.get(i);
        int pos = positions.get(i);
        if (node != null) {
          String valueStr = node.value.toString();
          // 노드 값의 길이를 고려하여 중앙 정렬
          int valueLen = valueStr.length();
          int startPos = pos - valueLen / 2; // 값의 시작 위치를 중앙으로 조정
          if (startPos < 0) startPos = 0; // 음수 방지
          valueLine.replace(startPos, startPos + valueLen, valueStr);
          // 자식 노드가 있는 경우, 연결선(/ 또는 \) 추가
          int offset = (int) Math.pow(2, maxDepth - level - 2);
          if (node.left != null) {
            int leftPos = pos - offset;
            connectorLine.replace(leftPos, leftPos + 1, "/");
          }
          if (node.right != null) {
            int rightPos = pos + offset;
            connectorLine.replace(rightPos, rightPos + 1, "\\");
          }
          // 다음 레벨의 자식 노드와 위치 추가
          nextNodes.add(node.left);
          nextPositions.add(pos - offset);
          nextNodes.add(node.right);
          nextPositions.add(pos + offset);
        } else {
          // null 노드는 공백으로 처리
          nextNodes.add(null);
          nextPositions.add(pos - (int) Math.pow(2, maxDepth - level - 2));
          nextNodes.add(null);
          nextPositions.add(pos + (int) Math.pow(2, maxDepth - level - 2));
        }
      }
      // 현재 레벨의 값과 연결선 출력
      System.out.println(valueLine.toString().replaceAll("\\s+$", ""));
      if (level < maxDepth - 1) { // 마지막 레벨에서는 연결선 생략
        System.out.println(connectorLine.toString().replaceAll("\\s+$", ""));
      }
      nodes = nextNodes;
      positions = nextPositions;
    }
  }

  private int getMaxDepth(TreeNode<T> node) {
    if (node == null) {
      return 0;
    }
    return 1 + Math.max(getMaxDepth(node.left), getMaxDepth(node.right));
  }

  public void levelOrder(TreeNode<T> treeNode) {

  }

  public static class MyTreeDuplicateException extends RuntimeException {

    public MyTreeDuplicateException() {
      super("해당 값은 이미 트리에 추가되어 있습니다.");
    }
  }

}

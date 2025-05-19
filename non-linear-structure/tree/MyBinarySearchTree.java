package tree;


import java.util.ArrayList;
import java.util.List;

// TODO
// 0. print 함수 완료하기
// 1. if, if in for loop, for loop in for loop 해결하기
// 1-1. 자식 노드가 1개일 때 왼쪽인지 오른쪽인지 구분하는 것이 의미가 있는지 확인하기
// 1-2. 자식 노드가 1개/2개일 때 구분하는 것이 의미가 있는지 확인하기
// 2. 클래스의 의존성 확인하기
// 3. 생성자 오버로딩으로 작은 팩토리 패턴 사용하기
// 4. 재귀함수 -> while 루프로 수정해보기

// 해당 클래스의 제네릭은 root의 제네릭으로 사용되는 의존성이 있음
public class MyBinarySearchTree<T extends Comparable<T>> {

  private TreeNode<T> root;

  // 생성자에 new TreeNode()를 하는 것, 클래스간의 종속성이 있는데 밖에서 new TreeNode()를 하는게 굳이 이기도 하고, 위험할 수 있음
  // 타입 값만 받고 안에서 생성해줘도 됨
  // 이 클래스를 사용하는 사람은 TreeNode를 몰라도 됨
  public MyBinarySearchTree() {
    this(null);
  }

  public MyBinarySearchTree(TreeNode<T> root) {
    this.root = root;
  }

  public boolean isExists(TreeNode<T> value) {
    // TODO 재귀함수 사용하지 않고 구현한 로직 추가하기
    return isExistsRecursive(this.root, value.value);
  }

  // TODO 꼬리재귀 (학습할 것) -> stackoverflow 예방
  // 재귀를 사용할거면 위 메서드만으로 재귀를 구현할 수 있도록 하는 것이 필요
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

  // TODO 복잡도를 낮추기 위해 중첩된 조건절을 해소할 필요가 있음
  private void insertNodeRecursive(TreeNode<T> current, TreeNode<T> insert) {
    int compare = current.value.compareTo(insert.value);
    if (compare == 0) {
//      throw new MyTreeDuplicateException();
      System.out.println(insert.value + " is duplicated value.");
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
      if (current.isLeaf()) { // 조건 케이스가 2개를 초과하는 경우는 switch문을 사용해서 가독성 높이기
        if (parent == null) { // root node 일 때
          this.root = null;
        } else if (isCurrentLeft) {
          parent.linkLeft(null);
        } else {
          parent.linkRight(null);
        }
      }
      // 삭제 대상 자식 노드가 왼쪽에만 있는 경우 // TODO 차수가 0, 1, 2일때의 케이스 별로 로직이 어떻게 달라져야 하는지, (차수가 1일때 left와 right가 달라지느냐 / 차수가 1일때랑 2일때랑 일반화가 불가능한거냐) => 해당 케이스별로 코딩이 아니라 모국어로 로직을 구현을 해봐야 할 것
      else if (current.hasOnlyLeft()) {
        if (parent == null) { // root node 일 때
          this.root = current.left;
        } else if (isCurrentLeft) {
          parent.linkLeft(current.left);
        } else {
          parent.linkRight(current.left);
        }
      }
      // 삭제 대상 자식 노드가 오른쪽에만 있는 경우
      else if (current.hasOnlyRight()) {
        if (parent == null) {
          this.root = current.right;
        } else if (isCurrentLeft) {
          parent.linkLeft(current.right);
        } else {
          parent.linkRight(current.right);
        }
      }
      // 삭제 대상 노드에 자식 노드가 두개 있는 경우 -> "왼쪽 서브트리"에서 선택하기 TODO 왼쪽 오른쪽 유연하게 선택도 가능함 - 성능에 따라 (왼쪽, 오른쪽 섭트리 depth를 확인해서)
      else if (current.hasLeftAndRight()) {
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
        } else if (biggestNode.hasOnlyLeft()) {
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

    TreeMetaInfo treeMetaInfo = new TreeMetaInfo();
    searchTreeMetaInfo(root, treeMetaInfo, 0);

    int maxDepth = treeMetaInfo.getMaxDepth();
    int maxWidth = (int) Math.pow(treeMetaInfo.maxValueLength, maxDepth);
    // 각 레벨의 노드와 위치를 저장
    List<TreeNode<T>> nodes = new ArrayList<TreeNode<T>>();
    List<Integer> positions = new ArrayList<Integer>();
    nodes.add(root);
    positions.add(maxWidth / 2); // 루트 노드의 중앙 위치

    // 레벨 순회
    for (int level = 0; level < maxDepth; level++) {
      List<TreeNode<T>> nextLevelNodes = new ArrayList<TreeNode<T>>();
      List<Integer> nextLevelPositions = new ArrayList<Integer>();
      // 현재 레벨의 출력 준비
      StringBuilder valueLine = new StringBuilder();
      StringBuilder connectorLine = new StringBuilder();
      // 최대 너비만큼 공백 초기화 // TODO 가급적 for loop도 중첩시키지 말것
      for (int i = 0; i < maxWidth; i++) {
        valueLine.append(" ");
        connectorLine.append(" ");
      }

      // 현재 레벨의 노드 순회
      for (int i = 0; i < nodes.size(); i++) {
        TreeNode<T> curNode = nodes.get(i);
        int curPosition = positions.get(i);
        if (curNode != null) {
          String valueStr = curNode.value.toString();
          // 중앙 정렬하여 값 세팅
          int valueLength = valueStr.length();
          int startPosition = Math.max(curPosition - valueLength / 2, 0); // 값의 시작 위치를 중앙으로 조정 (음수 방지)
          valueLine.replace(startPosition, startPosition + valueLength, valueStr);
          // 자식 노드가 있는 경우, 연결선(/ 또는 \) 추가
          int offset = (int) Math.pow(2, maxDepth - level - 2);
          if (curNode.left != null) { //TODO for loop에 있는 중첩된 if문이 최악의 복잡도이다
            int leftPos = curPosition - offset + 1;
            connectorLine.replace(leftPos, leftPos + 1, "/");
          }
          if (curNode.right != null) {
            int rightPos = curPosition + offset - 1;
            connectorLine.replace(rightPos, rightPos + 1, "\\");
          }
          // 다음 레벨의 자식 노드와 위치 추가
          nextLevelNodes.add(curNode.left);
          nextLevelPositions.add(curPosition - offset);
          nextLevelNodes.add(curNode.right);
          nextLevelPositions.add(curPosition + offset);
        } else {
          // null 노드는 공백으로 처리
          nextLevelNodes.add(null);
          nextLevelPositions.add(curPosition - (int) Math.pow(2, maxDepth - level - 2));
          nextLevelNodes.add(null);
          nextLevelPositions.add(curPosition + (int) Math.pow(2, maxDepth - level - 2));
        }
      }

      // 현재 레벨의 값과 연결선 출력
      System.out.println(valueLine.toString().replaceAll("\\s+$", ""));
      if (level < maxDepth - 1) { // 마지막 레벨에서는 연결선 생략
        System.out.println(connectorLine.toString().replaceAll("\\s+$", ""));
      }
      nodes = nextLevelNodes;
      positions = nextLevelPositions;
    }
  }

  private int getMaxDepth(TreeNode<T> node) {
    if (node == null) {
      return 0;
    }
    return 1 + Math.max(getMaxDepth(node.left), getMaxDepth(node.right));
  }

  private int getMaxValueLength(TreeNode<T> node) {
    if (node == null) {
      return 0;
    }
    int currentLength = node.value.toString().length();
    int leftLength = getMaxValueLength(node.left);
    int rightLength = getMaxValueLength(node.right);
    return Math.max(currentLength, Math.max(leftLength, rightLength));
  }

  public TreeMetaInfo getTreeMetaInfo() {
    TreeMetaInfo treeMetaInfo = new TreeMetaInfo();
    searchTreeMetaInfo(this.root, treeMetaInfo, 0);
    return treeMetaInfo;
  }

  private void searchTreeMetaInfo(TreeNode<T> node, TreeMetaInfo metaInfo, int depth) {
    if (node == null) {
      return;
    }

    metaInfo.setMaxDepth(Math.max(depth, metaInfo.getMaxDepth()));
    metaInfo.setMaxValueLength(Math.max(node.value.toString().length(), metaInfo.getMaxValueLength()));
    searchTreeMetaInfo(node.left, metaInfo, depth + 1);
    searchTreeMetaInfo(node.right, metaInfo, depth + 1);
  }


  public static class MyTreeDuplicateException extends RuntimeException {

    public MyTreeDuplicateException() {
      super("해당 값은 이미 트리에 추가되어 있습니다.");
    }
  }


  static class TreeMetaInfo {

    @Override
    public String toString() {
      return "TreeMetaInfo{" +
          "maxDepth=" + maxDepth +
          ", maxValueLength=" + maxValueLength +
          '}';
    }

    private int maxDepth;
    private int maxValueLength;

    public TreeMetaInfo() {
      this.maxDepth = 1;
      this.maxValueLength = 0;
    }

    public int getMaxDepth() {
      return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
      this.maxDepth = maxDepth;
    }

    public int getMaxValueLength() {
      return maxValueLength;
    }

    public void setMaxValueLength(int maxValueLength) {
      this.maxValueLength = maxValueLength;
    }
  }

}

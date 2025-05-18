package tree;

public class Test {

  public static void main(String[] args) {
    MyBinarySearchTree myBinarySearchTree = new MyBinarySearchTree();
    myBinarySearchTree.insertNode(new TreeNode<Integer>(5));
    myBinarySearchTree.insertNode(new TreeNode<Integer>(3));
    myBinarySearchTree.insertNode(new TreeNode<Integer>(2));
    myBinarySearchTree.insertNode(new TreeNode<Integer>(6));
    myBinarySearchTree.insertNode(new TreeNode<Integer>(8));
    myBinarySearchTree.insertNode(new TreeNode<Integer>(12));
    myBinarySearchTree.insertNode(new TreeNode<Integer>(10));
    myBinarySearchTree.insertNode(new TreeNode<Integer>(11));
    myBinarySearchTree.insertNode(new TreeNode<Integer>(1));
    myBinarySearchTree.insertNode(new TreeNode<Integer>(9));
    myBinarySearchTree.insertNode(new TreeNode<Integer>(20));
    myBinarySearchTree.insertNode(new TreeNode<Integer>(13));
    myBinarySearchTree.insertNode(new TreeNode<Integer>(19));
    myBinarySearchTree.insertNode(new TreeNode<Integer>(18));


    myBinarySearchTree.printTree();
    System.out.println("------------------------------------------------------------------------------");

    myBinarySearchTree.deleteNode(new TreeNode<Integer>(18));
    myBinarySearchTree.printTree();
    System.out.println("------------------------------------------------------------------------------");
    myBinarySearchTree.deleteNode(new TreeNode<Integer>(19));
    myBinarySearchTree.printTree();
    System.out.println("------------------------------------------------------------------------------");
    myBinarySearchTree.deleteNode(new TreeNode<Integer>(13));
    myBinarySearchTree.printTree();
    System.out.println("------------------------------------------------------------------------------");
    myBinarySearchTree.deleteNode(new TreeNode<Integer>(9));
    myBinarySearchTree.printTree();
    System.out.println("------------------------------------------------------------------------------");
    myBinarySearchTree.deleteNode(new TreeNode<Integer>(11));
    myBinarySearchTree.printTree();
    System.out.println("------------------------------------------------------------------------------");



    myBinarySearchTree.deleteNode(new TreeNode<Integer>(8));
    myBinarySearchTree.printTree();
    System.out.println("------------------------------------------------------------------------------");

    myBinarySearchTree.deleteNode(new TreeNode<Integer>(12));
    myBinarySearchTree.printTree();
    System.out.println("------------------------------------------------------------------------------");

    myBinarySearchTree.insertNode(new TreeNode<Integer>(4));
    myBinarySearchTree.printTree();
    System.out.println("------------------------------------------------------------------------------");

    myBinarySearchTree.deleteNode(new TreeNode<Integer>(3));
    myBinarySearchTree.printTree();
    System.out.println("------------------------------------------------------------------------------");
    System.out.println("------------------------------------------------------------------------------");
    System.out.println("------------------------------------------------------------------------------");


    MyBinarySearchTree myBinarySearchTree2 = new MyBinarySearchTree();
    myBinarySearchTree2.insertNode(new TreeNode(10));
    myBinarySearchTree2.insertNode(new TreeNode(5));
    myBinarySearchTree2.insertNode(new TreeNode(3));
    myBinarySearchTree2.insertNode(new TreeNode(4));
    myBinarySearchTree2.insertNode(new TreeNode(2));
    myBinarySearchTree2.insertNode(new TreeNode(8));
    myBinarySearchTree2.insertNode(new TreeNode(6));
    myBinarySearchTree2.insertNode(new TreeNode(7));
    myBinarySearchTree2.insertNode(new TreeNode(30));
    myBinarySearchTree2.insertNode(new TreeNode(20));
    myBinarySearchTree2.insertNode(new TreeNode(25));
    myBinarySearchTree2.insertNode(new TreeNode(17));
    myBinarySearchTree2.insertNode(new TreeNode(13));
    myBinarySearchTree2.insertNode(new TreeNode(23));
    myBinarySearchTree2.printTree();
    System.out.println("------------------------------------------------------------------------------");

    myBinarySearchTree2.deleteNode(new TreeNode<Integer>(5));
    myBinarySearchTree2.printTree();
    System.out.println("------------------------------------------------------------------------------");

    myBinarySearchTree2.deleteNode(new TreeNode<Integer>(20));
    myBinarySearchTree2.printTree();
    System.out.println("------------------------------------------------------------------------------");

    myBinarySearchTree2.deleteNode(new TreeNode<Integer>(30));
    myBinarySearchTree2.printTree();
    System.out.println("------------------------------------------------------------------------------");

    myBinarySearchTree2.deleteNode(new TreeNode<Integer>(7));
    myBinarySearchTree2.printTree();
    System.out.println("------------------------------------------------------------------------------");
  }

}

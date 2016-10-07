package edu.nyu.cs.pqs.backup;


import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class _BinarySearchTree<T> implements Iterable<T> {
  private Comparator<T> comparator;
  private Node root;
  //private Predicate<T> predicate;

  @Override
  public Iterator<T> iterator() {
    return new BinarySearchTreeIterator(root);
  }

  private class Node {
    private T value;
    private Node parent;
    private Node left;
    private Node right;

    Node(T value) {
      this.setValue(value);
    }

    public T getValue() {
      return value;
    }

    public void setValue(T value) {
      this.value = value;
    }

    public Node getLeft() {
      return left;
    }

    public void setLeft(Node left) {
      this.left = left;
    }

    public Node getRight() {
      return right;
    }

    public void setRight(Node right) {
      this.right = right;
    }

    public Node getParent() {
      return parent;
    }

    public boolean hasParent() {
      return parent != null;
    }

    public boolean isParentOfLesserValue() {
      return comparator.compare(parent.getValue(), getValue()) < 0;
    }

    public void setParent(Node parent) {
      this.parent = parent;
    }
  }

  public _BinarySearchTree(Comparator<T> comparator) {
    this.comparator = comparator;
  }

  public void insert(T value) {
    if (value == null) {
      throw new NullPointerException();
    }
    if (root == null) {
      root = new Node(value);
    } else {
      Node node = root;
      while (node != null) {
        int compareValue = comparator.compare(value, node.getValue());
        if (compareValue < 1) {
          if (node.getLeft() == null) {
            node.setLeft(new Node(value));
            return;
          } else {
            node = node.getLeft();
          }
        } else {
          if (node.getRight() == null) {
            node.setRight(new Node(value));
            return;
          } else {
            node = node.getRight();
          }
        }

      }
    }
  }

  public boolean remove(T element) {
    return false;
  }

  private class BinarySearchTreeIterator implements Iterator<T> {
    private Node currentNode;
    private Stack<Node> stack = new Stack<Node>();

    BinarySearchTreeIterator(Node node) {
      stack.push(node);
      while (node.getLeft() != null) {
        node = node.getLeft();
        stack.push(node);
      }
    }

    @Override
    public T next() {
      if (stack.isEmpty()) {
        throw new NoSuchElementException();
      }
      Node node = stack.pop();
      if (node.getRight() != null) {
        stack.push(node.getRight());
        Node node2 = node.getRight();
        while (node2.getLeft() != null) {
          node2 = node2.getLeft();
          stack.push(node2);
        }
      }
      return node.getValue();
    }

    @Override
    public boolean hasNext() {
      return stack.size() > 0;
    }

    @Override
    public void remove() {
      // TODO Auto-generated method stub
      
    }

    // @Override
    // public boolean hasNext() {
    // if (currentNode.getRight() != null) {
    // return true;
    // } else {
    // if (currentNode.hasParent() &&
    // !currentNode.isParentOfLesserValue()) {
    // return true;
    // }
    // }
    // return false;
    // }
    // @Override
    // public T next() {
    // if (currentNode.getRight() != null) {
    // currentNode = currentNode.getRight();
    // return currentNode.getValue();
    // } else {
    // if (currentNode.hasParent() &&
    // !currentNode.isParentOfLesserValue()) {
    // currentNode = currentNode.getParent();
    // return currentNode.getValue();
    // }
    // }
    // throw new NoSuchElementException();
    // }

  }

}

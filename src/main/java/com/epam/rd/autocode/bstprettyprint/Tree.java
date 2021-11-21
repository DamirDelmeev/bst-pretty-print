package com.epam.rd.autocode.bstprettyprint;

import java.util.ArrayList;
import java.util.List;

class Tree<E> implements PrintableTree<E> {
    Node<E> root;
    int size = 0;

    public Tree() {
        this.root = new Node<>(null);
    }

    public void add(int i) {
        if (size == 0) {
            root = new Node(i);
            size++;
            root.line = new Line();
            return;
        }
        Node<E> newNode = new Node(i);
        Node<E> lastNode = findLastNode(root, newNode);
        if (lastNode == null) {
            return;
        }
        size++;
        newNode.parent = lastNode;
        newNode.line = new Line();
        if ((lastNode.compareTo(newNode)) < 0) {
            lastNode.right = newNode;
        } else {
            lastNode.left = newNode;
        }
    }

    public Node<E> getMainRoot() {
        return root;
    }

    private Node<E> findLastNode(Node<E> oldNode, Node<E> newNode) {
        Node<E> lastNode = oldNode;
        int compare = oldNode.compareTo(newNode);
        if (compare < 0 && oldNode.right != null) {
            lastNode = findLastNode(oldNode.right, newNode);
            return lastNode;
        }
        if (compare > 0 && oldNode.left != null) {
            lastNode = findLastNode(oldNode.left, newNode);
            return lastNode;
        }
        if (compare == 0) {
            return null;
        }
        return lastNode;
    }

    @Override
    public String prettyPrint() {
        TreeIterator<Integer> treeIterator = new TreeIterator(this.getMainRoot());
        while (treeIterator.hasNext()) {
            Node<Integer> someNode = treeIterator.next;
            Line changeLine = someNode.line;
            int countSpace = findCountSpace(someNode, (Node<Integer>) this.getMainRoot());
            treeIterator.next();
            changeLine.setNum(someNode.element);
            if (someNode.left != null & someNode.right != null) {
                changeLine.setSuffix(Suffix.middle);
            }
            if (someNode.left != null & someNode.right == null) {
                changeLine.setSuffix(Suffix.up);
            }
            if (someNode.left == null & someNode.right != null) {
                changeLine.setSuffix(Suffix.down);
            }
            if (someNode.parent != null && someNode.parent.element > someNode.element) {
                changeLine.setPrefix(Prefix.down);
            }
            if (someNode.parent != null && someNode.parent.element < someNode.element) {
                changeLine.setPrefix(Prefix.up);
            }
            if (countSpace != 0) {
                String spaces = String.format("%" + countSpace + "s", "");
                changeLine.setSpace(spaces);
            }
        }
        return String.join("\n", fillSpaces()).concat("\n");
    }

    List<String> fillSpaces() {
        TreeIterator<Integer> treeIterator = new TreeIterator(this.getMainRoot());
        List<String> result = new ArrayList<>();
        while (treeIterator.hasNext()) {
            Node<Integer> someNode = treeIterator.next;
            Line changeLine = someNode.line;
            treeIterator.next();
            int countSpace = findCountSpace(someNode, (Node<Integer>) this.getMainRoot());
            if (countSpace == 0) {
                result.add(changeLine.print());
            } else {
                if (findInner(someNode) > 0) {
                    List<Node<Integer>> parents = getAllParents(someNode);
                    List<Integer> delimiters = new ArrayList<>();
                    parents.forEach((parent -> {
                        if (parent.line.getPrefix().equals(Prefix.down) && someNode.element > parent.element) {
                            int indexByElement = parent.line.getIndexByElement(Prefix.down.getValue());
                            delimiters.add(indexByElement);
                        } else if (parent.line.getPrefix().equals(Prefix.up) && someNode.element < parent.element) {
                            int indexByElement = parent.line.getIndexByElement(Prefix.up.getValue());
                            delimiters.add(indexByElement);
                        }
                    }));
                    someNode.line.setDelimiter(delimiters);
                }
                result.add(changeLine.print());
            }
        }
        return result;
    }

    int findInner(Node<Integer> someNode) {
        int result = 0;
        if ((someNode.equals(findMinNode(root))) | (someNode.equals(findMaxNode(root)))) {
            return result;
        }
        while (someNode.parent != null && (!someNode.parent.equals(root))) {
            result++;
            someNode = someNode.parent;
        }
        return result;
    }

    int findCountSpace(Node<Integer> someNode, Node<Integer> root) {
        int result = 0;
        Node<Integer> nodeForCheck = someNode;
        if (nodeForCheck.parent == null) {
            return 0;
        }
        while (!nodeForCheck.parent.equals(root)) {
            result = result + nodeForCheck.parent.element.toString().length() + 1;
            nodeForCheck = nodeForCheck.parent;
        }
        return result + root.element.toString().length();
    }

    private Node<E> findMinNode(Node<E> root) {
        Node<E> minNode = root;
        while (minNode.left != null) {
            minNode = minNode.left;
        }
        return minNode;
    }

    private Node<E> findMaxNode(Node<E> root) {
        Node<E> maxNode = root;
        while (maxNode.right != null) {
            maxNode = maxNode.right;
        }
        return maxNode;
    }

    private List<Node<Integer>> getAllParents(Node<Integer> someNode) {
        List<Node<Integer>> list = new ArrayList<>();
        while (someNode.parent != null) {
            list.add(someNode.parent);
            someNode = someNode.parent;
        }
        return list;
    }
}

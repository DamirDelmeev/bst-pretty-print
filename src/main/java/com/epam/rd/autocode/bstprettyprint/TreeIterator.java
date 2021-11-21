package com.epam.rd.autocode.bstprettyprint;

import java.util.Iterator;

class TreeIterator<E> implements Iterator<Node<E>> {
    Node<E> next;

    TreeIterator(Node<E> root) {
        this.next = root;
        findLeftNode();
    }

    public boolean hasNext() {
        return next != null && next.element != null;
    }

    public Node<E> next() {
        Node<E> temp = next;
        if (next.right != null) {
            return goToRight(temp);
        }
        return goToParent(temp);
    }

    private void findLeftNode() {
        while (next.left != null) {
            next = next.left;
        }
    }

    private Node<E> goToRight(Node<E> temp) {
        next = next.right;
        while (next.left != null) {
            next = next.left;
        }
        return temp;
    }

    private Node<E> goToParent(Node<E> temp) {
        while (true) {
            if (next.parent == null) {
                next = null;
                return temp;
            }
            if (next.parent.left == next) {
                next = next.parent;
                return temp;
            }
            next = next.parent;
        }
    }
}

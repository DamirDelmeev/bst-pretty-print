package com.epam.rd.autocode.bstprettyprint;

class Node<E> implements Comparable<E> {
    Node<E> parent;
    Node<E> right;
    Node<E> left;
    E element;
    Line line;

    public Node(E element) {
        this.element = element;
    }

    @Override
    public int compareTo(Object o) {
        Node<E> node = (Node<E>) o;
        return Integer.compare(Math.abs((Integer) this.element), Math.abs((Integer) node.element));
    }


}

package com.epam.rd.autocode.bstprettyprint;

public interface PrintableTree<E> {
    static PrintableTree getInstance() {
        return new Tree();
    }

    void add(int i);

    String prettyPrint();
}


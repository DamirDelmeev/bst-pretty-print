package com.epam.rd.autocode.bstprettyprint;

public enum Suffix {
    empty(""),
    up("┘"),
    down("┐"),
    middle("┤");

    private final String value;

    Suffix(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

package com.epam.rd.autocode.bstprettyprint;

public enum Prefix {

    empty(""),
    up("└"),
    down("┌");
    private final String value;

    Prefix(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

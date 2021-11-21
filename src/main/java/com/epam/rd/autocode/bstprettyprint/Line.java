package com.epam.rd.autocode.bstprettyprint;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private Prefix prefix;
    private Suffix suffix;
    private String space;
    private List<Integer> delimiter;
    private Integer num;

    public Line() {
        this.prefix = Prefix.empty;
        this.suffix = Suffix.empty;
        this.space = "";
        this.delimiter = new ArrayList<>();
    }

    public Prefix getPrefix() {
        return prefix;
    }

    public void setPrefix(Prefix prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(Suffix suffix) {
        this.suffix = suffix;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setDelimiter(List<Integer> del) {
        this.delimiter = del;
    }

    public String print() {
        char[] charArray = space.toCharArray();
        delimiter.forEach(integer -> {
            charArray[integer] = '│';
        });
        space = new String(charArray);
        return space + prefix.getValue() + num + suffix.getValue();
    }

    public int getIndexByElement(String str) {
        return print().indexOf(str);
    }
}

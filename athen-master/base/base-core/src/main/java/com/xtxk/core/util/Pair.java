package com.xtxk.core.util;

/**
 * Created by Administrator on 2020/12/17.
 */
public class Pair<F, S> {

    private F first;
    
    private S second;

    public F getFirst() {
        return first;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public S getSecond() {
        return second;
    }

    public void setSecond(S second) {
        this.second = second;
    }
}

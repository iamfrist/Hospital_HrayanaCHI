package com.imf.haryanachi.networkModel.fetch;

public class ComoList {

    String name;

    public ComoList(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

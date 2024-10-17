package org.example.model;

public enum Color {

    WHITE("w"), BLACK("b"), EMPTY("");

    private final String name;

    Color(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

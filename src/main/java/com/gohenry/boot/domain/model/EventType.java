package com.gohenry.boot.domain.model;

public enum EventType {
    CREATE, UPDATE, DELETE;

    public static void main(String... args) {
        System.out.println(CREATE.ordinal());
    }
}

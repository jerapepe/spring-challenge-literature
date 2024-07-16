package com.jera.Literatura.api;

public interface Data {
    <T> T getData(String json, Class<T> clase);
}
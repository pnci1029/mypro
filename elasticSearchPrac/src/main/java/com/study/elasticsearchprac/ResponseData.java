package com.study.elasticsearchprac;
public class ResponseData<T> {
    private String status;
    private int statusCode;
    private T data;

    public ResponseData() {
    }

    public ResponseData(String status, int statusCode, T data) {
        this.status = status;
        this.statusCode = statusCode;
        this.data = data;
    }
}

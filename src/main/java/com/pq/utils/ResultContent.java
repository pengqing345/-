package com.pq.utils;

import com.pq.entity.Result;

public class ResultContent extends Result {
    private Object content;

    public Object getContent() {
        return this.content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public ResultContent(int code, String message, Object content) {
        super(code, message);
        this.content = content;
    }

    public static Result success(String message, Object content) {
        return new ResultContent(0, message, content);
    }

    public static Result failed(String message, Object content) {
        return new ResultContent(-1, message, content);
    }
}

package com.mobiquity.LocalDelicacies;

/**
 * Created by jwashington on 7/21/14.
 */
public class BaseEvent {
    StackTraceElement[] stack;
    public BaseEvent() {
        stack = Thread.currentThread().getStackTrace();
    }
}

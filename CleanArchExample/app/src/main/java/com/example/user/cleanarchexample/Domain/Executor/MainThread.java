package com.example.user.cleanarchexample.Domain.Executor;

public interface MainThread {

    void post(final Runnable runnable);
}

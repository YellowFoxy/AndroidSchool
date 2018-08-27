package com.example.user.broadcastreceiversample;

public class MySingleton {

    public static String NEXT = "next";
    public static String BACK = "back";

    private MySingleton() {
        state = SingletonState.STATE_A;
    }

    private static MySingleton thisInstance;

    public static MySingleton getInstance() {
        if (thisInstance == null)
            thisInstance = new MySingleton();

        return thisInstance;
    }

    private SingletonState state;

    public SingletonState getState() {
        return state;
    }

    public void setState(SingletonState value) {
        state = value;
    }

    public void changeState(String action) {
        if (action.equals(NEXT))
            setState(state.next());
        else if (action.equals(BACK))
            setState(state.back());
    }
}


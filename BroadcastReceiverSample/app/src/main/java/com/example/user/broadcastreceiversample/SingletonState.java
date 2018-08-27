package com.example.user.broadcastreceiversample;

public enum SingletonState{
    STATE_A,
    STATE_B,
    STATE_C,
    STATE_D,
    STATE_E;

    private static SingletonState[] vals = values();
    public SingletonState next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }

    public SingletonState back() {
        if (this.ordinal() == 0)
            return STATE_E;
        return vals[(this.ordinal() - 1) % vals.length];
    }

    public String toString(){
        switch (this){
            case STATE_A:
                return "STATE_A";
            case STATE_B:
                return "STATE_B";
            case STATE_C:
                return "STATE_C";
            case STATE_D:
                return "STATE_D";
            case STATE_E:
                return "STATE_E";
        }
        return "None";
    }

}

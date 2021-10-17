package com.liu.model.dataModel;

import java.io.Serializable;

public class Step implements Serializable {
    private static final long serialVersionUID = 5495446426670336572L;
    private long interval;//下这一步所用时间
    private int [] pos;
    private boolean isRegret;//是否属于悔棋的操作

    public Step(long interval, int[] pos, boolean isRegret) {
        this.interval = interval;
        this.pos = pos;
        this.isRegret = isRegret;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }

    public boolean isRegret() {
        return isRegret;
    }

    public void setRegret(boolean regret) {
        isRegret = regret;
    }
}

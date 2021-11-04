package com.liu.model.dataModel;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Save implements Serializable {
    private static final long serialVersionUID = 6495446426670326572L;
    public ArrayList<Step> getStepList() {
        return stepList;
    }

    public void setStepList(ArrayList<Step> stepList) {
        this.stepList = stepList;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public ArrayList<Step> stepList;
    boolean isFinished;
    LocalDateTime time;
    String userId;
    String blackPlayer;
    String whitePlayer;
    String winner;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(String blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public String getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(String whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public Save(ArrayList<Step> stepList, boolean isFinished, LocalDateTime time, String userId, String blackPlayer, String whitePlayer, String winner) {
        this.stepList = stepList;
        this.isFinished = isFinished;
        this.time = time;
        this.userId = userId;
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;
        this.winner = winner;
    }


    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
//int [][] chessArr, 考虑耦合与效率的矛盾
}

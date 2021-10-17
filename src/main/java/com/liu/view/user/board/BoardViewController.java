package com.liu.view.user.board;


import com.liu.model.dataModel.Save;
import com.liu.model.dataModel.Step;
import com.liu.model.dataModel.User;
import com.liu.util.GameUtil;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.time.LocalDateTime;
import java.util.*;

public class BoardViewController {
    private long timeNow;
    private boolean ifBlack = true;
    private SimpleStringProperty side;
    BoardView playView;
    int lineNum;
    int sc;
    private SimpleBooleanProperty end;
    private int [][] chessArr;
    private int chessNum = 0;
    ArrayList<Step> stepList;//TODO: handle this
    ArrayList<int []> chessPosList = new ArrayList<int []>();
    EventHandler<MouseEvent> handler;

    public BoardViewController(BoardView view){
         handler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)  {
                putChess(event);
            }};

         view.lineBoard.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);

         this.playView = view;
         lineNum = view.lineNum;
         chessArr = new int [lineNum][lineNum];
         sc = view.sc;
         stepList = new ArrayList<Step>();
         timeNow = System.currentTimeMillis();

         side = new SimpleStringProperty("black");
         end = new SimpleBooleanProperty(false);
    }


    private void putChess(MouseEvent event){
        int x =(int) (Math.round(event.getX()/sc));
        int y =(int) (Math.round(event.getY()/sc));

        if(chessArr[x-1][y-1]!=0){//already have chess
            return;
        }

        long interval = System.currentTimeMillis() - timeNow;
        timeNow = System.currentTimeMillis();
        stepList.add(new Step(interval,new int[]{x,y},false));

        chessArr[x-1][y-1] = ifBlack?1:-1;
        chessNum++;
        chessPosList.add(new int[]{x,y});

        playView.putChess(ifBlack,x,y,chessNum);

        end.set(GameUtil.judgeVictory(chessArr,x-1, y-1,ifBlack,lineNum));
        if(end.get()){
            playView.lineBoard.removeEventHandler(MouseEvent.MOUSE_CLICKED,handler);
        }

        ifBlack = !ifBlack;
        side.set(ifBlack?"black":"white");
    }

    public void regret(){
        if(chessNum <= 0){
            return;
        }
        long interval = System.currentTimeMillis() - timeNow;
        timeNow = System.currentTimeMillis();
        int [] pos = chessPosList.remove(chessPosList.size()-1);
        stepList.add(new Step(interval,pos,true));
        chessArr[pos[0]-1][pos[1]-1] = 0;
        chessNum--;
        playView.removeLastChess();
        ifBlack = !ifBlack;
        side.set(ifBlack?"black":"white");
    }



    public void loadSave(Save save){
        if(save.isFinished()){
            throw new IllegalArgumentException("the game is finished!");
        }
//加载时, 先加载,再显示视图
        this.stepList = save.getStepList();
        for(Step step:stepList) {
            loadStep(step);
        }
    }

    public void review(Save save){
        if(!save.isFinished()){
            throw new IllegalArgumentException("the game is not finished!");
        }
        playView.lineBoard.removeEventHandler(MouseEvent.MOUSE_CLICKED,handler);
        this.stepList = save.getStepList();
        Iterator<Step> i1 = stepList.iterator();
        Iterator<Step> i2 = stepList.iterator();
        setTimeOut(i1,i2);
    }

    public void setTimeOut(Iterator<Step> i1, Iterator<Step> i2){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            loadStep(i1.next());

                        }
                    });
                if (i2.hasNext()) {
                    setTimeOut(i1, i2);
                }
            }
        },i2.next().getInterval());
    }


    private void loadStep(Step step){
            if (!step.isRegret()){
                int x = step.getPos()[0];
                int y = step.getPos()[1];
                chessArr[x-1][y-1] = ifBlack?1:-1;
                chessNum++;
                chessPosList.add(new int[]{x,y});

                playView.putChess(ifBlack,x,y,chessNum);
                ifBlack = !ifBlack;
                side.set(ifBlack?"black":"white");
            }
            else {
                int [] pos = chessPosList.remove(chessPosList.size()-1);
                chessArr[pos[0]-1][pos[1]-1] = 0;
                chessNum--;

                playView.removeLastChess();
                ifBlack = !ifBlack;
                side.set(ifBlack?"black":"white");
            }
    }

    public ArrayList<Step> getStepList(){
        return stepList;
    }


    public SimpleBooleanProperty endProperty() {
        return end;
    }

    public SimpleStringProperty sideProperty() {
        return side;
    }





}

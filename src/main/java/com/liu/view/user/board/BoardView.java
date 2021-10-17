package com.liu.view.user.board;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;


public class BoardView extends VBox {
    int sc;
    Pane lineBoard;
    int lineNum = 16;

    public BoardView(int scale){
        super();
        sc = scale;
        init();
    }

    private void init(){
        lineBoard = new Pane();
        this.setAlignment(Pos.CENTER);
        this.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        //Pane lineBoard = new Pane();
        this.getChildren().add(lineBoard);
        //lineBoard.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        //lineBoard.setStyle("-fx-background-color: red");\

        lineBoard.setPrefSize(sc*(lineNum+1),sc*(lineNum+1));
        lineBoard.setMaxWidth(sc*(lineNum+1));
        lineBoard.setPadding(new Insets(sc));
        lineBoard.setStyle("-fx-background-color: #F9D691");
        //ArrayList<Line> lineList = new ArrayList<>();

        for(int i = 1; i<=lineNum;i++){
            Line lineH = new Line(i*sc,sc,i*sc,sc*lineNum);
            lineH.setStrokeWidth(1);
            Line lineV = new Line(sc,i*sc,sc*lineNum,i*sc);
            lineV.setStrokeWidth(1);
            lineBoard.getChildren().add(lineH);
            lineBoard.getChildren().add(lineV);
        }
        //Circle point = new Circle(9*sc,9*sc,2);
        //point.setStyle("-fx-background-color: black");
        //lineBoard.getChildren().add(point);
    }

    public void putChess(boolean isBlack, int cx, int cy,int chessNum){
        if(cx>lineNum||cx<1||cy<1||cy>lineNum){
            throw new IllegalArgumentException("wrong coordinate!");
        }
        ChessView chessView = new ChessView(isBlack, chessNum);
        chessView.setLayoutX(sc*(cx-0.5));
        chessView.setLayoutY(sc*(cy-0.5));
        lineBoard.getChildren().add(chessView);
    }

    public void removeLastChess() {
        lineBoard.getChildren().remove(lineBoard.getChildren().size()-1);
    }

    class ChessView extends StackPane {
        protected ChessView(boolean isBlack,int chessNum){
            super();
            Label label = new Label(String.valueOf(chessNum));
            //label.setTextFill(Color.BLACK);//TODO: use CSS to Change the Color
            String name = "white";
            if(isBlack){
                name = "black";
                label.getStyleClass().add("whiteLabel");
            }
            else{
                label.getStyleClass().add("blackLabel");
            }
            Image chessImg = new Image(name+".png");
            System.out.println(name);
            ImageView chessView = new ImageView(chessImg);
            chessView.setPreserveRatio(true);
            chessView.setFitWidth(sc);
            this.getChildren().add(chessView);
            this.getChildren().add(label);
        }
    }

}

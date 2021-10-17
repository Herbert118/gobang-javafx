package com.liu.util;

public class GameUtil {
    public static boolean judgeVictory(int [][] chessArr, int i,int j,boolean ifBlack, int lineNum){
        int flag = ifBlack?1:-1;
        int num = 0;
        for( int k = j; k>=0;k--){
            if(chessArr[i][k] == flag){ num++; }
            else{ break; }
        }
        for(int k = j; k<lineNum;k++){
            if(chessArr[i][k]==flag){ num++;}
            else{break;}
        }
        num--;
        if(num >=5){return true;}
        num = 0;

        for(int k = i; k>=0;k--){
            if(chessArr[k][j] == flag){num++; }
            else{break;}
        }
        for(int k = i; k<lineNum;k++){
            if(chessArr[k][j] == flag){num++; }
            else{break;}
        }

        num--;
        if(num >=5){return true;}
        num = 0;

        int m, n;
        for(m =i, n= j; m>=0&&n>=0;m--,n--){
            if(chessArr[m][n] == flag){num++; }
            else{break;}
        }
        for(m =i, n= j; m<lineNum&&n<lineNum;m++,n++){
            if(chessArr[m][n] == flag){num++;}
            else{break;}
        }

        num--;
        if(num >=5){return true;}
        num = 0;

        for(m =i, n= j; m>=0&&n<lineNum;m--,n++){
            System.out.println(chessArr[m][n]);
            if(chessArr[m][n] == flag){num++; }
            else{break;}
        }
        for(m =i, n= j; m<lineNum&&n>=0;m++,n--){
            System.out.println(chessArr[m][n]);
            if(chessArr[m][n] == flag){num++;}
            else{break;}
        }
        num--;
        if(num >=5){return true;}
        num = 0;
        return false;
    }
}

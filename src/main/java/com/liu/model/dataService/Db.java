package com.liu.model.dataService;

import com.liu.model.dataModel.Save;
import com.liu.model.dataModel.Step;
import com.liu.model.dataModel.User;
import com.liu.util.ObjectInputUtil;
import com.liu.util.ObjectOutputUtil;
import com.liu.view.component.Alert;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Db {
    //private UserDb userDb
    //private saveDb saveDb;

    HashMap<String,ArrayList<Save>> saveMap;
    ArrayList<User> userList;

    private static Db db;
    public static Db getInstance(){
        if(db == null){
            db = new Db();
        }
        return db;
    }
    private Db(){
        loadFiles();
    }

    private void loadFiles(){
        loadUserFile();
        loadSaveFile();
        saveFiles();

        for(User user: userList){
            if(!saveMap.containsKey(user.getId())){
                ArrayList<Save> newSaveList = new ArrayList<Save>();
                newSaveList.add(new Save(new ArrayList<Step>(),false, LocalDateTime.now(),user.getId(),"testBlack","testWhite","testBlack"));
                saveMap.put(user.getId(),newSaveList);
            }

        }
    }

    private void loadSaveFile() {
        try {
            ObjectInputUtil<HashMap<String,ArrayList<Save>>> oiu1
                    = new ObjectInputUtil<HashMap<String,ArrayList<Save>>>("saves.ser");
            saveMap = oiu1.readSerialObject();
        } catch (FileNotFoundException e) {
            Alert.showAlert("saves.ser 不存在, 已创建");
        } catch (Exception e) {
            Alert.showAlert("saves.ser 不存在, 已创建");
        }

        if(saveMap == null){
            saveMap = new HashMap<String,ArrayList<Save>>();
            for(User user : userList){
                saveMap.put(user.getName(),new ArrayList<Save>());
            }
        }
    }

    private void loadUserFile() {
        try {
            ObjectInputUtil<ArrayList<User>> oiu2 = new ObjectInputUtil<ArrayList<User>>("users.ser");
            userList = oiu2.readSerialObject();
        } catch (FileNotFoundException e) {
            Alert.showAlert("users.ser 不存在, 已创建");
        } catch (Exception e) {
            Alert.showAlert("users.ser 不存在, 已创建");
        }
        if(userList == null){
            userList = new ArrayList<User>();
        }
        if(userList.isEmpty()){
            userList.add(new User("admin","admin","admin123","admin@admin.com","admin"));
            userList.add(new User("test","test","test123","test@test.com","user"));
        }

    }

    public void saveFiles(){
        saveSaveFile();
        saveUserFile();
    }

    public void saveUserFile() {
        try {
            ObjectOutputUtil<HashMap<String,ArrayList<Save>>> oou1 =
                    new ObjectOutputUtil<HashMap<String,ArrayList<Save>>>("saves.ser");
            oou1.writeSerialObject(saveMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveSaveFile() {
        try {
            ObjectOutputUtil<ArrayList<User>> oou2 = new ObjectOutputUtil<ArrayList<User>>("users.ser");
            oou2.writeSerialObject(userList);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addUser(User newUser){
        for(User user:userList){
            if(user.equals(newUser)){
                return false;
            }
        }
        userList.add(newUser);
        return true;
    }

    public ArrayList<User> searchUser(String info){
        ArrayList<User> resultList = new ArrayList<User>();
        for(User user: userList){
            if(user.getName().contains(info)){
                resultList.add(user);
                break;
            }
            if (user.getEmail().contains(info)){
                resultList.add(user);
            }
            if (user.getId().contains(info)){
                resultList.add(user);
            }
        }

        return userList;
    }
    //TODO: finish this
    private boolean updateUser(){
        return true;
    }

    public boolean deleteUser(User user){
        if(userList.contains(user)){
            userList.remove(user);
            return true;
        }
        return false;
    }

    public boolean addSave(String userId,Save save){
        if(saveMap.containsKey(userId)){
            saveMap.get(userId).add(save);
        }
        else{
            ArrayList<Save> newSaveList = new ArrayList<Save>();
            newSaveList.add(save);
            saveMap.put(userId,newSaveList);
        }
        return true;
    }

    public ArrayList<Save> getUserSaveList(String userId){
        return saveMap.get(userId);
    }

    public ArrayList<Save> searchReview(String info){
        System.out.println(info);
        ArrayList<Save> resultList = new ArrayList<Save>();
        for(ArrayList<Save> reviews:saveMap.values()){
            for(Save review: reviews){
                if(review.isFinished()==true) {
                    if (review.getTime().toString().contains(info)) {
                        resultList.add(review);
                    }
                    else if ((review.getUserId().contains(info))) {
                        resultList.add(review);
                    }
                    else if (review.getWhitePlayer().contains(info)) {
                        resultList.add(review);
                    }
                    else if (review.getBlackPlayer().contains(info)) {
                        resultList.add(review);
                    }
                }
            }
        }
        return resultList;
    }

    public ArrayList<Save> searchReview(String userId,String info){
        ArrayList<Save> resultList = new ArrayList<Save>();
        ArrayList<Save> reviews = saveMap.get(userId);
        if(reviews == null){
            throw new IllegalArgumentException("user is not in map!");
        }
            for(Save review: reviews){
                if(review.getTime().toString().contains(info)&&review.isFinished() == true){
                    resultList.add(review);
                }
                else if((review.getUserId().contains(info))){
                    resultList.add(review);
                }
                else if(review.getWhitePlayer().contains(info)){
                    resultList.add(review);
                }
                else if(review.getBlackPlayer().contains(info)){
                    resultList.add(review);
                }
            }
        return resultList;
    }
    //TODO: finish this
    public boolean updateSave(){
        return false;
    }

    public boolean deleteSave(Save save){
        if(saveMap.get(save.getUserId()).contains(save)){
            saveMap.get(save.getUserId()).remove(save);
            return true;
        }
        return false;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public ArrayList<Save> getAllSaveList() {
        ArrayList<Save> resultList = new ArrayList<Save>();
        for(ArrayList<Save> saveList:saveMap.values()){
            resultList.addAll(saveList);
        }
        return resultList;
    }
}

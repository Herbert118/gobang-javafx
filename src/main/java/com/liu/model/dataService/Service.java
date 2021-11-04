package com.liu.model.dataService;

import com.liu.model.dataModel.Save;
import com.liu.model.dataModel.User;
import com.liu.util.CheckUtil;
import com.liu.view.login.LoginView;
import com.liu.view.login.LoginViewController;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class Service {
    private Db db;
    private static Service service;
    private Service(){
        db = Db.getInstance();
    }
    public static Service getInstance(){
        if (service == null){
            service = new Service();
        }
        return service;
    }
    public boolean addUser(User user){
        if(user!=null){
            return db.addUser(user);
        }
        return false;
    }
    public boolean addUser(String name, String id, String password, String email) {
        if (CheckUtil.checkName(name) && CheckUtil.checkId(id)
                && CheckUtil.checkPassword(password) && CheckUtil.checkEmail(email)) {
            return db.addUser(new User(name, id, password, email));
        } else {
            throw new IllegalArgumentException("wrong argument for user!");
        }

    }
    public ArrayList<User> getUserList(){
        return db.getUserList();
    }
    public ArrayList<User> searchUser(String info){
        if(info!=null){
            return db.searchUser(info);
        }
        else{
            return new ArrayList<User>();
        }
    }

    public boolean deleteUser(User user){
        return db.deleteUser(user);
    }


    public boolean addSave(String userId, Save save){
        if(userId!=null && save!=null) {
            return db.addSave(userId, save);
        }
        return false;
    }

    public ArrayList<Save> searchReview(String info){
        if(info != null){
            return db.searchReview(info);
        }
        else{
            return new ArrayList<Save>();
        }
    }
    public ArrayList<Save> searchReview(String userId,String info){
        if(info != null){
            return db.searchReview(userId,info);
        }
        else{
            return new ArrayList<Save>();
        }
    }

    public ArrayList<Save> getSaveList(String userId){
        ArrayList<Save> allSaveList = db.getUserSaveList(userId);
        ArrayList<Save> resultList = new ArrayList<Save>();
        for(Save save: allSaveList){
            if(!save.isFinished()){
                resultList.add(save);
            }
        }
        return resultList;
    }
    public ArrayList<Save> getReviewList(String userId){
        ArrayList<Save> allSaveList = db.getUserSaveList(userId);
        ArrayList<Save> resultList = new ArrayList<Save>();
        for(Save save: allSaveList){
            if(save.isFinished()){
                resultList.add(save);
            }
        }
        return resultList;
    }

    public boolean deleteSave(Save save){
        return db.deleteSave(save);
    }

    public void saveFile(){
        db.saveFiles();
    }


    public boolean addUser(String name, String id, String password, String email, String authority) {
        if (CheckUtil.checkName(name) && CheckUtil.checkId(id)
                && CheckUtil.checkPassword(password) && CheckUtil.checkEmail(email)&&CheckUtil.checkAuthority(authority)){
            return db.addUser(new User(name, id, password, email,authority));
        } else {
            throw new IllegalArgumentException("wrong argument for user!");
        }
    }

    public boolean updateUser(User oldUser, String name, String id, String password, String email, String authority) {

        return db.deleteUser(oldUser)&&this.addUser(name,id,password,email,authority);
    }

    public User checkLogin(String id, String password) {
        for(User user: db.getUserList()){
            if(user.getId().equals(id)){
                if(user.getPassword().equals(password)){
                    return user;
                }
                else {
                    throw new IllegalArgumentException("密码错误");
                }
            }

        }
        throw new IllegalArgumentException("未发现此用户");
    }


    public ArrayList<Save> getallReviewList() {
        ArrayList<Save> allSaveList = db.getAllSaveList();
        ArrayList<Save> resultList = new ArrayList<Save>();
        for(Save save: allSaveList){
            if(save.isFinished()){
                resultList.add(save);
            }
        }
        return resultList;
    }

    public Save saveClone(Save save){
        return db.saveClone(save);
    }
}

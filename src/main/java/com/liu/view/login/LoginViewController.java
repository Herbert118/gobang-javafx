package com.liu.view.login;

import com.liu.mainapp.Router;
import com.liu.model.dataModel.User;
import com.liu.model.dataService.Service;
import com.liu.view.component.Alert;
import javafx.scene.input.KeyEvent;


public class LoginViewController {
	private LoginView view;
	private Service service;
	private Router router;
	public LoginViewController(LoginView view){
		this.view = view;
		this.service = Service.getInstance();
		this.router = Router.getInstance();
		attachEvents();
	}
	
	
	private void attachEvents() {
		view.loginBtn.setOnAction(e -> {
			login();
		});
		view.addEventHandler(KeyEvent.KEY_PRESSED,e->{
			System.out.println(e.getCode().getName());
			if(e.getCode().getName().equals("Enter")){
				login();
			}
		});
	}
	private void login(){
		String id = view.idFld.getText();
		String password = view.passwordFld.getText();
		User user;
		if(id.isEmpty()||password.isEmpty()){
			Alert.showAlert("登录名或密码不能为空");
			return;
		}
		try{
			user = service.checkLogin(id,password);
			router.setUser(user);
			if(user.getAuthority().equals("admin")){
				router.navToAdminMenuView();
			}
			else {
				router.navToUserMenuView();
			}
		}
		catch (IllegalArgumentException ex){
			Alert.showAlert(ex.getMessage());
		}
	}
	
	
}

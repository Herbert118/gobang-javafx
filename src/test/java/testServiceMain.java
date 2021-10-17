import com.liu.model.dataModel.User;
import com.liu.model.dataService.Service;
//should  be junit test case but it is more convenient...
public class testServiceMain {
    public static void main(String [] args){
        Service service = Service.getInstance();
       // service.addUser("name","id", "123pass","254@qq.com");
        System.out.println(service.getUserList().get(0).getName());;
        service.saveFile();
    }

}

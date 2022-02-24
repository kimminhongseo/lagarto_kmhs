import com.portfolio.lagarto.Const;
import com.portfolio.lagarto.model.UserEntity;
import com.portfolio.lagarto.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class UserInformation {
    @Autowired //필요한 메소드 자동찾기
    private UserService service;

    @GetMapping("/userInformation")
    public void userInformation(){
//        @RequestParam String nicknm, UserEntity entity, Model model
//        System.out.println(nicknm);
//        entity.setNickname(nicknm);
//        model.addAttribute(Const.User, service.selUser(entity));
    }
}

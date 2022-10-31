package al.spree.spring.security.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/")
    public String getInfoForAllEmps() {
        return "view-for-all-amployees";
    }

    @GetMapping("/hr_info")
    public String getInfoOnlyForHR() {
        return "view-for-hr";
    }

    @GetMapping("/manager_info")
    public String getInfoOnlyForManagers(){
        return "view-for-managers";
    }
}

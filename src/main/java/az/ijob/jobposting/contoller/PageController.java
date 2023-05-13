package az.ijob.jobposting.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping({"/index","/home"})
    public String index(){
        return "index";
    }

}

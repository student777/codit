package codit.controller;

        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Malzahar on 2016-06-20.
 */

@Controller
public class MainController {
    @RequestMapping( "/" )
    public String main() {
        return "/WEB-INF/index.jsp";
    }
}

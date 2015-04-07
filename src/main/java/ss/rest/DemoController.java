package ss.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ss.domain.User;

@Controller
@RequestMapping(value="/demo")
public class DemoController {
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String getData() {
        return "{'data':'data'}";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public @ResponseBody User getUser(@PathVariable int id) {
        User user = new User();
        user.setId(id);
        user.setName("Username");
        return user;
    }
}
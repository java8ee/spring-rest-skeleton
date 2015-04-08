package ss.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ss.domain.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/user")
public class UserController {
    private final Map<Integer, User> users = new HashMap<Integer, User>();
    private final String[] data = {"Bob", "Jim", "Mike", "Nick", "Sam", "Karl", "Jane", "Kate"};

    public UserController() {
        for (int i = 1; i <= data.length; i++) {
            users.put(i, new User(i, data[i - 1]));
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Collection<User> getData() {
        return users.values();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public @ResponseBody User getUser(@PathVariable int id) {
        return users.get(id);
    }
}
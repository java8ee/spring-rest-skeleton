package ss.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ss.domain.User;
import ss.domain.UserWrapper;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping(value="/user")
public class UserController {
    private final Map<Integer, User> users = new TreeMap<Integer, User>();
    private final String[] data = {"Bob", "Jim", "Mike", "Nick", "Sam", "Karl", "Jane", "Kate"};

    public UserController() {
        for (int i = 1; i <= data.length; i++) {
            users.put(i, new User(i, data[i - 1]));
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<User> getData() {
        return users.values();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable int id) {
        return users.get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user) {
        int id = users.size() + 1;
        user.setId(id);
        users.put(id, user);

        return user;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable int id, @RequestBody UserWrapper userWrapper) {
        User user = users.get(id);
        if (user != null) {
            for (Map.Entry<String, Object> entry : userWrapper.getMap().entrySet()) {
                switch (entry.getKey()) {
                    case "id" :
                        int newId = (Integer) entry.getValue();
                        user.setId(newId);
                        users.remove(id);
                        id = newId;
                        break;
                    case "name" :
                        user.setName((String) entry.getValue());
                        break;
                }
            }
            users.put(id, user);
        } else {
            throw new IllegalArgumentException("ID=" + id + "doesn't exist");
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        users.remove(id);
    }
}
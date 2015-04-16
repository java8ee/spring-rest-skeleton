package ss.domain;

import java.util.HashMap;
import java.util.Map;

public class UserWrapper extends User {
    private Map<String, Object> map = new HashMap<String, Object>();

    public void setId(int id) {
        map.put("id", id);
        super.setId(id);
    }

    public void setName(String name) {
        map.put("name", name);
        super.setName(name);
    }

    public Map<String, Object> getMap() {
        return map;
    }
}
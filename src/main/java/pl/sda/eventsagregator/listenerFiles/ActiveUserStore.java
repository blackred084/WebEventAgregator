package pl.sda.eventsagregator.listenerFiles;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@Data
public class ActiveUserStore {

    public List<String> users;

    public ActiveUserStore() {
        users = new ArrayList<String>();
    }

    public ActiveUserStore activeUserStore(){
        return new ActiveUserStore();
    }

    public List<String> getUsers() {
        return users;
    }

}
package pl.sda.eventsagregator.listenerFiles;

import org.springframework.stereotype.Component;
import pl.sda.eventsagregator.entities.AppUser;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.List;

@Component
public class LoggedUser implements HttpSessionBindingListener {

    private String username;
    private ActiveUserStore activeUserStore;
    private AppUser appUser;

    public LoggedUser(String username, ActiveUserStore activeUserStore) {
        this.username = username;
        this.activeUserStore = activeUserStore;
    }

    public LoggedUser() {}

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        List<String> users = activeUserStore.getUsers();
        LoggedUser user = (LoggedUser) event.getValue();
        if (!users.contains(appUser.getUsername())) {
            users.add(appUser.getUsername());
        }
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        List<String> users = activeUserStore.getUsers();
        LoggedUser user = (LoggedUser) event.getValue();
        if (users.contains(appUser.getUsername())) {
            users.remove(appUser.getUsername());
        }
    }


}

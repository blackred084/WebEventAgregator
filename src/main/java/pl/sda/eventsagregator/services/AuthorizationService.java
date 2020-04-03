package pl.sda.eventsagregator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.eventsagregator.dao.*;
import pl.sda.eventsagregator.entities.*;

import java.time.LocalDateTime;

@Service
public class AuthorizationService {

    private BCryptPasswordEncoder passwordEncoder;

    private UserDao userDao;

    private UserRoleDao userRoleDao;

    private RoleDao roleDao;

    private StatusDao statusDao;

    private UserStatusDao userStatusDao;

    @Autowired
    public AuthorizationService(BCryptPasswordEncoder passwordEncoder,
                                UserDao userDao,
                                UserRoleDao userRoleDao,
                                RoleDao roleDao,
                                StatusDao statusDao,
                                UserStatusDao userStatusDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.userRoleDao = userRoleDao;
        this.roleDao = roleDao;
        this.statusDao = statusDao;
        this.userStatusDao = userStatusDao;
    }

    public void register(AppUserDto appUserDto) {
        AppUser appUser = new AppUser();
        appUser.setLogin(appUserDto.getLogin());
        appUser.setUsername(appUserDto.getUsername());

        String password = appUserDto.getPasswordRaw() != null ?
                passwordEncoder.encode(appUserDto.getPasswordRaw()) : appUserDto.getEncryptedPassword();

        appUser.setEncryptedPassword(password);
        appUser.setEmail(appUserDto.getEmail());
        appUser.setCreationDate(LocalDateTime.now());

        userDao.create(appUser);

        String roleName = appUserDto.getRole() != null ? appUserDto.getRole() : "ROLE_USER";
        Role role = roleDao.findByName(roleName);

        AppUser retrievedUser = userDao.findByUserName(appUser.getUsername());

        UserRole userRole = new UserRole(retrievedUser.getId(), role.getId());

        String statusName = appUserDto.getStatusName() != null ? appUserDto.getStatusName() : "ACTIVE";
        Status status = statusDao.findByName(statusName);
        UserStatus userStatus = new UserStatus(appUser.getId(), status.getId());

        userRoleDao.create(userRole);
        userStatusDao.create(userStatus);
        userDao.create(appUser);
    }

}

package auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {

    private static final long serialVersionUID = 1416132138315457558L;

    // extra instance variables
    final String fullname;
    final String email;
    final String title;
    final String sid;

    public CustomUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
                             boolean credentialsNonExpired, boolean accountNonLocked,
                             Collection<? extends GrantedAuthority> authorities, String fullname,
                             String email, String title, String sid) {

        super(username, password, enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);

        this.fullname = fullname;
        this.email = email;
        this.title = title;
        this.sid = sid;
    }

    public String getFullname() {
        return this.fullname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSid() {
        return this.sid;
    }
}
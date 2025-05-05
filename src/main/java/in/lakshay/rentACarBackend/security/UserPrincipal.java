package in.lakshay.rentACarBackend.security;

import in.lakshay.rentACarBackend.entities.abstracts.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

// implements Spring Security's UserDetails interface
// wraps our User entity with security-specific stuff
public class UserPrincipal implements UserDetails {
    private int id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    // constructor
    public UserPrincipal(int id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    // factory method to create from our User entity
    public static UserPrincipal create(User user) {
        // Default role is ROLE_USER
        // Admin users should have email ending with @admin.rentacar.com
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Add default user role
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        // Add admin role if email matches pattern
        if (user.getEmail() != null && user.getEmail().endsWith("@admin.rentacar.com")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new UserPrincipal(
                user.getUserId(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    // factory method with attributes (for OAuth2)
    public static UserPrincipal create(User user, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return email;  // email is our username
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // account never expires
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // account never locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // credentials never expire
    }

    @Override
    public boolean isEnabled() {
        return true;  // account always enabled
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}

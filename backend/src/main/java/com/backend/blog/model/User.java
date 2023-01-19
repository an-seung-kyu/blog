package com.backend.blog.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Getter
@NoArgsConstructor
@Document(collection = "user")
public class User implements UserDetails {
    @Id
    private String _id;
    private String userEmail;
    private String userPassword;
    private String userName;
    private Date createdAt;
    private Boolean use;
    private Set<User> userRoles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }
    public String getUserEmail() {
        return this.userEmail;
    }
    @Override
    public String getPassword() {
        return null;
    }
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

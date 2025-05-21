package com.MEDCIN.g04.config;

import com.MEDCIN.g04.models.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoUserDetails implements UserDetails {
    private final UserInfo userInfo; // Ajoutez cette ligne

    private String name;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInfoUserDetails(UserInfo userInfo) {
        this.userInfo = userInfo; // Ajoutez cette ligne
        name = userInfo.getEmail();
        password = userInfo.getPassword();
        // On transforme la chaîne de rôles en une liste d'objets GrantedAuthority

        authorities = Arrays.stream(userInfo.getRole().name().split(","))
                .map(role -> new SimpleGrantedAuthority(role)) // Crée un SimpleGrantedAuthority pour chaque rôle
                .collect(Collectors.toList());

    }
    // Retourne la liste des rôles / permissions de l'utilisateur

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    // Getter pour récupérer l'objet UserInfo complet
    public UserInfo getUserInfo() {
        return userInfo;
    }
    // Retourne le mot de passe de l'utilisateur (nécessaire pour l'authentification)

    @Override
    public String getPassword() {
        return password;
    }
    // Retourne le nom d'utilisateur (ici l'email)

    @Override
    public String getUsername() {
        return name;
    }
    // Indique si le compte de l'utilisateur n'est pas expiré (toujours true ici, donc pas expiré)

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    // Indique si le compte n'est pas verrouillé (toujours true ici, donc pas verrouillé)

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    // Indique si les identifiants (mot de passe) ne sont pas expirés (toujours true ici)

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    // Indique si le compte est activé (toujours true ici, donc activé)

    @Override
    public boolean isEnabled() {
        return true;
    }
}
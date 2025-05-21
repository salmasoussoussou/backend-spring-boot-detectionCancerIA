package com.MEDCIN.g04.config;

import com.MEDCIN.g04.models.UserInfo;
import com.MEDCIN.g04.repositories.UserInfoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
// Annotation qui indique que cette classe est un composant Spring géré automatiquement (service)

public class UserInfoUserDetailsService implements UserDetailsService {

    private final UserInfoRepository repository;
    // Le repository qui permet d'accéder aux données des utilisateurs en base

    public UserInfoUserDetailsService(UserInfoRepository repository) {
        this.repository = repository;
    }
    // Injection du repository via le constructeur (dependency injection)

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Méthode qui charge un utilisateur à partir de son nom d'utilisateur (email ici)

        Optional<UserInfo> userInfo = repository.findByEmail(username);
        return userInfo.map(UserInfoUserDetails::new)
                // Si trouvé, crée un objet UserInfoUserDetails à partir du UserInfo
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        // Sinon, lance une exception indiquant que l'utilisateur n'existe pas
    }
}
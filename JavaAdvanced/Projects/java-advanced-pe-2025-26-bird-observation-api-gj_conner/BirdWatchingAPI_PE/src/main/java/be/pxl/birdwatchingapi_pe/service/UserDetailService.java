package be.pxl.birdwatchingapi_pe.service;

import be.pxl.birdwatchingapi_pe.repository.UserRepository;
import be.pxl.birdwatchingapi_pe.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email){
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException(email);
        }
        return new MyUserPrincipal(user);
    }
}

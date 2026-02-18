package be.pxl.birdwatchingapi_pe.service;

import be.pxl.birdwatchingapi_pe.api.UserDto;
import be.pxl.birdwatchingapi_pe.api.UserUpdateDto;
import be.pxl.birdwatchingapi_pe.api.UserRequest;
import be.pxl.birdwatchingapi_pe.repository.UserRepository;
import be.pxl.birdwatchingapi_pe.domain.User;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void createUser(@Valid UserRequest userRequest){
        if (userRepository.findByEmail(userRequest.getEmail()) != null) {
            throw new IllegalArgumentException("Emailadres is al in gebruik.");
        }

        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setName(userRequest.getName());
        user.setPassword(getBCryptPassword(userRequest.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public UserDto updateUser(UserUpdateDto userUpdateDto, Long id) {

        // Fetch user
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found."));

        boolean updated = false;

        // Update email only if provided
        if (userUpdateDto.getEmail() != null && !userUpdateDto.getEmail().isBlank()) {
            user.setEmail(userUpdateDto.getEmail());
            updated = true;
        }

        // Update password only if provided
        if (userUpdateDto.getPassword() != null && !userUpdateDto.getPassword().isBlank()) {
            user.setPassword(getBCryptPassword(userUpdateDto.getPassword()));
            updated = true;
        }

        // If no fields were provided → 400 Bad Request
        if (!updated) {
            throw new IllegalArgumentException("Geen geldige input gegevens om bij te werken.");
        }

        userRepository.save(user);

        return new UserDto(user);
    }


    private String getBCryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}

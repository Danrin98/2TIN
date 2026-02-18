package be.pxl.activity.service;

import be.pxl.activity.api.DTO.UserDTO;
import be.pxl.activity.api.request.UserDetailRequest;
import be.pxl.activity.api.request.UserRequest;
import be.pxl.activity.domain.User.User;
import be.pxl.activity.domain.User.UserDetails;
import be.pxl.activity.exception.UserDetailRequestValidator;
import be.pxl.activity.exception.UserRequestValidator;
import be.pxl.activity.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public long createUser(UserRequest userRequest){
        if (userRepository.findByEmail(userRequest.getEmail()) != null) {
            throw new IllegalArgumentException("Emailadres is al in gebruik.");
        }
        UserRequestValidator.validate(userRequest);

        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setName(userRequest.getName());
        user.setPassword(getBCryptPassword(userRequest.getPassword()));
        user.setUserDetails(new UserDetails());
        User newUser = userRepository.save(user);
        return newUser.getId();
    }

    public UserDTO updateUserDetails(UserDetailRequest userDetailRequest, String email){
        User user = userRepository.findByEmail(email);

        UserDetailRequestValidator.validate(userDetailRequest);

        UserDetails userDetails = user.getUserDetails();
        userDetails.setWeight_kg(userDetailRequest.getWeight_kg());
        userDetails.setHeight_cm(userDetailRequest.getHeight_cm());
        userDetails.setDate_of_birth(userDetailRequest.getDate_of_birth());

        userRepository.save(user);
        return new UserDTO(user);
    }

    private String getBCryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}

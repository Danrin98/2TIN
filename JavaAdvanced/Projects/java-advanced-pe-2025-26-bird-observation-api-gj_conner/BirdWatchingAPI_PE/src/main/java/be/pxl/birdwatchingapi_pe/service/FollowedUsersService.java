package be.pxl.birdwatchingapi_pe.service;

import be.pxl.birdwatchingapi_pe.domain.FollowedUsers;
import be.pxl.birdwatchingapi_pe.api.FollowedUsersResponse;
import be.pxl.birdwatchingapi_pe.api.FollowedUsersRequest;
import be.pxl.birdwatchingapi_pe.repository.FollowedUsersRepository;
import be.pxl.birdwatchingapi_pe.repository.UserRepository;
import be.pxl.birdwatchingapi_pe.domain.User;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowedUsersService {

    private final FollowedUsersRepository followedUsersRepository;
    private final UserRepository userRepository;

    public FollowedUsersService(FollowedUsersRepository followedUsersRepository, UserRepository userRepository) {
        this.followedUsersRepository = followedUsersRepository;
        this.userRepository = userRepository;
    }

    public FollowedUsersResponse followUser(@Valid FollowedUsersRequest request, String email) {
        User user = userRepository.findByEmail(email);

        if (userRepository.existsByName(request.getName())) {
            if (followedUsersRepository.existsByNameAndUser(request.getName(), user)) {
                throw new IllegalArgumentException("User already followed.");
            }
            else {
                FollowedUsers saved = followedUsersRepository.save(new FollowedUsers(request.getName(), user));
                return new FollowedUsersResponse(saved.getName());
            }
        }
        else {
            throw new IllegalArgumentException("User does not exist.");
        }

    }

    public List<FollowedUsersResponse> getFollowedUsers(String email) {
        User user = userRepository.findByEmail(email);
        return followedUsersRepository.findAllByUser(user)
                .stream()
                .map(f -> new FollowedUsersResponse(f.getName()))
                .toList();
    }

    @Transactional
    public void deleteFollowedUser(String name, String email) {
        User user = userRepository.findByEmail(email);

        if (!followedUsersRepository.existsByNameAndUser(name, user)) {
            throw new IllegalArgumentException("This user is not being followed.");
        }

        followedUsersRepository.deleteByNameAndUser(name, user);
    }
}

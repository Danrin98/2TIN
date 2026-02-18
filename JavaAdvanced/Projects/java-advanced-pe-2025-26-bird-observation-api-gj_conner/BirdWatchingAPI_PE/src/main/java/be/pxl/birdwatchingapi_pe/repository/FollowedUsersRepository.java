package be.pxl.birdwatchingapi_pe.repository;

import be.pxl.birdwatchingapi_pe.domain.FollowedUsers;
import be.pxl.birdwatchingapi_pe.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowedUsersRepository extends JpaRepository<FollowedUsers, Integer> {
    boolean existsByNameAndUser(String name, User user);
    List<FollowedUsers> findAllByUser(User user);
    void deleteByNameAndUser(String name, User user);
}

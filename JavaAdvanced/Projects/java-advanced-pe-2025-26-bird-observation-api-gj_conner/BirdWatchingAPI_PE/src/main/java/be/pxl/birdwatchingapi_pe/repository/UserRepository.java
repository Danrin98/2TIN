package be.pxl.birdwatchingapi_pe.repository;

import be.pxl.birdwatchingapi_pe.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    boolean existsByName(String name);
}

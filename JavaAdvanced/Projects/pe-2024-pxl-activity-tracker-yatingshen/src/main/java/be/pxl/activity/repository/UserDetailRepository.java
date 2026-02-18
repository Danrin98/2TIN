package be.pxl.activity.repository;

import be.pxl.activity.domain.User.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetails, Long> {
}

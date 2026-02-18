package be.pxl.birdwatchingapi_pe.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "followed users")
public class FollowedUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public FollowedUsers() {
    }

    public FollowedUsers(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }
}



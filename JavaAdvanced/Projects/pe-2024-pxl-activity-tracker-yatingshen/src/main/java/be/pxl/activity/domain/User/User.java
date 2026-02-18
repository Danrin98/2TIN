package be.pxl.activity.domain.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity //Zodat de code een table in de database word
@Table(name ="users")
//Gebruik "users" ipv "user" omdat in SQL het woord al bestaat!!

public class User {

    @Id //een Id toewijzen is verplicht voor het aanmaken van een database
    @GeneratedValue(strategy = GenerationType.IDENTITY) //genereerd primarykey
    private long Id;
    @NotNull(message = "Name cannot be null")
    @Column(nullable = false) //Moet uniek en niet null zijn
    private String name;
    @Column(nullable = false, unique = true)
    @NotNull(message = "Email cannot be null")
    private String email;
    private String password;
    @OneToOne(cascade = CascadeType.ALL) //
    private UserDetails userDetails;

    public User(){

    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

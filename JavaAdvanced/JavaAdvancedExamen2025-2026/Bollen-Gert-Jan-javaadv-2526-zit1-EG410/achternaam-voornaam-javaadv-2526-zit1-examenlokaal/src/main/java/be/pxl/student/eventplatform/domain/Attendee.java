package be.pxl.student.eventplatform.domain;

//TODO vul de klasse aan zoals beschreven in de opdracht

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Attendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fullName;
    private String email;

    public Attendee() {}

    public Attendee(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    // Aanvullend op de oorspronkelijke opgave. Lijst van registraties voor deze attendee is hier niet noodzakelijk
    //private List<Registration> registrations;

}

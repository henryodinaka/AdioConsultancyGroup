package adio.group.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author LEOGOLD
 */
@Entity
@Table(name = "users")
public class Users {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private int id;
    
    @Column (name = "firstName",nullable = false)
    private String firstName;
    
    
    
    @Column (name = "lastName",nullable = false)
    private String lastName;
    
    
    @Column (name = "phoneNumber",nullable = false,unique = true)
    private String phoneNumber;
    
    
    @Column (name = "email",nullable = false,unique = true)
    private String email;
    
    
    @Column (name = "coverLetter",nullable = false)
    private String coverLetter;
    
    
    @Column (name = "passport",nullable = false)
    private String passport;
    
    
    @Column (name = "resume",nullable = false)
    private String resume;
    
    
    @Column (name = "role",nullable = false)
    private String role;

    @CreationTimestamp
    @Column(name = "created")
    private Date created;
    
    @UpdateTimestamp
    @Column(name = "modified")
    private Date modified;
    public Users() {
    }

    public Users(String firstName, String lastName, String phoneNumber, String email, String coverLetter, String passport, String resume, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.coverLetter = coverLetter;
        this.passport = passport;
        this.resume = resume;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", email=" + email + ", coverLetter=" + coverLetter + ", passport=" + passport + ", resume=" + resume + ", role=" + role + '}';
    }
    
    
}

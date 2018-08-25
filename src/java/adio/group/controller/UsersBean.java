package adio.group.controller;

import adio.group.model.Users;
import adio.group.service.UserService;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author LEOGOLD
 */
@Named(value = "userBean")
@RequestScoped
public class UsersBean {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String coverLetter;
    private UploadedFile passport;
    private String passportPath;
    private UploadedFile resume;
    private String resumePath;
    private String filePath;

    private String message;

    private Users user;

    private List<Users> allUser;

    @Autowired
    private UserService userService;

    public UsersBean() {
    }

    public String save() {
        userService.save();
        return "succes";
    }

    public String userList() {
        allUser = userService.viewUser();
        System.out.println(allUser);
        return "user_list?faces-redirect=true";

    }

    public String appCheck() {

        if (userService.totalApplicant() < 5) {
            return "application_form?faces-redirect=true";
        } else {

            FacesMessage message = new FacesMessage("Application Closed");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        }

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

    public UploadedFile getPassport() {
        return passport;
    }

    public void setPassport(UploadedFile passport) {
        this.passport = passport;
    }

    public UploadedFile getResume() {
        return resume;
    }

    public void setResume(UploadedFile resume) {
        this.resume = resume;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Users> getAllUser() {
        return allUser;
    }

    public void setAllUser(List<Users> allUser) {
        this.allUser = allUser;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getPassportPath() {
        return passportPath;
    }

    public void setPassportPath(String passportPath) {
        this.passportPath = passportPath;
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

}

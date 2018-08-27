package adio.group.utils;

import adio.group.controller.UsersBean;
import adio.group.model.Users;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.apache.commons.io.FilenameUtils;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LEOGOLD
 */
@Service
@Transactional
public class FileUploadService {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    UsersBean userBean;
    @Autowired
    ServletContext context;
    private Session session;
    Users user;
    Query query;

    //this is the path to upload the file on the server
    String relPathPassport = "files/passport/";
    String relPathResume = "/files/resume/";

    final static String ROOT = System.getProperty("user.dir");

    public String passportUpload() throws SQLException {
        String absolutePath = context.getRealPath("files/passport");
        String passportPath = ROOT + relPathPassport;
        Path folder = Paths.get(absolutePath);
        String fileName = FilenameUtils.getBaseName(userBean.getPassport().getFileName());
        String extension = FilenameUtils.getExtension(userBean.getPassport().getFileName());
        String filePath = null;
        if (checkPassPortSize()) {
            if (extension.equals("jpg") || extension.equals("jpeg")) {
                try {
                    Path file = Files.createTempFile(folder, fileName + "-", "." + extension);

                    InputStream inputStream = userBean.getPassport().getInputstream();
                    Files.copy(inputStream, file, StandardCopyOption.REPLACE_EXISTING);
                    filePath = relPathPassport + file.getFileName();

                    System.out.println("Checking the file path " + filePath);

                } catch (IOException ioex) {
                    System.err.println("File not uploaded, IOException caught " + ioex);
                }
                userBean.setPassportPath(filePath);
                return "success";
            } else {

                FacesMessage message = new FacesMessage("Invalid File Format, must be .jpeg file");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "invalid";
            }
        }
        return null;
    }

    public String resume() throws SQLException {
        String resumePath = ROOT + relPathResume;
        System.out.println("The root path is " + resumePath);
        Path folder = Paths.get(resumePath);
        String fileName = FilenameUtils.getBaseName(userBean.getResume().getFileName());
        String extension = FilenameUtils.getExtension(userBean.getResume().getFileName());
        String filePath = null;
        if (checkResumeSize()) {
            if (extension.equals("pdf") || extension.equals("doc") || extension.equals("docx")) {
                try {
                    Path file = Files.createTempFile(folder, fileName + "-", "." + extension);

                    InputStream inputStream = userBean.getPassport().getInputstream();
                    Files.copy(inputStream, file, StandardCopyOption.REPLACE_EXISTING);
                    filePath = relPathResume + file.getFileName();

                    System.out.println("Checking the file path " + filePath);

                } catch (IOException ioex) {
                    System.err.println("File not uploaded, IOException caught " + ioex);
                }
                userBean.setResumePath(filePath);

                return "success";
            } else {

                FacesMessage message = new FacesMessage("Invalid File Format, must be .pdf .doc or .docx file");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "invalid";
            }
        }
        return null;
    }

    public boolean checkResumeSize() {
        int max = 2 * 1024 * 1024; // 2MB

        if (userBean.getResume().getSize() > max) {
            FacesMessage message = new FacesMessage("File is too large, must be less than 2Mb");
            FacesContext.getCurrentInstance().addMessage(null, message);

            return false;
        } else {
            return true;
        }
    }

    public boolean checkPassPortSize() {
        double max = 0.1 * 1024 * 1024; // 2MB

        if (userBean.getResume().getSize() > max) {
            FacesMessage message = new FacesMessage("File is too large, must be less than 100Kb");
            FacesContext.getCurrentInstance().addMessage(null, message);

            return false;
        } else {
            return true;
        }
    }

    public String viewUserImage() {
        session = sessionFactory.getCurrentSession();
        query = session.createQuery("FROM Users user WHERE user.id=:id");
        query.setParameter("id", user.getId());
        user = (Users) query.uniqueResult();

        if (user != null) {
            String path = user.getPassport();
            userBean.setFilePath(path);
            return path;
        } else {
            return "failed";
        }
    }

    public List<Users> allImage() {
        List<Users> imageList = null;
        session = sessionFactory.getCurrentSession();
        query = session.createQuery("FROM Users");
        imageList = query.list();
        return imageList;
    }

}

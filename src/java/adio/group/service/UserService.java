package adio.group.service;

import adio.group.controller.UsersBean;
import adio.group.model.Users;
import adio.group.utils.FileUploadService;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LEOGOLD
 */
@Service
@Transactional
public class UserService {
    
    private static final String ROLE_ADMIN = "admin";
    private static final String ROLE_USER = "user";
    
    @Autowired
    private UsersBean userBean;
    @Autowired 
    private SessionFactory sessionFactory;
    @Autowired
    FileUploadService fileService;
    private Users user;
    private List<Users> userList;


    HttpSession httpSession;
    private Session session; 
    private Query query;
    int quryResult = 0;
    int role = 0;
    public String save() throws SQLException{
        fileService.resume();
        fileService.passportUpload(); 

//            user = new Users(
//                    userBean.getFirstName(),
//                    userBean.getLastName(),
//                    userBean.getEmail(),
//                    userBean.getPhoneNumber(),
//                    userBean.getCoverLetter(),
//                    userBean.getResumePath(),
//                    userBean.getPassportPath(),
//                    ROLE_USER
//            );
//
//            session = sessionFactory.getCurrentSession();
//            session.save(user);
            return "index";
         
        }
    
    
    
    public List<Users> viewUser() {

        try {

            session = sessionFactory.getCurrentSession();
            query = session.createQuery("FROM Users");
            userList = query.list();

        } catch (NullPointerException npex) {
            System.err.println("Can not perform " + npex.getMessage());
        }

            return userList;
        
    }
    
    public int totalApplicant(){
        
       return (int) session.createCriteria("Users")
                  .setProjection(Projections.rowCount())
                  .uniqueResult();
    }
}

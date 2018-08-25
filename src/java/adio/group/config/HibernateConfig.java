package adio.group.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import static org.hibernate.cfg.AvailableSettings.DIALECT; 
import static org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO; 
import static org.hibernate.cfg.AvailableSettings.SHOW_SQL; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author LEOGOLD
 */
@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class HibernateConfig {

  @Autowired
  private Environment env;
  
  

  @Bean
  public DataSource getDataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    
    // Setting JDBC properties
    dataSource.setDriverClassName(env.getProperty("database.driver"));
    dataSource.setUrl(env.getProperty("database.url"));
    dataSource.setUsername(env.getProperty("database.user"));
    dataSource.setPassword(env.getProperty("database.password"));
    
    return dataSource;
  }

  @Bean
  public SessionFactory getSessionFactory() {
    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(getDataSource());
    sessionBuilder.scanPackages("adio.group.model");

    Properties props = new Properties();
    // Setting Hibernate properties
    props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
    props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
    props.put(DIALECT, env.getProperty("hibernate.dialect"));

    sessionBuilder.addProperties(props);

    return sessionBuilder.buildSessionFactory();
  }

  @Autowired
  @Bean
  public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
    HibernateTransactionManager txManager = new HibernateTransactionManager(sessionFactory);
    return txManager;
  }
}

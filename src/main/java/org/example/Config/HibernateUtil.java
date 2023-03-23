package org.example.Config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private HibernateUtil() {}
    static {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();


        try {
//            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
//            sessionFactory = configuration.buildSessionFactory();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Exception e){
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static SessionFactory getSessionFactory() {
//        if(sessionFactory==null){
//            Properties properties = new Properties();
//            properties.setProperty("hibernate.connection.driver_class","com.mysql.cj.jdbc.Driver");
//            properties.setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/shop2");
//            properties.setProperty("hibernate.connection.username","root");
//            properties.setProperty("hibernate.connection.passs","amir22828_Sql");
//        }
        return sessionFactory;
    }

//    private static SessionFactory sessionFactory;
//
//    static{
//        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .configure()
//                .build();
//
//        try{
//            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//        }
//        catch(Exception e){
//            StandardServiceRegistryBuilder.destroy(registry);
//        }
//    }
//
//    public static SessionFactory getSessionFactory(){
//        return sessionFactory;
//    }
//
//    public static void writeMessagesToLogsFile(String message){
//        Logger logger = LogManager.getLogger(HibernateUtil.class);
//        logger.info(message);
//    }




}

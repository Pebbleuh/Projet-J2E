package com.example.projetj2e.persistance;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitListener implements ServletContextListener {

/* mettre dans le web.xml
<listener>
    <listener-class>InitListener</listener-class>
</listener>
*/

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName("com.example.projetj2e.persistance.MediathequeData");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }

}

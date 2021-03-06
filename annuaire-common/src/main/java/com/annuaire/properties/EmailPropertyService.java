package com.annuaire.properties;

import java.io.IOException;

public class EmailPropertyService {
    private static PropertyService service;
    private static EmailPropertyService instance;


    private EmailPropertyService() throws IOException{
        service = new PropertyService("email");
    }


    public static EmailPropertyService getInstance() throws IOException{
        if (instance == null) {
            instance = new EmailPropertyService();
        }
        return instance;
    }


    public String getHostname() {
        return service.getProperty("hostname");
    }


    public int getPort() {
        return Integer.valueOf(service.getProperty("port"));
    }

    public String getUsername(){ return service.getProperty("username");}

    public String getPassword(){return service.getProperty("password");}

    public String getAdminEmail(){return service.getProperty("admin.email");}

    public String getNotificationSubject(){return service.getProperty("notification.subject");}

    public String getSender(){return service.getProperty("from");}
}


package com.bdd.pet.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static ConfigReader manager;
    private static final Properties props = new Properties();
    private ConfigReader() throws IOException {
        InputStream inputStream = ConfigReader.class.getResourceAsStream("/AppConfig.properties");
        props.load(inputStream);
    }
    public static ConfigReader getInstance(){
        if(manager == null){
            synchronized (ConfigReader.class){
                try {
                    manager = new ConfigReader();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }return manager ;
    }
    public String getString(String key){
        return System.getProperty(key,props.getProperty(key));
    }
}

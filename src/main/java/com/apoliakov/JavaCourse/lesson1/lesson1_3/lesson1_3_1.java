package  com.apoliakov.JavaCourse.lesson1.lesson1_3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class lesson1_3_1 {
    public static void main(String[] args) throws IOException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "app.properties";

        Properties appProps = new Properties();
        appProps.load(new FileInputStream(appConfigPath));

        Properties catalogProps = new Properties();
        catalogProps.load(new FileInputStream(appConfigPath));

        String appVersion = appProps.getProperty("version");
        assertEquals("1.0", appVersion);

        assertEquals("files", catalogProps.getProperty("c1"));

        //xml
        String iconConfigPath = rootPath + "icons.xml";
        Properties iconProps = new Properties();
        iconProps.loadFromXML(new FileInputStream(iconConfigPath));

        assertEquals("icon1.jpg", iconProps.getProperty("fileIcon"));

        //getProperty(
        String appName = appProps.getProperty("name", "defaultName");
        String appGroup = appProps.getProperty("group", "baeldung");
        String appDownloadAddr = appProps.getProperty("downloadAddr");

        assertEquals("1.0", appVersion);
        assertEquals("TestApp", appName);
        assertEquals("baeldung", appGroup);
        assertNull(appDownloadAddr);

        //setProperty()
        appProps.setProperty("name", "NewAppName"); // update an old value
        appProps.setProperty("downloadAddr", "www.baeldung.com/downloads"); // add new key-value pair

        String newAppName = appProps.getProperty("name");
        assertEquals("NewAppName", newAppName);

        String newAppDownloadAddr = appProps.getProperty("downloadAddr");
        assertEquals("www.baeldung.com/downloads", newAppDownloadAddr);

        appProps.put("version", 2);

        //remove()
        String versionBeforeRemoval = appProps.getProperty("version");
       // assertEquals("1.0", versionBeforeRemoval);

        appProps.remove("version");
        String versionAfterRemoval = appProps.getProperty("version");
        assertNull(versionAfterRemoval);

        //store()
        String newAppConfigPropertiesFile = rootPath + "newApp.properties";
        appProps.store(new FileWriter(newAppConfigPropertiesFile), "store to properties file");

        //storeToXML()
        String newAppConfigXmlFile = rootPath + "newApp.xml";
        appProps.storeToXML(new FileOutputStream(newAppConfigXmlFile), "store to xml file");

        //
        appProps.list(System.out); // list all key-value pairs

        Enumeration<Object> valueEnumeration = appProps.elements();
        while (valueEnumeration.hasMoreElements()) {
            System.out.println(valueEnumeration.nextElement());
        }

        Enumeration<Object> keyEnumeration = appProps.keys();
        while (keyEnumeration.hasMoreElements()) {
            System.out.println(keyEnumeration.nextElement());
        }

        int size = appProps.size();
        assertEquals(6, size);

        //default.properties

        String defaultConfigPath = rootPath + "default.properties";
        Properties defaultProps = new Properties();
        defaultProps.load(new FileInputStream(defaultConfigPath));
        String defaultSite =  defaultProps.getProperty("site");
        appProps = new Properties(defaultProps);
        appProps.load(new FileInputStream(appConfigPath));

        assertEquals("1.0", appVersion);
        assertEquals("TestApp", appName);
        assertEquals("www.google.com", defaultSite);

    }
}
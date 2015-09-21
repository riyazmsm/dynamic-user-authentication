package org.wso2.carbon.dataservices.core.auth.lolc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

 

import org.wso2.carbon.dataservices.core.DataServiceFault;

import org.wso2.carbon.dataservices.core.auth.DynamicUserAuthenticator;


public class DynAuthClass implements DynamicUserAuthenticator {

       Properties prop = new Properties();

       InputStream input = null;

      

    @Override

    public String[] lookupCredentials(String user) throws DataServiceFault {

       try {


              input = getClass().getResourceAsStream("DBCredential.properties");

                     prop.load(input);

              } catch (FileNotFoundException e) {

                     // TODO Auto-generated catch block

                     e.printStackTrace();

              }

       catch (IOException e) {

              // TODO Auto-generated catch block

              e.printStackTrace();

       }     

      

       Enumeration<?> e = prop.propertyNames();

              while (e.hasMoreElements()) {

                     String key = (String) e.nextElement();

                     String value = prop.getProperty(key);

                     //System.out.println("Key : " + key + ", Value : " + value);

                    

                     /**

                         * check the property key value with the passed string

                         */

                     if (key.equals(user)){

                           return new String[] { value.substring(0,value.indexOf("/")),value.substring(value.indexOf("/")+1)};

                     }

              }

 

        throw new DataServiceFault("The user '" + user + "' not supported in invoking the target data service");

     }

}
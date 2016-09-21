/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.locator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author bao
 */
public class LookupServiceUtils {

    static Context c = null;
    
    public static Object lookupService(String serviceName) {
        /*
         * Dung cho tomcat
         */

        try {
            if (c == null) {
                InputStream inputStream = LookupServiceUtils.class.getResourceAsStream("/jndi.properties");
                Properties properties = new Properties();
                properties.load(inputStream);
                c = new InitialContext(properties);
            }
            return c.lookup(serviceName + "/remote");
        } catch (IOException e) {
            java.util.logging.Logger.getLogger("lookup-" + serviceName).log(java.util.logging.Level.SEVERE, e.toString());
            throw new RuntimeException(e);
        } catch (javax.naming.NamingException ne) {
            java.util.logging.Logger.getLogger("lookup-" + serviceName).log(java.util.logging.Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }

        /*
         * Dung cho jboss
         */
//        try {
//            /*    
//            Properties properties = new Properties();
//            properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
//            properties.put("java.naming.factory.url.pkgs", "=org.jboss.naming:org.jnp.interfaces");
//            properties.put("java.naming.provider.url", "10.0.0.153:1099");
//            Context c = new InitialContext(properties);
//             */
//            Context c = new InitialContext();
//            return c.lookup(serviceName + "/remote");
//        } catch (javax.naming.NamingException ne) {
//            java.util.logging.Logger.getLogger("lookup-" + serviceName).log(java.util.logging.Level.SEVERE, "exception caught", ne);
//            throw new RuntimeException(ne);
//        }

    }
}



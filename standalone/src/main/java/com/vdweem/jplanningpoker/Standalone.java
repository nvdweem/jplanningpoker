package com.vdweem.jplanningpoker;

import java.io.File;
import java.io.FilenameFilter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.swing.JOptionPane;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;

/**
 * com.vdweem.jplanningpoker.Standalone
 * @author       Niels
 */
public class Standalone {

    /**
     * Default application entrypoint.
     * @param args
     * @throws ServletException
     * @throws LifecycleException
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws ServletException, LifecycleException, UnsupportedEncodingException {
        // Determine the port which will be used for the server.
        String portStr = args.length > 0 ? args[0] : "8080";
        int port = 80;
        try {
            port = Integer.parseInt(portStr);
        } catch (Exception e) {}
        startCloseThread(port);

        // Get the current path and the WAR file which will be used for the application.
        String pathStr = Standalone.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        File path = new File(URLDecoder.decode(pathStr, "UTF-8")).getParentFile();
        File[] wars = path.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name != null && name.endsWith(".war");
            }
        });

        // If there are no war files found, display an error.
        if (wars.length == 0) {
            System.err.println("The jPlanningPoker war file should be available in this folder.");
            System.exit(1);
            return;
        }

        // Setup Tomcat.
        String appBase = path.getAbsolutePath();
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);

        // Setup the paths.
        tomcat.setBaseDir(appBase);
        tomcat.getHost().setAppBase(appBase);
        StandardServer server = (StandardServer)tomcat.getServer();
        AprLifecycleListener listener = new AprLifecycleListener();
        server.addLifecycleListener(listener);

        // Add the war file as the ROOT application.
        tomcat.addWebapp("/", wars[0].getAbsolutePath());
        tomcat.start();
        tomcat.getServer().await();
    }

    /**
     * Shows a JOptionpane message and shuts down the application if the message is closed.
     * @param port
     */
    private static void startCloseThread(final int port) {
        new Thread() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null, "Close this dialog to shutdown jPlanningPoker.\nThe application should be available at http://localhost" + (port == 80 ? "" : ":" + port));
                System.exit(0);
            }
        }.start();
    }
}

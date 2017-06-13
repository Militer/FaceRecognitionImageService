package application.util;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

/**
 * User: militer
 * Date: 05.06.2017.
 */
public class LibrariesLoader {
    private static final String LIB_BIN = "/lib/";
    private static final String DOCKER_PATH = "docker/";
    private static final String UBUNTU_PATH = "ubuntu/";
    private final static String LIBOPENCV_JAVA320_SO = "libopencv_java320.so";

    public static void load() {
        try {
            System.loadLibrary(LIBOPENCV_JAVA320_SO);
            System.out.println("Additional libraries loaded with success!");
        } catch (UnsatisfiedLinkError e) {
            System.out.println("Failed to load library. Trying to add them from jar...");
            loadFromJar();
            System.out.println("Libraries successfully added from jar!");
        }
    }

    /**
     * When packaged into JAR extracts libraries and
     * load them in memory
     */
    private static void loadFromJar() {
        loadLibrary(LIBOPENCV_JAVA320_SO);
    }

    /**
     * Puts library to temp dir and loads to memory
     */
    private static void loadLibrary(String name) {
        String pathToLib = LIB_BIN;
        if (DockerUtil.isDockerEnvironment()) {
            System.out.println("Loading Docker libraries...");
            pathToLib += DOCKER_PATH;
        } else {
            System.out.println("Loading Ubuntu libraries...");
            pathToLib += UBUNTU_PATH;
        }
        try (InputStream in = new ClassPathResource(pathToLib + name).getInputStream()) {
            // We have to create the file in tmp in order to have it on the filesystem
            File fileOut = new File(System.getProperty("java.io.tmpdir") + "/" + name);
            System.out.println("Writing dll to: " + fileOut.getAbsolutePath());
            System.out.println("New file created: " + fileOut.createNewFile());
            try (OutputStream out = new FileOutputStream(fileOut)) {
                IOUtils.copy(in, out);
            }
            System.load(fileOut.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package application.util;

import java.io.File;

/**
 * User: militer
 * Date: 10.06.2017.
 */
public class DockerUtil {
    public static boolean isDockerEnvironment() {
        File dockerEnvironmentFile = new File("/.dockerenv");
        return dockerEnvironmentFile.exists() && !dockerEnvironmentFile.isDirectory();
    }
}

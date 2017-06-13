package application;

import application.interceptor.performance.PerformanceInterceptor;
import application.util.LibrariesLoader;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

/**
 * Created by militer on 19.03.2017.
 */

@SpringBootApplication
@EnableEurekaClient
public class Main {
    public static final String staticFolder = "static";
    public static final String fileContainingFaces = "haarcascade_frontalface_alt";

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public PerformanceInterceptor getPerformanceInterceptor() {
        return new PerformanceInterceptor();
    }

    @Bean
    public CascadeClassifier getCascadeClassifier() throws IOException {
        LibrariesLoader.load();
        try (InputStream inputStream = new ClassPathResource(staticFolder + "/" + fileContainingFaces + ".xml").getInputStream()) {
            // We have to create the file in tmp in order to have it on the filesystem
            File frontalFaceCopy = File.createTempFile(fileContainingFaces, ".xml");
            try (OutputStream outputStream = new FileOutputStream(frontalFaceCopy)) {
                IOUtils.copy(inputStream, outputStream);
            } finally {
                inputStream.close();
            }
            return new CascadeClassifier(frontalFaceCopy.getAbsolutePath());
        }
    }
}

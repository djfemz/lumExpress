package africa.semicolon.lumexpress.service.cloud;

import com.cloudinary.utils.ObjectUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CloudinaryCloudServiceImplTest {
    @Autowired
    private CloudService cloudService;

    private MultipartFile file;

    @BeforeEach
    void setUp() throws IOException {
        Path path = Paths
                .get("/home/semicolon/Pictures/peak.jpg");

        file =
                new MockMultipartFile("peak",
                        Files.readAllBytes(path));
    }

    @Test
    @DisplayName("cloudinary upload test")
    void uploadTest() {
        try {
            String response =
                    cloudService.upload(file.getBytes(),
                            ObjectUtils.emptyMap());
            assertThat(response).isNotNull();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
package africa.semicolon.lumexpress.service.cloud;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties(prefix = "cloudinary.api")
@Configuration("cloudinaryProperties")
public class CloudinaryConfig {
    private String name;
    private String key;
    private String secret;

    @Bean
    public CloudinaryConfig cloudinaryConfig(){
        return  new CloudinaryConfig();
    }
}

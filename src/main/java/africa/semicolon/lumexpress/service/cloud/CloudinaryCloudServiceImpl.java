package africa.semicolon.lumexpress.service.cloud;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryCloudServiceImpl implements CloudService{
    private final Cloudinary cloudinary =
            new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dlvi5kpsr",
            "api_key", "828148521634137",
            "api_secret", "67Y0_KW-HRiWfLJq96J2q15_-g8",
            "secure", true));

    @Override
    public String upload(byte[] imageBytes, Map<?, ?> map) throws IOException {
        var uploadResponse =
                cloudinary.uploader()
                        .upload(imageBytes, ObjectUtils.emptyMap());
        return  uploadResponse.get("url").toString();
    }
}

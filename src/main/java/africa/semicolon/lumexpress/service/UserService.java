package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dto.request.LoginRequest;
import africa.semicolon.lumexpress.data.dto.response.LoginResponse;
import africa.semicolon.lumexpress.data.models.LumExpressUser;
import africa.semicolon.lumexpress.exception.UserNotFoundException;

public interface UserService {
    LoginResponse login(LoginRequest loginRequest);
    LumExpressUser getUserByUsername(String email) throws UserNotFoundException;

}

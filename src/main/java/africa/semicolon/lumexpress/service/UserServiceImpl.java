package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dto.request.LoginRequest;
import africa.semicolon.lumexpress.data.dto.response.LoginResponse;
import africa.semicolon.lumexpress.data.models.Admin;
import africa.semicolon.lumexpress.data.models.Customer;
import africa.semicolon.lumexpress.data.models.LumExpressUser;
import africa.semicolon.lumexpress.data.models.Vendor;
import africa.semicolon.lumexpress.data.repositories.AdminRepository;
import africa.semicolon.lumexpress.data.repositories.CustomerRepository;
import africa.semicolon.lumexpress.data.repositories.VendorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
       Optional<Customer> customer =  customerRepository.findByEmail(loginRequest.getEmail());
       if (customer.isPresent()&&customer.get().getPassword()
                .equals(loginRequest.getPassword())) return buildSuccessfulLoginResponse(customer.get());
       Optional<Admin> admin = adminRepository.findByEmail(loginRequest.getEmail());
       if (admin.isPresent() &&
               admin.get().getPassword()
                       .equals(loginRequest.getPassword())) return buildSuccessfulLoginResponse(admin.get());
       Optional<Vendor> vendor = vendorRepository.findByEmail(loginRequest.getEmail());
       if (vendor.isPresent()&&vendor.get().getPassword()
               .equals(loginRequest.getPassword())) return buildSuccessfulLoginResponse(vendor.get());

       return LoginResponse.builder()
               .code(400)
               .message("Login failed. Bad credentials")
               .build();
    }


    private LoginResponse buildSuccessfulLoginResponse(LumExpressUser user) {
        return LoginResponse.builder()
                .message("user logged in successfully")
                .code(200)
                .build();
    }


}

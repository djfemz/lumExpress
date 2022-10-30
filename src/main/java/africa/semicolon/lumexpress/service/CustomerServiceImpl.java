package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dto.request.CustomerRegistrationRequest;
import africa.semicolon.lumexpress.data.dto.request.EmailNotificationRequest;
import africa.semicolon.lumexpress.data.dto.request.UpdateCustomerDetails;
import africa.semicolon.lumexpress.data.dto.response.CustomerRegistrationResponse;
import africa.semicolon.lumexpress.data.models.*;
import africa.semicolon.lumexpress.data.repositories.CustomerRepository;
import africa.semicolon.lumexpress.exception.LumExpressException;
import africa.semicolon.lumexpress.exception.UserNotFoundException;
import africa.semicolon.lumexpress.service.notification.EmailNotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private final ModelMapper mapper=new ModelMapper();

    private final VerificationTokenService verificationTokenService;


    private final EmailNotificationService emailNotificationService;

    //TODO: remove all hardcoded values, store 3rd-party secrets in environment
    @Override
    public CustomerRegistrationResponse register(CustomerRegistrationRequest registerRequest) throws LumExpressException {
        Optional<Customer> foundCustomer = customerRepository.findByEmail(registerRequest.getEmail());
        if (foundCustomer.isPresent()) throw new LumExpressException(
            String.format("email %s has already been used", registerRequest.getEmail())
        );
        Customer customer = mapper.map(registerRequest, Customer.class);
        customer.setCart(new Cart());
        setCustomerAddress(registerRequest, customer);
        customer.getAuthorities().add(Authority.BUY);
        Customer savedCustomer = customerRepository.save(customer);
        log.info("customer saved in db::{}", savedCustomer);
        var token =verificationTokenService.createToken(savedCustomer.getEmail());
        emailNotificationService.
                sendHtmlMail(buildEmailNotificationRequest(token, savedCustomer.getFirstName()));
        return registrationResponseBuilder(savedCustomer);
    }

    private EmailNotificationRequest buildEmailNotificationRequest(VerificationToken verificationToken,
                                                                   String customerName) {
        var message = getEmailTemplate();
        String mail = null;
        if (message!=null) {
            //TODO: remove hard-coded url to app environment
            var verificationUrl="http://localhost:8080/api/v1/customer/verify/" + verificationToken.getToken();
            mail = String.format(message, customerName, verificationUrl);
            log.info("mailed url-->{}", verificationUrl);
        }
        return EmailNotificationRequest.builder()
                .userEmail(verificationToken.getUserEmail())
                .mailContent(mail)
                .build();
    }

    private String getEmailTemplate(){
        try(BufferedReader bufferedReader =
                    new BufferedReader(
                            new FileReader("/home/semicolon/Documents/spring-boot-classes/c11/lum-express/src/main/resources/welcome.txt"))){
            return bufferedReader.lines().collect(Collectors.joining());
        }catch(IOException exception){
            exception.printStackTrace();
        }
        return null;
    }

    private void setCustomerAddress(CustomerRegistrationRequest registerRequest, Customer customer) {
        Address customerAddress = new Address();
        customerAddress.setCountry(registerRequest.getCountry());
        customer.getAddresses().add(customerAddress);
    }

    private CustomerRegistrationResponse registrationResponseBuilder(
                                            Customer customer){
        return CustomerRegistrationResponse.builder()
                .message("success")
                .userId(customer.getId())
                .code(201)
                .build();
    }

    @Override
    public String completeCustomerProfile(UpdateCustomerDetails updateCustomerDetails) throws UserNotFoundException {
        Customer customerToUpdate = customerRepository.findById(updateCustomerDetails
                        .getCustomerId())
                .orElseThrow(()->new UserNotFoundException(
                        String.format("customer with id %d, not found",
                        updateCustomerDetails.getCustomerId())));
        mapper.map(updateCustomerDetails, customerToUpdate);
        Set<Address> customerAddressList = customerToUpdate.getAddresses();
        Optional<Address> foundAddress = customerAddressList.stream().findFirst();
        if (foundAddress.isPresent()) applyAddressUpdate(foundAddress.get(), updateCustomerDetails);
        customerToUpdate.getAddresses().add(foundAddress.get());
        customerToUpdate.setEnabled(true);
        Customer updatedCustomer  = customerRepository.save(customerToUpdate);
        return String.format("%s details updated successfully", updatedCustomer.getFirstName());
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    private void applyAddressUpdate(Address address, UpdateCustomerDetails updateCustomerDetails) {
        address.setBuildingNumber(updateCustomerDetails.getBuildingNumber());
        address.setCity(updateCustomerDetails.getCity());
        address.setStreet(updateCustomerDetails.getStreet());
        address.setState(updateCustomerDetails.getState());
    }

}

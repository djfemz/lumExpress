package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dto.request.CustomerRegistrationRequest;
import africa.semicolon.lumexpress.data.dto.request.UpdateCustomerDetails;
import africa.semicolon.lumexpress.data.dto.response.CustomerRegistrationResponse;
import africa.semicolon.lumexpress.data.models.Customer;
import africa.semicolon.lumexpress.exception.LumExpressException;
import africa.semicolon.lumexpress.exception.UserNotFoundException;

import java.util.List;

public interface CustomerService {
    CustomerRegistrationResponse register(CustomerRegistrationRequest registerRequest) throws LumExpressException;
    String completeCustomerProfile(UpdateCustomerDetails updateCustomerDetails) throws UserNotFoundException;

    List<Customer> getAllCustomers();
}

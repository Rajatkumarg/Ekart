package com.infy.ekart.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.ekart.dto.CustomerCredDTO;
import com.infy.ekart.dto.CustomerDTO;
import com.infy.ekart.exception.EKartException;
import com.infy.ekart.service.CustomerService;

//add the missing annotations
@RestController
@RequestMapping(value = "/customer-api")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerAPI {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private Environment environment;

	static Log logger = LogFactory.getLog(CustomerAPI.class);

	@GetMapping("/get")
	public String getData() {
		return "Rajat";
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<CustomerDTO> authenticateCustomer(@Valid @RequestBody CustomerCredDTO custCredDTO)
			throws EKartException {
		
		logger.info("CUSTOMER TRYING TO LOGIN, VALIDATING CREDENTIALS. CUSTOMER EMAIL ID: " + custCredDTO.getEmailId());
		CustomerDTO customerDTOFromDB = customerService.authenticateCustomer(custCredDTO.getEmailId(),
				custCredDTO.getPassword());
		logger.info("CUSTOMER LOGIN SUCCESS, CUSTOMER EMAIL : " + customerDTOFromDB.getEmailId());
		return new ResponseEntity<>(customerDTOFromDB, HttpStatus.OK);
	}

	@PostMapping(value = "/register")
	public ResponseEntity<String> registerCustomer(@Valid @RequestBody CustomerDTO customerDTO) throws EKartException {

		logger.info("CUSTOMER TRYING TO REGISTER. CUSTOMER EMAIL ID: " + customerDTO.getEmailId());
		String registeredWithEmailID = customerService.registerNewCustomer(customerDTO);
		registeredWithEmailID = environment.getProperty("CustomerAPI.CUSTOMER_REGISTRATION_SUCCESS")
				+ registeredWithEmailID;
		return new ResponseEntity<>(registeredWithEmailID, HttpStatus.OK);
	}

	@PutMapping(value = "/customer/{customerEmailId:.+}/address/")
	public ResponseEntity<String> updateShippingAddress(
			@PathVariable @Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+", message = "{invalid.email.format}") String customerEmailId,
			@RequestBody String address) throws EKartException {

		customerService.updateShippingAddress(customerEmailId, address);
		String modificationSuccessMsg = environment.getProperty("CustomerAPI.UPDATE_ADDRESS_SUCCESS");
		return new ResponseEntity<>(modificationSuccessMsg, HttpStatus.OK);

	}

	@DeleteMapping(value = "/customer/{customerEmailId:.+}")
	public ResponseEntity<String> deleteShippingAddress(
			@Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+", message = "{invalid.email.format}") @PathVariable("customerEmailId") String customerEmailId)
			throws EKartException {

		customerService.deleteShippingAddress(customerEmailId);
		String modificationSuccessMsg = environment.getProperty("CustomerAPI.CUSTOMER_ADDRESS_DELETED_SUCCESS");
		return new ResponseEntity<>(modificationSuccessMsg, HttpStatus.OK);

	}

}
package com.banking.customerservice.service;
import com.banking.customerservice.dto.*;
import com.banking.customerservice.dto.projection.CustomerSummaryDTO;
import com.banking.customerservice.service.*;

import com.banking.customerservice.entity.Customer;
import com.banking.customerservice.entity.CustomerEducation;
import com.banking.customerservice.entity.CustomerIdentity;
import com.banking.customerservice.entity.Dependant;
import com.banking.customerservice.exception.DataSaveException;
import com.banking.customerservice.exception.DuplicateCustomerException;
import com.banking.customerservice.repository.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(CustomerRequest request) {
    	
    	boolean exists = customerRepository.existsByFirstnameAndLastname(request.getFirstname(), request.getLastname());
    	
    	if (exists)
    	{
    		throw new DuplicateCustomerException("Customer already exists");
    	}
        Customer customer = new Customer();
        customer.setSalutation(request.getSalutation());
        customer.setFirstname(request.getFirstname());
        customer.setLastname(request.getLastname());
        customer.setDob(request.getDob());       
        customer.setGender(request.getGender());
        customer.setReligion(request.getReligion());        
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());        
        customer.setOccupation(request.getOccupation());
        customer.setAnnual_income(request.getAnnual_income());
        customer.setMarital_status(request.getMarital_status());
        customer.setSpouse_name(request.getSpouse_name());
        customer.setSpouse_education(request.getSpouse_education());
        customer.setSpouse_occupation(request.getSpouse_occupation());
        customer.setSpouse_income(request.getSpouse_income());

        if (request.getDependants() != null) {
            for (DependantDTO dto : request.getDependants()) {
                Dependant dependent = new Dependant();      
                dependent.setName(dto.getName());
                dependent.setRelationship(dto.getRelationship());
                dependent.setAge(dto.getAge());
                dependent.setCustomer(customer);
                customer.getDependents().add(dependent);
            }
        }
        
        if (request.getEducation()!=null) {
        	for (CustEducationDTO edudto : request.getEducation()) {
        		CustomerEducation education = new CustomerEducation();
        		education.setInstitution(edudto.getInstitution());
        		education.setQualification(edudto.getQualification());
        		education.setCompletionYear(edudto.getCompletion_year());
        	}
        }
        
        if (request.getIdentity()!=null) {
        	for (CustIDDTO iddto : request.getIdentity()) {
        		CustomerIdentity custidentity = new CustomerIdentity();
        		custidentity.setIdtype(iddto.getIdtype());
        		custidentity.setIdentityValue(iddto.getIdentityValue());
        		custidentity.setValidUntil(iddto.getValidUntil());
        	}
        }
        
        try {
        	return customerRepository.save(customer);
        } catch(DataSaveException ex) {
        	throw new DataSaveException("Data save Failed");        	
        }
        		
    }

    @Transactional(readOnly = true)
    public CustomerRequest getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return new CustomerRequest(customer);
    }
    
    public void deleteCustomer(Long id) {
    	boolean exists = customerRepository.existsById(id);
    	
    	if (!exists)
    	{
    		throw new RuntimeException("Customer not found");
    	}        
    	customerRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public List<CustomerRequest> getAllCustomers() {
        return customerRepository.findAll()
            .stream()
            .map(CustomerRequest::new)
            .toList();
    }
    
    public List<CustomerSummaryDTO> getCustomerSummary()  {
    	return customerRepository.findAllBy();
    }
}

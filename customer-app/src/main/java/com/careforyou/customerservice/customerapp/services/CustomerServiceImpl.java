package com.careforyou.customerservice.customerapp.services;

import com.careforyou.customerservice.customerapp.Dtos.AddressInfoDto;
import com.careforyou.customerservice.customerapp.Dtos.CustomerDto;
import com.careforyou.customerservice.customerapp.Dtos.EligibilityDto;
import com.careforyou.customerservice.customerapp.Dtos.NameInfoDto;
import com.careforyou.customerservice.customerapp.entities.AddressInfo;
import com.careforyou.customerservice.customerapp.entities.Customer;
import com.careforyou.customerservice.customerapp.entities.Eligibility;
import com.careforyou.customerservice.customerapp.entities.NameInfo;
import com.careforyou.customerservice.customerapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto getCustomerById(int id) {
        Customer customer = customerRepository.findById(id).orElseThrow(()-> new RuntimeException("Customer not found with Id: "+id));
        return mapEntityToDto(customer);
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customerToSave = mapDtoToEntity(customerDto);
        customerRepository.save(customerToSave);
        return customerDto;
    }

    @Override
    public CustomerDto findCustomerByCustomerNumber(String customerNumber) {
        Customer customer =customerRepository.findByCustomerNumber(customerNumber);
        if(customer == null){
            return  null;
        }
        return mapEntityToDto(customer);
    }

    private CustomerDto mapEntityToDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setBirthDate(customer.getBithDate());
        dto.setCustomerNumber(customer.getCustomerNumber());

        NameInfoDto nameInfoDto = new NameInfoDto();
        nameInfoDto.setFirstName(customer.getNameInfo().getFirstName());
        nameInfoDto.setMiddleName(customer.getNameInfo().getMiddleName());
        nameInfoDto.setLastName(customer.getNameInfo().getLastName());
        dto.setNameInfoDto(nameInfoDto);

        AddressInfoDto addressInfoDto = new AddressInfoDto();
        addressInfoDto.setAddressLine1(customer.getAddressInfo().getAddressLine1());
        addressInfoDto.setAddressLine2(customer.getAddressInfo().getAddressLine2());
        addressInfoDto.setAddressLine3(customer.getAddressInfo().getAddressLine3());
        addressInfoDto.setCity(customer.getAddressInfo().getCity());
        addressInfoDto.setState(customer.getAddressInfo().getState());
        addressInfoDto.setPinCode(customer.getAddressInfo().getPinCode());
        dto.setAddressInfoDto(addressInfoDto);

        EligibilityDto eligibilityDto = new EligibilityDto();
        eligibilityDto.setSmoker(customer.getEligibility().isSmoker());
        eligibilityDto.setTrafficViolationExits(customer.getEligibility().isTrafficViolationExits());
        eligibilityDto.setCriminalRecordExits(customer.getEligibility().isCriminalRecordExits());
        dto.setEligibilityDto(eligibilityDto);

        return dto;
    }

    public static Customer mapDtoToEntity(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setBithDate(dto.getBirthDate());
        customer.setCustomerNumber(dto.getCustomerNumber());

        NameInfo nameInfo = new NameInfo();
        nameInfo.setFirstName(dto.getNameInfoDto().getFirstName());
        nameInfo.setMiddleName(dto.getNameInfoDto().getMiddleName());
        nameInfo.setLastName(dto.getNameInfoDto().getLastName());
        customer.setNameInfo(nameInfo);

        AddressInfo addressInfo = new AddressInfo();
        addressInfo.setAddressLine1(dto.getAddressInfoDto().getAddressLine1());
        addressInfo.setAddressLine2(dto.getAddressInfoDto().getAddressLine2());
        addressInfo.setAddressLine3(dto.getAddressInfoDto().getAddressLine3());
        addressInfo.setCity(dto.getAddressInfoDto().getCity());
        addressInfo.setState(dto.getAddressInfoDto().getState());
        addressInfo.setPinCode(dto.getAddressInfoDto().getPinCode());
        customer.setAddressInfo(addressInfo);

        Eligibility eligibility = new Eligibility();
        eligibility.setSmoker(dto.getEligibilityDto().isSmoker());
        eligibility.setTrafficViolationExits(dto.getEligibilityDto().isTrafficViolationExits());
        eligibility.setCriminalRecordExits(dto.getEligibilityDto().isCriminalRecordExits());
        customer.setEligibility(eligibility);

        return customer;
    }

}

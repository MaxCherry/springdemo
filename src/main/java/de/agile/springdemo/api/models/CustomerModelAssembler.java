package de.agile.springdemo.api.models;

import de.agile.springdemo.api.controller.HalContactPersonController;
import de.agile.springdemo.api.controller.HalCustomerController;
import de.agile.springdemo.domain.vo.CustomerVO;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CustomerModelAssembler extends RepresentationModelAssemblerSupport<CustomerVO, CustomerModel> {

    private ModelMapper modelMapper;

    public CustomerModelAssembler(ModelMapper modelMapper) {
        super(HalCustomerController.class, CustomerModel.class);
        this.modelMapper = modelMapper;
    }


    @Override
    public CustomerModel toModel(CustomerVO customerVO) {
        CustomerModel modelWithId = createModelWithId(customerVO.getCustomerNo(), customerVO);
        //modelWithId.add(linkTo(methodOn(HalCustomerController.class).findOne(customerVO.getCustomerNo())).withSelfRel());
        modelWithId.add(linkTo(methodOn(HalContactPersonController.class).contactPeopleOfCustomer(customerVO.getCustomerNo())).withRel("contactPeople"));
        modelMapper.map(customerVO, modelWithId);
        return modelWithId;
    }
}

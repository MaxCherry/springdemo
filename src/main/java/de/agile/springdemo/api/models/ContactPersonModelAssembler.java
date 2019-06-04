package de.agile.springdemo.api.models;

import de.agile.springdemo.api.controller.HalContactPersonController;
import de.agile.springdemo.domain.vo.ContactPersonVO;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class ContactPersonModelAssembler extends RepresentationModelAssemblerSupport<ContactPersonVO, ContactPersonModel> {

    private ModelMapper modelMapper;
    private String customerNo;


    public ContactPersonModelAssembler(ModelMapper modelMapper, String customerNo) {
        super(HalContactPersonController.class, ContactPersonModel.class);
        this.modelMapper = modelMapper;
        this.customerNo = customerNo;
    }

    @Override
    public ContactPersonModel toModel(ContactPersonVO contactPersonVO) {
        ContactPersonModel model = createModelWithId(contactPersonVO.getId(), contactPersonVO, customerNo);
        modelMapper.map(contactPersonVO, model);
        EntityLinks entityLinks;
        return model;
    }


}
;
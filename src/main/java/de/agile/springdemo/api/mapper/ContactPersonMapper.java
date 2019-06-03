package de.agile.springdemo.api.mapper;

import de.agile.springdemo.domain.entity.ContactPerson;
import de.agile.springdemo.domain.vo.ContactPersonVO;
import org.mapstruct.Mapper;

@Mapper
public interface ContactPersonMapper {

    ContactPersonVO contactPersonToContactPersonVO(ContactPerson contactPerson);

    ContactPerson contactPersonVOToContactPerson(ContactPersonVO contactPerson);
}

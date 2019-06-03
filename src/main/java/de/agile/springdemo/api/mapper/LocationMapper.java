package de.agile.springdemo.api.mapper;

import de.agile.springdemo.domain.entity.Location;
import de.agile.springdemo.domain.vo.LocationVO;
import org.mapstruct.Mapper;

@Mapper
public interface LocationMapper {

    Location locationVOToLocation(LocationVO locationVO);

    LocationVO locationToLocationVO(Location location);

}

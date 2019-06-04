package de.agile.springdemo.api.controller;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class HalRootController {

    @GetMapping("/hal")
    ResponseEntity<RepresentationModel> root() {

        RepresentationModel representationModel = new RepresentationModel();

        representationModel.add(linkTo(methodOn(HalRootController.class).root()).withSelfRel());
        representationModel.add(linkTo(methodOn(HalCustomerController.class).findAll()).withRel("customers"));

        return ResponseEntity.ok(representationModel);
    }

}

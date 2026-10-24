package com.waterpurifier.customer.service.mapper;

import com.waterpurifier.customer.controller.requestDto.CustomerRequest;
import com.waterpurifier.customer.controller.responseDto.CustomerResponse;
import com.waterpurifier.customer.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CustomerMapper {

    @Mapping(target = "fullName", expression = "java(customerEntity.getFullName())")
    @Mapping(target = "alternateMobile", source = "alternativeNumber")
    @Mapping(target = "addressLine1", source = "address1")
    @Mapping(target = "addressLine2", source = "address2")
    @Mapping(target = "createdAt", source = "createdDate")
    @Mapping(target = "updatedAt", source = "lastModifiedDate")
    CustomerResponse toResponse(CustomerEntity customerEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customerCode", ignore = true)   // assigned by CustomerCodeGenerator
    @Mapping(target = "status", ignore = true)         // defaults to ACTIVE in the service
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "mobileNumber", source = "mobileNumber", qualifiedByName = "normaliseMobile")
    @Mapping(target = "alternativeNumber", source = "alternateMobile", qualifiedByName = "normaliseMobile")
    @Mapping(target = "email", source = "email", qualifiedByName = "normaliseEmail")
    @Mapping(target = "address1", source = "addressLine1")
    @Mapping(target = "address2", source = "addressLine2")
    CustomerEntity toEntity(CustomerRequest customerRequest);


    @org.mapstruct.Named("normaliseMobile")
    default String normaliseMobile(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed.replaceAll("[\\s-]", "");
    }

    @org.mapstruct.Named("normaliseEmail")
    default String normaliseEmail(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed.toLowerCase();
    }
}

package com.waterpurifier.customer.controller;

import com.waterpurifier.customer.controller.requestDto.CustomerRequest;
import com.waterpurifier.customer.controller.responseDto.CustomerResponse;
import com.waterpurifier.customer.controller.responseDto.ServiceResponse;
import com.waterpurifier.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
@Tag(name = "Customer management Operations",description = "Customer Operations like register,update, search and delete customers")
public class CustomerController {



    private final CustomerService customerService;

    @PostMapping
    @Operation(
            summary = "Register a new customer",
            description = """
                    Creates a customer and assigns a customer code such as CUST-2026-00001.
                    The customer starts in the ACTIVE status. Mobile number and email must be
                    unique across all customers; a conflict returns 409.""",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Customer details to register",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CustomerRequest.class),
                            examples = @ExampleObject(
                                    name = "Indian customer",
                                    summary = "A complete customer record",
                                    value = """
                                            {
                                              "firstName": "Asha",
                                              "lastName": "Sharma",
                                              "mobileNumber": "+919876543210",
                                              "alternateMobile": "+919812345678",
                                              "email": "asha.sharma@example.com",
                                              "addressLine1": "12 MG Road",
                                              "addressLine2": "Near Central Mall",
                                              "city": "Pune",
                                              "state": "Maharashtra",
                                              "postalCode": "411001",
                                              "preferredChannel": "EMAIL",
                                              "marketingConsent": true,
                                              "notes": "Prefers morning appointments"
                                            }"""))))
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Customer registered",
                    headers = @Header(
                            name = "Location",
                            description = "URI of the new customer",
                            schema = @Schema(type = "string")),
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CustomerResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "One or more fields are invalid",
                    content = @Content(
                            mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(
                    responseCode = "409",
                    description = "Mobile number or email already belongs to another customer",
                    content = @Content(
                            mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<ServiceResponse<CustomerResponse>> registerCustomer(CustomerRequest customerRequest){
       CustomerResponse created =  customerService.registrCustomer(customerRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();
        return ResponseEntity.ok(ServiceResponse.of(created));
    }

}

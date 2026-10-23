package com.waterpurifier.customer.controller.responseDto;

import com.waterpurifier.customer.utils.CustomerStatus;
import com.waterpurifier.customer.utils.NotificationChannel;

import java.time.Instant;
import java.util.UUID;

public record CustomerResponse(
        UUID id,
        String customerCode,
        String firstName,
        String lastName,
        String mobileNumber,
        String alternateMobile,
        String email,
        String addressLine1,
        String addressLine2,
        String city,
        String state,
        String postalCode,
        NotificationChannel preferredChannel,
        boolean marketingConsent,
        CustomerStatus status,
        String notes,
        Long version,
        Instant createdAt,
        Instant updatedAt
        ) {}


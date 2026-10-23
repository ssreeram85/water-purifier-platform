package com.waterpurifier.customer.controller.requestDto;

import com.waterpurifier.customer.utils.NotificationChannel;
import jakarta.validation.constraints.*;

public record CustomerRequest(

        @NotBlank @Size(max = 80) String firstName,
        @NotBlank @Size(max = 80) String lastName,
        @NotBlank
        @Pattern(regexp = "^\\+[1-9]\\d{7,14}$", message = "must be in E.164 format, for example +919876543210")
        String mobileNumber,
        @Pattern(regexp = "^\\+[1-9]\\d{7,14}$", message = "must be in E.164 format, for example +919876543210")
        String alternateMobile,
        @Email @Size(max = 255) String email,
        @Size(max = 255) String addressLine1,
        @Size(max = 255) String addressLine2,
        @Size(max = 100) String city,
        @Size(max = 100) String state,
        @Pattern(regexp = "^$|^[1-9][0-9]{5}$", message = "must be a valid 6-digit PIN code")
        String postalCode,
        @NotNull NotificationChannel preferredChannel,
        boolean marketingConsent,
        @Size(max = 500) String notes
) {
}

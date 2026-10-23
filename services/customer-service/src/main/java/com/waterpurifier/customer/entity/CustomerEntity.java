package com.waterpurifier.customer.entity;

import com.waterpurifier.customer.utils.CustomerStatus;
import com.waterpurifier.customer.utils.NotificationChannel;

import com.waterpurifier.customer.validations.ValidMobileNumber;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "customers")
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@NoArgsConstructor
public class CustomerEntity {

    @Id
    private UUID id;

    @Column(name = "customer_code", nullable = false, length = 30, updatable = false)
    private String customerCode;

    @Column(name = "first_name", nullable = false, length = 80)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 80)
    private String lastName;

    @NotBlank(message = "Mobile number is required")
    @ValidMobileNumber(countryCode = "+91")
    @Column(name = "mobile_number", nullable = false, length = 20)
    private String mobileNumber;

    @ValidMobileNumber(countryCode = "+91")
    @Column(name = "alternate_mobile", length = 20)
    private String alternativeNumber;

    @Email
    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "address_line1", length = 255)
    private String address1;
    @Column(name = "address_line2", length = 255)
    private String address2;
    @Column(name = "city", length = 100)
    private String city;
    @Column(name = "state", length = 100)
    private String state;
    @Column(name = "postal_code", length = 6)
    private String postalCode;
    @Enumerated(EnumType.STRING)
    @Column(name = "preferred_channel", length = 20, nullable = false)
    private NotificationChannel preferredChannel;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private CustomerStatus status;
    @Column(name = "marketing_consent", nullable = false)
    private boolean marketingConsent;
    @Column(name = "notes", length = 500)
    private String notes;
    @Version
    @Column(name = "version", nullable = false)
    private Long version;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdDate;
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Instant lastModifiedDate;

    @PrePersist
    void assignId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}

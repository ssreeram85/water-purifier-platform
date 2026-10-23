package com.waterpurifier.customer.entity;

import com.waterpurifier.customer.utils.PurchaseStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "purifier_purchases")
@Setter
@Getter
@NoArgsConstructor
public class PurifierPurchase {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @Column(name = "product_model", nullable = false, length = 120)
    private String productModel;

    @Column(name = "product_variant", length = 120)
    private String productVariant;

    @Column(name = "serial_number", length = 80)
    private String serialNumber;

    @Column(name = "invoice_number", nullable = false, length = 80)
    private String invoiceNumber;

    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

    @Column(name = "purchase_price", precision = 12, scale = 2)
    private BigDecimal purchasePrice;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(name = "warranty_months")
    private Integer warrantyMonths;

    @Column(name = "installation_date")
    private LocalDate installationDate;

    @Column(name = "amc_start_date")
    private LocalDate amcStartDate;

    @Column(name = "amc_end_date")
    private LocalDate amcEndDate;

    @Column(name = "dealer_name", length = 150)
    private String dealerName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PurchaseStatus status;

    @Version
    @Column(nullable = false)
    private Long version;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    void assignId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    public LocalDate warrantyStartDate() {
        return installationDate != null ? installationDate : purchaseDate;
    }

    public LocalDate warrantyEndDate() {
        return warrantyMonths == null ? null : warrantyStartDate().plusMonths(warrantyMonths);
    }
}

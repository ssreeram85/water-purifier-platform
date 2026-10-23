package com.waterpurifier.customer.repository;

import com.waterpurifier.customer.entity.PurifierPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PurifierPurchaseRepository extends JpaRepository<PurifierPurchase, UUID> {

    List<PurifierPurchase> findByCustomerIdOrderByPurchaseDateDesc(UUID customerId);

    Optional<PurifierPurchase> findByIdAndCustomerId(UUID id, UUID customerId);

    boolean existsByInvoiceNumber(String invoiceNumber);

    boolean existsByInvoiceNumberAndIdNot(String invoiceNumber, UUID id);

    boolean existsBySerialNumber(String serialNumber);

    boolean existsBySerialNumberAndIdNot(String serialNumber, UUID id);

    long countByCustomerId(UUID customerId);
}

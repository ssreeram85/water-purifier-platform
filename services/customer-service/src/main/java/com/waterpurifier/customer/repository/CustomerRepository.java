package com.waterpurifier.customer.repository;

import com.waterpurifier.customer.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {

    Optional<CustomerEntity>  findByMobileNumber(String  mobileNumber);
    boolean existsByMobileNumber(String mobileNumber);
    boolean existsByMobileNumberAndIdNot(String mobileNumber, UUID id);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCaseAndIdNot(String email, UUID id);

    @Query("""
            select c from Customer c
            where :search is null
               or lower(concat(c.firstName, ' ', c.lastName)) like lower(concat('%', :search, '%'))
               or lower(coalesce(c.email, '')) like lower(concat('%', :search, '%'))
               or c.mobileNumber like concat('%', :search, '%')
               or lower(c.customerCode) like lower(concat('%', :search, '%'))
            """)
    Page<CustomerEntity> search(@Param("search") String search, Pageable pageable);


}

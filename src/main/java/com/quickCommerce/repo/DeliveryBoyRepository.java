package com.quickCommerce.repo;

import com.quickCommerce.deliveryentity.DeliveryBoy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryBoyRepository extends JpaRepository<DeliveryBoy, Long> {
    Optional<DeliveryBoy> findByEmail(String email);

}

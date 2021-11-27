package com.maruli.sofco.repository;

import com.maruli.sofco.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}

package com.mediasoft.qrpay.service;
import com.mediasoft.qrpay.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation,Long> {

}

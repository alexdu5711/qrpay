package com.mediasoft.qrpay.service;

import com.mediasoft.qrpay.entities.Annonces;
import com.mediasoft.qrpay.entities.Operation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation,Long> {

}

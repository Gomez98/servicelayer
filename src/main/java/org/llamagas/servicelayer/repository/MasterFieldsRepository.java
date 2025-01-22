package org.llamagas.servicelayer.repository;

import org.llamagas.servicelayer.model.domain.MasterFields;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterFieldsRepository extends JpaRepository<MasterFields, String> {
}

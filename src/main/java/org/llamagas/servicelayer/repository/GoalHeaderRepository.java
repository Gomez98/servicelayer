package org.llamagas.servicelayer.repository;

import org.llamagas.servicelayer.model.domain.GoalHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalHeaderRepository extends JpaRepository<GoalHeader, String> {
}

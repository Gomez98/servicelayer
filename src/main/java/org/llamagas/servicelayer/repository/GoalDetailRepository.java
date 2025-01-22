package org.llamagas.servicelayer.repository;

import org.llamagas.servicelayer.model.domain.GoalDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoalDetailRepository extends JpaRepository<GoalDetail, String> {

    Optional<GoalDetail> findByGoalHeader_Id(String goalHeaderId);
}

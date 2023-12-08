package com.sxnd.develop.sample.infrastructure.persistence.repository;



import com.sxnd.develop.sample.infrastructure.persistence.entity.StageRouteFileEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface StageRouteFileRepository extends CrudRepository<StageRouteFileEntity, String>, JpaSpecificationExecutor<StageRouteFileEntity> {
	
	List<StageRouteFileEntity> findByTruckId(String jobId);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM StageRouteFileEntity a WHERE a.truckId = :jobId")
	void deleteByTruckId(@Param("jobId") String jobId);

	@Query("SELECT a FROM StageRouteFileEntity a WHERE a.taskSn = :taskSn and a.commandType = :commandType")
	StageRouteFileEntity getStageFileFromTaskSnAndCommandType(Integer taskSn, Integer commandType);
}

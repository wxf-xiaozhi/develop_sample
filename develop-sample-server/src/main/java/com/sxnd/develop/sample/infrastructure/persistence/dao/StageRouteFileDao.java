package com.sxnd.develop.sample.infrastructure.persistence.dao;




import com.sxnd.develop.sample.common.enums.RouteTypeEnum;
import com.sxnd.develop.sample.model.StageRouteFile;

import java.util.List;
import java.util.UUID;

public interface StageRouteFileDao extends Dao<StageRouteFile> {

	void deleteByTruckId(UUID id);

	List<StageRouteFile> findByTruckId(UUID id);

	void deleteAll();

    StageRouteFile getFileFromList(List<StageRouteFile> fileList, RouteTypeEnum road);

	StageRouteFile getFileFromTaskSnAndCommandType(Integer taskSn, Integer commandType);
}

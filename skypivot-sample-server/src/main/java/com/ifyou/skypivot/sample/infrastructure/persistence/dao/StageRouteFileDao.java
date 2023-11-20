package com.ifyou.skypivot.sample.infrastructure.persistence.dao;




import com.ifyou.skypivot.sample.enums.RouteTypeEnum;
import com.ifyou.skypivot.sample.model.StageRouteFile;

import java.util.List;
import java.util.UUID;

public interface StageRouteFileDao extends Dao<StageRouteFile> {

	void deleteByTruckId(UUID id);

	List<StageRouteFile> findByTruckId(UUID id);

	void deleteAll();

    StageRouteFile getFileFromList(List<StageRouteFile> fileList, RouteTypeEnum road);

	StageRouteFile getFileFromTaskSnAndCommandType(Integer taskSn, Integer commandType);
}

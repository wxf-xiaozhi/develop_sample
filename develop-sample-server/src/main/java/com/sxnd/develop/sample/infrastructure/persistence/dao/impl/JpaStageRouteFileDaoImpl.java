package com.sxnd.develop.sample.infrastructure.persistence.dao.impl;




import com.sxnd.develop.sample.enums.RouteTypeEnum;
import com.sxnd.develop.sample.infrastructure.util.DaoUtil;
import com.sxnd.develop.sample.utils.SqlDao;
import com.sxnd.develop.sample.infrastructure.persistence.dao.StageRouteFileDao;
import com.sxnd.develop.sample.infrastructure.persistence.entity.StageRouteFileEntity;
import com.sxnd.develop.sample.infrastructure.persistence.repository.StageRouteFileRepository;
import com.sxnd.develop.sample.infrastructure.util.UUIDConverter;
import com.sxnd.develop.sample.infrastructure.persistence.dao.JpaAbstractDao;
import com.sxnd.develop.sample.model.StageRouteFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Component
@SqlDao
@Slf4j
public class JpaStageRouteFileDaoImpl extends JpaAbstractDao<StageRouteFileEntity, StageRouteFile> implements StageRouteFileDao {

	@Autowired
	StageRouteFileRepository stageRouteFileRepository;

	@Override
	protected Class<StageRouteFileEntity> getEntityClass() {

		return StageRouteFileEntity.class;
	}

	@Override
	protected CrudRepository<StageRouteFileEntity, String> getCrudRepository() {

		return stageRouteFileRepository;
	}

	@Override
	public void deleteByTruckId(UUID id) {
		stageRouteFileRepository.deleteByTruckId(UUIDConverter.fromTimeUUID(id));

	}

	@Override
	public List<StageRouteFile> findByTruckId(UUID id) {
		Collection<StageRouteFileEntity> entities = stageRouteFileRepository.findByTruckId(UUIDConverter.fromTimeUUID(id));
		return  DaoUtil.convertDataList(entities);
	}

	@Override
	public void deleteAll() {
		stageRouteFileRepository.deleteAll();

	}

	@Override
	public StageRouteFile getFileFromList(List<StageRouteFile> fileList, RouteTypeEnum type) {
		StageRouteFile file = null;
		if (null != fileList && !fileList.isEmpty() && null != type && !type.equals(RouteTypeEnum.UNKNOW)) {
			for (StageRouteFile e : fileList) {
				if (e.getRouteType().equals(type)) {
					file = e;
					break;
				}
			}
		}
		return file;
	}

	@Override
	public StageRouteFile getFileFromTaskSnAndCommandType(Integer taskSn, Integer commandType) {
		StageRouteFileEntity stageRouteFileEntity = stageRouteFileRepository.getStageFileFromTaskSnAndCommandType(taskSn, commandType);
		if(stageRouteFileEntity != null){
			return stageRouteFileEntity.toData();
		}else{
			log.info("任务taskSn:{},commandType:{}",taskSn,commandType);
		}
		return  null;

	}
}

package com.sxnd.develop.sample.infrastructure.persistence.entity;



import com.sxnd.develop.sample.constants.ModelConstants;
import com.sxnd.develop.sample.common.enums.RouteTypeEnum;
import com.sxnd.develop.sample.infrastructure.util.UUIDConverter;
import com.sxnd.develop.sample.infrastructure.util.mapping.JsonStringType;
import com.sxnd.develop.sample.model.StageRouteFile;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Table(name = ModelConstants.STAGE_ROUTE_FILE)
public class StageRouteFileEntity extends AbsBaseSqlEntity<StageRouteFile>  {
	//implements BaseEntityInterface<StageRouteFile>{

	@Column(name = ModelConstants.STAGE_ROUTE_FILE_TRUCK_ID_PROPERTY)
	private String truckId;

	@Column(name = ModelConstants.STAGE_ROUTE_FILE_ROUTE_TYPE_PROPERTY)
	private Integer routeType;

	@Column(name = ModelConstants.STAGE_ROUTE_FILE_ROUTE_FILE_NAME_PROPERTY)
	private String routeFileName;

	@Column(name = ModelConstants.STAGE_ROUTE_FILE_ROUTE_FILE_MD5_PROPERTY)
	private String routeFileMD5;

	@Column(name = ModelConstants.STAGE_ROUTE_FILE_TASK_SN)
	private Integer taskSn;

	@Column(name = ModelConstants.STAGE_ROUTE_FILE_COMMAND_TYPE)
	private Integer commandType;

	@Column(name = ModelConstants.STAGE_ROUTE_FILE_CREATE_TIME)
	private Date createTime;

	@Override
	public StageRouteFile toData() {

		StageRouteFile file = new StageRouteFile();
		file.setId(UUIDConverter.fromString(id));
		file.setTruckId(UUIDConverter.fromString(truckId));
		file.setRouteType(RouteTypeEnum.valueOf(routeType));
		file.setRouteFileName(routeFileName);
		file.setRouteFileMD5(routeFileMD5);
		file.setTaskSn(taskSn);
		file.setCommandType(commandType);
		file.setCreateTime(createTime);
		return file;
	}

	public StageRouteFileEntity(StageRouteFile file) {
		if(file.getId() != null) {
			this.id = UUIDConverter.fromTimeUUID(file.getId());
		}

		this.truckId = UUIDConverter.fromTimeUUID(file.getTruckId());
		this.routeType = file.getRouteType().getCode();
		this.routeFileName = file.getRouteFileName();
		this.routeFileMD5 = file.getRouteFileMD5();
		this.commandType = file.getCommandType();
		this.taskSn = file.getTaskSn();
		this.createTime = file.getCreateTime();
	}


	public StageRouteFileEntity() {
		super();

	}

}

package com.ifyou.skypivot.sample.model;

import com.ifyou.skypivot.sample.enums.RouteTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class StageRouteFile implements Serializable {

	private static final long serialVersionUID = 2807453083619743362L;

	private UUID id;
	private UUID truckId;
	private Integer taskSn;
	private Integer commandType;
	private RouteTypeEnum routeType;
	private String routeFileName;
	private String routeFileMD5;
	private Date createTime;
	public StageRouteFile() {
		super();
	}

}

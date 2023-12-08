/**
 * Copyright © 2016-2018 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sxnd.develop.sample.infrastructure.persistence.entity;


import com.sxnd.develop.sample.constants.ModelConstants;
import com.sxnd.develop.sample.infrastructure.util.UUIDConverter;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * Created by ashvayka on 13.07.17.
 */
@Data
@MappedSuperclass
public abstract class AbsBaseSqlEntity<D> implements BaseEntityInterface<D> {

    @Id
    @Column(name = ModelConstants.ID_PROPERTY)
    protected String id;

    @Override
    public UUID getId() {
        return toUUID(id);
    }

    public void setId(UUID uid) {
        this.id = toString(uid);
    }

    protected UUID toUUID(String src){
    	UUID uuid = null;
    	
    	if(null != src && !src.isEmpty()) {
    		uuid = UUIDConverter.fromString(src);
    	}
        return uuid;
    }

    protected String toString(UUID timeUUID){
    	String sID = null;
    	
    	if(null != timeUUID) {
    		sID = UUIDConverter.fromTimeUUID(timeUUID);
    	}
        return sID;
    }
    
    protected void setData(D d){
    }

    protected UUID stringToUUID(String sUUID){
        return toUUID(sUUID);
    }

    protected String uuidToString(UUID uuid){
        return toString(uuid);
    }

    public static Integer boolToIntg(Boolean bValue) {
    	Integer rInteger = 0;
    	
    	if(null != bValue) {
    		if(bValue) {
    			rInteger = 1;
    		}
    	}
    	return rInteger;
	}
    
    public static Boolean intToBool(Integer iValue) {
    	Boolean bReturn = false;
    	
    	if(null != iValue) {
    		if(iValue.equals(0)) {
    			bReturn = false;
    		}
    		if(iValue.equals(1)) {
    			bReturn = true;
    		}
    	}
    	return bReturn;
	}
}

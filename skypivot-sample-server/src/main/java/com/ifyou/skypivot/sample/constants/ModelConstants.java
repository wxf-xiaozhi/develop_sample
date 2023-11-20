/**
 * Copyright © 2016-2018 The Thingsboard Authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ifyou.skypivot.sample.constants;


import cn.hutool.core.util.ArrayUtil;
import com.ifyou.skypivot.sample.infrastructure.util.UUIDConverter;

import java.util.UUID;

public class ModelConstants {

    private ModelConstants() {
    }

    public static final UUID NULL_UUID = UUID.fromString("0");
    public static final String NULL_UUID_STR = UUIDConverter.fromTimeUUID(NULL_UUID);
//    public static final TenantId SYSTEM_TENANT = new TenantId(ModelConstants.NULL_UUID);

    //    public static final UUID MAX_UUID = UUIDs.startOf(Integer.MAX_VALUE);
    public static final String MAX_UUID_STR = "fffffffffffffffffffffffffffffff";

    /**
     * Generic constants.
     */
    public static final String ID_PROPERTY = "id";
    public static final String USER_ID_PROPERTY = "user_id";
    public static final String TENANT_ID_PROPERTY = "tenant_id";
    public static final String DEVICE_ID_PROPERTY = "device_id";
    public static final String DEVICE_TYPE_ID_PROPERTY = "device_type_id";
    public static final String MODEL_ID_PROPERTY = "model_id";
    public static final String TITLE_PROPERTY = "title";
    public static final String ALIAS_PROPERTY = "alias";
    public static final String SEARCH_TEXT_PROPERTY = "search_text";
    public static final String ADDITIONAL_INFO_PROPERTY = "additional_info";
    public static final String ENTITY_TYPE_PROPERTY = "entity_type";
    public static final String PARTITION_COLUMN = "partition";
    public static final String CREATE_TIME_PROPERTY = "create_time";
    public static final String UPDATE_TIME_PROPERTY = "update_time";
    public static final String OP_USER_SN_PROPERTY = "op_user_sn";
    public static final String KEY_COLUMN = "key";
    public static final String TS_COLUMN = "ts";



    public static final String ATDS_DISPATCHING_STAGE_JOB_EVENT = "atds_dispatching_stage_job_event";
    public static final String ATDS_DISPATCHING_STAGE_JOB_EVENT_TRANSCATION_ID_PROPERTY = "transcation_id";
    public static final String ATDS_DISPATCHING_STAGE_JOB_EVENT_TASK_SN_PROPERTY = "task_sn";
    public static final String ATDS_DISPATCHING_STAGE_JOB_EVENT_INITIATOR_TYPE_PROPERTY = "initiator_type";
    public static final String ATDS_DISPATCHING_STAGE_JOB_EVENT_JOB_TYPE_PROPERTY = "job_type";
    public static final String ATDS_DISPATCHING_STAGE_JOB_EVENT_DISPTCHING_TYPE_PROPERTY = "disptching_type";
    public static final String ATDS_DISPATCHING_STAGE_JOB_EVENT_TRUCK_ID_PROPERTY = "truck_id";
    public static final String ATDS_DISPATCHING_STAGE_JOB_EVENT_POSITION_INFO_PROPERTY = "position_info";
    public static final String ATDS_DISPATCHING_STAGE_JOB_EVENT_POSITION_TYPE_PROPERTY = "position_type";
    public static final String ATDS_DISPATCHING_STAGE_JOB_EVENT_MATERIAL_CODE_PROPERTY = "material_code";
    public static final String ATDS_DISPATCHING_STAGE_JOB_EVENT_FROM_ID_PROPERTY = "from_id";
    public static final String ATDS_DISPATCHING_STAGE_JOB_EVENT_FROM_ID_TYPE_PROPERTY = "from_id_type";
    public static final String ATDS_DISPATCHING_STAGE_JOB_EVENT_TO_ID_PROPERTY = "to_id";
    public static final String ATDS_DISPATCHING_STAGE_JOB_EVENT_TO_ID_TYPE_PROPERTY = "to_id_type";
    public static final String ATDS_DISPATCHING_STAGE_JOB_EVENT_TO_ID_NAME_PROPERTY = "to_id_name";
    public static final String ATDS_DISPATCHING_STAGE_JOB_EVENT_DISPATCHING_RESULT_PROPERTY = "dispatching_result";
    public static final String ATDS_DISPATCHING_STAGE_JOB_EVENT_FAILURE_REASON_PROPERTY = "failure_reason";
    public static final String ATDS_DISPATCHING_STAGE_JOB_EVENT_FILE_NAME_PROPERTY = "file_name";
    public static final String ATDS_DISPATCHING_STAGE_JOB_EVENT_SUB_AREA_SN_PROPERTY = "sub_area_sn";
    public static final String ATDS_DISPATCHING_STAGE_JOB_EVENT_EVENT_TIME_PROPERTY = "event_time";
    public static final String ATDS_DISPATCHING_STAGE_JOB_EVENT_DRIVE_MODE_PROPERTY = "drive_mode";



    public static final String STAGE_ROUTE_FILE = "stage_route_file";
    public static final String STAGE_ROUTE_FILE_TRUCK_ID_PROPERTY = "truck_id";
    public static final String STAGE_ROUTE_FILE_ROUTE_TYPE_PROPERTY = "route_type";
    public static final String STAGE_ROUTE_FILE_ROUTE_FILE_NAME_PROPERTY = "route_file_name";
    public static final String STAGE_ROUTE_FILE_ROUTE_FILE_MD5_PROPERTY = "route_file_md5";
    public static final String STAGE_ROUTE_FILE_TASK_SN = "task_sn";
    public static final String STAGE_ROUTE_FILE_COMMAND_TYPE = "command_type";
    public static final String STAGE_ROUTE_FILE_CREATE_TIME = "create_time";

    public static final String ATDS_DISPATCHING_STAGE_JOB = "atds_dispatching_stage_job";
    public static final String ATDS_DISPATCHING_STAGE_JOB_SUB_AREA_ID_PROPERTY = "sub_area_id";
    public static final String ATDS_DISPATCHING_STAGE_JOB_SUB_AREA_SN_PROPERTY = "sub_area_sn";
    public static final String ATDS_DISPATCHING_STAGE_JOB_SHIFT_ID_PROPERTY = "shift_id";
    public static final String ATDS_DISPATCHING_STAGE_JOB_TRUCK_ID_PROPERTY = "truck_id";
    public static final String ATDS_DISPATCHING_STAGE_JOB_TRUCK_DRIVER_ID_PROPERTY = "truck_driver_id";
    public static final String ATDS_DISPATCHING_STAGE_JOB_TRUCK_DRIVER_SN_PROPERTY = "truck_driver_sn";
    public static final String ATDS_DISPATCHING_STAGE_JOB_JOB_TYPE_PROPERTY = "job_type";
    public static final String ATDS_DISPATCHING_STAGE_JOB_DISPTCHING_TYPE_PROPERTY = "disptching_type";
    public static final String ATDS_DISPATCHING_STAGE_JOB_FROM_ID_PROPERTY = "from_id";
    public static final String ATDS_DISPATCHING_STAGE_JOB_FROM_ID_TYPE_PROPERTY = "from_id_type";
    public static final String ATDS_DISPATCHING_STAGE_JOB_TO_ID_PROPERTY = "to_id";
    public static final String ATDS_DISPATCHING_STAGE_JOB_TASK_SN_PROPERTY = "task_sn";
    public static final String ATDS_DISPATCHING_STAGE_JOB_TO_ID_TYPE_PROPERTY = "to_id_type";
    public static final String ATDS_DISPATCHING_STAGE_JOB_TO_ID_IMEI_PROPERTY = "to_id_imei";
    public static final String ATDS_DISPATCHING_STAGE_JOB_MATERIAL_CODE_PROPERTY = "material_code";
    public static final String ATDS_DISPATCHING_STAGE_JOB_TASK_STATUS_PROPERTY = "task_status";
    public static final String ATDS_DISPATCHING_STAGE_JOB_STATUS_PROPERTY = "status";
    public static final String ATDS_DISPATCHING_STAGE_JOB_START_TIME_PROPERTY = "start_time";
    public static final String ATDS_DISPATCHING_STAGE_JOB_FINISH_TIME_PROPERTY = "finish_time";
    public static final String ATDS_DISPATCHING_STAGE_JOB_PASS_NODES_PROPERTY = "pass_nodes";
    public static final String ATDS_DISPATCHING_STAGE_JOB_PATH_MILEAGE_PROPERTY = "path_mileage";
    public static final String ATDS_DISPATCHING_STAGE_JOB_CURRENT_NODE_PROPERTY = "current_node";
    public static final String ATDS_DISPATCHING_STAGE_JOB_CURRENT_MILEAGE_PROPERTY = "current_mileage";
    public static final String ATDS_DISPATCHING_STAGE_JOB_FAILURE_REASON_PROPERTY = "failure_reason";
    public static final String ATDS_DISPATCHING_STAGE_JOB_SITE_SN_PROPERTY = "site_sn";
    public static final String ATDS_DISPATCHING_STAGE_JOB_SITE_TYPE_PROPERTY = "site_type";
    public static final String ATDS_DISPATCHING_STAGE_JOB_DRIVE_FLAG_PROPERTY = "drive_in";
    public static final String ATDS_DISPATCHING_STAGE_JOB_DRIVE_MODE_PROPERTY = "drive_mode";
    public static final String ATDS_DISPATCHING_STAGE_JOB_COMMAND_TYPE_PROPERTY = "command_type";



    /**
     * Main names of cassandra key-value columns storage.
     */
    public static final String BOOLEAN_VALUE_COLUMN = "bool_v";
    public static final String STRING_VALUE_COLUMN = "str_v";
    public static final String LONG_VALUE_COLUMN = "long_v";
    public static final String DOUBLE_VALUE_COLUMN = "dbl_v";

    protected static final String[] NONE_AGGREGATION_COLUMNS = new String[]{LONG_VALUE_COLUMN, DOUBLE_VALUE_COLUMN, BOOLEAN_VALUE_COLUMN, STRING_VALUE_COLUMN, KEY_COLUMN, TS_COLUMN};

    protected static final String[] COUNT_AGGREGATION_COLUMNS = new String[]{count(LONG_VALUE_COLUMN), count(DOUBLE_VALUE_COLUMN), count(BOOLEAN_VALUE_COLUMN), count(STRING_VALUE_COLUMN)};

    protected static final String[] MIN_AGGREGATION_COLUMNS = ArrayUtil.addAll(COUNT_AGGREGATION_COLUMNS,
            new String[]{min(LONG_VALUE_COLUMN), min(DOUBLE_VALUE_COLUMN), min(BOOLEAN_VALUE_COLUMN), min(STRING_VALUE_COLUMN)});
    protected static final String[] MAX_AGGREGATION_COLUMNS = ArrayUtil.addAll(COUNT_AGGREGATION_COLUMNS,
            new String[]{max(LONG_VALUE_COLUMN), max(DOUBLE_VALUE_COLUMN), max(BOOLEAN_VALUE_COLUMN), max(STRING_VALUE_COLUMN)});
    protected static final String[] SUM_AGGREGATION_COLUMNS = ArrayUtil.addAll(COUNT_AGGREGATION_COLUMNS,
            new String[]{sum(LONG_VALUE_COLUMN), sum(DOUBLE_VALUE_COLUMN)});
    protected static final String[] AVG_AGGREGATION_COLUMNS = SUM_AGGREGATION_COLUMNS;

    public static String min(String s) {
        return "min(" + s + ")";
    }

    public static String max(String s) {
        return "max(" + s + ")";
    }

    public static String sum(String s) {
        return "sum(" + s + ")";
    }

    public static String count(String s) {
        return "count(" + s + ")";
    }

    public static String[] getFetchColumnNames(Aggregation aggregation) {
        switch (aggregation) {
            case NONE:
                return NONE_AGGREGATION_COLUMNS;
            case MIN:
                return MIN_AGGREGATION_COLUMNS;
            case MAX:
                return MAX_AGGREGATION_COLUMNS;
            case SUM:
                return SUM_AGGREGATION_COLUMNS;
            case COUNT:
                return COUNT_AGGREGATION_COLUMNS;
            case AVG:
                return AVG_AGGREGATION_COLUMNS;
            default:
                throw new RuntimeException("Aggregation type: " + aggregation + " is not supported!");
        }
    }
}

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
package com.ifyou.skypivot.sample.infrastructure.util;

import com.datastax.driver.core.utils.UUIDs;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by ashvayka on 13.07.17.
 */
@Slf4j
public class UUIDConverter {

    public static UUID fromString(String src) {
        return UUID.fromString(src.substring(7, 15) + "-" + src.substring(3, 7) + "-1"
                + src.substring(0, 3) + "-" + src.substring(15, 19) + "-" + src.substring(19));
    }

    public static String fromTimeUUID(UUID src) {
        if (src.version() != 1) {
            throw new IllegalArgumentException("Only Time-Based UUID (Version 1) is supported!");
        }
        String str = src.toString();
        // 58e0a7d7-eebc-11d8-9669-0800200c9a66 => 1d8eebc58e0a7d796690800200c9a66. Note that [11d8] -> [1d8]
        return str.substring(15, 18) + str.substring(9, 13) + str.substring(0, 8) + str.substring(19, 23) + str.substring(24);
    }

    public static List<String> fromTimeUUIDs(List<UUID> uuids) {
        if (uuids == null) {
            return null;
        }
        return uuids.stream().map(UUIDConverter::fromTimeUUID).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        System.out.println("UUID:"+uuid.toString()+",version:"+uuid.version());
        UUID uuid2 = UUIDs.timeBased();
        System.out.println("UUID2:"+uuid2.toString()+",version:"+uuid2.version());
        String str = "5bebbfd0-066e-11ee-b8fd-27cdcd3eedcc";
        String dd =  str.substring(15, 18) + str.substring(9, 13) + str.substring(0, 8) + str.substring(19, 23) + str.substring(24);
        System.out.println(dd);

        String truckId = "1ee3cabcd6c6010881169d9e44ded2a";
        UUID uuid1 = fromString(truckId);
        log.info(uuid1.toString());
        log.info("truckId："+ fromString(truckId).toString());


        String parkPlaceID = "1ee3cabe346b480881169d9e44ded2a";
        log.info("parkPlaceID："+ fromString(parkPlaceID).toString());

        String shovelId = "1ed1ec5753ce8b0870c19be1c86c665";
        log.info("shovelId："+ fromString(shovelId).toString());

        String loadAreaId = "1ee3cabc73d1f90881169d9e44ded2a";
        log.info("loadAreaId："+ fromString(loadAreaId).toString());
    }

}


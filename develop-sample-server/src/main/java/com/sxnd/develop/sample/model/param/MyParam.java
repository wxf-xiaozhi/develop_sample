package com.sxnd.develop.sample.model.param;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: MyParam
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-05-23 14:10
 */
@Data
public class MyParam {

    private Date date;

    private List<UserParam> list;

    @Data
    public static class UserParam{
        private String name;

        private Integer age;
    }
}

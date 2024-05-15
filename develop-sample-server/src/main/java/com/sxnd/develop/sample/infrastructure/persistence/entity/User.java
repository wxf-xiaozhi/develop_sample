package com.sxnd.develop.sample.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName: User
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-05-15 15:04
 */
@Data
@TableName("`t_user`")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer age;

    private String email;
}

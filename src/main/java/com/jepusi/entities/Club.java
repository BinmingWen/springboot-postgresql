package com.jepusi.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @class: com.jepusi.entities.Club
 * @description:
 * @author: 温明彬
 * @company: 广州博瑞信息技术股份有限公司
 * @create: 2021/11/18 9:48
 */


@Data
@TableName(value = "t_club")
public class Club implements Serializable {
    @TableId(type = IdType.AUTO)
    private  Integer id;
    private String name;
    private Integer money;
    private  String nick_name;
    private Date birthday;
    private Date create_time;
    private Date update_time;
}

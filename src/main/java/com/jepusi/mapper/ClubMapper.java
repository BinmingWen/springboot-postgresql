package com.jepusi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jepusi.entities.Club;
import org.apache.ibatis.annotations.Mapper;

/**
 * @class: com.jepusi.mapper.IClubDao
 * @description:
 * @author: 温明彬
 * @company: 广州博瑞信息技术股份有限公司
 * @create: 2021/11/18 9:55
 */

@Mapper
public interface ClubMapper extends BaseMapper<Club> {
}

package com.jepusi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jepusi.entities.Club;
import com.jepusi.mapper.ClubMapper;
import com.jepusi.service.IClubService;
import org.springframework.stereotype.Service;

/**
 * @class: com.jepusi.service.impl.ClubServiceImpl
 * @description:
 * @author: 温明彬
 * @company: 广州博瑞信息技术股份有限公司
 * @create: 2021/11/18 9:54
 */

@Service
public class ClubServiceImpl extends ServiceImpl<ClubMapper,Club> implements IClubService {
}

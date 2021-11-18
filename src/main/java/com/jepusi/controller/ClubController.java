package com.jepusi.controller;

import com.jepusi.entities.Club;
import com.jepusi.service.IClubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @class: com.jepusi.controller.ClubController
 * @description:
 * @author: 温明彬
 * @company: 广州博瑞信息技术股份有限公司
 * @create: 2021/11/18 9:57
 */


@RestController
public class ClubController {
    @Resource
    private IClubService clubService;

    @GetMapping(value = "/getClubById")
    public Club getClubById(Integer id) {
        return clubService.getById(id);
    }

    @PostMapping(value = "/saveClub")
    public void saveClub(Club club) {
        clubService.save(club);
    }

    @GetMapping(value = "/getClubs")
    public List<Club> getClubs() {
        return clubService.list(null);
    }

    @GetMapping(value = "/deleteById")
    public void deleteById(int id) {
         clubService.removeById(id);
    }
}

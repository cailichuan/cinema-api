package com.cinema.read_api.service.impl;

import com.cinema.read_api.mapper.ReadLeavingMessageMapper;
import com.cinema.read_api.mapper.ReadUserMapper;
import com.cinema.read_api.service.ReadLeavingMessageService;
import model.entity.LeavingMessage;
import model.entity.User;
import model.vo.ActiveUserVo;
import model.vo.LeavingMessageVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReadLeavingMessageServiceImpl  implements ReadLeavingMessageService {

    @Resource
    private ReadLeavingMessageMapper readLeavingMessageMapper;

    @Resource
    private ReadUserMapper readUserMapper;
    @Override
    public List<LeavingMessageVo> findAll() {
        List<LeavingMessage> leavingMessages = readLeavingMessageMapper.selectList();
        List<LeavingMessageVo> result = new ArrayList<>();
        for (LeavingMessage leavingMessage : leavingMessages) {
            User user = readUserMapper.selectById(leavingMessage.getUid());
            result.add(new LeavingMessageVo(leavingMessage.getId(),leavingMessage,user));
        }
        return result;
    }

    @Override
    public List<ActiveUserVo> findActiveUsers() {
        ArrayList<ActiveUserVo> result = new ArrayList<>();
        List<User> users = readUserMapper.selectList();
        for (User user : users) {
            ActiveUserVo activeUserVo = new ActiveUserVo();
            activeUserVo.setUser(user);
            activeUserVo.setNumber(readLeavingMessageMapper.selectListByUid(user.getId()).size());
            result.add(activeUserVo);

        }

        //按照留言数量排序
        result.sort((v1,v2) -> v2.getNumber().compareTo(v1.getNumber()));
        return result;
    }
}

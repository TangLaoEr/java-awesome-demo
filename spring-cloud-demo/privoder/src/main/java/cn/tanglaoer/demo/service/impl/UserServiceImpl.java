package cn.tanglaoer.demo.service.impl;

import cn.tanglaoer.demo.entity.User;
import cn.tanglaoer.demo.mapper.UserMapper;
import cn.tanglaoer.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author： tks
 * @date： 2023/3/2
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    public static final String tag = "hello";

    @Override
    public User getById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public String sendMessage() {
        return null;
    }

    @Override
    public String sendMessageTag() {
        return null;
    }
}

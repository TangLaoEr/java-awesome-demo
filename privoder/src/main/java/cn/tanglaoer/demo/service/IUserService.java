package cn.tanglaoer.demo.service;

import cn.tanglaoer.demo.entity.User;

/**
 * @author： tks
 * @date： 2023/3/2
 */
public interface IUserService {
    User getById(Integer id);

    String sendMessage();

    String sendMessageTag();
}

package cn.itheima.service;

import cn.itheima.dao.UserDao;
import cn.itheima.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public Boolean login(User user) {
        int count= userDao.login(user);
        return count==1;
    }

}

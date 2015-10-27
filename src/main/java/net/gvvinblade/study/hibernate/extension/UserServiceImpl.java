package net.gvvinblade.study.hibernate.extension;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveUser(UserEntity user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public UserEntity getUser(Integer id) {
        return sessionFactory.getCurrentSession().get(UserEntity.class, id);
    }


}

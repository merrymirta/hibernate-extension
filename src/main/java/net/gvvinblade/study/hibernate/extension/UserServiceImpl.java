package net.gvvinblade.study.hibernate.extension;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.glasnost.orika.MapperFacade;

/**
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private MapperFacade mapper;

    @Override
    public void saveUser(User user){
        sessionFactory.getCurrentSession().saveOrUpdate(mapper.map(user, UserEntity.class));
    }

    @Override
    public User getUser(Integer id){
        return mapper.map(sessionFactory.getCurrentSession().get(UserEntity.class, id), User.class);
    }



}

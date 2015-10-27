package net.gvvinblade.study.hibernate.extension;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.ExpectedDatabase;

/**
 * Created by Igor_Seliverstov on 10/27/2015.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@Transactional
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class })
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    @ExpectedDatabase("expected.xml")
    public void testSaveUser() throws Exception {
        userService.saveUser(createDefault());
        sessionFactory.getCurrentSession().flush();
    }

    @Test
    public void testGetUser() throws Exception {

    }

    private UserEntity createDefault(){
        UserEntity user = new UserEntity();
        user.setName("Igor");
        return user;
    }
}
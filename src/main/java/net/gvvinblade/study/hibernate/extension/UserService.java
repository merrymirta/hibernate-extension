package net.gvvinblade.study.hibernate.extension;

/**
 * Created by Igor_Seliverstov on 10/27/2015.
 */
public interface UserService {
    void saveUser(User user);

    User getUser(Integer id);
}

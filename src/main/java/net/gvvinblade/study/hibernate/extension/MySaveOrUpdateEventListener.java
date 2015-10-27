package net.gvvinblade.study.hibernate.extension;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.event.spi.SaveOrUpdateEventListener;
import org.springframework.stereotype.Service;

/**
 * Created by Igor_Seliverstov on 10/27/2015.
 *
 */
@Service
public class MySaveOrUpdateEventListener implements SaveOrUpdateEventListener {
    @Override
    public void onSaveOrUpdate(SaveOrUpdateEvent event) throws HibernateException {
        if (event.getEntity() instanceof UserEntity) {
            UserEntity entity = (UserEntity) event.getEntity();
            entity.setName(entity.getName() + " updated by listener");
        }
    }
}

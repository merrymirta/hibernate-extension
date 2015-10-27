package net.gvvinblade.study.hibernate.extension;

import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.SaveOrUpdateEventListener;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Igor_Seliverstov on 10/27/2015.
 */
@Service
public class MyIntegrator implements Integrator {

    @Autowired(required = true)
    private List<SaveOrUpdateEventListener> saveOrUpdateEventListeners;

    private static final Log LOG = LogFactory.getLog(MyIntegrator.class);

    @Override
    public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {

        if (saveOrUpdateEventListeners != null && !saveOrUpdateEventListeners.isEmpty()) {
            serviceRegistry.getService(EventListenerRegistry.class)
                    .appendListeners(EventType.SAVE_UPDATE, toArray(saveOrUpdateEventListeners));
        }

        LOG.debug("integrate");
    }

    @SuppressWarnings("unchecked")
    private <T> T[] toArray(Collection<T> source) {
        return (T[]) source.toArray();
    }

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        LOG.debug("disintegrate");
    }
}

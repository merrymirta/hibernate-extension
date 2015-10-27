package net.gvvinblade.study.hibernate.extension;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Igor_Seliverstov on 10/27/2015.
 *
 */
@Service
public class MyIntegrator implements Integrator {

    @Autowired
    private ListableBeanFactory beanFactory;

    private static final Log LOG = LogFactory.getLog(MyIntegrator.class);

    @Override
    public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {

        Field[] declaredFields = EventType.class.getDeclaredFields();

        for (Field declaredField : declaredFields) {
            if(
                    Modifier.isFinal(declaredField.getModifiers()) &&
                            Modifier.isPublic(declaredField.getModifiers()) &&
                            Modifier.isStatic(declaredField.getModifiers()) &&
                            declaredField.getType().isAssignableFrom(EventType.class)
                    ){
                try {
                    EventType eventType = (EventType) declaredField.get(null);
                    Class listenerInterface = eventType.baseListenerInterface();
                    Object[] objects = toArray(beanFactory.getBeansOfType(listenerInterface).values());
                    if(objects.length > 0){
                                    serviceRegistry.getService(EventListenerRegistry.class).appendListeners(eventType, objects);
                    }

                } catch (IllegalAccessException e) {
                    //should never happen
                    throw new RuntimeException(e);
                }

            }
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

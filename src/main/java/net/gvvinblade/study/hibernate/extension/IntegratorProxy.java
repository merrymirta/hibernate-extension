package net.gvvinblade.study.hibernate.extension;

import java.util.Collection;

import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

/**
 * Created by Igor_Seliverstov on 10/27/2015.
 *
 */
public class IntegratorProxy implements Integrator {

    private static Collection<Integrator> integrators;

    static void setIntegrators(Collection<Integrator> integrators){
        IntegratorProxy.integrators = integrators;
    }

    @Override
    public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        if(integrators != null){
            for (Integrator integrator : integrators) {
                integrator.integrate(metadata, sessionFactory, serviceRegistry);
            }
        }
    }

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        if(integrators != null){
            for (Integrator integrator : integrators) {
                integrator.disintegrate(sessionFactory, serviceRegistry);
            }
        }
    }
}

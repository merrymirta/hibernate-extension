package net.gvvinblade.study.hibernate.extension;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;
import org.springframework.stereotype.Service;

/**
 * Created by Igor_Seliverstov on 10/27/2015.
 */
@Service
public class MyIntegrator implements Integrator {

    private static final Log LOG = LogFactory.getLog(MyIntegrator.class);

    @Override
    public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        LOG.debug("integrate");
    }

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        LOG.debug("disintegrate");
    }
}

package net.gvvinblade.study.hibernate.extension;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.integrator.spi.Integrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

/**
 * Created by Igor_Seliverstov on 10/27/2015.
 *
 */
@Service
public class SpringIntergatorsInitializer implements Ordered {

    @Autowired(required = false)
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private List<Integrator> integrators;

    @PostConstruct
    public void init(){
        if(integrators != null && !integrators.isEmpty()){
            IntegratorProxy.setIntegrators(new ArrayList<>(integrators));
        }
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}


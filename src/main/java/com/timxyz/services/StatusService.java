package com.timxyz.services;

import com.timxyz.models.Status;
import com.timxyz.repositories.StatusRepositoy;
import com.timxyz.services.exceptions.ServiceException;
import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 * Created by Dario on 5/6/2017.
 */
@Service
public class StatusService extends BaseService<Status, StatusRepositoy> {
        
    public Status save(Status model) throws ServiceException {
        Status sameName = getByName(model.getName());
        
        if (sameName != null && model.getId() != sameName.getId()) {
            throw new ServiceException("Status s navedenim imenom već postoji.");
        }
        return super.save(model);
    }

    public Status getByName(String name) {
        return repository.findFirstByName(name);}

    public Collection<Status> filterByName(String name) {
        return repository.filterByName(name);
    }
}



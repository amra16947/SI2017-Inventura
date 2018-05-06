package com.timxyz.services;

import com.timxyz.models.Location;
import com.timxyz.models.LocationType;
import com.timxyz.repositories.LocationTypeRepository;
import com.timxyz.services.exceptions.ServiceException;

import java.util.Collection;

import org.springframework.stereotype.Service;

@Service
public class LocationTypeService extends BaseService<LocationType, LocationTypeRepository> {
    public LocationType save(LocationType model) throws ServiceException {
        LocationType sameName = getByName(model.getName());

        if (sameName != null && model.getId() != sameName.getId()) {
            throw new ServiceException("Tip lokacije s navedenim imenom već postoji.");
        }

        return super.save(model);
    }

    public void delete(Long id) throws ServiceException {
        LocationType lt = get(id);

        if (lt.getLocations().size() > 0) {
            throw new ServiceException("Tip lokacije se ne može obrisati jer je vezan za neke od postojećih lokacija.");
        }

        super.delete(id);
    }

    public Collection<LocationType> filterByName(String name) {
        return repository.filterByName(name);
    }
    
    public LocationType getByName(String name) {
        return repository.findFirstByName(name);
    }
}
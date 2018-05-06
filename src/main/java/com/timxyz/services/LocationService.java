package com.timxyz.services;

import com.timxyz.models.Location;
import com.timxyz.repositories.LocationRepository;
import com.timxyz.services.exceptions.ServiceException;
import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 * Created by ramic on 04.05.2017..
 */
@Service
public class LocationService extends BaseService<Location, LocationRepository> {
    public Location save(Location model) throws ServiceException {
        Location sameName = getByName(model.getName());

        if (sameName != null && model.getId() != sameName.getId()) {
            throw new ServiceException("Lokacija s navedenim imenom već postoji.");
        }

        if (model.getId() != null) {
            // Check against cyclic parent locations
            Location parent = model.getParent();

            while (parent != null) {
                if (parent.getId() == model.getId()) {
                    throw new ServiceException("Uočena je ciklična veza između nadlokacija.");
                }

                parent = parent.getParent();
            }
        }

        return super.save(model);
    }

    public void delete(Long id) throws ServiceException {
        Location location = get(id);

        if (location.getItems().size() > 0) {
            throw new ServiceException("Lokacija se ne može obrisati jer postoje inventurne stavke koje se na njoj nalaze.");
        }

        super.delete(id);
    }

    public Collection<Location> filterByName(String name) {
        return repository.filterByName(name);
    }
    
    public Location getByName(String name) {
        return repository.findFirstByName(name);
    }
}


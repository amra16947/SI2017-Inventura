package com.timxyz.services;

import com.timxyz.models.Item;
import com.timxyz.repositories.ItemRepository;
import com.timxyz.services.exceptions.ServiceException;
import org.springframework.stereotype.Service;
import java.util.Collection;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Dario on 5/9/2017.
 */
@Service
public class ItemService extends BaseService<Item, ItemRepository> {

    private static int counter = 0;
    
    public Item save(Item model) throws ServiceException {

        List<String> codes = Arrays.asList("kom", "m", "kg");
        if(model.getId() == null){
             model.setSkuNumber(genRandom()); 
            //counter++;
            //model.setSkuNumber(String.valueOf(counter));
        } 
   
        if (model.getId() == null && findFirstBySkuNumber(model.getSkuNumber()) != null) {
            throw new ServiceException("Inventurna stavka s navedenim inventurnim brojem već postoji.");
        } else if (!codes.contains(model.getUnitOfMeasurement().toLowerCase())) {
            throw new ServiceException("Neispravna mjerna jedinica.");
        }
        return super.save(model);
    }

    public Item findFirstBySkuNumber(String skuNumber) {
        return repository.findFirstBySkuNumber(skuNumber);
    }

    public Collection<Item> filterByName(String name) {
        return repository.filterByName(name);
    }

    public Collection<Item> getByLocation(Long id) {
        return repository.getByLocation(id);
    }

    private String genRandom() {
        int value=0, min=1000, max=1000000000;
            do{
                Random rand = new Random(); 
                value = rand.nextInt((max - min) + 1) + min;
            }while(findFirstBySkuNumber(String.valueOf(value)) != null);
        return String.valueOf(value);
    }
}



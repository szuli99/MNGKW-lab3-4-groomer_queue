package pl.edu.wat.groomer_queue.mapper;

import org.springframework.stereotype.Service;
import pl.edu.wat.groomer_queue.dto.OwnerRequest;
import pl.edu.wat.groomer_queue.dto.OwnerResponse;
import pl.edu.wat.groomer_queue.entity.Owner;

@Service
public class OwnerMapper {



    public Owner map(OwnerRequest ownerRequest) {
        Owner owner = new Owner();
        owner.setName(ownerRequest.getName());
        owner.setSurname(ownerRequest.getSurname());
        owner.setTelephone(ownerRequest.getTelephone());
        fillOwnerRequest(owner, ownerRequest);
        return owner;
    }

    private void fillOwnerRequest(Owner owner, OwnerRequest ownerRequest) {
//        owner.setScore(ownerRequest.getScore());
        // empty for byte buddy
    }

    public OwnerResponse map(Owner owner) {
        OwnerResponse ownerResponse = new OwnerResponse(owner.getId(), owner.getName(), owner.getSurname());
        fillOwner(ownerResponse, owner);
        return ownerResponse;
    }

    private void fillOwner(OwnerResponse ownerResponse, Owner owner) {
        //ownerResponse.setRank(owner.getRank());
        // empty for byte buddy
    }


}

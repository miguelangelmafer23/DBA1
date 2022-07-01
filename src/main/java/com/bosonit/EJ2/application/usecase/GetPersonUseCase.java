package com.bosonit.EJ2.application.usecase;

import com.bosonit.EJ2.exceptions.NotFoundException;
import com.bosonit.EJ2.application.port.GetPersonPort;
import com.bosonit.EJ2.domain.PersonaEnt;
import com.bosonit.EJ2.infraestructure.DTOs.OutPutPersonaDTO;
import com.bosonit.EJ2.infraestructure.Repository.PersonaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class GetPersonUseCase implements GetPersonPort {

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    ModelMapper modelMapper;

    public OutPutPersonaDTO getPersonaByID(Integer id) throws Exception {
        PersonaEnt personaEnt = personaRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        OutPutPersonaDTO outPutPersonaDTO = modelMapper.map(personaEnt, OutPutPersonaDTO.class);
        return outPutPersonaDTO;
    }

    //getnombre

    public List<OutPutPersonaDTO> getPersonByName(String name) {
        List<PersonaEnt> listPersona = personaRepository.findByName(name);
        TypeToken<List<OutPutPersonaDTO>> typeToken = new TypeToken<>() {
        };
        List<OutPutPersonaDTO> outPutPersonaDTOList = modelMapper.map(listPersona, typeToken.getType());
        return outPutPersonaDTOList;
    }

    //getall

    public List<OutPutPersonaDTO> getAllPerson() {
        List<PersonaEnt> personaList = personaRepository.findAll();
        TypeToken<List<OutPutPersonaDTO>> typeToken = new TypeToken<>() {
        };
        List<OutPutPersonaDTO> outPutPersonaDTOList = modelMapper.map(personaList, typeToken.getType());

        return outPutPersonaDTOList;
    }

    //getbypage

    public Page<PersonaEnt> getAllPersonPage(Integer pageNumber, Integer pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<PersonaEnt> personaEntPage = personaRepository.findAll(page);
        return personaEntPage;
    }

    //////////////////////////CRITERIA BUILDER////////////////

    @PersistenceContext
    private EntityManager entityManager;

    public List<PersonaEnt> getData(HashMap<String, String> conditions) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PersonaEnt> query = cb.createQuery(PersonaEnt.class);
        Root<PersonaEnt> root = query.from(PersonaEnt.class);
        List<Predicate> predicates = new ArrayList<>();
        conditions.forEach((field,value) ->
                {
                    switch (field){
                        case "usuario":
                            predicates.add(cb.equal(root.get(field),(String) value));
                            break;
                        case "name":
                            predicates.add(cb.equal(root.get(field),(String) value));
                            break;
                        case "surname":
                            predicates.add(cb.equal(root.get(field),(String) value));
                            break;
                        case "created_date":
                            String dateCondition=(String) conditions.get("dateCondition");
                            Date fecha = parseDate(value);
                            switch (dateCondition)
                            {
                                case "after":
                                    predicates.add(cb.greaterThan(root.<Date>get(field),fecha));
                                    break;
                                case "before":
                                    predicates.add(cb.lessThan(root.<Date>get(field),fecha));
                                    break;
                                case "equal":
                                    predicates.add(cb.equal(root.<Date>get(field),fecha));
                                break;
                            }
                            break;
                    }

                });

        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(query).getResultList();
    }

    //////////////////////////CRITERIA BUILDER////////////////
    private Date parseDate(String value) {
        Date creationDate;
        try{
            creationDate = new SimpleDateFormat("yyyy-MM-dd").parse(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return creationDate;
    }

}
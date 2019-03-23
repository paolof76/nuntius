package com.group.nuntius.service;

import com.group.nuntius.Tools;
import com.group.nuntius.model.Institution;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface InstitutionRepository extends CrudRepository<Institution, Long> {

    List<Institution> findByName(String name);

    default Optional<Institution> getByName(String name) {
        return Tools.getFirst(findByName(name));
    }

    List<Institution> findByAbbreviation(String abbreviation);

    default Optional<Institution> getByAbbreviation(String abbreviation) {
        return Tools.getFirst(findByAbbreviation(abbreviation));
    }
}

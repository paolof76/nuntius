package com.group.nuntius.service;

import com.group.nuntius.model.Account;
import com.group.nuntius.model.Institution;
import org.springframework.data.repository.CrudRepository;

public interface InstitutionRepository extends CrudRepository<Institution, Long> {

}

package com.group.nuntius.service;

import com.group.nuntius.model.Institution;

public class Setup {

    private InstitutionRepository institutionRepository;

    public Setup(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    public void createExampleInstitutions() {
        createInstitution("United Web Bank", "UWB", "UWBA45KDASK");
        createInstitution("Bank Swiss", "BS", "BS234USF");
        createInstitution("John Bear Bank", "JB", "JB78HDSAH");
        createInstitution("F10 Bank", "F10", "F10AD7MC37");
    }

    private void createInstitution(String name, String abbreviation, String bic) {
        if (institutionRepository.getByAbbreviation(abbreviation).isPresent()) {
            return;
        }

        Institution institution = new Institution(name, abbreviation, bic);
        institutionRepository.save(institution);
    }

}

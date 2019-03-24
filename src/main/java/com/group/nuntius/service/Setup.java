package com.group.nuntius.service;

import com.group.nuntius.model.Institution;
import com.group.nuntius.model.Promotion;

public class Setup {

    private InstitutionRepository institutionRepository;
    private PromotionRepository promotionRepository;

    public Setup(InstitutionRepository institutionRepository, PromotionRepository promotionRepository) {
        this.institutionRepository = institutionRepository;
        this.promotionRepository = promotionRepository;
    }

    public void createExampleInstitutions() {
        createInstitution("United Web Bank", "UWB", "UWBA45KDASK");
        createInstitution("Bank Swiss", "BS", "BS234USF");
        createInstitution("John Bear Bank", "JB", "JB78HDSAH");
        createInstitution("F10 Bank", "F10", "F10AD7MC37");
    }

    public void createExampleOfPromotion() {
        Institution institution = institutionRepository.getByName("United Web Bank").get();
        Promotion promotion = new Promotion(
                "GETIT!", "Be in the first thousand to subscribe and you will have free trasfer fees for a jear!",
                institution, 0L, 1000L, 350L);
        promotionRepository.save(promotion);
    }

    private void createInstitution(String name, String abbreviation, String bic) {
        if (institutionRepository.getByAbbreviation(abbreviation).isPresent()) {
            return;
        }

        Institution institution = new Institution(name, abbreviation, bic);
        institutionRepository.save(institution);
    }

}

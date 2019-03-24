package com.group.nuntius.service;

import com.group.nuntius.model.Institution;
import com.group.nuntius.model.Promotion;
import com.group.nuntius.obp.clientapi.DirectAuthenticationClient;
import com.group.nuntius.obp.clientapi.ObpBankMetaApiClient;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Optional;

public class Setup {

    private DirectAuthenticationClient directAuthenticationClient;
    private ObpBankMetaApiClient obpBankMetaApiClient;
    private InstitutionRepository institutionRepository;
    private PromotionRepository promotionRepository;

    public Setup(ConfigurableApplicationContext context) {
        this.directAuthenticationClient = context.getBean(DirectAuthenticationClient.class);
        this.obpBankMetaApiClient = context.getBean(ObpBankMetaApiClient.class);
        this.institutionRepository = context.getBean(InstitutionRepository.class);
        this.promotionRepository = context.getBean(PromotionRepository.class);
    }

    public void createExampleInstitutions() {
        createInstitution("United Web Bank", "UWB", "UWBA45KDASK");
        createInstitution("Bank Swiss", "BS", "BS234USF");
        createInstitution("John Bear Bank", "JB", "JB78HDSAH");
        createInstitution("F10 Bank", "F10", "F10AD7MC37");

        String token = directAuthenticationClient.login("nuntius", "@Nuntius1234",
                "o153kts4lby2cej3z4cm5d4lrlai0k5xrh4ewk03");

        token = "DirectLogin token=" + token;

        ObpBankMetaApiClient.Banks banks = obpBankMetaApiClient.getBanks(token);
        banks.getBanks().forEach(bank -> createInstitution(bank.getFullName(), bank.getShortName(), bank.getId()));
    }

    public void createExampleOfPromotion() {
        Optional<Institution> institutionOptional = institutionRepository.getByName("United Web Bank");

        if (institutionOptional.isPresent() && promotionRepository.findByCode("GET IT TOO!").isEmpty()) {
            Institution institution = institutionOptional.get();

            Promotion promotion = new Promotion(
                    "GET IT TOO!",
                    "Be in the first thousand to subscribe and you will have free transfer fees for a year!",
                    institution,
                    0L,
                    1000L,
                    350L);

            promotionRepository.save(promotion);
        }
    }

    private void createInstitution(String name, String abbreviation, String bic) {
        if (institutionRepository.getByAbbreviation(abbreviation).isPresent()) {
            return;
        }

        Institution institution = new Institution(name, abbreviation, bic);
        institutionRepository.save(institution);
    }

}

package de.spricom.zaster2.service.classifieres.postbank;

import de.spricom.zaster2.entities.ClassificationCategory;
import de.spricom.zaster2.entities.PostbankGiroEntity;
import org.springframework.stereotype.Component;

@Component
public class BdvClassifier implements PostbankClassifier {

    @Override
    public String getName() {
        return "BdV";
    }

    @Override
    public ClassificationCategory getCategory() {
        return ClassificationCategory.VERSICHERUNG;
    }

    @Override
    public boolean matches(PostbankGiroEntity entity) {
        return entity.getPartnerName().equals("BdV Verwaltungs GmbH");
    }
}

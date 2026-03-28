package de.spricom.zaster2.service.classifieres.postbank;

import de.spricom.zaster2.entities.ClassificationCategory;
import de.spricom.zaster2.entities.PostbankGiroEntity;
import org.springframework.stereotype.Component;

@Component
public class AllianzRiesterClassifier implements PostbankClassifier {

    @Override
    public String getName() {
        return "Allianz Riester";
    }

    @Override
    public ClassificationCategory getCategory() {
        return ClassificationCategory.VERSICHERUNG;
    }

    @Override
    public boolean matches(PostbankGiroEntity entity) {
        return entity.getPartnerName().toUpperCase().startsWith("ALLIANZ")
                && entity.getPaymentReference().contains("AL-1382758095");
    }
}

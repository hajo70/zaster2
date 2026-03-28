package de.spricom.zaster2.service.classifieres.postbank;

import de.spricom.zaster2.entities.ClassificationCategory;
import de.spricom.zaster2.entities.PostbankGiroEntity;
import org.springframework.stereotype.Component;

@Component
public class KreditkarteClassifier implements PostbankClassifier {
    @Override
    public String getName() {
        return "ABRECHNUNG KREDITKARTE";
    }

    @Override
    public ClassificationCategory getCategory() {
        return ClassificationCategory.SONSTIGES;
    }

    @Override
    public boolean matches(PostbankGiroEntity entity) {
        return entity.getPartnerName().equals("ABRECHNUNG KREDITKARTE");
    }
}

package de.spricom.zaster2.service.classifieres.postbank;

import de.spricom.zaster2.entities.ClassificationCategory;
import de.spricom.zaster2.entities.PostbankGiroEntity;
import org.springframework.stereotype.Component;

@Component
public class O2Classifier implements PostbankClassifier {
    @Override
    public String getName() {
        return "O2";
    }

    @Override
    public ClassificationCategory getCategory() {
        return ClassificationCategory.HAUS;
    }

    @Override
    public boolean matches(PostbankGiroEntity entity) {
        return entity.getPartnerName().startsWith("Telefonica Germany");
    }
}

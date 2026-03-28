package de.spricom.zaster2.service.classifieres.postbank;

import de.spricom.zaster2.entities.ClassificationCategory;
import de.spricom.zaster2.entities.PostbankGiroEntity;
import org.springframework.stereotype.Component;

@Component
public class IonosClassifier implements PostbankClassifier {
    @Override
    public String getName() {
        return "IONOS SE";
    }

    @Override
    public ClassificationCategory getCategory() {
        return ClassificationCategory.HOSTING;
    }

    @Override
    public boolean matches(PostbankGiroEntity entity) {
        return entity.getPartnerName().equals("IONOS SE");
    }
}

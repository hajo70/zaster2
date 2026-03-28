package de.spricom.zaster2.service.classifieres.postbank;

import de.spricom.zaster2.entities.ClassificationCategory;
import de.spricom.zaster2.entities.PostbankGiroEntity;
import org.springframework.stereotype.Component;

@Component
public class TaiChiClassifier implements PostbankClassifier {

    @Override
    public String getName() {
        return "Tai Chi";
    }

    @Override
    public ClassificationCategory getCategory() {
        return ClassificationCategory.KURS;
    }

    @Override
    public boolean matches(PostbankGiroEntity entity) {
        return entity.getPartnerName().equals("Sascha Krysztofiak");
    }
}

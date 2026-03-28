package de.spricom.zaster2.service.classifieres.postbank;

import de.spricom.zaster2.entities.ClassificationCategory;
import de.spricom.zaster2.entities.PostbankGiroEntity;
import org.springframework.stereotype.Component;

@Component
public class KaiHannoverClassifier implements PostbankClassifier {
    @Override
    public String getName() {
        return "Kai aus Hannover";
    }

    @Override
    public ClassificationCategory getCategory() {
        return ClassificationCategory.SPENDE;
    }

    @Override
    public boolean matches(PostbankGiroEntity entity) {
        return entity.getPartnerName().equals("Kai aus Hannover");
    }
}

package de.spricom.zaster2.service.classifieres.postbank;

import de.spricom.zaster2.entities.ClassificationCategory;
import de.spricom.zaster2.entities.PostbankGiroEntity;
import org.springframework.stereotype.Component;

@Component
public class BitpandaClassifier implements PostbankClassifier {
    @Override
    public String getName() {
        return "Bitpanda";
    }

    @Override
    public ClassificationCategory getCategory() {
        return ClassificationCategory.KRYPTO;
    }

    @Override
    public boolean matches(PostbankGiroEntity entity) {
        return entity.getPartnerName().startsWith("Bitpanda");
    }
}

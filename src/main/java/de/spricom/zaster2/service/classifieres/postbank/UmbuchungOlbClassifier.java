package de.spricom.zaster2.service.classifieres.postbank;

import de.spricom.zaster2.entities.ClassificationCategory;
import de.spricom.zaster2.entities.PostbankGiroEntity;
import org.springframework.stereotype.Component;

@Component
public class UmbuchungOlbClassifier implements PostbankClassifier {

    @Override
    public String getName() {
        return "Umbuchung-OLB";
    }

    @Override
    public ClassificationCategory getCategory() {
        return ClassificationCategory.UMBUCHUNG;
    }

    @Override
    public boolean matches(PostbankGiroEntity entity) {
        return entity.getIban().equals("DE21280200505305248600");
    }
}

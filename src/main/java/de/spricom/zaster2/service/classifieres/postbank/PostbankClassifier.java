package de.spricom.zaster2.service.classifieres.postbank;

import de.spricom.zaster2.entities.ClassificationCategory;
import de.spricom.zaster2.entities.PostbankGiroEntity;

public interface PostbankClassifier {
    String getName();
    ClassificationCategory getCategory();
    boolean matches(PostbankGiroEntity entity);
}

package de.spricom.zaster2.service;

import de.spricom.zaster2.entities.GiroClassificationEntity;
import de.spricom.zaster2.entities.PostbankGiroEntity;
import de.spricom.zaster2.repository.GiroClassificationRepository;
import de.spricom.zaster2.repository.PostbankGiroRepository;
import de.spricom.zaster2.service.classifieres.postbank.PostbankClassifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GiroClassificationService {
    private final List<PostbankClassifier> postbankClassifiers;
    private final GiroClassificationRepository classificationRepository;
    private final PostbankGiroRepository postbankGiroRepository;

    @Transactional
    public void classifyPostbank() {
        postbankGiroRepository.findAllUnclassified().forEach(this::classifyPostbank);
    }

    private void classifyPostbank(PostbankGiroEntity entity) {
        for (PostbankClassifier classifier : postbankClassifiers) {
            if (classifier.matches(entity)) {
                var classification = getOrCreateClassification(classifier);
                classification.getPostbankBookings().add(entity);
                classificationRepository.save(classification);
            }
        }
    }

    private GiroClassificationEntity getOrCreateClassification(PostbankClassifier classifier) {
        return classificationRepository.findByName(classifier.getName())
                .orElseGet(() -> createClassification(classifier));
    }

    private GiroClassificationEntity createClassification(PostbankClassifier classifier) {
        GiroClassificationEntity entity = new GiroClassificationEntity();
        entity.setName(classifier.getName());
        entity.setCategory(classifier.getCategory());
        return classificationRepository.save(entity);
    }
}

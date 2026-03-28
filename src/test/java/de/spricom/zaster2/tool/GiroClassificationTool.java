package de.spricom.zaster2.tool;

import de.spricom.zaster2.service.GiroClassificationService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class GiroClassificationTool {

    @Autowired
    private GiroClassificationService classificationService;

    @Test
    void classifyUnclassified() {
        classificationService.classifyPostbank();
    }
}

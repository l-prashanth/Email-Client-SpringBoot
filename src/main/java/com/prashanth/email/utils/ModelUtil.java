package com.prashanth.email.utils;

import com.prashanth.email.model.enums.OperationId;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import static com.prashanth.email.constants.Constants.*;
import static com.prashanth.email.model.enums.OperationId.EMAIL_FIELDS;


@Slf4j
@AllArgsConstructor
@Component
public class ModelUtil {

    public void modelHandler(OperationId operationId, Model model, String... values) {
        if (operationId.equals(EMAIL_FIELDS)) {
            model.addAttribute(RECIPIENT_MODEL, values[0]);
            model.addAttribute(SUBJECT_MODEL, values[1]);
            model.addAttribute(BODY_MODEL, values[2]);
        }
    }

}

package org.opensrp.event;

import com.google.gson.Gson;
import org.motechproject.scheduler.domain.MotechEvent;
import org.opensrp.dto.form.FormSubmissionDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormSubmissionEvent {
    public static final String SUBJECT = "FORM-SUBMISSION";

    private List<FormSubmissionDTO> formSubmissions;

    public FormSubmissionEvent(List<FormSubmissionDTO> formSubmissions) {
        this.formSubmissions = formSubmissions;
    }

    public MotechEvent toEvent() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("data", new Gson().toJson(formSubmissions));
        return new MotechEvent(SUBJECT, parameters);
    }
}

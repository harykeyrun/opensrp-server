package org.opensrp.service.reporting.rules;

import org.opensrp.util.SafeMap;
import org.springframework.stereotype.Component;

import static org.opensrp.common.AllConstants.ChildCloseFormFields.DEATH_CAUSE;
import static org.opensrp.common.AllConstants.ChildCloseFormFields.FEVER_RELATED_VALUE;

@Component
public class IsDeathWasCausedByFeverRelatedRule implements IRule {

    @Override
    public boolean apply(SafeMap reportFields) {
        return FEVER_RELATED_VALUE.equalsIgnoreCase(reportFields.get(DEATH_CAUSE));
    }
}

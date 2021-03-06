package org.opensrp.service.reporting.rules;

import org.opensrp.util.SafeMap;
import org.junit.Before;
import org.junit.Test;
import org.opensrp.service.reporting.rules.IsDeathDueToSevereHyperTensionOrFitsRule;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;



public class IsDeathDueToSevereHyperTensionOrFitsRuleTest {

    private IsDeathDueToSevereHyperTensionOrFitsRule rule;

    @Before
    public void setUp() {
        rule = new IsDeathDueToSevereHyperTensionOrFitsRule();
    }

    @Test
    public void shouldReturnFalseWhenDeathIsNotDueToHyperTensionOrFits() {
        boolean didRuleSucceed = rule.apply(new SafeMap().put("maternalDeathCause", null));

        assertFalse(didRuleSucceed);

        didRuleSucceed = rule.apply(new SafeMap().put("maternalDeathCause", ""));

        assertFalse(didRuleSucceed);

        didRuleSucceed = rule.apply(new SafeMap().put("maternalDeathCause", "fever_infection"));

        assertFalse(didRuleSucceed);
    }

    @Test
    public void shouldReturnTrueWhenDeathIsDueToHyperTensionOrFits() {
        boolean didRuleSucceed = rule.apply(new SafeMap().put("maternalDeathCause", "hypertension_fits"));

        assertTrue(didRuleSucceed);
    }
}

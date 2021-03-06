package org.opensrp.service.reporting.rules;

import org.opensrp.util.SafeMap;
import org.junit.Before;
import org.junit.Test;
import org.opensrp.service.reporting.rules.CurrentFPMethodIsEitherMaleOrFemaleSterilizationRule;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;



public class CurrentFPMethodIsEitherMaleOrFemaleSterilizationRuleTest {

    CurrentFPMethodIsEitherMaleOrFemaleSterilizationRule rule;

    @Before
    public void setUp() {
        rule = new CurrentFPMethodIsEitherMaleOrFemaleSterilizationRule();
    }

    @Test
    public void shouldReturnFalseWhenCurrentFPMethodOfECIsNeitherMaleNorFemaleSterilization() {
        boolean didRuleSucceed = rule.apply(new SafeMap().put("currentMethod", "ocp"));

        assertFalse(didRuleSucceed);
    }

    @Test
    public void shouldReturnTrueIfCurrentFPMethodOfTheECIsEitherMaleOrFemaleSterilization() {
        boolean didRuleSucceed = rule.apply(new SafeMap().put("currentMethod", "female_sterilization"));

        assertTrue(didRuleSucceed);

        didRuleSucceed = rule.apply(new SafeMap().put("currentMethod", "male_sterilization"));

        assertTrue(didRuleSucceed);
    }
}

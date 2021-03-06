package org.opensrp.service.reporting;

import org.opensrp.domain.Child;
import org.opensrp.domain.EligibleCouple;
import org.opensrp.domain.Mother;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import org.opensrp.repository.AllChildren;
import org.opensrp.repository.AllEligibleCouples;
import org.opensrp.repository.AllMothers;
import org.opensrp.service.reporting.ILocationLoader;
import org.opensrp.service.reporting.LocationLoader;

public class LocationLoaderTest {
    @Mock
    private AllChildren allChildren;
    @Mock
    private AllMothers allMothers;
    @Mock
    private AllEligibleCouples allEligibleCouples;
    @Mock
    private Child child;
    @Mock
    private Mother mother;
    @Mock
    private EligibleCouple eligibleCouple;

    private ILocationLoader locationLoader;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        locationLoader = new LocationLoader(allEligibleCouples, allMothers, allChildren);
        when(allChildren.findByCaseId("child id 1")).thenReturn(child);
        when(allMothers.findByCaseId("mother id 1")).thenReturn(mother);
        when(allEligibleCouples.findByCaseId("ec id 1")).thenReturn(eligibleCouple);
        when(child.motherCaseId()).thenReturn("mother id 1");
        when(mother.ecCaseId()).thenReturn("ec id 1");
    }

    @Test
    public void shouldReturnLocationFromChildEntityIfBindTypeIsChild() {
        when(allChildren.findByCaseId("child id 1")).thenReturn(child);

        locationLoader.loadLocationFor("child", "child id 1");

        verify(child).motherCaseId();
        verify(mother).ecCaseId();
        verify(eligibleCouple).location();
    }

    @Test
    public void shouldReturnLocationFromMotherEntityIfBindTypeIsMother() {
        when(allMothers.findByCaseId("mother id 1")).thenReturn(mother);

        locationLoader.loadLocationFor("mother", "mother id 1");

        verify(mother).ecCaseId();
        verify(eligibleCouple).location();
    }

    @Test
    public void shouldReturnLocationFromEligibleCoupleEntityIfBindTypeIsEligibleCouple() {
        when(allEligibleCouples.findByCaseId("ec id 1")).thenReturn(eligibleCouple);

        locationLoader.loadLocationFor("eligible_couple", "ec id 1");

        verify(eligibleCouple).location();
    }
}

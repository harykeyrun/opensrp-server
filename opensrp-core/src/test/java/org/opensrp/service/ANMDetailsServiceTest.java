package org.opensrp.service;

import org.opensrp.domain.ANMDetail;
import org.opensrp.domain.ANMDetails;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.opensrp.dto.ANMDTO;
import org.opensrp.repository.AllChildren;
import org.opensrp.repository.AllEligibleCouples;
import org.opensrp.repository.AllMothers;
import org.opensrp.service.ANMDetailsService;

import java.util.List;

import static java.util.Arrays.asList;
import static org.opensrp.common.util.EasyMap.mapOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ANMDetailsServiceTest {

    @Mock
    private AllEligibleCouples allEligibleCouples;
    @Mock
    private AllMothers allMothers;
    @Mock
    private AllChildren allChildren;
    private ANMDetailsService service;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        service = new ANMDetailsService(allEligibleCouples, allMothers, allChildren);
    }

    @Test
    public void shouldGetBeneficiariesCountForANM() {
        List<String> anmIdentifiers = asList("demo1");
        when(allEligibleCouples.allOpenECs(anmIdentifiers)).thenReturn(mapOf("demo1", 1));
        when(allEligibleCouples.fpCountForANM(anmIdentifiers)).thenReturn(mapOf("demo1", 2));
        when(allMothers.allOpenMotherCount(anmIdentifiers)).thenReturn(mapOf("demo1", 3));
        when(allMothers.allOpenPNCCount(anmIdentifiers)).thenReturn(mapOf("demo1", 4));
        when(allChildren.openChildCount(anmIdentifiers)).thenReturn(mapOf("demo1", 5));


        ANMDetails anmDetails = service.anmDetails(asList(new ANMDTO("demo1", "demo1", null)));

        assertEquals(new ANMDetails(asList(new ANMDetail("demo1", "demo1", null, 1, 2, 3, 4, 5))), anmDetails);
    }
}

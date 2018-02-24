package com.snappyappsdev.nonverbalapp.database;

import com.snappyappsdev.nonverbalapp.database.model.Pec;
import com.snappyappsdev.nonverbalapp.database.model.PecDao;
import com.snappyappsdev.nonverbalapp.database.model.PecService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by lrocha on 2/19/18.
 */

public class PecServiceTest {

    @Mock AppDatabase appDatabaseMock;
    @Mock PecDao pecDao;

    private PecService pecService;

    private Pec otherPec;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(appDatabaseMock.pecDao()).thenReturn(pecDao);
        when(pecDao.getPecs()).thenReturn(Flowable.just(getDbMockPecs()));

        pecService = new PecService(Schedulers.trampoline() , appDatabaseMock);
    }

    @Test
    public void getPecs() throws Exception {
        pecService.getPecs().test().assertValue(getDbMockPecs());

        // Create a different list and have the db call return that on subsequent calls
        List<Pec> modifiedList = getDbMockPecs();
        modifiedList.remove(0);
        when(pecDao.getPecs()).thenReturn(Flowable.just(modifiedList));

        // Verify we still get the cached list rather than the different db response
        pecService.getPecs().test().assertValue(getDbMockPecs());
    }

    @Test
    public void getPec() throws Exception {
        // Load trending pecs to mimic most likely state of app
        pecService.getPecs().subscribe();
        otherPec = new Pec("other_Pec");
        Pec pec = getDbMockPecs().get(0);

        // Change requester to return a different pec if ever invoked
        when(pecDao.getPec(anyString())).thenReturn(Flowable.just(otherPec));

        // Verify we still get the pec_1 pec, which is cached from our trending pecs call above
        pecService.getPec(pec.getTitle()).test().assertValue(pec);

        // Fetch a pec that would not be in the cache and verify the db result is returned
        pecService.getPec("other_Pec").test().assertValue(otherPec);
    }


    private List<Pec> getDbMockPecs(){
        List<Pec> pecs = new ArrayList<>();
        for(int i = 0 ; i < 5; i++){
            pecs.add(new Pec("pec_" + i));
        }
        return pecs;
    }

}

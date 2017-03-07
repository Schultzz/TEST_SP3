package net.sf.javaanpr.test;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import net.sf.javaanpr.imageanalysis.CarSnapshot;
import net.sf.javaanpr.intelligence.Intelligence;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(JUnitParamsRunner.class)
public class RecognitionAllIt {

    @Test
    @FileParameters("src/test/resources/input.csv")
    public void testAllSnapshots(String path, String expectedResult) throws Exception {

        final String image = "snapshots/" + path;
        CarSnapshot carSnap = new CarSnapshot(image);
        assertThat("carSnap is null", carSnap, notNullValue());
        assertThat("carSnap.image is null", carSnap.getImage(), notNullValue());
        Intelligence intel = new Intelligence();
        assertThat(intel, is(notNullValue()));
        String spz = intel.recognize(carSnap);
        assertThat("The licence plate is null - are you sure the image has the correct color space?", spz, notNullValue());
        assertThat(spz, equalTo(expectedResult));
        carSnap.close();
    }
}

package com.mobiquity.LocalDelicacies.delicacies;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.mobiquity.LocalDelicacies.R;
import com.mobiquity.LocalDelicacies.location.Location;
import com.mobiquity.LocalDelicacies.location.LocationDetailActivity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static com.mobiquity.LocalDelicacies.support.Assert.assertViewIsVisible;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by jwashington on 7/23/14.
 */
@RunWith(RobolectricTestRunner.class)
public class DelicacyDetailActivityTest {

    DelicacyDetailActivity activity;
    Intent intent;


    @Before
    public void setUp()
    {
        intent = DelicacyDetailActivity.createIntent(
                Robolectric.application.getApplicationContext(),
                getTestDelicacy());

        activity = Robolectric.buildActivity(DelicacyDetailActivity.class)
                .withIntent(intent)
                .create()
                .start()
                .resume()
                .get();
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull(activity);
    }

    @Test
    public void shouldBeAbleToCreateIntent() throws Exception
    {
        Delicacy delicacy = new Delicacy("test_delicacy", null);
        Intent intent = activity.createIntent(activity, delicacy);
        assertNotNull(intent);
        Delicacy bundledDelicacy = Delicacy.createDelicacyFromBundle(intent.getExtras());
        assertTrue(bundledDelicacy.equals(delicacy));
    }

    @Test
    public void shouldHaveAnIntent() throws Exception
    {
        assertNotNull(activity.getIntent());
        assertThat(intent, equalTo(activity.getIntent()));
    }

    @Test
    public void shouldContainDelicacyName() throws Exception
    {
        TextView delicacyName = (TextView) activity.findViewById(R.id.name);
        assertViewIsVisible(delicacyName);
        assertThat(delicacyName.getText().toString(), equalTo(getBundledDelicacy().getName()));
    }

    @Test
    public void shouldContainDelicacyImage() throws Exception
    {
        ImageView image = (ImageView)activity.findViewById(R.id.image);
        assertViewIsVisible(image);
    }

    @Test
    public void shouldContainDelicacyDescription() throws Exception
    {
        TextView description = (TextView) activity.findViewById(R.id.description);
        assertViewIsVisible(description);
    }

    @Test
    public void shouldContainBookmarkImage() throws Exception
    {
        ImageView bookmarkImage = (ImageView) activity.findViewById(R.id.bookmarked_button);
        assertViewIsVisible(bookmarkImage);
        if(getBundledDelicacy().isBookmarked())
        {
            assertThat(bookmarkImage.getDrawable(), equalTo(activity.getResources().getDrawable(R.drawable.love)));
        }
        else
        {
            assertThat(bookmarkImage.getDrawable(), equalTo(activity.getResources().getDrawable(R.drawable.no_love)));

        }
    }

    @Test
    public void shouldContainRatingBar() throws Exception
    {
        RatingBar ratingBar = (RatingBar) activity.findViewById(R.id.ratingBar);
        assertViewIsVisible(ratingBar);
    }

    private Delicacy getTestDelicacy()
    {
        return new Delicacy("Ice Cream", "delicacy_url");
    }

    private Delicacy getBundledDelicacy()
    {
        return Delicacy.createDelicacyFromBundle(intent.getExtras());
    }

}

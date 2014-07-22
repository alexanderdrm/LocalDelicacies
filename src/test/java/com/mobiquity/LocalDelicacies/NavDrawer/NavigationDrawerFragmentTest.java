package com.mobiquity.LocalDelicacies.NavDrawer;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.widget.DrawerLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.mobiquity.LocalDelicacies.ApplicationBus;
import com.mobiquity.LocalDelicacies.R;
import com.mobiquity.LocalDelicacies.support.BusHelper;
import com.mobiquity.LocalDelicacies.support.ResourceLocator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowHandler;
import org.robolectric.shadows.ShadowToast;

import static com.mobiquity.LocalDelicacies.support.FragmentUtil.startFragment;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @since 1.0
 */
@RunWith (RobolectricTestRunner.class)
public class NavigationDrawerFragmentTest
{
    private ListAdapter adapter;
    private ListView drawerList;

    private NavigationDrawerFragment navFragment;
    private ApplicationBus bus;
    private BusHelper busHelper;


    @Before
    public void setUp()
    {
       navFragment = new NavigationDrawerFragment();

        startFragment(navFragment);
        drawerList = (ListView) navFragment.getView().findViewById(R.id.drawer_list);
        adapter = drawerList.getAdapter();
        bus = ApplicationBus.getInstance();
        busHelper = new BusHelper();
        bus.register(busHelper);
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( navFragment );
    }

    @Test
    public void shouldHaveListView() throws Exception
    {
        assertNotNull(drawerList);
    }

    @Test
    public void shouldHaveAdapter() throws Exception
    {
        Assert.assertNotNull(adapter);
    }

    @Test
    public void shouldDisplayToastWhenClicked() throws Exception
    {
        for ( int index = 0; index < adapter.getCount(); index++ )
        {
            TextView navItem = (TextView) adapter.getView( index, null, null );
            Robolectric.shadowOf(drawerList).performItemClick( index );
            ShadowHandler.idleMainLooper();
            assertThat( ShadowToast.getTextOfLatestToast(),
                    equalTo( navItem.getText().toString() ) );
        }
    }

    @Test
    public void shouldHaveNavDrawerItems() throws Exception
    {
        String[] stringArray = ResourceLocator.getStringArray(R.array.nav_titles);
        for ( int index = 0; index < adapter.getCount() - 1; index++ )
        {
            assertThat( stringArray[index],
                    equalTo(adapter.getItem(index)) );
        }
    }


    @Test
    public void shouldHaveItemClickListener() throws Exception
    {
        Assert.assertNotNull(drawerList.getOnItemClickListener());
    }

    @Test
    public void shouldPostNavigationDrawerClickEvent() throws Exception
    {
        Robolectric.shadowOf(drawerList).performItemClick( 0 );
        assertTrue(busHelper.getLastEvent() instanceof NavigationDrawerClickEvent);


    }




}

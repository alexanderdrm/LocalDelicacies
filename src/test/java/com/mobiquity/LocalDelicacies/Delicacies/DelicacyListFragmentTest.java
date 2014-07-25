package com.mobiquity.LocalDelicacies.delicacies;

import android.app.Fragment;
import android.content.Context;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.mobiquity.LocalDelicacies.R;
import com.mobiquity.LocalDelicacies.filters.PermissiveFilter;
import com.mobiquity.LocalDelicacies.support.FragmentUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

import static com.mobiquity.LocalDelicacies.support.Assert.assertViewIsGone;
import static com.mobiquity.LocalDelicacies.support.Assert.assertViewIsVisible;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by jwashington on 7/22/14.
 */

@RunWith(RobolectricTestRunner.class)

public class DelicacyListFragmentTest {

    private Fragment delicacyListFragment;

    @Before
    public void setUp() throws Exception
    {
        delicacyListFragment = new DelicacyListFragment();
        FragmentUtil.startFragment(delicacyListFragment);
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( delicacyListFragment );
    }

    @Test
    public void shouldContainListView()
    {
        View delicacyList = delicacyListFragment.getView().findViewById(R.id.delicacy_list);
        assertNotNull(delicacyList);
        assertTrue(delicacyList instanceof ListView);
    }

    @Test
    public void shouldContainAdapter()
    {
        ListView delicacyList = (ListView) delicacyListFragment.getView().findViewById(R.id.delicacy_list);
        ListAdapter adapter = delicacyList.getAdapter();
        assertNotNull(adapter);
        assertTrue(adapter instanceof DelicacyListAdapter);
    }

    @Test
    public void shouldShowTextViewWhenListIsEmpty()
    {
        TestEmptyDelicacyListFragment emptyFragment = new TestEmptyDelicacyListFragment();
        FragmentUtil.startFragment(emptyFragment);
        assertNotNull(emptyFragment);

        ListView listView = (ListView) emptyFragment.getView().findViewById(R.id.delicacy_list);
        TextView emptyViewMessage = (TextView) emptyFragment.getView().findViewById(android.R.id.empty);

        assertTrue(listView.getAdapter().isEmpty());
        assertViewIsGone(listView);
        assertViewIsVisible(emptyViewMessage);
    }

    private class TestEmptyDelicacyListFragment extends DelicacyListFragment
    {
        @Override
        protected DelicacyListAdapter createAdapter(Context context) {
            return new DelicacyListAdapter(context, new ArrayList<Delicacy>(), new PermissiveFilter());
        }
    }



}

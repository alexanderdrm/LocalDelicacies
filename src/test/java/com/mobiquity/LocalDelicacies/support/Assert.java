package com.mobiquity.LocalDelicacies.support;

import android.view.View;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @since 1.0
 */
public class Assert
{
    public static void assertViewIsVisible( View view )
    {
        assertNotNull( view );
        assertThat( view.getVisibility(),
                    equalTo( View.VISIBLE ) );
    }

    public static void assertViewIsGone( View view )
    {
        assertNotNull( view );
        assertThat( view.getVisibility(),
                    equalTo( View.GONE ) );
    }
}

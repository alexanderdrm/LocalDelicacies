package com.mobiquity.LocalDelicacies.filters;

import com.mobiquity.LocalDelicacies.filters.Filter;

/**
 * Created by jwashington on 7/23/14.
 */
public class PermissiveFilter implements Filter {
    @Override
    public boolean shouldDisplay(Object object) {
        return true;
    }
}

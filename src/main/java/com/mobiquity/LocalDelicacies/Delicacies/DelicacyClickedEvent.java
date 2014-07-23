package com.mobiquity.LocalDelicacies.delicacies;

import com.mobiquity.LocalDelicacies.BaseEvent;

/**
 * Created by jwashington on 7/22/14.
 */
public class DelicacyClickedEvent extends BaseEvent {

    private Delicacy delicacy;

    public DelicacyClickedEvent(Delicacy delicacy) {
        this.delicacy = delicacy;
    }

    public Delicacy getDelicacy() {
        return delicacy;
    }

    public void setDelicacy(Delicacy delicacy) {
        this.delicacy = delicacy;
    }
}

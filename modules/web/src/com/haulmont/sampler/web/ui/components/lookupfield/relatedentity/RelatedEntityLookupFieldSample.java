package com.haulmont.sampler.web.ui.components.lookupfield.relatedentity;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.MetadataTools;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.sampler.entity.Order;

import javax.inject.Inject;

@UiController("related-entity-lookupfield")
@UiDescriptor("related-entity-lookupfield.xml")
@LoadDataBeforeShow
public class RelatedEntityLookupFieldSample extends ScreenFragment {

    @Inject
    private InstanceContainer<Order> orderDc;
    @Inject
    private Metadata metadata;
    @Inject
    private MetadataTools metadataTools;
    @Inject
    private Notifications notifications;

    @Subscribe
    protected void onInit(InitEvent event) {
        // InstanceContainer initialization. It is usually done automatically if the screen is
        // inherited from StandardEditor and is used as an entity editor.
        Order order = metadata.create(Order.class);
        orderDc.setItem(order);
    }

    @Subscribe(id = "orderDc", target = Target.DATA_CONTAINER)
    protected void onOrderDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<Order> event) {
        Object str = event.getValue() instanceof Entity
                ? metadataTools.getInstanceName((Entity) event.getValue())
                : event.getValue();
        notifications.create()
                .withCaption(event.getProperty() + " = " + str)
                .show();
    }


}
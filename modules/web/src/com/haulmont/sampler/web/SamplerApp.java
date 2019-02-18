package com.haulmont.sampler.web;

import com.haulmont.cuba.core.sys.AppContext;
import com.haulmont.cuba.security.auth.LoginPasswordCredentials;
import com.haulmont.cuba.web.DefaultApp;
import com.haulmont.cuba.web.sys.RedirectHandler;
import org.apache.commons.lang3.BooleanUtils;

public class SamplerApp extends DefaultApp {

    @Override
    protected String routeTopLevelWindowId() {
        if (BooleanUtils.toBoolean(AppContext.getProperty("sampler.developerMode"))) {
            return super.routeTopLevelWindowId();
        }

        if (!getConnection().isAuthenticated()) {
            getConnection().login(new LoginPasswordCredentials("demo", "cuba123", getLocale()));
        }

        return "mainWindow";
    }

    public RedirectHandler getRedirectHandler() {
        return redirectHandler;
    }
}
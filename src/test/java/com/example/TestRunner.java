package com.example;

import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

public class TestRunner {

    public static void main(String[] args) {
        SummaryGeneratingListener listener = new SummaryGeneratingListener();

        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.discoveryRequest()
                .selectors(DiscoverySelectors.selectPackage("com.example"))
                .build();

        LauncherFactory.create().execute(request, listener);

        System.out.println(listener.getSummary().getTestsFailedCount());


    }
}

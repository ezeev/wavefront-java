package com.wavefront.integrations.example;

import com.apple.eawt.AppEvent;
import com.codahale.metrics.ConsoleReporter;
import com.wavefront.integrations.metrics.WavefrontReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jvm.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.TimeUnit;


/**
 * Created by evanpease on 11/14/16.
 */
public class DropWizard {

    public static void main(String [] args) {

            Logger logger = LoggerFactory.getLogger(DropWizard.class);

            MetricRegistry metricRegistry = new MetricRegistry();
            metricRegistry.register("jvm.gc", new GarbageCollectorMetricSet());
            metricRegistry.register("jvm.memory", new MemoryUsageGaugeSet());
            metricRegistry.register("jvm.thread-states", new ThreadStatesGaugeSet());
            metricRegistry.register("filedescriptor.usage", new FileDescriptorRatioGauge());

            final WavefrontReporter wavefrontReporter = WavefrontReporter.forRegistry(metricRegistry)
                    .withSource("dropwizard-test-host")
                    .prefixedWith("dropwizard")
                    .build("192.168.99.100", 2878);

            wavefrontReporter.start(10, TimeUnit.SECONDS);


            //final ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metricRegistry).build();
            //consoleReporter.start(10,TimeUnit.SECONDS);

            System.out.println("Entering forever loop");

            while (true) {

            }

    }
}

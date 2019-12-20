package com.universal.configuration;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import com.codahale.metrics.jvm.ThreadStatesGaugeSet;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Pankaj on 17/12/19.
 */
@Configuration
@EnableMetrics
public class MetricsConfiguration extends MetricsConfigurerAdapter {

    private MetricRegistry registry;

    @Override
    public MetricRegistry getMetricRegistry() {
        if (this.registry == null) {
            registry = new MetricRegistry();
            // register JVM metrics
            registry.register("jvm.garbage-collector", new GarbageCollectorMetricSet());
            registry.register("jvm.memory", new MemoryUsageGaugeSet());
            registry.register("jvm.thread-states", new ThreadStatesGaugeSet());
        }
        return registry;
    }

    @Override
    public void configureReporters(MetricRegistry metricRegistry) {
        JmxReporter.forRegistry(metricRegistry).build().start();
    }
}

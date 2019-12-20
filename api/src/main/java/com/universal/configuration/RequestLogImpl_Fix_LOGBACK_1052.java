package com.universal.configuration;

import ch.qos.logback.access.jetty.RequestLogImpl;
import org.eclipse.jetty.util.component.LifeCycle;

/**
 * Created by pankaj on 11/09/19.
 */

public class RequestLogImpl_Fix_LOGBACK_1052 extends RequestLogImpl implements LifeCycle {
}

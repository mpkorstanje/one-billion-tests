package com.example;

import com.sun.management.HotSpotDiagnosticMXBean;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import javax.management.MBeanServer;
import java.lang.management.ManagementFactory;
import java.util.stream.LongStream;
import java.util.stream.Stream;

class LotsOfTests {

    private static final int ONE_MILLION =     1_000_000;
    private static final int ONE_BILLION = 1_000_000_000;
    private static final long LIMIT = ONE_BILLION;

    @TestFactory
    Stream<DynamicTest> test(){
        Stream<Long> longStream = LongStream.range(0, LIMIT).boxed();
        return DynamicTest.stream(longStream, String::valueOf, aLong -> {
            if(aLong % 10_000 == 0) {
                System.out.println(aLong);
            }

            // Show that we're leaving garbage behind.
            if(aLong == ONE_MILLION) {
                MBeanServer server = ManagementFactory.getPlatformMBeanServer();
                HotSpotDiagnosticMXBean mxBean = ManagementFactory.newPlatformMXBeanProxy(
                        server, "com.sun.management:type=HotSpotDiagnostic", HotSpotDiagnosticMXBean.class);
                mxBean.dumpHeap(System.currentTimeMillis() + ".hprof", true);

                System.exit(1);
            }

        } );
    }
}

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
    private static long startTime;
    @TestFactory
    Stream<DynamicTest> test(){
        startTime = System.currentTimeMillis();
        Stream<Long> longStream = LongStream.range(0, LIMIT).boxed();
        return DynamicTest.stream(longStream, String::valueOf, aLong -> {
            if(aLong % ONE_MILLION == 0) {
                double testPerSecond = aLong.doubleValue() / (System.currentTimeMillis() - startTime) * 1000;
                long testRemaining = LIMIT - aLong;
                double minutesRemaining = testRemaining / testPerSecond / 60;
                System.out.println(aLong + " " + testPerSecond + " test/s " + minutesRemaining + " min left");
            }

//            // Show that we're leaving garbage behind.
//            if(aLong == ONE_MILLION) {
//                MBeanServer server = ManagementFactory.getPlatformMBeanServer();
//                HotSpotDiagnosticMXBean mxBean = ManagementFactory.newPlatformMXBeanProxy(
//                        server, "com.sun.management:type=HotSpotDiagnostic", HotSpotDiagnosticMXBean.class);
//                mxBean.dumpHeap(System.currentTimeMillis() + ".hprof", true);
//
//                System.exit(1);
//            }

        } );
    }
}

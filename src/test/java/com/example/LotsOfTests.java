package com.example;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.LongStream;
import java.util.stream.Stream;

class LotsOfTests {

    private static final int ONE_BILLION = 1_000_000_000;
    private static final long LIMIT = ONE_BILLION;

    @TestFactory
    Stream<DynamicTest> test(){
        Stream<Long> longStream = LongStream.range(0, LIMIT).boxed();
        return DynamicTest.stream(longStream, String::valueOf, aLong -> {
            if(aLong % 10_000 == 0) {
                System.out.println(aLong);
            }
        } );
    }
}

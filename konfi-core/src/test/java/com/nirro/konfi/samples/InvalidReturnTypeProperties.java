package com.nirro.konfi.samples;

import com.nirro.konfi.annotation.KonfiProperty;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface InvalidReturnTypeProperties {

    @KonfiProperty(key = "pojo")
    Pojo pojo();

    @KonfiProperty(key = "optionalPojo")
    Optional<Pojo> optionalPojo();

    @KonfiProperty(key = "listOfPojo")
    List<Pojo> listOfPojo();

    @KonfiProperty(key = "optionalListOfPojo")
    Optional<List<Pojo>> optionalListOfPojo();

    @KonfiProperty(key = "setOfPojo")
    Set<Pojo> setOfPojo();

    @KonfiProperty(key = "optionalSetOfPojo")
    Optional<Set<Pojo>> optionalSetOfPojo();

    @KonfiProperty(key = "primitiveIntegerValue")
    int primitiveIntegerValue();

    @KonfiProperty(key = "primitiveCharValue")
    char primitiveCharValue();

    @KonfiProperty(key = "primitiveDoubleValue")
    double primitiveDoubleValue();

    @KonfiProperty(key = "primitiveLongValue")
    long primitiveLongValue();

    @KonfiProperty(key = "primitiveFloatValue")
    double primitiveFloatValue();

    @KonfiProperty(key = "primitiveIntegerArrayValue")
    int[] primitiveIntegerArrayValue();

    @KonfiProperty(key = "primitiveCharArrayValue")
    char[] primitiveCharArrayValue();

    @KonfiProperty(key = "primitiveDoubleArrayValue")
    double[] primitiveDoubleArrayValue();

    @KonfiProperty(key = "primitiveLongArrayValue")
    long[] primitiveLongArrayValue();

    @KonfiProperty(key = "primitiveFloatArrayValue")
    double[] primitiveFloatArrayValue();

    @KonfiProperty(key = "optionalOfOptionalString")
    Optional<Optional<String>> optionalOfOptionalString();

    @KonfiProperty(key = "ListOfOptionalString")
    List<Optional<String>> ListOfOptionalString();

    @KonfiProperty(key = "ListOfListOfString")
    List<List<String>> ListOfListOfString();

}
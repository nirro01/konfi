package com.nirro.konfi.samples;

import com.nirro.konfi.annotation.KonfiProperty;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LongProperties {

    @KonfiProperty(key = "value")
    Long value();

    @KonfiProperty(key = "invalidValue")
    Long invalidValue();

    @KonfiProperty(key = "optionalValue")
    Optional<Long> optionalValue();

    @KonfiProperty(key = "listOfValues")
    List<Long> listOfValues();

    @KonfiProperty(key = "optionalListOfValues")
    Optional<List<Long>> optionalListOfValues();

    @KonfiProperty(key = "setOfValues")
    Set<Long> setOfValues();

    @KonfiProperty(key = "optionalSetOfValues")
    Optional<Set<Long>> optionalSetOfValues();

    @KonfiProperty(key = "missingValue")
    Long missingValue();

    @KonfiProperty(key = "missingOptionalValue")
    Optional<Long> missingOptionalValue();

    @KonfiProperty(key = "missingListOfValues")
    List<Long> missingListOfValues();

    @KonfiProperty(key = "missingOptionalListOfValues")
    Optional<List<Long>> missingOptionalListOfValues();

    @KonfiProperty(key = "missingSetOfValues")
    Set<Long> missingSetOfValues();

    @KonfiProperty(key = "missingOptionalSetOfValues")
    Optional<Set<Long>> missingOptionalSetOfValues();
}
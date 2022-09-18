package com.nirro.konfi.samples;

import com.nirro.konfi.annotation.KonfiProperty;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IntegerProperties {

    @KonfiProperty(key = "value")
    Integer value();

    @KonfiProperty(key = "invalidValue")
    Integer invalidValue();

    @KonfiProperty(key = "optionalValue")
    Optional<Integer> optionalValue();

    @KonfiProperty(key = "listOfValues")
    List<Integer> listOfValues();

    @KonfiProperty(key = "optionalListOfValues")
    Optional<List<Integer>> optionalListOfValues();

    @KonfiProperty(key = "setOfValues")
    Set<Integer> setOfValues();

    @KonfiProperty(key = "optionalSetOfValues")
    Optional<Set<Integer>> optionalSetOfValues();

    @KonfiProperty(key = "missingValue")
    Integer missingValue();

    @KonfiProperty(key = "missingOptionalValue")
    Optional<Integer> missingOptionalValue();

    @KonfiProperty(key = "missingListOfValues")
    List<Integer> missingListOfValues();

    @KonfiProperty(key = "missingOptionalListOfValues")
    Optional<List<Integer>> missingOptionalListOfValues();

    @KonfiProperty(key = "missingSetOfValues")
    Set<Integer> missingSetOfValues();

    @KonfiProperty(key = "missingOptionalSetOfValues")
    Optional<Set<Integer>> missingOptionalSetOfValues();
}
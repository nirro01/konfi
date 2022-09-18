package com.nirro.konfi.samples;

import com.nirro.konfi.annotation.KonfiProperty;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DoubleProperties {

    @KonfiProperty(key = "value")
    Double value();

    @KonfiProperty(key = "invalidValue")
    Double invalidValue();

    @KonfiProperty(key = "optionalValue")
    Optional<Double> optionalValue();

    @KonfiProperty(key = "listOfValues")
    List<Double> listOfValues();

    @KonfiProperty(key = "optionalListOfValues")
    Optional<List<Double>> optionalListOfValues();

    @KonfiProperty(key = "setOfValues")
    Set<Double> setOfValues();

    @KonfiProperty(key = "optionalSetOfValues")
    Optional<Set<Double>> optionalSetOfValues();

    @KonfiProperty(key = "missingValue")
    Boolean missingValue();

    @KonfiProperty(key = "missingOptionalValue")
    Optional<Double> missingOptionalValue();

    @KonfiProperty(key = "missingListOfValues")
    List<Double> missingListOfValues();

    @KonfiProperty(key = "missingOptionalListOfValues")
    Optional<List<Double>> missingOptionalListOfValues();

    @KonfiProperty(key = "missingSetOfValues")
    Set<Double> missingSetOfValues();

    @KonfiProperty(key = "missingOptionalSetOfValues")
    Optional<Set<Double>> missingOptionalSetOfValues();
}
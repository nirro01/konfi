package com.nirro.konfi.samples;

import com.nirro.konfi.annotation.KonfiProperty;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FloatProperties {

    @KonfiProperty(key = "value")
    Float value();

    @KonfiProperty(key = "invalidValue")
    Float invalidValue();

    @KonfiProperty(key = "optionalValue")
    Optional<Float> optionalValue();

    @KonfiProperty(key = "listOfValues")
    List<Float> listOfValues();

    @KonfiProperty(key = "optionalListOfValues")
    Optional<List<Float>> optionalListOfValues();

    @KonfiProperty(key = "setOfValues")
    Set<Float> setOfValues();

    @KonfiProperty(key = "optionalSetOfValues")
    Optional<Set<Float>> optionalSetOfValues();

    @KonfiProperty(key = "missingValue")
    Float missingValue();

    @KonfiProperty(key = "missingOptionalValue")
    Optional<Float> missingOptionalValue();

    @KonfiProperty(key = "missingListOfValues")
    List<Float> missingListOfValues();

    @KonfiProperty(key = "missingOptionalListOfValues")
    Optional<List<Float>> missingOptionalListOfValues();

    @KonfiProperty(key = "missingSetOfValues")
    Set<Float> missingSetOfValues();

    @KonfiProperty(key = "missingOptionalSetOfValues")
    Optional<Set<Float>> missingOptionalSetOfValues();
}
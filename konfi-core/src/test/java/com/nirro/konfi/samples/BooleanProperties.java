package com.nirro.konfi.samples;

import com.nirro.konfi.annotation.KonfiProperty;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BooleanProperties {

    @KonfiProperty(key = "value")
    Boolean value();

    @KonfiProperty(key = "optionalValue")
    Optional<Boolean> optionalValue();

    @KonfiProperty(key = "listOfValues")
    List<Boolean> listOfValues();

    @KonfiProperty(key = "optionalListOfValues")
    Optional<List<Boolean>> optionalListOfValues();

    @KonfiProperty(key = "setOfValues")
    Set<Boolean> setOfValues();

    @KonfiProperty(key = "optionalSetOfValues")
    Optional<Set<Boolean>> optionalSetOfValues();

    @KonfiProperty(key = "missingValue")
    Boolean missingValue();

    @KonfiProperty(key = "missingOptionalValue")
    Optional<Boolean> missingOptionalValue();

    @KonfiProperty(key = "missingListOfValues")
    List<Boolean> missingListOfValues();

    @KonfiProperty(key = "missingOptionalListOfValues")
    Optional<List<Boolean>> missingOptionalListOfValues();

    @KonfiProperty(key = "missingSetOfValues")
    Set<Boolean> missingSetOfValues();

    @KonfiProperty(key = "missingOptionalSetOfValues")
    Optional<Set<Boolean>> missingOptionalSetOfValues();
}
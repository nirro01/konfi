package com.nirro.konfi.samples;

import com.nirro.konfi.annotation.KonfiProperty;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StringProperties {

    @KonfiProperty(key = "value")
    String value();

    @KonfiProperty(key = "optionalValue")
    Optional<String> optionalValue();

    @KonfiProperty(key = "listOfValues")
    List<String> listOfValues();

    @KonfiProperty(key = "optionalListOfValues")
    Optional<List<String>> optionalListOfValues();

    @KonfiProperty(key = "setOfValues")
    Set<String> setOfValues();

    @KonfiProperty(key = "optionalSetOfValues")
    Optional<Set<String>> optionalSetOfValues();

    @KonfiProperty(key = "missingValue")
    String missingValue();

    @KonfiProperty(key = "missingOptionalValue")
    Optional<String> missingOptionalValue();

    @KonfiProperty(key = "missingListOfValues")
    List<String> missingListOfValues();

    @KonfiProperty(key = "missingOptionalListOfValues")
    Optional<List<String>> missingOptionalListOfValues();

    @KonfiProperty(key = "missingSetOfValues")
    Set<String> missingSetOfValues();

    @KonfiProperty(key = "missingOptionalSetOfValues")
    Optional<Set<String>> missingOptionalSetOfValues();
}
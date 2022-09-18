package com.nirro.konfi;

import com.nirro.konfi.exception.InvalidReturnTypeException;
import com.nirro.konfi.exception.InvalidValueException;
import com.nirro.konfi.exception.MissingValueException;
import com.nirro.konfi.samples.*;
import com.nirro.konfi.source.EnvironmentVariables;
import com.nirro.konfi.source.PropertiesSources;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

class KonfiTest {

    private static final String VALUE = "value";
    private static final String INVALID_VALUE = "invalidValue";
    private static final String OPTIONAL_VALUE = "optionalValue";
    private static final String LIST_OF_VALUES = "listOfValues";
    private static final String OPTIONAL_LIST_OF_VALUES = "optionalListOfValues";
    private static final String SET_OF_VALUES = "setOfValues";
    private static final String OPTIONAL_SET_OF_VALUES = "optionalSetOfValues";

    @Test
    void StringTest() {
        var konfi = Konfi
                .builder(StringProperties.class)
                .sources(List.of(PropertiesSources.newMapSource(
                        Map.ofEntries(
                                Map.entry(VALUE, "a"),
                                Map.entry(OPTIONAL_VALUE, "a"),
                                Map.entry(LIST_OF_VALUES, "a,b"),
                                Map.entry(OPTIONAL_LIST_OF_VALUES, "a,b"),
                                Map.entry(SET_OF_VALUES, "a,b"),
                                Map.entry(OPTIONAL_SET_OF_VALUES, "a,b")))))
                .build();

        Assertions.assertEquals("a", konfi.value());
        Assertions.assertEquals(Optional.of("a"), konfi.optionalValue());
        Assertions.assertEquals(List.of("a", "b"), konfi.listOfValues());
        Assertions.assertEquals(Optional.of(List.of("a", "b")), konfi.optionalListOfValues());
        Assertions.assertEquals(Set.of("a", "b"), konfi.setOfValues());
        Assertions.assertEquals(Optional.of(Set.of("a", "b")), konfi.optionalSetOfValues());

        Assertions.assertThrows(MissingValueException.class, konfi::missingValue);
        Assertions.assertEquals(Optional.empty(), konfi.missingOptionalValue());
        Assertions.assertThrows(MissingValueException.class, konfi::missingListOfValues);
        Assertions.assertEquals(Optional.empty(), konfi.missingOptionalListOfValues());
        Assertions.assertThrows(MissingValueException.class, konfi::missingSetOfValues);
        Assertions.assertEquals(Optional.empty(), konfi.missingOptionalSetOfValues());
    }

    @Test
    void IntegerTest() {
        var konfi = Konfi
                .builder(IntegerProperties.class)
                .sources(Collections.singletonList(
                        PropertiesSources.newMapSource(
                                Map.ofEntries(
                                        Map.entry(VALUE, "1"),
                                        Map.entry(INVALID_VALUE, "a"),
                                        Map.entry(OPTIONAL_VALUE, "1"),
                                        Map.entry(LIST_OF_VALUES, "1,2"),
                                        Map.entry(OPTIONAL_LIST_OF_VALUES, "1,2"),
                                        Map.entry(SET_OF_VALUES, "1,2"),
                                        Map.entry(OPTIONAL_SET_OF_VALUES, "1,2")))))
                .build();

        Assertions.assertEquals(1, konfi.value());
        Assertions.assertThrows(InvalidValueException.class, konfi::invalidValue);
        Assertions.assertEquals(Optional.of(1), konfi.optionalValue());
        Assertions.assertEquals(List.of(1, 2), konfi.listOfValues());
        Assertions.assertEquals(Optional.of(List.of(1, 2)), konfi.optionalListOfValues());
        Assertions.assertEquals(Set.of(1, 2), konfi.setOfValues());
        Assertions.assertEquals(Optional.of(Set.of(1, 2)), konfi.optionalSetOfValues());

        Assertions.assertThrows(MissingValueException.class, konfi::missingValue);
        Assertions.assertEquals(Optional.empty(), konfi.missingOptionalValue());
        Assertions.assertThrows(MissingValueException.class, konfi::missingListOfValues);
        Assertions.assertEquals(Optional.empty(), konfi.missingOptionalListOfValues());
        Assertions.assertThrows(MissingValueException.class, konfi::missingSetOfValues);
        Assertions.assertEquals(Optional.empty(), konfi.missingOptionalSetOfValues());
    }

    @Test
    void LongTest() {
        var konfi = Konfi
                .builder(LongProperties.class)
                .sources(Collections.singletonList(
                        PropertiesSources.newMapSource(Map.ofEntries(
                                Map.entry(VALUE, "1"),
                                Map.entry(INVALID_VALUE, "a"),
                                Map.entry(OPTIONAL_VALUE, "1"),
                                Map.entry(LIST_OF_VALUES, "1,2"),
                                Map.entry(OPTIONAL_LIST_OF_VALUES, "1,2"),
                                Map.entry(SET_OF_VALUES, "1,2"),
                                Map.entry(OPTIONAL_SET_OF_VALUES, "1,2")))))
                .build();

        Assertions.assertEquals(1L, konfi.value());
        Assertions.assertThrows(InvalidValueException.class, konfi::invalidValue);
        Assertions.assertEquals(Optional.of(1L), konfi.optionalValue());
        Assertions.assertEquals(List.of(1L, 2L), konfi.listOfValues());
        Assertions.assertEquals(Optional.of(List.of(1L, 2L)), konfi.optionalListOfValues());
        Assertions.assertEquals(Set.of(1L, 2L), konfi.setOfValues());
        Assertions.assertEquals(Optional.of(Set.of(1L, 2L)), konfi.optionalSetOfValues());

        Assertions.assertThrows(MissingValueException.class, konfi::missingValue);
        Assertions.assertEquals(Optional.empty(), konfi.missingOptionalValue());
        Assertions.assertThrows(MissingValueException.class, konfi::missingListOfValues);
        Assertions.assertEquals(Optional.empty(), konfi.missingOptionalListOfValues());
        Assertions.assertThrows(MissingValueException.class, konfi::missingSetOfValues);
        Assertions.assertEquals(Optional.empty(), konfi.missingOptionalSetOfValues());
    }

    @Test
    void DoubleTest() {
        var konfi = Konfi
                .builder(DoubleProperties.class)
                .sources(Collections.singletonList(
                        PropertiesSources.newMapSource(Map.ofEntries(
                                Map.entry(VALUE, "1"),
                                Map.entry(INVALID_VALUE, "a"),
                                Map.entry(OPTIONAL_VALUE, "1"),
                                Map.entry(LIST_OF_VALUES, "1,2"),
                                Map.entry(OPTIONAL_LIST_OF_VALUES, "1,2"),
                                Map.entry(SET_OF_VALUES, "1,2"),
                                Map.entry(OPTIONAL_SET_OF_VALUES, "1,2")))))
                .build();

        Assertions.assertEquals(1D, konfi.value());
        Assertions.assertThrows(InvalidValueException.class, konfi::invalidValue);
        Assertions.assertEquals(Optional.of(1D), konfi.optionalValue());
        Assertions.assertEquals(List.of(1D, 2D), konfi.listOfValues());
        Assertions.assertEquals(Optional.of(List.of(1D, 2D)), konfi.optionalListOfValues());
        Assertions.assertEquals(Set.of(1D, 2D), konfi.setOfValues());
        Assertions.assertEquals(Optional.of(Set.of(1D, 2D)), konfi.optionalSetOfValues());

        Assertions.assertThrows(MissingValueException.class, konfi::missingValue);
        Assertions.assertEquals(Optional.empty(), konfi.missingOptionalValue());
        Assertions.assertThrows(MissingValueException.class, konfi::missingListOfValues);
        Assertions.assertEquals(Optional.empty(), konfi.missingOptionalListOfValues());
        Assertions.assertThrows(MissingValueException.class, konfi::missingSetOfValues);
        Assertions.assertEquals(Optional.empty(), konfi.missingOptionalSetOfValues());
    }

    @Test
    void FloatTest() {
        var konfi = Konfi
                .builder(FloatProperties.class)
                .sources(Collections.singletonList(
                        PropertiesSources.newMapSource(Map.ofEntries(
                                Map.entry(VALUE, "1"),
                                Map.entry(INVALID_VALUE, "a"),
                                Map.entry(OPTIONAL_VALUE, "1"),
                                Map.entry(LIST_OF_VALUES, "1,2"),
                                Map.entry(OPTIONAL_LIST_OF_VALUES, "1,2"),
                                Map.entry(SET_OF_VALUES, "1,2"),
                                Map.entry(OPTIONAL_SET_OF_VALUES, "1,2")))))
                .build();

        Assertions.assertEquals(1F, konfi.value());
        Assertions.assertThrows(InvalidValueException.class, konfi::invalidValue);
        Assertions.assertEquals(Optional.of(1F), konfi.optionalValue());
        Assertions.assertEquals(List.of(1F, 2F), konfi.listOfValues());
        Assertions.assertEquals(Optional.of(List.of(1F, 2F)), konfi.optionalListOfValues());
        Assertions.assertEquals(Set.of(1F, 2F), konfi.setOfValues());
        Assertions.assertEquals(Optional.of(Set.of(1F, 2F)), konfi.optionalSetOfValues());

        Assertions.assertThrows(MissingValueException.class, konfi::missingValue);
        Assertions.assertEquals(Optional.empty(), konfi.missingOptionalValue());
        Assertions.assertThrows(MissingValueException.class, konfi::missingListOfValues);
        Assertions.assertEquals(Optional.empty(), konfi.missingOptionalListOfValues());
        Assertions.assertThrows(MissingValueException.class, konfi::missingSetOfValues);
        Assertions.assertEquals(Optional.empty(), konfi.missingOptionalSetOfValues());
    }

    @Test
    void BooleanTest() {
        var konfi = Konfi
                .builder(BooleanProperties.class)
                .sources(Collections.singletonList(
                        PropertiesSources.newMapSource(Map.ofEntries(
                                Map.entry(VALUE, "true"),
                                Map.entry(OPTIONAL_VALUE, "true"),
                                Map.entry(LIST_OF_VALUES, "true,false"),
                                Map.entry(OPTIONAL_LIST_OF_VALUES, "true,false"),
                                Map.entry(SET_OF_VALUES, "true,false"),
                                Map.entry(OPTIONAL_SET_OF_VALUES, "true,false")))))
                .build();

        Assertions.assertEquals(true, konfi.value());
        Assertions.assertEquals(Optional.of(true), konfi.optionalValue());
        Assertions.assertEquals(List.of(true, false), konfi.listOfValues());
        Assertions.assertEquals(Optional.of(List.of(true, false)), konfi.optionalListOfValues());
        Assertions.assertEquals(Set.of(true, false), konfi.setOfValues());
        Assertions.assertEquals(Optional.of(Set.of(true, false)), konfi.optionalSetOfValues());

        Assertions.assertThrows(MissingValueException.class, konfi::missingValue);
        Assertions.assertEquals(Optional.empty(), konfi.missingOptionalValue());
        Assertions.assertThrows(MissingValueException.class, konfi::missingListOfValues);
        Assertions.assertEquals(Optional.empty(), konfi.missingOptionalListOfValues());
        Assertions.assertThrows(MissingValueException.class, konfi::missingSetOfValues);
        Assertions.assertEquals(Optional.empty(), konfi.missingOptionalSetOfValues());
    }

    @Test
    void MultipleRepositoriesTest() {
        var konfi = Konfi
                .builder(StringProperties.class)
                .sources(List.of(
                        PropertiesSources.newMapSource(Map.of(VALUE, "a")),
                        PropertiesSources.newMapSource(Map.of(VALUE, "b")),
                        PropertiesSources.newMapSource(Map.of(LIST_OF_VALUES, "a,b")),
                        PropertiesSources.newMapSource(Map.of(SET_OF_VALUES, "c,d"))))
                .build();
        System.out.println(konfi);
        Assertions.assertEquals("a", konfi.value());
        Assertions.assertEquals(List.of("a", "b"), konfi.listOfValues());
        Assertions.assertEquals(Set.of("c", "d"), konfi.setOfValues());
    }

    @Test
    void InvalidReturnTypeTest() {
        var konfi = Konfi
                .builder(InvalidReturnTypeProperties.class)
                .sources(Collections.singletonList(
                        PropertiesSources.newMapSource(Map.ofEntries(
                                Map.entry(VALUE, "true"),
                                Map.entry(OPTIONAL_VALUE, "true"),
                                Map.entry(LIST_OF_VALUES, "true,false"),
                                Map.entry(OPTIONAL_LIST_OF_VALUES, "true,false"),
                                Map.entry(SET_OF_VALUES, "true,false"),
                                Map.entry(OPTIONAL_SET_OF_VALUES, "true,false")))))
                .build();

        Assertions.assertThrows(InvalidReturnTypeException.class, konfi::pojo);
        Assertions.assertThrows(InvalidReturnTypeException.class, konfi::optionalPojo);
        Assertions.assertThrows(InvalidReturnTypeException.class, konfi::listOfPojo);
        Assertions.assertThrows(InvalidReturnTypeException.class, konfi::optionalListOfPojo);
        Assertions.assertThrows(InvalidReturnTypeException.class, konfi::setOfPojo);
        Assertions.assertThrows(InvalidReturnTypeException.class, konfi::optionalSetOfPojo);
        Assertions.assertThrows(InvalidReturnTypeException.class, konfi::primitiveIntegerValue);
        Assertions.assertThrows(InvalidReturnTypeException.class, konfi::primitiveCharValue);
        Assertions.assertThrows(InvalidReturnTypeException.class, konfi::primitiveDoubleValue);
        Assertions.assertThrows(InvalidReturnTypeException.class, konfi::primitiveLongValue);
        Assertions.assertThrows(InvalidReturnTypeException.class, konfi::primitiveFloatValue);
        Assertions.assertThrows(InvalidReturnTypeException.class, konfi::primitiveIntegerArrayValue);
        Assertions.assertThrows(InvalidReturnTypeException.class, konfi::primitiveCharArrayValue);
        Assertions.assertThrows(InvalidReturnTypeException.class, konfi::primitiveDoubleArrayValue);
        Assertions.assertThrows(InvalidReturnTypeException.class, konfi::primitiveLongArrayValue);
        Assertions.assertThrows(InvalidReturnTypeException.class, konfi::primitiveFloatArrayValue);
        Assertions.assertThrows(InvalidReturnTypeException.class, konfi::optionalOfOptionalString);
        Assertions.assertThrows(InvalidReturnTypeException.class, konfi::ListOfOptionalString);
        Assertions.assertThrows(InvalidReturnTypeException.class, konfi::ListOfListOfString);
    }

    @Test
    void AdvancedUsageTest() {
        var konfi = Konfi
                .builder(StringProperties.class)
                .sources(Collections.singletonList(
                        PropertiesSources.newMapSource(Map.ofEntries(
                                Map.entry(VALUE, "a  "),
                                Map.entry(OPTIONAL_VALUE, "a "),
                                Map.entry(LIST_OF_VALUES, "  a b  "),
                                Map.entry(OPTIONAL_LIST_OF_VALUES, "   a b "),
                                Map.entry(SET_OF_VALUES, " a b    "),
                                Map.entry(OPTIONAL_SET_OF_VALUES, " a b ")))))
                .collectionSeparator("\\s")
                .preProcessors(List.of(String::strip, String::toUpperCase))
                .build();

        Assertions.assertEquals("A", konfi.value());
        Assertions.assertEquals(Optional.of("A"), konfi.optionalValue());
        Assertions.assertEquals(List.of("A", "B"), konfi.listOfValues());
        Assertions.assertEquals(Optional.of(List.of("A", "B")), konfi.optionalListOfValues());
        Assertions.assertEquals(Set.of("A", "B"), konfi.setOfValues());
        Assertions.assertEquals(Optional.of(Set.of("A", "B")), konfi.optionalSetOfValues());
    }

    @Test
    void RefreshTest() {
        var mockSource = Mockito.mock(EnvironmentVariables.class);
        var firstAnswer = new Properties();
        firstAnswer.put(VALUE, "a");
        var secondAnswer = new Properties();
        secondAnswer.put(VALUE, "b");

        Mockito.when(mockSource.get()).thenReturn(firstAnswer, secondAnswer);
        var konfi = Konfi
                .builder(StringProperties.class)
                .sources(List.of(mockSource))
                .build();

        Assertions.assertEquals("a", konfi.value());
        Konfi.refresh(konfi);
        Assertions.assertEquals("b", konfi.value());
    }
}

package com.example;

import com.example.exception.BusinessException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("test junit 5 new api")
class StudentJupiterTest {

    @Test
    @DisplayName("test assertEqulas")
    void should_test_student_name_success() {
        //given when
        StudentCommand studentCommand = StudentCommand.builder().name("xiaoxiao").build();

        //then
        assertEquals(studentCommand.getName(), "xiaoxiao");
    }

    @Test
    @DisplayName("test assertAll")
    void should_test_every_test() {
        //given when
        int expected = 4;
        int actual = 2 + 2;
        Object nullValue = null;

        //then
        assertAll(
                "Assert All of these",
                () -> assertEquals(expected, actual, "INCONCEIVABLE!"),
                () -> assertFalse(nullValue == null),
                () -> assertNull(nullValue),
                () -> assertNotNull("A String", "INCONCEIVABLE!"),
                () -> assertTrue(nullValue != null));
    }

    @Test
    @DisplayName("It tests the length of student name should less than 10 chars")
    void should_throw_business_exception_when_student_name_length_more_than_10() {
        //given when
        BusinessException businessException = Assertions.assertThrows(BusinessException.class, this::buildStudentName);

        //then
        assertEquals(businessException.getMessage(), "The length of student name exceed 10 chars.");
    }

    private void buildStudentName() {
        StudentCommand.builder()
                .name(RandomStringUtils.randomAlphanumeric(11))
                .build();
    }

    @Test
    @DisplayName("It tests the length of student description should less than 20 chars")
    void should_throw_business_exception_when_student_description_length_more_than_20() {
        //given when
        BusinessException businessException = Assertions.assertThrows(BusinessException.class, this::buildStudentDescription);

        //then
        assertEquals(businessException.getMessage(), "The length of student name exceed 20 chars.");
    }

    private void buildStudentDescription() {
        StudentCommand.builder()
                .description(RandomStringUtils.randomAlphanumeric(21))
                .build();
    }
}
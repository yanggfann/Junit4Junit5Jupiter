package com.example;

import com.example.exception.BusinessException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;



public class StudentTest {

    @Test
    public void should_test_student_name_success() {
        //given when
        StudentCommand studentCommand = StudentCommand.builder().name("xiaoxiao").build();

        //then
        assertEquals(studentCommand.getName(), "xiaoxiao");
    }

    @Test
    public void should_test_every_test() {
        //given when
        int expected = 4;
        int actual = 2 + 2;
        Object nullValue = null;

        //then
        assertEquals(expected, actual);
        assertFalse(true);
        assertNull(nullValue);
        assertTrue(false);
    }

    @Test(expected = BusinessException.class)
    public void should_throw_business_exception_when_student_name_length_more_than_10() {
        //given when
        buildStudentName();
    }

    @Test(expected = BusinessException.class)
    public void should_throw_business_exception_when_student_description_length_more_than_20() {
        //given when
        StudentCommand studentCommand = StudentCommand.builder()
                .name(RandomStringUtils.randomAlphanumeric(11))
                .description(RandomStringUtils.randomAlphanumeric(19))
                .build();
    }

    @Test
    public void should_validate_message_when_student_name_length_more_than_10() {
        //given when
        try {
            buildStudentName();
        } catch (BusinessException e) {
            assertEquals(e.getMessage(), "The length of student name exceed 10 chars.");
        }


    }

    private void buildStudentName() {
        StudentCommand.builder()
                .name(RandomStringUtils.randomAlphanumeric(11))
                .build();
    }
}
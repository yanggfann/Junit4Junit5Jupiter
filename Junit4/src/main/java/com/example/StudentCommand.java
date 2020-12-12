package com.example;

import com.example.exception.BusinessException;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StudentCommand {
    public String name;
    public String description;

    public static class StudentCommandBuilder {
        public StudentCommand.StudentCommandBuilder name(String name) {
            if (name.length() > 10) {
                throw new BusinessException("The length of student name exceed 10 chars.");
            }
            this.name = name;
            return this;
        }

        public StudentCommand.StudentCommandBuilder description(String description) {
            if (description.length() > 20) {
                throw new BusinessException("The length of student name exceed 20 chars.");
            }
            this.description = description;
            return this;
        }
    }
}

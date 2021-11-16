package ucf.assignments;

/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Rebecca Pina Partidas
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class ToDoTest {
    @Test
    public void validDueDateTest()
    {
        // Make different test strings and mix them in asserts to get corresponding responses
        // Asserts corresponding responses are equal to responses returned from function

        // Inputs
        int rightDueDateLen = 10;
        int wrongDueDateLen = 0;

        String rightDueDate = "2021-11-15";
        String wrongDueDate = "2021-51-15";

        // Outputs
        String rightResponse = "correct";
        String wrongResponse1 = "Due date in add area has invalid input.\n Please make sure input is a valid date and try again.";
        String wrongResponse2 = "Due date in add area has invalid input.\n Please make sure input is in the format 'YYYY-MM-DD' and try again.";

        // Asserts
        assertEquals(rightResponse, ToDoController.validDueDate(rightDueDateLen, rightDueDate));
        assertEquals(wrongResponse2, ToDoController.validDueDate(wrongDueDateLen, rightDueDate));
        assertEquals(wrongResponse1, ToDoController.validDueDate(rightDueDateLen, wrongDueDate));
    }
}


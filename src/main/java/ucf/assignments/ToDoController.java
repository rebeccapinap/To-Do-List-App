package ucf.assignments;

/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Rebecca Pina Partidas
 */

// Libraries
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoController {
    // Data dictionary
    @FXML
    private TextField itemDescAdd;

    @FXML
    private TextField editDesc;

    @FXML
    private TextField dueDateAdd;

    @FXML
    private TextField editDueDate;

    @FXML
    private TextField itemDescSelect;

    @FXML
    private TextField fileLoad;

    @FXML
    private TextArea display;

    @FXML
    private TextArea error;

    public List<ListItem> list = new ArrayList<ListItem>();

    // When add Button is pressed in GUI
    @FXML
    public void addButton ()
    {
        int dueDateLen;

        // Makes error text box empty to reset
        error.setText("");

        // Gathers input from text fields in gui
        String itemDesc = itemDescAdd.getText();
        String dueDate = dueDateAdd.getText();

        dueDateLen = dueDate.length();

        // If length of the due date is not as long as it should be, gives an error
        if (dueDateLen != 10)
            error.setText("Due date in add area has invalid input.\n Please make sure input is in the format 'YYYY-MM-DD' and try again.");
         else
        {
            // If due date does not have a '-' at position 4 and 7, the input is invalid, so error
            // If due date first digit of month is not either 0 or 1, the input is invalid, so error
            // If due date first digit of month is not either 0, 1, 2, or, 3, the input is invalid, so error
            // Otherwise call add item method
            if (dueDate.indexOf("-") != 4 && dueDate.indexOf("-") != 7)
                error.setText("Due date in add area has invalid input.\n Please make sure input is in the format 'YYYY-MM-DD' and try again.");
            else if ((int)dueDate.charAt(5) < 48 || (int)dueDate.charAt(5) > 49)
                error.setText("Due date in add area has invalid input.\n Please make sure input is a valid date and try again.");
            else if ((int)dueDate.charAt(8) < 48 || (int)dueDate.charAt(8) > 51)
                error.setText("Due date in add area has invalid input.\n Please make sure input is a valid date and try again.");
            else
                addItem(itemDesc, dueDate);
        }
    }

    public void addItem (String itemDesc, String dueDate)
    {
        // Create an object of type ListItem and add it to the list of items
        // Set itemDesc of listItem object with description input in text field and same for due date and set complete status to incomplete
        // If due date is empty make an item with an empty due date
        if (dueDate == null)
            list.add(new ListItem(itemDesc, "",0));
        else
            list.add(new ListItem(itemDesc, dueDate, 0));
    }

    // When remove Button is pressed in GUI
    @FXML
    public void removeButton ()
    {
        // Makes error text box empty to reset
        error.setText("");

        // Gathers input from text field in gui
        String inputRem = itemDescSelect.getText();

        // Call removeItem method with input
        removeItem(inputRem);
    }

    public void removeItem (String inputRem)
    {
        // Search for object of type ListItem with description entered in text field of list item to select
        // Remove object of type ListItem by removing from list of items
        int i;
        int listSize = list.size();

        // Loops through to find object with description passed into the function
        // If it is found, removes item from list
        for(i = 0; i < listSize; i++)
        {
            String desc = list.get(i).getDesc();

            if (desc.equals(inputRem))
            {
                list.remove(i);
                break;
            }
        }
    }

    // When remove all items Button is pressed in GUI
    @FXML
    public void removeAllItems ()
    {
        // Makes error text box empty to reset
        error.setText("");

        // Removes all items in list
        list.removeAll(list);
    }

    // When edit item description Button is pressed in GUI
    @FXML
    public void editDescButton ()
    {
        // Makes error text box empty to reset
        error.setText("");

        // Gathers input from text fields in gui
        String inputEdit = itemDescSelect.getText();
        String inputDesc = editDesc.getText();

        // Calls edit description method with input
        editDesc(inputEdit, inputDesc);
    }

    public void editDesc (String inputEdit, String inputDesc)
    {
        // Search for object of type ListItem with description entered in text field of list item to select
        // Set item description to input in text field
        int i;
        int listSize = list.size();

        // Loops through to find object with description passed into the function
        // If it is found, set item description setter is called to set new description passed into function
        for(i = 0; i < listSize; i++)
        {
            String desc = list.get(i).getDesc();

            if (desc.equals(inputEdit))
            {
                list.get(i).setItemDesc(inputDesc);
                break;
            }
        }
    }

    // When edit item due date Button is pressed in GUI
    @FXML
    public void editDueDateButton ()
    {
        int dueDateLen;
        String validDueDate = "";
        // Makes error text box empty to reset
        error.setText("");

        // Gathers input from text fields in gui
        String inputEdit = itemDescSelect.getText();
        String inputDueDate = editDueDate.getText();

        dueDateLen = inputDueDate.length();

        validDueDate = validDueDate(dueDateLen, inputDueDate);

        if (validDueDate.equals("correct"))
            editDueDate(inputEdit, inputDueDate);
        else
            error.setText(validDueDate);
    }

    public static String validDueDate (int dueDateLen, String inputDueDate)
    {
        String valid;

        // If length of the due date is not as long as it should be, gives an error
        if (dueDateLen != 10)
            valid = "Due date in add area has invalid input.\n Please make sure input is in the format 'YYYY-MM-DD' and try again.";
        else
        {
            // If due date does not have a '-' at position 4 and 7, the input is invalid, so error
            // If due date first digit of month is not either 0 or 1, the input is invalid, so error
            // If due date first digit of month is not either 0, 1, 2, or, 3, the input is invalid, so error
            // Otherwise call edit due date method
            if (inputDueDate.indexOf("-") != 4 && inputDueDate.indexOf("-") != 7)
                valid = "Due date in add area has invalid input.\n Please make sure input is in the format 'YYYY-MM-DD' and try again.";
            else if ((int)inputDueDate.charAt(5) < 48 || (int)inputDueDate.charAt(5) > 49)
                valid = "Due date in add area has invalid input.\n Please make sure input is a valid date and try again.";
            else if ((int)inputDueDate.charAt(8) < 48 || (int)inputDueDate.charAt(8) > 51)
                valid = "Due date in add area has invalid input.\n Please make sure input is a valid date and try again.";
            else
                valid = "correct";
        }

        return valid;
    }

    public void editDueDate (String inputEdit, String inputDueDate)
    {
        // Search for object of type ListItem with description entered in text field of list item to select
        // Set item due date to input in text field
        int i;
        int listSize = list.size();

        // Loops through to find object with description passed into the function
        // If it is found, set item due date setter is called to set new due date passed into function
        for(i = 0; i < listSize; i++)
        {
            String desc = list.get(i).getDesc();

            if (desc.equals(inputEdit))
            {
                list.get(i).setItemDueDate(inputDueDate);
                break;
            }
        }
    }

    // When mark item complete Button is pressed in GUI
    @FXML
    public void markCompButton ()
    {
        // Makes error text box empty to reset
        error.setText("");

        // Gathers input from text field in gui
        String inputEdit = itemDescSelect.getText();

        // Call markItemComp method with input
        markItemComp(inputEdit);
    }

    public void markItemComp (String inputEdit)
    {
        // Search for object of type ListItem with description entered in text field of list item to select
        // Set item isComplete to complete
        int i;
        int listSize = list.size();

        // Loops through to find object with description passed into the function
        // If it is found, set is complete setter is called to set new complete status into function
        for(i = 0; i < listSize; i++)
        {
            String desc = list.get(i).getDesc();

            if (desc.equals(inputEdit))
            {
                list.get(i).setIsComplete(1);
                break;
            }
        }
    }

    // When mark item incomplete Button is pressed in GUI
    @FXML
    public void markIncompButton ()
    {
        // Makes error text box empty to reset
        error.setText("");

        // Gathers input from text field in gui
        String inputEdit = itemDescSelect.getText();

        // Call markItemIncomp method with input
        markItemIncomp(inputEdit);
    }

    public void markItemIncomp (String inputEdit)
    {
        // Search for object of type ListItem with description entered in text field of list item to select
        // Set item isComplete to incomplete
        int i;
        int listSize = list.size();

        // Loops through to find object with description passed into the function
        // If it is found, set is complete setter is called to set new incomplete status into function
        for(i = 0; i < listSize; i++)
        {
            String desc = list.get(i).getDesc();

            if (desc.equals(inputEdit))
            {
                list.get(i).setIsComplete(0);
                break;
            }
        }
    }

    // When display all items Button is pressed in GUI
    @FXML
    public void displayItems ()
    {
        // Do if display all items button is pressed:
        // Output every object of type ListItem's item description from each object of type Lists

        // Makes error text box empty to reset
        error.setText("");
        int i;
        int listSize = list.size();
        String output = "";

        // Checks if list is empty
        if (listSize == 0)
            output += "To Do list is empty.";

        // Loops through list of objects and adds item description, due date, and completion status to a string to be outputted
        for(i = 0; i < listSize; i++)
        {
            output += list.get(i).getDesc();
            output += ", ";
            output += list.get(i).getDueDate();
            output += ", ";
            if (list.get(i).getIsComplete() == 0)
                output += "Incomplete";
            if (list.get(i).getIsComplete() == 1)
                output += "Complete";
            output += "\n";
        }

        // Sets display text area in gui to the output
        display.setText(output);
    }

    // When display all incomplete items Button is pressed in GUI
    @FXML
    public void displayIncomplete ()
    {
        // Do if display incomplete items button is pressed:
        // Output every object of type ListItem's item description from each object of type Lists that has isComplete = incomplete

        // Makes error text box empty to reset
        error.setText("");
        int i;
        int listSize = list.size();
        String output = "";

        // Checks if list is empty
        if (listSize == 0)
            output += "To Do list is empty.";

        // Loops through list of objects and adds item description, due date, and completion status to a string to be outputted
        // Does this only if completion status is incomplete
        for(i = 0; i < listSize; i++)
        {
            if (list.get(i).getIsComplete() == 0)
            {
                output += list.get(i).getDesc();
                output += ", ";
                output += list.get(i).getDueDate();
                output += ", ";
                output += "Incomplete";
                output += "\n";
            }
        }

        display.setText(output);
    }

    // When display all complete items Button is pressed in GUI
    @FXML
    public void displayComplete ()
    {
        // Do if display complete items button is pressed:
        // Output every object of type ListItem's item description from each object of type Lists that has isComplete = complete
        // Makes error text box empty to reset
        error.setText("");
        int i;
        int listSize = list.size();
        String output = "";

        // Checks if list is empty
        if (listSize == 0)
            output += "To Do list is empty.";

        // Loops through list of objects and adds item description, due date, and completion status to a string to be outputted
        // Does this only if completion status is complete
        for(i = 0; i < listSize; i++)
        {
            if (list.get(i).getIsComplete() == 1)
            {
                output += list.get(i).getDesc();
                output += ", ";
                output += list.get(i).getDueDate();
                output += ", ";
                output += "Complete";
                output += "\n";
            }
        }

        display.setText(output);
    }

    // When save all items in list Button is pressed in GUI
    @FXML
    public void saveList () throws IOException {

        // Parse through every object of type ListItem in the list entered and
        // add list item description, due date, and completion status to a file
        // Save file to external storage

        // Makes error text box empty to reset
        error.setText("");
        FileWriter writer = new FileWriter("src/main/java/ucf/assignments/ToDoList.txt");
        PrintWriter print = new PrintWriter(writer);
        int i;
        int listSize = list.size();
        String output = "";

        // Print to file output list
        // gathered by looping through list objects and getting item description, due date, and completion status to a file
        for(i = 0; i < listSize; i++)
        {
            output += list.get(i).getDesc();
            output += ", ";
            output += list.get(i).getDueDate();
            output += ", ";
            if (list.get(i).getIsComplete() == 0)
                output += "Incomplete";
            if (list.get(i).getIsComplete() == 1)
                output += "Complete";
            output += "\n";
        }
        // Print to file
        print.printf(output);

        print.close();
    }

    // When load items from an external list Button is pressed in GUI
    @FXML
    public void loadListButton () throws FileNotFoundException {
        // Makes error text box empty to reset
        error.setText("");

        // Gathers input from text field in gui
        String filepath = fileLoad.getText();

        // Call loadList method with input
        loadList(filepath);
    }

    public void loadList (String filepath) throws FileNotFoundException {
        //Parse through file and add each line
        File input = new File(filepath);
        Scanner s = new Scanner(input);
        String temp;
        String[] indivInput;
        int complete = 0;

        // Scans in every line in File and separates values
        // Puts values into and object of type ListItem and adds it to the list
        while (s.hasNextLine())
        {
            temp = s.nextLine();
            indivInput = temp.split(", ", 3);
            if (indivInput[2].equals("Incomplete") || indivInput[2].equals("incomplete"))
                complete = 0;
            if (indivInput[2].equals("Complete") || indivInput[2].equals("complete"))
                complete = 1;
            list.add(new ListItem(indivInput[0], indivInput[1], complete));
        }
        s.close();
    }
}

class ListItem
{
    private String itemDesc;
    private String dueDate;
    private int isComplete;

    public ListItem (String itemDesc, String dueDate, int isComplete)
    {
        this.itemDesc = itemDesc;
        this.isComplete = isComplete;
        this.dueDate = dueDate;
    }

    public void setItemDesc(String itemDesc)
    {
        this.itemDesc = itemDesc;
    }

    public void setItemDueDate(String dueDate)
    {
        this.dueDate = dueDate;
    }

    public void setIsComplete(int isComplete)
    {
        this.isComplete = isComplete;
    }

    public String getDesc()
    {
        return itemDesc;
    }
    public String getDueDate()
    {
        return dueDate;
    }
    public int getIsComplete()
    {
        return isComplete;
    }

}

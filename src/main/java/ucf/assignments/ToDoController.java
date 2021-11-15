package ucf.assignments;

/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Rebecca Pina Partidas
 */

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoController {
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
    private TextField display;

    private List<ListItem> list = new ArrayList<ListItem>();

    @FXML
    private void addButton ()
    {
        String itemDesc = itemDescAdd.getText();
        String dueDate = dueDateAdd.getText();
        addItem(itemDesc, dueDate);
    }

    private void addItem (String itemDesc, String dueDate)
    {
        //Do if name was entered into create new item text box and add button was pressed:
        //Search for object of type Lists with name entered in text field of list to select
        //Create an object of type listItem and add it to the list of list items
        //Set itemDesc of listItem object with description input in text field and set complete status to incomplete
        if (dueDate.equals(""))
            list.add(new ListItem(itemDesc, 0));
        else
            list.add(new ListItem(itemDesc, dueDate, 0));
    }

    @FXML
    private void removeButton ()
    {
        String inputRem = itemDescSelect.getText();
        removeItem(inputRem);
    }

    private void removeItem (String inputRem)
    {
        //Do if name was entered into listItem text box and remove button was pressed:
        //Search for object of type ListItem with description entered in text field of list item to select
        //Remove object of type ListItem by removing from list of list items
        int i;
        int listSize = list.size();

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

    @FXML
    private void removeAllItems ()
    {
        list.removeAll(list);
    }

    @FXML
    private void editDescButton ()
    {
        String inputEdit = itemDescSelect.getText();
        String inputDesc = editDesc.getText();
        editDesc(inputEdit, inputDesc);
    }

    private void editDesc (String inputEdit, String inputDesc)
    {
        //Do if description was entered into edit item description text box and button was pressed:
        //Search for object of type Lists with name entered in text field of list to select
        //Search for object of type ListItem with description entered in text field of list item to select
        //Set item description to input in text field
        int i;
        int listSize = list.size();

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

    @FXML
    private void editDueDateButton ()
    {
        String inputEdit = itemDescSelect.getText();
        String inputDueDate = editDueDate.getText();
        editDueDate(inputEdit, inputDueDate);
    }

    private void editDueDate (String inputEdit, String inputDueDate)
    {
        //Do if due date was entered into edit item due date text box and button was pressed:
        //Do if due date text was in the format 'YYYY-MM-DD':
        //Search for object of type Lists with name entered in text field of list to select
        //Search for object of type ListItem with description entered in text field of list item to select
        //Set item due date to input in text field
        int i;
        int listSize = list.size();

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

    @FXML
    private void markCompButton ()
    {
        String inputEdit = itemDescSelect.getText();
        markItemComp(inputEdit);
    }

    private void markItemComp (String inputEdit)
    {
        //Do if complete button was pressed:
        //Search for object of type Lists with name entered in text field of list to select
        //Search for object of type ListItem with description entered in text field of list item to select
        //Set item isComplete to complete
        //Do if incomplete buttons was pressed:
        //Search for object of type Lists with name entered in text field of list to select
        //Search for object of type ListItem with description entered in text field of list item to select
        //Set item isComplete to incomplete
        int i;
        int listSize = list.size();

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

    @FXML
    private void markIncompButton ()
    {
        String inputEdit = itemDescSelect.getText();
        markItemIncomp(inputEdit);
    }

    private void markItemIncomp (String inputEdit)
    {
        //Do if complete button was pressed:
        //Search for object of type Lists with name entered in text field of list to select
        //Search for object of type ListItem with description entered in text field of list item to select
        //Set item isComplete to complete
        //Do if incomplete buttons was pressed:
        //Search for object of type Lists with name entered in text field of list to select
        //Search for object of type ListItem with description entered in text field of list item to select
        //Set item isComplete to incomplete
        int i;
        int listSize = list.size();

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

    @FXML
    private void displayItems ()
    {
        //Do if display all items button is pressed:
        //Output every object of type ListItem's item description from each object of type Lists
        int i;
        int listSize = list.size();
        String output = "";

        if (listSize == 0)
            output += "To Do list is empty.";

        for(i = 0; i < listSize; i++)
        {
            output += list.get(i).getDesc();
            output += " ";
            output += list.get(i).getDueDate();
            output += " ";
            output += list.get(i).getIsComplete();
            output += "\n";
        }

        display.setText(output);
    }

    @FXML
    private void displayIncomplete ()
    {
        //Do if display incomplete items button is pressed:
        //Output every object of type ListItem's item description from each object of type Lists that has isComplete = incomplete
        int i;
        int listSize = list.size();
        String output = "";

        if (listSize == 0)
            output += "To Do list is empty.";

        for(i = 0; i < listSize; i++)
        {
            if (list.get(i).getIsComplete() == 0)
            {
                output += list.get(i).getDesc();
                output += " ";
                output += list.get(i).getDueDate();
                output += " ";
                output += list.get(i).getIsComplete();
                output += "\n";
            }
        }

        display.setText(output);
    }

    @FXML
    private void displayComplete ()
    {
        //Do if display complete items button is pressed:
        //Output every object of type ListItem's item description from each object of type Lists that has isComplete = complete
        int i;
        int listSize = list.size();
        String output = "";

        if (listSize == 0)
            output += "To Do list is empty.";

        for(i = 0; i < listSize; i++)
        {
            if (list.get(i).getIsComplete() == 1)
            {
                output += list.get(i).getDesc();
                output += " ";
                output += list.get(i).getDueDate();
                output += " ";
                output += list.get(i).getIsComplete();
                output += "\n";
            }
        }

        display.setText(output);
    }

    @FXML
    private void saveList () throws IOException {
        //Do if name of list was entered into save list text box and button was pressed:
        //Search for object of type Lists with the name entered from list of Lists
        //If found, Parse through every object of type ListItem in the list entered and add list item description to a file
        //Save file to external storage
        FileWriter writer = new FileWriter("src/main/java/ucf.assignments/ToDoList.txt");
        PrintWriter print = new PrintWriter(writer);
        int i;
        int listSize = list.size();

        // Print to file
        for(i = 0; i < listSize; i++)
        {
            // Print to file
            print.printf("%s" + "%n", list.get(i));
        }

        print.close();
    }

    @FXML
    private void loadListButton ()
    {
        String filepath = fileLoad.getText();
        markItemIncomp(filepath);
    }

    private void loadList (String filepath) throws FileNotFoundException {
        //Do if file path was entered into load list text box and button was pressed:
        //addList(filepath)
        //Parse through file and add each line with addItem()
        File input = new File(filepath);
        Scanner s = new Scanner(input);
        ArrayList<String> list = new ArrayList<String>();

        // Scans in every line in File
        while (s.hasNextLine())
        {
            list.add(s.nextLine());
        }
        s.close();
    }
}

class ListItem
{
    private String itemDesc;
    private String dueDate;
    private int isComplete;

    public ListItem (String itemDesc, int isComplete)
    {
        this.itemDesc = itemDesc;
        this.isComplete = isComplete;
    }

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

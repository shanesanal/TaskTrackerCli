package com.ecommerce;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskTrackerCLI {

    private static final String TASK_FILES = "tasks.json";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: task-cli <command> [arguments]");
            return;
        }

        String command = args[0];

        try {
            switch (command) {
                case "add":
                    if(args.length < 2) {
                        System.out.println("Usage: task-cli add \"<task-description>\">");
                        return;
                    }
                    addTask(args[1]);
                    break;
                case "update":
                    if(args.length < 3) {
                        System.out.println("Usage: task-cli update <task-id> \"<new-description>\">");
                        return;
                    }

                    updateTask(Integer.parseInt(args[1]),args[2]);
                    break;
                case "delete":
                    if(args.length < 2) {
                        System.out.println("Usage: task-cli delete <task-id>");
                        return;
                    }
                    deleteTask(Integer.parseInt(args[1]));
                    break;

                case "mark-in-progress":
                    if(args.length < 2) {
                        System.out.println("Usage: task-cli mark-in-progress <task-id>");
                        return;
                    }
                    markTaskStatus(Integer.parseInt(args[1]),"in-progress");
                    break;
                case "mark-done":
                    if(args.length < 2) {
                        System.out.println("Usage: task-cli mark-done <task-id>");
                        return;
                    }

                    markTaskStatus(Integer.parseInt(args[1]),"done");
                    break;

                case "list":
                    if(args.length < 1) {
                        listAllTasks();
                    }
                    else {
                        listTasksByStatus(args[1]);
                    }
                    break;
                default:
                    System.out.println("Invalid command");
            }
        } catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

    }

    private static JSONArray loadTasks() throws IOException{
        File file = new File(TASK_FILES);
        if(!file.exists())
        {
            return new JSONArray();
        }
        String content = new String(Files.readAllBytes(Paths.get(TASK_FILES)));
        return new JSONArray(content);

    }
    private static void saveTasks(JSONArray tasks) throws IOException{
        try(FileWriter file = new FileWriter(TASK_FILES)){
            file.write(tasks.toString());
        }
    }

    private static void addTask(String description) throws IOException{
        JSONArray tasks = loadTasks();
        JSONObject task = new JSONObject();
        task.put("id", tasks.length() +1 );
        task.put("description", description);
        task.put("status", "To-do");
        task.put("createdAt", getCurrentDateTime());
        task.put("updatedAt", getCurrentDateTime());
        tasks.put(task);
        saveTasks(tasks);
        System.out.println("Added task successfully (ID:  " + task.get("id") + ")");
    }

    private static void updateTask(int id, String newDescription) throws IOException{
        JSONArray tasks = loadTasks();
        for(int  i = 0; i < tasks.length(); i++){
            JSONObject task = tasks.getJSONObject(i);
            if(task.getInt("id") == id){
                task.put("description", newDescription);
                task.put("updatedAt", getCurrentDateTime());
                saveTasks(tasks);
                System.out.println("Updated task successfully (ID:  " + task.get("id") + ")");
                return;
            }
        }
        System.out.println("Task not found (ID:  " + id + ")");
    }

    private static void deleteTask(int id) throws IOException{
        JSONArray tasks = loadTasks();
        for(int  i = 0; i < tasks.length(); i++){
            if(tasks.getJSONObject(i).getInt("id") == id){
                tasks.remove(i);
                saveTasks(tasks);
                System.out.println("Deleted task successfully (ID:  " + id + ")");
                return;
            }
        }
        System.out.println("Task not found (ID:  " + id + ")");
    }

    private static void markTaskStatus(int id, String status) throws IOException{
        JSONArray tasks = loadTasks();
        for(int  i = 0; i < tasks.length(); i++){
            JSONObject task = tasks.getJSONObject(i);
            if(task.getInt("id") == id){
                task.put("status", status);
                task.put("updatedAt", getCurrentDateTime());
                saveTasks(tasks);
                System.out.println("Marked task successfully (ID:  " + id + ")");
            }
        }
        System.out.println("Task not found (ID:  " + id + ")");
    }

    private static void listAllTasks() throws IOException, JSONException {
        JSONArray tasks = loadTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        for (int i = 0; i < tasks.length(); i++) {
            printTask(tasks.getJSONObject(i));
        }
    }

    private static void listTasksByStatus(String status) throws IOException, JSONException {
        JSONArray tasks = loadTasks();
        boolean found =false;
        for(int  i = 0; i < tasks.length(); i++){
            JSONObject task = tasks.getJSONObject(i);
            if(task.getString("status").equals(status)){
                printTask(task);
                found = true;
            }
        }

        if(!found){
            System.out.println("Task not found (ID:  " + status + ")");
        }

    }

    private static void printTask(JSONObject task) throws JSONException {
        System.out.println("ID:  " + task.getInt("id"));
        System.out.println("Description:  " + task.getString("description"));
        System.out.println("Status:  " + task.getString("status"));
        System.out.println("CreatedAt:  " + task.getString("createdAt"));
        System.out.println("UpdatedAt:  " + task.getString("updatedAt"));

        System.out.println("----------------------------------------");
    }

    private static String getCurrentDateTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }


}

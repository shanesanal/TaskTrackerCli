# TaskTrackerCli

A simple command-line interface (CLI) application to track and manage tasks. This project allows you to add, update, delete, and list tasks, as well as mark tasks as todo, in-progress, or done. Tasks are stored in a JSON file for persistence.

Features
Add a new task: Add a task with a description.

Update a task: Modify the description of an existing task.

Delete a task: Remove a task by its ID.

Mark task status: Mark a task as todo, in-progress, or done.

List tasks: View all tasks or filter them by status (todo, in-progress, done).

JSON storage: Tasks are stored in a tasks.json file for persistence.

Prerequisites
Java Development Kit (JDK): Ensure you have JDK 8 or later installed.

Maven: Required for building and managing dependencies.

Git: Optional, for version control.

Installation
Clone the Repository:

bash
Copy
git clone https://github.com/your-username/task-tracker-cli.git
cd task-tracker-cli
Build the Project:

bash
Copy
mvn clean package
Run the Application:

bash
Copy
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar <command> [arguments]
Usage
Commands
Command	Description	Example
add <description>	Add a new task	task-cli add "Buy groceries"
update <id> <desc>	Update the description of a task	task-cli update 1 "Buy groceries and cook"
delete <id>	Delete a task by its ID	task-cli delete 1
mark-in-progress <id>	Mark a task as in-progress	task-cli mark-in-progress 1
mark-done <id>	Mark a task as done	task-cli mark-done 1
list	List all tasks	task-cli list
list <status>	List tasks by status (todo, in-progress, done)	task-cli list in-progress
Example Workflow
Add a Task:

bash
Copy
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar add "Buy groceries"
Output:

Copy
Task added successfully (ID: 1)
Mark a Task as In Progress:

bash
Copy
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar mark-in-progress 1
Output:

Copy
Task marked as in-progress (ID: 1)
List All Tasks:

bash
Copy
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar list
Output:

Copy
ID: 1
Description: Buy groceries
Status: in-progress
Created At: 2023-10-15 12:34:56
Updated At: 2023-10-15 12:35:10
-----------------------------
Delete a Task:

bash
Copy
java -jar target/task-tracker-cli-1.0-SNAPSHOT.jar delete 1
Output:

Copy
Task deleted successfully (ID: 1)
Project Structure
Copy
task-tracker-cli/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── TaskTrackerCLI.java  # Main application code
│   └── test/                        # Unit tests (optional)
├── target/                          # Compiled classes and JAR file
├── tasks.json                       # JSON file for storing tasks
├── pom.xml                          # Maven configuration file
└── README.md                        # Project documentation
Dependencies
org.json: For JSON parsing and manipulation.

xml
Copy
<dependency>
    <groupId>org.json</groupId>
    <artifactId>json</artifactId>
    <version>20231013</version>
</dependency>
Run HTML
Contributing
Contributions are welcome! If you'd like to contribute, please follow these steps:

Fork the repository.

Create a new branch (git checkout -b feature/your-feature).

Commit your changes (git commit -m "Add your feature").

Push to the branch (git push origin feature/your-feature).

Open a pull request.

License
This project is licensed under the MIT License. See the LICENSE file for details.

Author
Shane Mathew Sanal
shanemathewsanal@gmail.com

Acknowledgments
Inspired by simple task management tools.

Built with Java and Maven.

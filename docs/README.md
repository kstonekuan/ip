# User Guide

## Features 

### Add tasks to the list

You can add different tasks to the list for tracking.
The types of tasks are:
- Todo
- Deadline
- Event

### Show tasks in the list

You can ask for the tasks in the list to be displayed.

### Mark a task as done

You mark a task as done when it is completed.

### Delete tasks from the list

You can delete the tasks from the list if they are not needed anymore.

### Find tasks with keyword search

You can search for tasks based on their descriptions.

### Save and load the task list

The list of tasks will be automatically loaded on start up and saved after every action that affects the list.

## Usage

### `todo` - Adds a todo task to the list

Adds a simple todo task to the list with a description.

Example of usage: 

```
todo borrow book
```

Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
  [T][✘] borrow book
Now you have 1 tasks in the list.
____________________________________________________________
```

### `deadline` - Adds a deadline task to the list

Adds a deadline task to the list with a task description and a by description.

Example of usage: 

```
deadline return book /by Sunday
```

Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
  [D][✘] return book (by: Sunday)
Now you have 2 tasks in the list.
____________________________________________________________
```

### `event` - Adds an event task to the list

Adds an event task to the list with a task description and an at description.

Example of usage: 

```
event project meeting /at Mon 2-4pm
```

Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
  [E][✘] project meeting (at: Mon 2-4pm)
Now you have 3 tasks in the list.
____________________________________________________________
```

### `list` - Displays tasks in the list

Displays the tasks in the list starting from index 1.
Each task's type, description and whether it is done is also shown.

Example of usage: 

```
list
```

Expected outcome:

```
____________________________________________________________
Here are the tasks in your list:
1.[T][✘] borrow book
2.[D][✘] return book (by: Sunday)
3.[E][✘] project meeting (at: Mon 2-4pm)
____________________________________________________________
```

### `done` - Marks a task as done

Marks a task as being done based on the index shown in list feature.
The task's status icon will change accordingly.

Example of usage: 

```
done 1
```

Expected outcome:

```
____________________________________________________________
Nice! I've marked this task as done:
  [T][✓] borrow book
____________________________________________________________
```

### `delete` - Removes the task from the list

Removes the task from the list based on the index shown in list feature.

Example of usage: 

```
delete 3
```

Expected outcome:

```
____________________________________________________________
Noted. I've removed this task:
  [E][✘] project meeting (at: Mon 2-4pm)
Now you have 2 tasks in the list.
____________________________________________________________
```

### `find` - Finds tasks with keyword search

Finds all tasks with descriptions that match the keyword search.
The tasks will be displayed in a numbered list similar to using `list`.

Example of usage: 

```
find book
```

Expected outcome:

```
____________________________________________________________
Here are the matching tasks in your list:
1.[T][✓] borrow book
2.[D][✓] return book (by: Sunday)
____________________________________________________________
```
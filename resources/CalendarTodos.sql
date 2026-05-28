Create Database CalendarTodos;

Create Table allTodos (
	todoId integer Primary Key,
	todoDescription text,
	todoDate date,
    todoComplete boolean
);


package com.in28minutes.springboot.ToDoWebApp.todo.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;
import com.in28minutes.springboot.ToDoWebApp.todo.Todo;

import jakarta.validation.Valid;

@Service
public class ToDoService {
	private int countOfTodos = 3;
	private static List<Todo> todos = new ArrayList<Todo>();
	static {
		todos.add(new Todo(1, "in28minutes", "Learn AWS", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(2, "Felipe", "Learn Angular", LocalDate.now(), false));
		todos.add(new Todo(3, "Felipe", "Learn DevOps", LocalDate.now().plusYears(2), false));
	}

	public List<Todo> findByUsername(String username) {
		Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);	
		return todos.stream().filter(predicate).toList();
	}
	
	public void addTodo(String name, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(++countOfTodos, name, description, targetDate, done);
		todos.add(todo);
	}
	
	public void deleteById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}
	
	public void updateTodo(@Valid Todo todo) {
		deleteById(todo.getId());
		todos.add(todo);
	}
	
	public Todo findById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}
}

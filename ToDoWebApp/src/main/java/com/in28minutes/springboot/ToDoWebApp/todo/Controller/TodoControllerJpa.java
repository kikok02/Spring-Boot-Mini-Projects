package com.in28minutes.springboot.ToDoWebApp.todo.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.in28minutes.springboot.ToDoWebApp.todo.Todo;
import com.in28minutes.springboot.ToDoWebApp.todo.Repository.TodoRepository;
import com.in28minutes.springboot.ToDoWebApp.todo.Service.ToDoService;

import jakarta.validation.Valid;


@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
	
	private TodoRepository todoRepository;
	
	
	public TodoControllerJpa(TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}



	@RequestMapping("/list-todos")
	public String listAllTodos(ModelMap modelMap) {
		String username = getLoggedinUsername(modelMap);
		List<Todo> todos = todoRepository.findByUsername(username);
		modelMap.addAttribute("todos", todos);
		return "list-todoPage";
	}



	private String getLoggedinUsername(ModelMap modelMap) {
		return (String)modelMap.get("name");
	}
	
	@RequestMapping(value="add-todo", method=RequestMethod.GET)
	public String showNewTodoPage(ModelMap modelMap) {
		String username = getLoggedinUsername(modelMap);
		Todo todo = new Todo(0, username, "Default Description", LocalDate.now().plusYears(1), false);
		modelMap.put("todo", todo);
		return "add-todoPage";
	}
	
	@RequestMapping(value="add-todo", method=RequestMethod.POST)
	public String addNewTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result) {
		
		if(result.hasErrors()){
			return "add-todoPage"; 
		}
		
		String username = getLoggedinUsername(modelMap);
		todo.setUsername(username);
		todoRepository.save(todo);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value="delete-todo")
	public String deleteTodo(@RequestParam int id) {
		todoRepository.deleteById(id);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.GET)
	public String showUpdateTodo(@RequestParam int id, ModelMap modelMap) {
		Todo todo = todoRepository.findById(id).get();
		modelMap.addAttribute("todo", todo);
		return "add-todoPage";
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.POST)
	public String updateTodo(ModelMap modelMap, Todo todo, BindingResult result) {
		
		if(result.hasErrors()){
			return "add-todoPage"; 
		}
		
		String username = getLoggedinUsername(modelMap);
		todo.setUsername(username);
		todoRepository.save(todo);
		return "redirect:list-todos";
	}
}

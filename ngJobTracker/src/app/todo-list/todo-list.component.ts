import { Component, OnInit, Input } from '@angular/core';
import { Todo } from '../models/todo';
import { TodoService } from '../todo.service';
import { IncompletePipe } from '../incomplete.pipe';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Job } from '../models/job';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent implements OnInit {
  @Input() jobId: number;
  title = 'ngToDO';
  selected = null;
  newTodo = new Todo();
  editTodo = null;
  todos: Todo[] = [];
  showComplete = false;

  getNumTodos() {
    return this.incomplete.transform(this.todos, false).length;
  }

  displayTodo(todo) {
    this.selected = todo;
  }

  displayTable() {
   this.selected = null;
  }

  setEditTodo() {
    this.editTodo = Object.assign({}, this.selected);
  }

  incompleteTodoWarning() {
    const incompleteTodos = this.getNumTodos();

    if (incompleteTodos >= 10) {
      return 'danger';
    } else if (incompleteTodos >= 5 && incompleteTodos < 10) {
      return 'warning';
    }
    return 'okay';
  }

  reload(jid?: number) {
    if (!jid) {
      this.todoService.indexUser().subscribe(
        (data) => this.todos = data,
        (err) => console.log(err)
      );
    } else {
      this.todoService.indexJob(jid).subscribe(
        (data) => this.todos = data,
        (err) => console.log(err)
      );
    }

  }

  addTodo(todo: Todo, form: NgForm) {
    const newerTodo: Todo = new Todo();
    newerTodo.completed = false;
    newerTodo.description = '';
    newerTodo.task = form.value.task;

    if (this.jobId) {
      const job = new Job();
      job.id = this.jobId;
      newerTodo.job = job;
    }

    this.todoService.create(newerTodo).subscribe(
      (data) => {
        form.reset();
        this.reload(this.jobId);
      },
      (err) => console.log(err)
    );
  }

  updateTodo(todo) {
    this.todoService.update(todo).subscribe(
      (data) => {
        this.reload(this.jobId);
        this.editTodo = null;
        this.selected = data;
      }
    );
  }

  updateTodoCompleted(todo) {
    this.todoService.update(todo).subscribe(
      (data) => {
        this.reload(this.jobId);
      }
    );
  }

  deleteTodo(id) {
    this.todoService.destroy(id).subscribe(
      (data) => this.reload(this.jobId),
      (err) => console.log(err)
    );
  }

  constructor(private todoService: TodoService,
              private incomplete: IncompletePipe,
              private currentRoute: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.reload(this.jobId);
  }

}

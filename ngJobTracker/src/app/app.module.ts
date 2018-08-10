import { AuthGuardService } from './auth/auth-guard.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HTTP_INTERCEPTORS  } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BoardListComponent } from './board-list/board-list.component';
import { NavigationComponent } from './navigation/navigation.component';
import { JobListComponent } from './job-list/job-list.component';
import { TodoListComponent } from './todo-list/todo-list.component';
import { JobService } from './job.service';
import { BoardService } from './board.service';
import { IncompletePipe } from './incomplete.pipe';
import { HomeComponent } from './home/home.component';
import { NoteListComponent } from './note-list/note-list.component';
import { ContactListComponent } from './contact-list/contact-list.component';
import { ContactService } from './contact.service';
import { JobStatusService } from './job-status.service';
import { NoteService } from './note.service';
import { TodoService } from './todo.service';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatChipsModule} from '@angular/material/chips';
import {MatTabsModule} from '@angular/material/tabs';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatSelectModule} from '@angular/material/select';
import { CompanyDetailComponent } from './company-detail/company-detail.component';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { AuthInterceptor } from './interceptors/auth-interceptor';
import { AuthService } from './auth.service';
import { GoogleKnowledgeGraphService } from './google-knowledge-graph.service';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { StatusFilterPipe } from './status-filter.pipe';
import { FileListComponent } from './file-list/file-list.component';
import { FileService } from './file.service';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';


@NgModule({
  declarations: [
    AppComponent,
    BoardListComponent,
    NavigationComponent,
    JobListComponent,
    TodoListComponent,
    IncompletePipe,
    HomeComponent,
    NoteListComponent,
    ContactListComponent,
    CompanyDetailComponent,
    AboutComponent,
    ContactComponent,
    RegisterComponent,
    LoginComponent,
    LogoutComponent,
    StatusFilterPipe,
    FileListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule.forRoot(),
    BrowserAnimationsModule,
    MatChipsModule,
    MatTabsModule,
    MatIconModule,
    MatInputModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatAutocompleteModule,
    MatProgressSpinnerModule
  ],
  providers: [
    AuthService,
    BoardService,
    JobService,
    ContactService,
    JobStatusService,
    NoteService,
    TodoService,
    IncompletePipe,
    StatusFilterPipe,
    ContactService,
    AuthGuardService,
    FileService,
    GoogleKnowledgeGraphService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true, deps: [AuthService] },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

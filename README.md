## Job Tracker

Fun build of a job tracking appliction. App allows user to keep track of each job search they undertake using "boards" to save and categorize all the jobs they have applied for. Users can save details about job salary.., company details, general notes, contacts, and tasks to complete for specific jobs or general todos.

Company search utilizes the Google Knowledge Graph API to give suggested companies.

Application uses Spring security to secure Spring REST routes using a stateless JWT strategy.

Angular uses local storage to save JWT to include it using a HttpInterceptor with each subsequent request. Angular routes are secured using Route guards. 

### Tech Overview
- MySQL
- JPA/Hibernate
- Spring REST
- Spring Data JPA
- Spring Security
  - JWT Auth  
- Angular 6
  - Local Storage of JWT
  - Interceptor to add Auth header
  - Route guards for securing routes
 
- Google Knowledge Graph API
# Additional Notes

## TODO
- Add *test user* to Google Cloud project so they can test it
  - **Note:** Test users will need a Google Account to be *white-listed*

## Misc. Info.
- [URL for Repo.](https://github.com/York-Solutions-B2E/TSG--Project_Two--MFiles)

## Member Benefits Dashboard (MFiles)

This is for the initial commit, as such this document is pending updates...

### Steps Taken

#### Google OAuth
1. On [Google Cloud Console](https://console.cloud.google.com/), create new project **or** use an existing project
2. Navigate to APIs and Services -> Credentials -> New Credentials -> OAuth Client ID
3. Configure the OAuth Consent Screen (use *External for most testing*)
4. In the *pom.xml* file, add the following dependencies:

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-oauth2-client</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

5. In the *application.properties* file:

```properties
spring.security.oauth2.client.registration.google.client-id=<YOUR_GOOGLE_CLIENT_ID>
spring.security.oauth2.client.registration.google.client-secret=<YOUR_GOOGLE_CLIENT_SECRET>
spring.security.oauth2.client.registration.google.scope=openid,profile,email
spring.security.oauth2.client.registration.google.redirect-uri={baseUrl}/login/oauth2/code/{registrationId}
```

6. In the *SecurityConfig.java* file:

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable()) // Disable CSRF for API, or handle it
      .authorizeHttpRequests(authorize -> authorize
        .antMatchers("/", "/error", "/webjars/**").permitAll() // Allow public access to certain paths
        .anyRequest().authenticated()
    )
    .oauth2Login(oauth2Login -> oauth2Login
      .defaultSuccessUrl("/success", true) // Redirect after successful login
      .failureUrl("/error") // Redirect on login failure
    );
    return http.build();
  }
}
```

7. Create applicable user controller(s) that handle successful login redirects **or** user information retrieval
8. For React, initiate the Google OAuth flow by redirecting to *Authorized Redirect URL*

```jsx
import React from 'react';

function LoginPage() {
  const handleGoogleLogin = () => {
    // Adjust Spring port if needed
    window.location.href = 'http://localhost:8080/oauth2/authorization/google';
  };

  return (
    <div>
    <h2>Login</h2>
    <button onClick={handleGoogleLogin}>Login with Google</button>
    </div>
  );
}

export default LoginPage;
```

**Additional Considerations:**
- Spring Boot should redirect back to a specified URL once authentication was successfully handled
- Pass the JWT to React so the app can continue along other pages while retaining authentication
- Ensure Spring Boot app has appropriate CORS configuration to allow requests from React front-end origin


**Note:**
- Delete a Google Cloud Project:
  - Projects page -> IAM and Admin (type in "Projects")
  - Select the Organization resource from the drop-down menu (to view all projects)
  - Select the Project for deletion
  - Using the hamburger menu, click Delete Project
- On the initial creation, at Step 2, Google Cloud will require the OAuth Consent Screen to be created first then you'll need to re-do Step 2 and, at this point, skip Step 3
  - Instruction order is from Google AI so I am keeping it inline with their order
- Google Cloud Project details:
  - App. Type: Web application
  - Name: Member Benefits API
  - Authorized JavaScript origins: `http://localhost:3000`
  - Authorized Redirect URLs: `http://localhost:8080/login/oauth2/code/google`
  - For Google OAuth Creds., see Sticky Note

#### GitHub
1. Create local directory (with Backend, Frontend and Database sub-directories)
2. Add and Update *README.md*
3. Initialize Git: `git init`
4. Stage all applicable files/directories: `git add -A`
5. Add message and commit: `git commit -m "Initial commit (project creation)..."`
6. Create public repo. on York Solutions: `gh repo create York-Solutions-B2E/TSG--Project_Two--MFiles --public`
7. Specify provided URL for *origin*: `git remote add origin https://github.com/York-Solutions-B2E/TSG--Project_Two--MFiles.git`
8. Set *main* branch: `git branch -M main`
9. Submit all applicable files/directories to repo.: `git push -u origin main`

#### Java (Spring Boot)
1. Obtain project *pom.xml* via [Spring Initializr](https://start.spring.io/)
2. Add generated project to *Backend* directory
3. Using terminal, bulk-created all applicable *Service*, *Entity* and *Repository* directories and *.java* files

**CONTINUE FROM HERE**

**Note:**
- Spring Initializr:
  - Java v25 (with Maven)
  - Spring v3.5.6
  - Group: `net.yorksolutions`
  - Artifact: `Backend`
  - Description: `Project Two for Member Benefits Dashboard`
  - Dependencies: Spring Web, Spring Security, OAuth2 Client, PostgreSQL Driver, Spring Data JPA, Spring Boot DevTools and Docker Compose Support

#### JavaScript (React)
The project was initialized using Vite as a build-tool. Also, I selected the *JavaScript + React Compiler* option (under *React*).

**Getting Started Steps:**
1. Create Vite project: `npm create vite@latest` (I started from the project directory and called this project "Frontend" to ensure I was working from the "Frontend" folder and not a folder within that)
2. Install any packages listed in *packages.json*: `sudo npm install` (ensure that you `cd` into the directory)
3. Update *vite.config.ts* for the port of 3000 (this is what I have Google set up to direct to/through)

```javascript
server: {
  port: 3000,
},
```

4. Start the local server

**Recurring Steps:**
1. Transpile TypeScript into JavaScript: `tsc` || `tsc src/index.ts`
2. Run the project (DEV; Initializes the server): `npm run dev`

**Note:**
- The `npm run dev` command, for Vite, will automatically transpile TypeScript, however it does **not** handle type-checking
  - Type checking is, typically, handled by the IDE, a separate `tsc` command
- When ready for production, run `npm run build`
  - The *package.json* file can be updated to auto-run the `tsc` command for type-checking

```json
// `--noEmit` and `vite build` will run a full type-check without
// ... generating JavaScript files
{
  "scripts": {
    "build": "tsc --noEmit && vite build"
  }
}
```

- The initial goal was to use TypeScript, however I think it will be faster to use JavaScript both because it is my first time using React, the documentation on the React site is in JavaScript and because I do not have a strong grasp on TypeScript so that would slow me down

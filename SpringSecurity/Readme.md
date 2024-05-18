## Springboot Security
### 1.form based default password generator

```java 
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-web</artifactId>
</dependency>

```

### 2.properties

````java
spring.security.user.name=mansoor
spring.security.user.password=demo1234
````

### 3.Basic Authentication

```java

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> {
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl) requests.anyRequest()).authenticated();
        });
//        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return (SecurityFilterChain) http.build();
    }
}

```

### 4.InMemory Security

```java

@Bean
public UserDetailsService userDetailsService() {
    UserDetails user1 = User.withUsername("user1")
            .password("{noop}password1")
            .roles("USER")
            .build();
    UserDetails admin = User.withUsername("admin")
            .password("{noop}password1")
            .roles("ADMIN")
            .build();


    return new InMemoryUserDetailsManager(user1, admin);

}
```

### 5. Role Based Authentication

```java

@PreAuthorize("hasRole('USER')")
@GetMapping("/user")
public String userEndpoint() {
    return "User Got the Message";
}

@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/admin")
public String adminEndpoint() {
    return "Admin called ";
}
```

### 6. H2 Database

```java

SecurityConfig .

class


spring.h2.console.enabled=true;
spring.datasource.url=jdbc:h2:mem:test

@Bean

SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests((requests) -> {
        ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl) requests.requestMatchers("h2-console/**").permitAll().anyRequest()).authenticated();
    });

    http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        http.formLogin(Customizer.withDefaults());
    http.httpBasic(Customizer.withDefaults());
    http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
    http.csrf(AbstractHttpConfigurer::disable);
    return (SecurityFilterChain) http.build();
} 

<dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
</dependency>


```

### 7. Database Authentication

```java

@Autowired
DataSource dataSource;

@Bean
public UserDetailsService userDetailsService() {
    UserDetails user = User.withUsername("user").password("{noop}user1234").roles("USER").build();
    UserDetails admin = User.withUsername("admin").password("{noop}admin1234").roles("ADMIN").build();

    JdbcUserDetailsManager jdbcUserDetailsManager
            = new JdbcUserDetailsManager(dataSource);
    jdbcUserDetailsManager.createUser(user);
    jdbcUserDetailsManager.createUser(admin);
    return jdbcUserDetailsManager;


}
```

##### Password Encoder

```java

bcrypt involves
using salting
salting helps

increase security

        (pwd) mansoor  +XwZ78 =SALT
                       |
$2a$12$JBXkj&his&fhk$lF&hjlI$h&ohy&fk$uhj$u6b4V =
hashed password

```

```java

@Bean
public UserDetailsService userDetailsService() {
    UserDetails user = User.withUsername("user")
            .password(passwordEncoder().encode("user1234"))
            .roles("USER")
            .build();
    UserDetails admin = User.withUsername("admin")
            .password(passwordEncoder().encode("admin1234"))
            .roles("ADMIN")
            .build();

    JdbcUserDetailsManager jdbcUserDetailsManager
            = new JdbcUserDetailsManager(dataSource);
    jdbcUserDetailsManager.createUser(user);
    jdbcUserDetailsManager.createUser(admin);
    return jdbcUserDetailsManager;

}

@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

```

##### Useful Links

```java
sql

https://github.com/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl

//create table users(username varchar_ignorecase(50) not null primary key,password varchar_ignorecase(500) not null,enabled boolean not null);
//create table authorities (username varchar_ignorecase(50) not null,authority varchar_ignorecase(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
//create unique index ix_auth_username on authorities (username,authority);


jwt

https://github.com/jwtk/jjwt?tab=readme-ov-file#maven


<dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.12.5</version>
</dependency>
<dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.12.5</version>
            <scope>runtime</scope>
</dependency>
<dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
//            <!-- or jjwt-gson if Gson is preferred -->
            <version>0.12.5</version>
            <scope>runtime</scope>
</dependency>
```
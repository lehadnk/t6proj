package t6proj.authorization.communication.http;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Component
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresAuthorizedUser {
}

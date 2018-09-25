package com.sda.todo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TodoUser {
    public static TodoUser unnasigned() {
        return new TodoUser("Nie przypisany", null);
    }

    private String name;
    private String password;
}
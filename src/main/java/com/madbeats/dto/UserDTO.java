package com.madbeats.dto;
/**
import com.madbeats.entity.User;

public class UserDTO {
    private String id;
    private String username;

    // Constructor
    public UserDTO(String id, String username) {
        this.id = id;
        this.username = username;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Método estático para convertir un objeto User a UserDTO
    public static UserDTO from(User user) {
        return new UserDTO(user.getId(), user.getUsername());
    }

    // Métodos equals() y hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        if (id != null ? !id.equals(userDTO.id) : userDTO.id != null) return false;
        return username != null ? username.equals(userDTO.username) : userDTO.username == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }

    // Método toString()
    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
**/
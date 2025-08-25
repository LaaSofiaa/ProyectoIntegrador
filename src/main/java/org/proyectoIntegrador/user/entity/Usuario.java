package org.proyectoIntegrador.user.entity;

public class Usuario {
    private Long id;
    private String nombre;
    private String email;
    private Integer edad;

    // Constructores
    public Usuario() {}

    public Usuario(Long id, String nombre, String email, Integer edad) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.edad = edad;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }
}

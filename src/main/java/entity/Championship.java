package entity;

import java.io.Serializable;

public class Championship implements Serializable {
    private Long id;
    private String name;

    public Championship() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

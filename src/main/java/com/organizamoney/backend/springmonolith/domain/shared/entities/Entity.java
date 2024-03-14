package com.organizamoney.backend.springmonolith.domain.shared.entities;

import java.io.Serial;
import java.io.Serializable;

public abstract class Entity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Long _id;

    public Long getId() {
        return _id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Entity entity = (Entity) obj;
        return getId() != null ? getId().equals(entity.getId()) : entity.getId() == null;
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }
}

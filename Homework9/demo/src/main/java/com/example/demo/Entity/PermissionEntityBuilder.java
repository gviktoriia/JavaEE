package com.example.demo.Entity;

import com.example.demo.Permission;

public class PermissionEntityBuilder {
    private Integer id;
    private Permission permission;

    PermissionEntityBuilder() {
    }

    public PermissionEntityBuilder id(final Integer id) {
        this.id = id;
        return this;
    }

    public PermissionEntityBuilder permission(final Permission permission) {
        this.permission = permission;
        return this;
    }

    public PermissionEntity build() {
        return new PermissionEntity(this.id, this.permission);
    }

    public String toString() {
        return "PermissionEntity.PermissionEntityBuilder(id=" + this.id + ", permission=" + this.permission + ")";
    }
}

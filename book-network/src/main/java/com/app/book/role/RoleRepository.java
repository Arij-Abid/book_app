package com.app.book.role;

import java.util.Optional;

public interface RoleRepository{// extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String roleStudent);
}

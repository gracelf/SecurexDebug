package byAJ.Securex.repositories;

import byAJ.Securex.models.Role;

import org.springframework.data.repository.CrudRepository;


public interface RoleRepo extends CrudRepository<Role, Long> {
    Role findByRole(String role);
}

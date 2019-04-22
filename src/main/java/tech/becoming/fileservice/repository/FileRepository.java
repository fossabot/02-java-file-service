package tech.becoming.fileservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.becoming.fileservice.entity.File;

@Repository
public interface FileRepository extends CrudRepository<File, String> {
}

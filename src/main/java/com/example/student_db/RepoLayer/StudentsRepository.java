package com.example.student_db.RepoLayer;

import com.example.student_db.classlayer.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository
        extends JpaRepository<Students, Integer>
{

}

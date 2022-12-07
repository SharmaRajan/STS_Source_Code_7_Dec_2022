package com.sharmajee.StudentMaster;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.sharmajee.StudentMaster.entity.StudentEntity;
import com.sharmajee.StudentMaster.repository.StudentRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
@Rollback(false)
public class StudentRepositoryTest {
	
	@Autowired
	private StudentRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateuser()	{
		StudentEntity student = new StudentEntity();
		student.setEmail("rajan@gmail.com");
		student.setPassword("Rajan");
		student.setFirstName("Rajan");
		student.setLastName("Sharma");
		
		StudentEntity savedStudent = repo.save(student);
		StudentEntity existStudent = entityManager.find(StudentEntity.class, savedStudent.getId());
		
		assertThat(existStudent.getEmail()).isEqualTo(student.getEmail());
	}
}

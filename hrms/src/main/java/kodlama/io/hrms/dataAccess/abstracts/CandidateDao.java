package kodlama.io.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.hrms.entities.concretes.Candidate;
import kodlama.io.hrms.entities.concretes.Employer;

public interface CandidateDao extends JpaRepository<Candidate,Integer> {
	 Candidate findByEmail(String email);
	 Candidate findByidentityNumber(String identityNumber);

}

package kodlama.io.hrms.core.utilities.adapters.abstracts;

import kodlama.io.hrms.entities.concretes.Candidate;

public interface MernisCheckService {
	public boolean checkIfRealPerson(Candidate candidate);

}

package kodlama.io.hrms.business.concretes;

import java.util.List;

import kodlama.io.hrms.business.abstracts.UserService;
import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.entities.concretes.User;

public class UserManager implements UserService {

	@Override
	public DataResult<List<User>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result add(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<User> getByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}

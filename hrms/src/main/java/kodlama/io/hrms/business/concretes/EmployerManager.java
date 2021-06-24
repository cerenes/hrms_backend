package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import kodlama.io.hrms.business.abstracts.EmployerService;
import kodlama.io.hrms.core.utilities.adapters.abstracts.VerficationCodeService;
import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.ErrorResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.core.utilities.results.SuccessDataResult;
import kodlama.io.hrms.core.utilities.results.SuccessResult;
import kodlama.io.hrms.dataAccess.abstracts.EmployerDao;
import kodlama.io.hrms.entities.concretes.Employer;




public class EmployerManager implements EmployerService {
	private EmployerDao employerDao;
	private VerficationCodeService verficationCode;
	
	@Autowired
	public EmployerManager(EmployerDao employerDao,VerficationCodeService verficationCode) {
		super();
		this.employerDao = employerDao;
		this.verficationCode = verficationCode;
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(),"Employer List");
	}

	@Override
	public Result add(Employer employer) {
		
		        var domain = employer.getEmail().split("@")[1];
		        var result = employer.getWebAddress().equals(domain);
			if(employer.getWebAddress() == null || employer.getCompanyName() == null || employer.getPhoneNumber() == null
					|| employer.getEmail() == null || employer.getPassword() == null || employer.getPassword() == null) {
				return new ErrorResult("Boş alan bırakmayınız");
			}else if(!checkEmail(employer.getEmail())) {
				return new ErrorResult("Email kullanılmaktadır.");
			}
			
		     else if (!result) {
		            return new ErrorResult("Email adresi ile web sitesi eslesmiyor");
		     }
		        

			
			this.employerDao.save(employer);
			this.verficationCode.sendCode();
			return new SuccessResult("İş veren eklendi");
			
	}
		
		private  boolean checkEmail(String email) {
			if (this.employerDao.findByEmail(email) == null) {
				return true;
			}
			return false;
			
		}
	

	@Override
	public DataResult<Employer> getByEmail(String email) {
		return new SuccessDataResult<Employer>(this.employerDao.findByEmail(email));
	}

}

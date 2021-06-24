package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.CandidateService;
import kodlama.io.hrms.core.utilities.adapters.abstracts.MailCheckService;
import kodlama.io.hrms.core.utilities.adapters.abstracts.MernisCheckService;
import kodlama.io.hrms.core.utilities.adapters.abstracts.VerficationCodeService;
import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.ErrorResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.core.utilities.results.SuccessDataResult;
import kodlama.io.hrms.core.utilities.results.SuccessResult;
import kodlama.io.hrms.dataAccess.abstracts.CandidateDao;
import kodlama.io.hrms.entities.concretes.Candidate;

@Service
public class CandidateManager implements CandidateService {
	    private CandidateDao candidateDao; 
		private MernisCheckService mernisCheckService;
		private MailCheckService mailCheckService;
		private VerficationCodeService verficationCodeService;

	  @Autowired
		public CandidateManager(CandidateDao candidateDao, MernisCheckService mernisCheckService, MailCheckService mailCheckService,VerficationCodeService verficationCodeService) {
			super();
			this.candidateDao = candidateDao;
			this.mernisCheckService = mernisCheckService;
			this.mailCheckService = mailCheckService;
			this.verficationCodeService = verficationCodeService ;
		}
				@Override
				public Result add(Candidate candidate) {
					
					if(candidate.getFirstName() == null || candidate.getLastName() == null || candidate.getBirthYear() == null 
							|| candidate.getIdentityNumber() == null || candidate.getEmail() == null || candidate.getPassword() == null  )  {
						return new ErrorResult("Boş alan bırakmayınız");
						
					}else if(!mernisCheckService.checkIfRealPerson(candidate)) {
						return new ErrorResult("Mernis Doğrulaması yapılamadı");
						
					}else if(!checkEmail(candidate.getEmail())) {
						return new ErrorResult("Email kullanılmaktadır");
					}else if(!checkNationalId(candidate.getIdentityNumber())) {
						return new ErrorResult("TC Kimlik Numarası kullanılmaktadır");
					}else {
						mailCheckService.checkEmail(candidate.getEmail());
						this.verficationCodeService.sendCode();
						this.candidateDao.save(candidate);
						return new SuccessResult("Aday eklendi");
					}
				}
				
				private  boolean checkEmail(String email) {
					if (this.candidateDao.findByEmail(email) == null) {
						return true;
					}
					return false;
					
				}
				private boolean checkNationalId(String nationalId) {
					if(this.candidateDao.findByidentityNumber(nationalId) == null) {
						return true;
					}
					return false;
				}
				


	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult <List<Candidate>>(this.candidateDao.findAll(),
				"İş Arayanlar Listelendi");
				
	}

	
	@Override
	public DataResult<Candidate> getByEmail(String email) {
		return new SuccessDataResult<Candidate>(this.candidateDao.findByEmail(email));
	}

	@Override
	public DataResult<Candidate> getByidentityNumber(String identityNumber) {
		return new SuccessDataResult<Candidate>(this.candidateDao.findByidentityNumber(identityNumber));
	}

}

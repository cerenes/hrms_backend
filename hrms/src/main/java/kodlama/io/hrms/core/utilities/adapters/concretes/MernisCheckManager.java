package kodlama.io.hrms.core.utilities.adapters.concretes;

import java.rmi.RemoteException;
import java.util.Locale;

import kodlama.io.hrms.core.utilities.adapters.abstracts.MernisCheckService;
import kodlama.io.hrms.entities.concretes.Candidate;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

public class MernisCheckManager implements MernisCheckService{

	@Override
	public boolean checkIfRealPerson(Candidate candidate) {
		
			KPSPublicSoapProxy client = new KPSPublicSoapProxy();
			
			boolean result = true;
			try {
				result = client.TCKimlikNoDogrula(Long.parseLong(candidate.getIdentityNumber()), 
						candidate.getFirstName().toUpperCase(new Locale ("tr")), 
						candidate.getLastName().toUpperCase(new Locale ("tr")), candidate.getBirthYear().getYear());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return result;
		}
	}



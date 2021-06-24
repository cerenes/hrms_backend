package kodlama.io.hrms.core.utilities.adapters.concretes;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import kodlama.io.hrms.core.utilities.adapters.abstracts.VerficationCodeService;

public class VerficationCodeManager implements VerficationCodeService {

	@Override
	public void sendVerificationLink(String email) {
		UUID uuid = UUID.randomUUID();
		String verificationLink = "https://hrmsverificationemail/" + uuid.toString();
		System.out.println("Doğrulama bağlantısı gönderildi: " + email);
		System.out.println("Hesabınızı doğrulamak için lütfen bağlantıya tıklayın: " + verificationLink);
	}

	@Override
	public String sendCode() {
		UUID uuid = UUID.randomUUID();
		String verificationCode = uuid.toString();
		System.out.println("Aktivasyon kodunuz: " + verificationCode);
		return verificationCode;
	}

}

package kodlama.io.hrms.core.utilities.adapters.abstracts;

public interface VerficationCodeService {
	void sendVerificationLink(String email);

	String sendCode();

}

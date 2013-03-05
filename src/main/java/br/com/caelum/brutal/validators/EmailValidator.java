package br.com.caelum.brutal.validators;

import br.com.caelum.brutal.dao.UserDAO;
import br.com.caelum.brutal.factory.MessageFactory;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class EmailValidator {
	
	private UserDAO users;
	private Validator validator;
	private MessageFactory messageFactory;

	public EmailValidator(Validator validator, UserDAO users, MessageFactory messageFactory) {
		this.users = users;
		this.validator = validator;
		this.messageFactory = messageFactory;
	}
	
	public boolean validate(String email){
		if (users.existsWithEmail(email)) {
			validator.add(messageFactory.build("error", "user.errors.email.used"));
		}
		
		return !validator.hasErrors();
	}
}

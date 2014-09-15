package com.scienaptic.web.mail;

import com.scienaptic.web.form.Message;

public interface MailService {
	public void sendSecureMail(Message message);
}
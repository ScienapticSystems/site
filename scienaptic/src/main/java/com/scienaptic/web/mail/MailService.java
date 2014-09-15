package com.scienaptic.web.mail;

import com.scienaptic.web.form.Message;
import com.scienaptic.web.form.Resume;

public interface MailService {
	public void sendSecureMail(Message message);

	public void sendSecureMail(Resume resume);
}
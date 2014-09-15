package com.scienaptic.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scienaptic.web.form.Message;
import com.scienaptic.web.form.Resume;
import com.scienaptic.web.mail.MailService;

@Controller
@RequestMapping("contact_us")
public class ContactUsController {
	@Autowired
	private MailService mailService;

	@RequestMapping(value = "/mail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody void sendMail(@RequestBody Message message) {
		mailService.sendSecureMail(message);
	}

	@RequestMapping(value = "/mail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody void sendResume(@RequestBody Resume resume) {
		mailService.sendSecureMail(resume);
	}
}

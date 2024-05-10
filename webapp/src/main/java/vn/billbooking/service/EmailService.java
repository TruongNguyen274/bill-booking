package vn.billbooking.service;

import vn.billbooking.model.dto.EmailAccountDTO;
import vn.billbooking.model.dto.EmailTemplateDTO;

public interface EmailService {

    boolean sendEmailForRegister(String type, EmailTemplateDTO emailTemplateDTO);

    boolean sendEmailForCheckout(EmailTemplateDTO emailTemplateDTO);

    boolean sendEmailForResetPassword(EmailAccountDTO emailDTO);

}

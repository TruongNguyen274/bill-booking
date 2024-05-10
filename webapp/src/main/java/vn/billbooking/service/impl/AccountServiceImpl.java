package vn.billbooking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.billbooking.model.dto.AccountDTO;
import vn.billbooking.model.dto.EmailTemplateDTO;
import vn.billbooking.model.dto.FileDTO;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Role;
import vn.billbooking.model.mapper.AccountMapper;
import vn.billbooking.repository.AccountRepository;
import vn.billbooking.service.AccountService;
import vn.billbooking.service.EmailService;
import vn.billbooking.service.FileUploadService;
import vn.billbooking.service.RoleService;
import vn.billbooking.utils.ContantUtil;
import vn.billbooking.utils.DateUtil;
import vn.billbooking.utils.RandomUtil;
import vn.billbooking.utils.ValidatorUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> findAllByActive() {
        return accountRepository.findByStatusIsTrue();
    }

    @Override
    public Account findById(long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username).orElse(null);
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Account findByPhone(String phone) {
        return accountRepository.findByPhone(phone).orElse(null);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account save(AccountDTO accountDTO) {
        if (accountDTO == null) {
            return null;
        }

        Account account = new Account();
        if (accountDTO.getId() > 0) {
            account = findById(accountDTO.getId());
        }

        // mapper data
        accountMapper.toEntity(account, accountDTO);

        // upload file
        if (!ValidatorUtil.isEmpty(accountDTO.getAvatarMul())) {
            FileDTO photoDTO = fileUploadService.uploadFile(accountDTO.getAvatarMul());
            account.setAvatar(photoDTO.getPath());
        }

        // generate id
        boolean isNewAccount = accountDTO.getId() == 0;
        if (isNewAccount) {
            // set code id
            if (account.getId() != 0) {
                String codeId = RandomUtil.generateId(account.getId());
                account.setCode(codeId);
            }
            //set username
            if (!ValidatorUtil.isEmpty(accountDTO.getEmail())) {
                account.setUsername(accountDTO.getEmail());
            } else {
                if (!ValidatorUtil.isEmpty(accountDTO.getPhone())) {
                    account.setUsername(accountDTO.getPhone());
                }
            }
            // set password
            String randompass = RandomUtil.generatePassword(ContantUtil.PASSWORD_LENGTH);
            String encodedPassword = passwordEncoder.encode(randompass);
            account.setPassword(encodedPassword);

            String[] arrayToEmail = new String[]{account.getEmail()};

            Map<String, Object> properties = new HashMap<>();
            properties.put("fullname", account.getFullName());
            properties.put("username", account.getUsername());
            properties.put("password", randompass);
            properties.put("emailContact", "Email: info@billbooking.vn");
            properties.put("phoneContact", "Phone: 1900 3118");

            // send email for register
            EmailTemplateDTO emailTemplateDTO = new EmailTemplateDTO();
            emailTemplateDTO.setTo(arrayToEmail);
            emailTemplateDTO.setSubject("Đăng Ký Tài Khoản Tại billbooking.vn");
            emailTemplateDTO.setProperties(properties);
            emailService.sendEmailForRegister("OWNER", emailTemplateDTO);
        }

        return accountRepository.save(account);
    }

    @Override
    public Account save(AccountDTO accountDTO, String roleType) {
        try {
            if (accountDTO == null) {
                return null;
            }

            // set account
            Account account = new Account();
            if (accountDTO.getId() > 0) {
                account = findById(accountDTO.getId());
            }

            // set role
            Role role = roleService.findByName(roleType);
            if (role != null) {
                accountDTO.setRoleId(role.getId());
            }

            // mapper data
            accountMapper.toEntity(account, accountDTO);

            // set data
            if (!ValidatorUtil.isEmpty(accountDTO.getEmail())) {
                account.setUsername(accountDTO.getEmail().trim());
            } else {
                account.setUsername(accountDTO.getPhone().trim());
            }
            account.setStatus(true);

            account = accountRepository.save(account);

            // generate id
            boolean isNewAccount = accountDTO.getId() == 0;
            if (isNewAccount && account != null) {
                // set code id
                String codeId = RandomUtil.generateId(account.getId());
                account.setCode("US" + codeId);

                // set password
                String genPassword = null;
                if (accountDTO.getPassword() == null) {
                    genPassword = RandomUtil.generatePassword(ContantUtil.PASSWORD_LENGTH);
                    String encodedPassword = passwordEncoder.encode(genPassword);
                    account.setPassword(encodedPassword);
                } else {
                    genPassword = accountDTO.getPassword();
                    String encodedPassword = passwordEncoder.encode(genPassword);
                    account.setPassword(encodedPassword);
                }

                // save
                account = accountRepository.save(account);

                // send email
                String email = account.getEmail();
                if (email != null) {
                    String[] toEmail = {email};

                    Map<String, Object> properties = new HashMap<>();
                    properties.put("fullname", account.getFullName());
                    properties.put("username", account.getUsername());
                    properties.put("password", genPassword);
                    properties.put("link", "sss");
                    properties.put("emailContact", "Email: info@billbooking.vn");
                    properties.put("phoneContact", "Hotline: 1900 3118");

                    EmailTemplateDTO emailTemplateDTO = new EmailTemplateDTO();
                    emailTemplateDTO.setTo(toEmail);
                    emailTemplateDTO.setSubject("Xác Nhận Đăng Ký Tài Khoản Tại billbooking.vn");
                    emailTemplateDTO.setContent("");
                    emailTemplateDTO.setProperties(properties);

                    emailService.sendEmailForRegister(roleType, emailTemplateDTO);
                }
            }

            return account;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Account> findByRoleAndStatusIsTrue(Role role) {
        return accountRepository.findByRoleAndStatusIsTrue(role);
    }

    @Override
    public List<Account> getOwnerActiveKaraoke() {
        return accountRepository.getOwnerActiveKaraoke();
    }

}

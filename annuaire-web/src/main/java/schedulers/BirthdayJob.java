package schedulers;


import com.annuaire.dao.ContactDao;
import com.annuaire.db.TransactionHandler;
import com.annuaire.properties.EmailPropertyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.annuaire.service.EmailService;
import com.annuaire.util.Utils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BirthdayJob implements Job {
    private final static Logger logger = LogManager.getLogger(BirthdayJob.class);
    private final static EmailPropertyService properties = EmailPropertyService.getInstance();
    private final EmailService service = new EmailService();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<String> birthdayBoysEmails = getBirthdayBoysEmails();
        if (birthdayBoysEmails != null && !birthdayBoysEmails.isEmpty()){
            String birthdayList = Utils.joinListWithSemicolon(birthdayBoysEmails);
            Map<String, String> emailParams = buildEmailParams(birthdayList);
            service.sendEmail(emailParams);

            logger.info("Sending notification to admin, that " + birthdayList + "have birthday today");
        }
    }

    private Date today() {
        java.util.Date utilDate = new java.util.Date();
        return new Date(utilDate.getTime());
    }

    private List<String> getBirthdayBoysEmails() {
        final List<String> emails = new ArrayList<>();
        TransactionHandler.run(connection -> {
            ContactDao dao = new ContactDao(connection);
            emails.addAll(dao.getEmailsByDateOfBirth(today()));
        });

        return emails;
    }

    private Map<String, String> buildEmailParams(String emailList){
        Map<String, String> params = new HashMap<>();
        params.put("subject", properties.getNotificationSubject());
        String message = emailList + " have birthday today";
        params.put("message", message);
        params.put("emails", properties.getAdminEmail());

        return params;
    }
}
package dao;


import db.constants.ContactConstants;
import entities.Contact;
import builders.ContactBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.DaoUtils;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDao extends AbstractTemplateDao<Contact, Long> {
    private final static Logger logger = LogManager.getLogger(ContactDao.class);

    public ContactDao() {
        super();
    }

    @Override
    public Long save(Contact contact) {
        Long id = null;

        try (PreparedStatement statement = getPreparedStatementAndReturnGeneratedKeys(ContactConstants.SAVE)) {
            statement.setString(1, contact.getFirstName());
            statement.setString(2, contact.getLastName());
            statement.setString(3, contact.getMiddleName());
            statement.setDate(4, contact.getDateOfBirth());
            statement.setString(5, contact.getSex().value());
            statement.setString(6, contact.getCitizenship());
            statement.setString(7, contact.getMaritalStatus());
            statement.setString(8, contact.getWebsite());
            statement.setString(9, contact.getEmail());
            statement.setString(10, contact.getPlaceOfWork());
            String photoPath = DaoUtils.fileToPath(contact.getPhoto());
            statement.setString(11, photoPath);

            logger.info(statement.toString());

            statement.executeUpdate();
            id = obtainIdFromStatement(statement);
        } catch (SQLException e) {
            logger.error(e);
        }

        return id;
    }

    @Override
    public List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<>();

        try (PreparedStatement statement = getPreparedStatement(ContactConstants.GET_ALL)) {
            logger.info(statement.toString());

            ResultSet set = statement.executeQuery();
            contacts = fillListFromResultSet(set);
        } catch (SQLException e) {
            logger.error(e);
        }

        return contacts;
    }

    @Override
    public Contact getById(Long id) {
        Contact contact = null;

        try (PreparedStatement statement = getPreparedStatement(ContactConstants.GET_BY_ID)) {
            statement.setLong(1, id);

            logger.info(statement.toString());

            ResultSet set = statement.executeQuery();
            contact = fillContactFromResultSet(set);

        } catch (SQLException e) {
            logger.error(e);
        }

        return contact;
    }

    @Override
    public void update(Contact contact) {
        try (PreparedStatement statement = getPreparedStatement(ContactConstants.UPDATE)) {
            statement.setString(1, contact.getFirstName());
            statement.setString(2, contact.getLastName());
            statement.setString(3, contact.getMiddleName());
            statement.setDate(4, contact.getDateOfBirth());
            statement.setString(5, contact.getSex().value());
            statement.setString(6, contact.getCitizenship());
            statement.setString(7, contact.getMaritalStatus());
            statement.setString(8, contact.getWebsite());
            statement.setString(9, contact.getEmail());
            statement.setString(10, contact.getPlaceOfWork());
            String photoPath = DaoUtils.fileToPath(contact.getPhoto());
            statement.setString(11, photoPath);
            statement.setLong(12, contact.getId());

            logger.info(statement.toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement statement = getPreparedStatement(ContactConstants.DELETE)) {
            statement.setLong(1, id);
            logger.info(statement.toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public List<Contact> getBy(String params) {
        //todo getBy specific params
        return null;
    }

    public List<Contact> getWithOffset(int limit, int offset) {
        List<Contact> contacts = new ArrayList<>();

        try (PreparedStatement statement = getPreparedStatement(ContactConstants.GET_WITH_OFFSET)) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);

            logger.info(statement.toString());

            ResultSet set = statement.executeQuery();
            contacts = fillListFromResultSet(set);

        } catch (SQLException e) {
            logger.error(e);
        }

        return contacts;
    }

    public String getEmailById(Long id) {
        String email = null;
        try (PreparedStatement statement = getPreparedStatement(ContactConstants.GET_EMAIL_BY_ID)) {
            statement.setLong(1, id);

            logger.info(statement.toString());

            ResultSet set = statement.executeQuery();
            if (set.next()){
                email = set.getString("email");
            }

        } catch (SQLException e) {
            logger.error(e);
        }

        return email;
    }

    public List<String> getEmailsByDateOfBirth(Date date) {
        List<String> emails = new ArrayList<>();
        try (PreparedStatement statement = getPreparedStatement(ContactConstants.GET_EMAILS_BY_DATE_OF_BIRTH)) {
            statement.setDate(1, date);

            logger.info(statement.toString());

            ResultSet set = statement.executeQuery();
            while (set.next()){
                String email = set.getString("email");
                emails.add(email);
            }

        } catch (SQLException e) {
            logger.error(e);
        }

        return emails;
    }

    private List<Contact> fillListFromResultSet(ResultSet set) throws SQLException {
        List<Contact> contacts = new ArrayList<>();

        while (set.next()) {
            ContactBuilder builder = new ContactBuilder();
            builder.id(set.getLong("id"))
                    .firstName(set.getString("firstName"))
                    .lastName(set.getString("lastName"))
                    .middleName(set.getString("middleName"))
                    .dateOfBirth(set.getDate("dateOfBirth"))
                    .sex(set.getString("sex"))
                    .citizenship(set.getString("citizenship"))
                    .maritalStatus(set.getString("maritalStatus"))
                    .website(set.getString("website"))
                    .email(set.getString("email"))
                    .placeOfWork(set.getString("placeOfWork"));
                    //.photo(DaoUtils.pathToFile(set.getString("photoPath")));

            Contact contact = builder.build();
            contacts.add(contact);
        }

        return contacts;
    }

    private Contact fillContactFromResultSet(ResultSet set) throws SQLException{
        Contact contact = null;

        if (set.next()) {
            ContactBuilder builder = new ContactBuilder();
            builder.id(set.getLong("id"))
                    .firstName(set.getString("firstName"))
                    .lastName(set.getString("lastName"))
                    .middleName(set.getString("middleName"))
                    .dateOfBirth(set.getDate("dateOfBirth"))
                    .sex(set.getString("sex"))
                    .citizenship(set.getString("citizenship"))
                    .maritalStatus(set.getString("maritalStatus"))
                    .website(set.getString("website"))
                    .email(set.getString("email"))
                    .placeOfWork(set.getString("placeOfWork"));
                    //.photo(DaoUtils.pathToFile(set.getString("photoPath")));

            contact = builder.build();
        }

        return contact;
    }
}

package com.qa.hippo.main.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OTPFetcher {

    // Database credentials
    private static final String DB_URL = "jdbc:postgresql://ecomm-backend.chfh7l48r6z1.ap-south-1.rds.amazonaws.com:5432/hippoecomm";
    private static final String USER = "niyaz_read";
    private static final String PASS = "Test01";
    private static String site_code=null;
    private static String scope_ID=null;
    private static String ENQUIRY_ID;
    private static String MESSAGE_ID;
    private static String ORDER_ID;

    public static String fetchOTP(String mobileNumber) {
        String otp = null;
        String query = "SELECT otp FROM user_otps WHERE mobile = ? ";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, mobileNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                otp = resultSet.getString("otp");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return otp;
    }

    public static String getSiteCode(String Mobile){
        String query="select site_code from crm.\"Project_Site\" ps inner join crm.\"Customer\" c on c.id =ps.customer_id where c.mobile = ? order by ps.created_at desc limit 1";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, Mobile);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                site_code = resultSet.getString("site_code");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return site_code;
    }

    public static String getOpportunityCode(String mobile_number){
        String query = "SELECT o.opportunity_code " +
                "FROM crm.\"Project_Site\" ps " +
                "INNER JOIN crm.\"Customer\" c ON c.id = ps.customer_id " +
                "INNER JOIN crm.\"Opportunity\" o ON o.project_site_id = ps.id " +
                "WHERE c.mobile = ? " +
                "ORDER BY o.created_at DESC " +
                "LIMIT 1";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, mobile_number);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                scope_ID = resultSet.getString("opportunity_code");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scope_ID;
    }

    public static String getEnquiryID(String phone){
        String query="SELECT e.enquiry_number\n" +
                "FROM crm.\"Project_Site\" ps\n" +
                "INNER JOIN crm.\"Customer\" c ON c.id = ps.customer_id\n" +
                "INNER JOIN crm.\"Opportunity\" o ON o.project_site_id = ps.id\n" +
                "INNER JOIN crm.\"Enquiry\" e ON e.opportunity_id = o.id\n" +
                "WHERE c.mobile = ? \n" +
                "ORDER BY e.created_at DESC\n" +
                "LIMIT 1;\n";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, phone);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ENQUIRY_ID = resultSet.getString("enquiry_number");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ENQUIRY_ID;
    }

    public static String getMessageID(String enquiryNumber){
        String query = "SELECT etm.message_id FROM crm.\"Project_Site\" ps " +
                "INNER JOIN crm.\"Customer\" c ON c.id = ps.customer_id " +
                "INNER JOIN crm.\"Opportunity\" o ON o.project_site_id = ps.id " +
                "INNER JOIN crm.\"Enquiry\" e ON e.opportunity_id = o.id " +
                "INNER JOIN crm.\"Boq\" b ON b.enquiry_id = e.id " +
                "INNER JOIN crm.\"Enquiry_Task_Mapping\" etm ON etm.enquiry_id = e.id " +
                "WHERE e.enquiry_number = CAST(? AS INTEGER) AND etm.message_id IS NOT NULL " +
                "ORDER BY etm.\"sequence\" ASC";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, enquiryNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                MESSAGE_ID = resultSet.getString("message_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return MESSAGE_ID;
    }

    public static String getOrderID(String enquiryNumber) {
        System.out.println("DB Page enquiry "+enquiryNumber);

        String query = "SELECT o2.id FROM crm.\"Project_Site\" ps " +
                "INNER JOIN crm.\"Customer\" c ON c.id = ps.customer_id " +
                "INNER JOIN crm.\"Opportunity\" o ON o.project_site_id = ps.id " +
                "INNER JOIN crm.\"Enquiry\" e ON e.opportunity_id = o.id " +
                "INNER JOIN crm.\"Boq\" b ON b.enquiry_id = e.id " +
                "INNER JOIN crm.\"Enquiry_Task_Mapping\" etm ON etm.enquiry_id = e.id " +
                "INNER JOIN public.\"order\" o2 ON o2.\"number\" = etm.order_number " +
                "WHERE e.enquiry_number = ? " + // Ensuring enquiry_number is used correctly
                "AND (etm.status = 'in Progress' OR etm.message_id IS NOT NULL) " +
                "ORDER BY etm.\"sequence\" ASC " +
                "LIMIT 1";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, Integer.parseInt(enquiryNumber)); // Convert String to Integer

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    ORDER_ID = resultSet.getString("id");
                }
            }

        } catch (NumberFormatException e) {
            System.err.println("Invalid enquiry number format: " + enquiryNumber);
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error fetching Order ID for Enquiry Number: " + enquiryNumber);
            e.printStackTrace();
        }

        return ORDER_ID;
    }

    public static void main(String[] args) {
            String site = getMessageID("351");
//            System.out.println(site);
        System.out.println(site);
    }
}


package com.testautomation.framework.utils;

import org.openqa.selenium.NotFoundException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.search.FlagTerm;
import javax.mail.search.OrTerm;
import javax.mail.search.SearchTerm;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;


public class EmailUtils {
    /**
     *  To get the html body and return in string format
     */
    @SuppressWarnings("unused")
    private boolean textIsHtml = true;
    private String getText(Part p) throws MessagingException,IOException {

        if (p.isMimeType("text/*")) {
            String s = (String)p.getContent();
            textIsHtml = p.isMimeType("text/html");
            return s;
        }
        if (p.isMimeType("multipart/alternative")) {
            // prefer html text over plain text
            Multipart mp = (Multipart)p.getContent();
            String text = null;
            for (int i = 0; i < mp.getCount(); i++) {
                Part bp = mp.getBodyPart(i);
                if (bp.isMimeType("text/plain")) {
                    if (text == null)
                        text = getText(bp);
                    continue;
                } else if (bp.isMimeType("text/html")) {
                    String s = getText(bp);
                    if (s != null)
                        return s;
                } else {
                    return getText(bp);
                }
            }
            return text;
        } else if (p.isMimeType("multipart/*")) {
            Multipart mp = (Multipart)p.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                String s = getText(mp.getBodyPart(i));
                if (s != null)
                    return s;
            }
        }
        return null;
    }

    /**
     *  To get email from email provider
     * @param emailProvider
     * @param userName
     * @param password
     * @param subjectKeyword
     * @param fromEmail
     * @return
     * @throws IOException
     */
    public String getEmail(String emailProvider,String userName, String password, final String subjectKeyword, final String fromEmail) throws Exception {
        Properties properties = new Properties();
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");
        String emailBody=null;

        if ("GMAIL".equals(emailProvider.toUpperCase())) {// server setting
            properties.put("mail.imap.host", "imap.gmail.com");
            properties.put("mail.imap.port", 993);
            // SSL setting
            properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.setProperty("mail.imap.socketFactory.fallback", "false");
            properties.setProperty("mail.imap.socketFactory.port", String.valueOf(993));

        } else if ("YAHOO".equals(emailProvider.toUpperCase())) {// server setting
            properties.put("mail.imap.host", "imap.mail.yahoo.com");
            properties.put("mail.imap.port", 993);
            // SSL setting
            properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.setProperty("mail.imap.socketFactory.fallback", "false");
            properties.setProperty("mail.imap.socketFactory.port", String.valueOf(993));

            properties.put("mail.imap.host", "imap-mail.outlook.com");
            properties.put("mail.imap.port", 993);
            // SSL setting
            properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.setProperty("mail.imap.socketFactory.fallback", "false");
            properties.setProperty("mail.imap.socketFactory.port", String.valueOf(993));

            throw new NotFoundException("Platform Type Not Found. Please Provide a Valid PlatformType");
        } else if ("HOTMAIL".equals(emailProvider.toUpperCase())) {
            properties.put("mail.imap.host", "imap-mail.outlook.com");
            properties.put("mail.imap.port", 993);
            // SSL setting
            properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.setProperty("mail.imap.socketFactory.fallback", "false");
            properties.setProperty("mail.imap.socketFactory.port", String.valueOf(993));

            throw new NotFoundException("Platform Type Not Found. Please Provide a Valid PlatformType");
        } else {
            throw new NotFoundException("Platform Type Not Found. Please Provide a Valid PlatformType");
        }
        Session session = Session.getDefaultInstance(properties);
        try {
            //Session session = Session.getDefaultInstance(props, null);
            //Store store = session.getStore("imaps");
            // connects to the message store
            Store store = session.getStore("imap");
            System.out.println("userName:"+userName+":::"+password);
            store.connect(userName, password);
            // store.connect(userName, password);
            System.out.println("Connected to Email server….");
            // opens the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_ONLY);
            //create a search term for all "unseen" messages
            Flags seen = new Flags(Flags.Flag.SEEN);
            FlagTerm unseenFlagTerm = new FlagTerm(seen, true);
            //create a search term for all recent messages
            Flags recent = new Flags(Flags.Flag.RECENT);
            FlagTerm recentFlagTerm = new FlagTerm(recent, false);
            SearchTerm searchTerm = new OrTerm(unseenFlagTerm,recentFlagTerm);
            // performs search through the folder
            Message[] foundMessages = folderInbox.search(searchTerm);
            int msgLength = foundMessages.length;
            System.out.println("foundMessages::"+msgLength);

          for (int i=msgLength-1 ; i>=foundMessages.length-10;i--) {
//            for (int i=0 ; i<=msgLength-1;i++) {
                if(msgLength==0){
                    System.out.println("Message count:"+msgLength);
                    System.out.println("Message not found, Inbox message count is Zero");
                    emailBody = null;
                    break;
                }
                if(i==-1){
                    break;
                }

                Message message = foundMessages[i];
                Address[] froms = message.getFrom();
                String email = froms == null ? null : ((InternetAddress)froms[0]).getAddress();

                if(message.getSubject()==null){
                    continue;
                }

                Date date = new Date();//Getting Present date from the system
                long diff = date.getTime()-message.getReceivedDate().getTime();//Get The difference between two dates
                long diffMinutes = diff / (60 * 1000) % 60; //Fetching the difference of minute
                if(30<diffMinutes){
                    System.out.println("diffMinutes::"+diffMinutes+"::"+3);
                    System.out.println("Message not found, email received time more than 30 mins");
                    System.out.println("Message Subject::"+message.getSubject());
                    emailBody = null;
                    break;
                }

                try {
                    String subject = message.getSubject();
                    System.out.println("Try Message Subject::"+subject+"::"+subjectKeyword);
                    System.out.println("Try Message email::"+email+"::"+fromEmail);
                    System.out.println("Try Message diffMinutes::"+diffMinutes+"::"+3);
                    if(subject.contains(subjectKeyword) && email.contains(fromEmail) && diffMinutes<=3){
                        System.out.println("Found message # At "+ i + " :"+ "Subject:"+ subject);
                        System.out.println("From: "+ email +" on : "+message.getReceivedDate());
                        emailBody = getText(message);
                        break;
                    } else {
                        System.out.println("Message not found");
                        emailBody = null;
                    }
                } catch (NullPointerException expected) {
                    // TODO Auto-generated catch block
                    expected.printStackTrace();
                }
            }
            // disconnect
            folderInbox.close(false);
            store.close();
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider.");
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store.");
            ex.printStackTrace();
        }
        return emailBody;
    }


}

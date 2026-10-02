package com.example.chasky.angusMail;

import java.io.IOException;
import java.util.Properties;

import org.eclipse.angus.mail.imap.IMAPFolder;

import jakarta.mail.Authenticator;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.event.MessageCountAdapter;
import jakarta.mail.event.MessageCountEvent;

public class EmailMonitor {

    private final Session session;
    private final String username;
    private final String password;
    private final String mbox;
    private final int freq;

    public EmailMonitor(String host, String username, String password,
            String mbox, int freq) {
        this.username = username;
        this.password = password;
        this.mbox = mbox;
        this.freq = freq;

        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");
        props.put("mail.imaps.host", host);
        props.put("mail.imaps.port", "993");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        this.session = Session.getInstance(props, authenticator);
        // session.setDebug(true);
    }

    public void monitor() {
        try {
            Store store = session.getStore("imaps");
            store.connect(username, password);

            Folder folder = store.getFolder(mbox);
            if (folder == null || !folder.exists()) {
                System.out.println("Invalid folder: " + mbox);
                return;
            }

            folder.open(Folder.READ_WRITE);

            folder.addMessageCountListener(new MessageCountAdapter() {
                @Override
                public void messagesAdded(MessageCountEvent ev) {
                    Message[] msgs = ev.getMessages();
                    System.out.println("Got " + msgs.length + " new message(s)");
                    for (Message msg : msgs) {
                        try {
                            System.out.println("-----");
                            System.out.println("Message " + msg.getMessageNumber() + ":");
                            msg.writeTo(System.out);
                        } catch (IOException | MessagingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            boolean supportsIdle = folder instanceof IMAPFolder;

            for (;;) {
                if (supportsIdle) {
                    IMAPFolder imapFolder = (IMAPFolder) folder;
                    imapFolder.idle();
                    System.out.println("IDLE done");
                } else {
                    Thread.sleep(freq);
                    folder.getMessageCount(); // force EXISTS check
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
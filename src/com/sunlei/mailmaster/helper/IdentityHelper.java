package com.sunlei.mailmaster.helper;

import android.util.Log;

import com.sunlei.mailmaster.Account;
import com.sunlei.mailmaster.Identity;
import com.sunlei.mailmaster.K9;
import com.sunlei.mailmaster.mail.Address;
import com.sunlei.mailmaster.mail.Message;
import com.sunlei.mailmaster.mail.MessagingException;

public class IdentityHelper {

    /**
     * Find the identity a message was sent to.
     *
     * @param account
     *         The account the message belongs to.
     * @param message
     *         The message to get the recipients from.
     *
     * @return The identity the message was sent to, or the account's default identity if it
     *         couldn't be determined which identity this message was sent to.
     *
     * @see Account#findIdentity(com.sunlei.mailmaster.mail.Address)
     */
    public static Identity getRecipientIdentityFromMessage(Account account, Message message) {
        Identity recipient = null;

        try {
            for (Address address : message.getRecipients(Message.RecipientType.TO)) {
                Identity identity = account.findIdentity(address);
                if (identity != null) {
                    recipient = identity;
                    break;
                }
            }
            if (recipient == null) {
                Address[] ccAddresses = message.getRecipients(Message.RecipientType.CC);
                if (ccAddresses.length > 0) {
                    for (Address address : ccAddresses) {
                        Identity identity = account.findIdentity(address);
                        if (identity != null) {
                            recipient = identity;
                            break;
                        }
                    }
                }
            }
        } catch (MessagingException e) {
            Log.w(K9.LOG_TAG, "Error finding the identity this message was sent to", e);
        }

        if (recipient == null) {
            recipient = account.getIdentity(0);
        }

        return recipient;
    }
}

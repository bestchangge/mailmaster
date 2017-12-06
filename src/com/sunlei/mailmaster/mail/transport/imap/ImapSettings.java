package com.sunlei.mailmaster.mail.transport.imap;

import com.sunlei.mailmaster.mail.AuthType;
import com.sunlei.mailmaster.mail.ConnectionSecurity;
import com.sunlei.mailmaster.mail.store.ImapStore;
import com.sunlei.mailmaster.mail.store.ImapStore.ImapConnection;

/**
 * Settings source for IMAP. Implemented in order to remove coupling between {@link ImapStore} and {@link ImapConnection}.
 */
public interface ImapSettings {
    String getHost();

    int getPort();

    ConnectionSecurity getConnectionSecurity();

    AuthType getAuthType();

    String getUsername();

    String getPassword();

    String getClientCertificateAlias();

    boolean useCompression(int type);

    String getPathPrefix();

    void setPathPrefix(String prefix);

    String getPathDelimeter();

    void setPathDelimeter(String delimeter);

    String getCombinedPrefix();

    void setCombinedPrefix(String prefix);
}

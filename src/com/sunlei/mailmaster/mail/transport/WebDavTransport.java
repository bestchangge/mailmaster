
package com.sunlei.mailmaster.mail.transport;

import android.util.Log;

import com.sunlei.mailmaster.Account;
import com.sunlei.mailmaster.K9;
import com.sunlei.mailmaster.mail.Message;
import com.sunlei.mailmaster.mail.MessagingException;
import com.sunlei.mailmaster.mail.ServerSettings;
import com.sunlei.mailmaster.mail.Transport;
import com.sunlei.mailmaster.mail.store.WebDavStore;

public class WebDavTransport extends Transport {
    public static final String TRANSPORT_TYPE = WebDavStore.STORE_TYPE;

    /**
     * Decodes a WebDavTransport URI.
     *
     * <p>
     * <b>Note:</b> Everything related to sending messages via WebDAV is handled by
     * {@link WebDavStore}. So the transport URI is the same as the store URI.
     * </p>
     */
    public static ServerSettings decodeUri(String uri) {
        return WebDavStore.decodeUri(uri);
    }

    /**
     * Creates a WebDavTransport URI.
     *
     * <p>
     * <b>Note:</b> Everything related to sending messages via WebDAV is handled by
     * {@link WebDavStore}. So the transport URI is the same as the store URI.
     * </p>
     */
    public static String createUri(ServerSettings server) {
        return WebDavStore.createUri(server);
    }


    private WebDavStore store;

    public WebDavTransport(Account account) throws MessagingException {
        if (account.getRemoteStore() instanceof WebDavStore) {
            store = (WebDavStore) account.getRemoteStore();
        } else {
            store = new WebDavStore(account);
        }

        if (K9.DEBUG)
            Log.d(K9.LOG_TAG, ">>> New WebDavTransport creation complete");
    }

    @Override
    public void open() throws MessagingException {
        if (K9.DEBUG)
            Log.d(K9.LOG_TAG, ">>> open called on WebDavTransport ");

        store.getHttpClient();
    }

    @Override
    public void close() {
    }

    @Override
    public void sendMessage(Message message) throws MessagingException {
        store.sendMessages(new Message[] { message });
    }
}

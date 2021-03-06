package crittercism.android;

import com.crittercism.app.CrittercismConfig;
import com.mopub.volley.DefaultRetryPolicy;
import com.voxelbusters.nativeplugins.defines.Keys.Mime;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

public final class dc {
    private static SSLSocketFactory a;
    private URL b;
    private Map c;
    private int d;
    private boolean e;
    private boolean f;
    private String g;
    private boolean h;
    private int i;

    static {
        a = null;
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, null, null);
            SSLSocketFactory socketFactory = instance.getSocketFactory();
            if (socketFactory == null) {
                return;
            }
            if (socketFactory instanceof ab) {
                a = ((ab) socketFactory).a();
            } else {
                a = socketFactory;
            }
        } catch (GeneralSecurityException e) {
            a = null;
        }
    }

    public dc(URL url) {
        this.c = new HashMap();
        this.d = 0;
        this.e = true;
        this.f = true;
        this.g = "POST";
        this.h = false;
        this.i = DefaultRetryPolicy.DEFAULT_TIMEOUT_MS;
        this.b = url;
        this.c.put("User-Agent", Arrays.asList(new String[]{CrittercismConfig.API_VERSION}));
        this.c.put("Content-Type", Arrays.asList(new String[]{"application/json"}));
        this.c.put("Accept", Arrays.asList(new String[]{Mime.PLAIN_TEXT, "application/json"}));
    }

    public final HttpURLConnection a() {
        HttpURLConnection httpURLConnection = (HttpURLConnection) this.b.openConnection();
        for (Entry entry : this.c.entrySet()) {
            for (String addRequestProperty : (List) entry.getValue()) {
                httpURLConnection.addRequestProperty((String) entry.getKey(), addRequestProperty);
            }
        }
        httpURLConnection.setConnectTimeout(this.i);
        httpURLConnection.setReadTimeout(this.i);
        httpURLConnection.setDoInput(this.e);
        httpURLConnection.setDoOutput(this.f);
        if (this.h) {
            httpURLConnection.setChunkedStreamingMode(this.d);
        }
        httpURLConnection.setRequestMethod(this.g);
        if (httpURLConnection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            if (a != null) {
                httpsURLConnection.setSSLSocketFactory(a);
            } else {
                throw new GeneralSecurityException();
            }
        }
        return httpURLConnection;
    }
}

package com.google.android.gms.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.AttestationData;
import com.google.android.gms.safetynet.SafeBrowsingData;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.safetynet.SafetyNetApi.AttestationResult;
import com.google.android.gms.safetynet.SafetyNetApi.SafeBrowsingResult;
import java.util.List;

public class zzqn implements SafetyNetApi {

    static abstract class zzb extends zzqk<AttestationResult> {
        protected zzql zzaUI;

        public zzb(GoogleApiClient googleApiClient) {
            super(googleApiClient);
            this.zzaUI = new zzqj() {
                final /* synthetic */ zzb zzaUJ;

                {
                    this.zzaUJ = r1;
                }

                public void zza(Status status, AttestationData attestationData) {
                    this.zzaUJ.zzb(new zza(status, attestationData));
                }
            };
        }

        protected AttestationResult zzaZ(Status status) {
            return new zza(status, null);
        }

        protected /* synthetic */ Result zzb(Status status) {
            return zzaZ(status);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzqn.1 */
    class AnonymousClass1 extends zzb {
        final /* synthetic */ byte[] zzaUD;
        final /* synthetic */ zzqn zzaUE;

        AnonymousClass1(zzqn com_google_android_gms_internal_zzqn, GoogleApiClient googleApiClient, byte[] bArr) {
            this.zzaUE = com_google_android_gms_internal_zzqn;
            this.zzaUD = bArr;
            super(googleApiClient);
        }

        protected void zza(zzqo com_google_android_gms_internal_zzqo) throws RemoteException {
            com_google_android_gms_internal_zzqo.zza(this.zzaUI, this.zzaUD);
        }
    }

    static abstract class zzc extends zzqk<SafeBrowsingResult> {
        protected zzql zzaUI;

        public zzc(GoogleApiClient googleApiClient) {
            super(googleApiClient);
            this.zzaUI = new zzqj() {
                final /* synthetic */ zzc zzaUK;

                {
                    this.zzaUK = r1;
                }

                public void zza(Status status, SafeBrowsingData safeBrowsingData) {
                    this.zzaUK.zzb(new zzd(status, safeBrowsingData));
                }
            };
        }

        protected /* synthetic */ Result zzb(Status status) {
            return zzba(status);
        }

        protected SafeBrowsingResult zzba(Status status) {
            return new zzd(status, null);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzqn.2 */
    class AnonymousClass2 extends zzc {
        final /* synthetic */ zzqn zzaUE;
        final /* synthetic */ List zzaUF;
        final /* synthetic */ String zzaUG;

        AnonymousClass2(zzqn com_google_android_gms_internal_zzqn, GoogleApiClient googleApiClient, List list, String str) {
            this.zzaUE = com_google_android_gms_internal_zzqn;
            this.zzaUF = list;
            this.zzaUG = str;
            super(googleApiClient);
        }

        protected void zza(zzqo com_google_android_gms_internal_zzqo) throws RemoteException {
            com_google_android_gms_internal_zzqo.zza(this.zzaUI, this.zzaUF, 1, this.zzaUG);
        }
    }

    static class zza implements AttestationResult {
        private final Status zzSC;
        private final AttestationData zzaUH;

        public zza(Status status, AttestationData attestationData) {
            this.zzSC = status;
            this.zzaUH = attestationData;
        }

        public String getJwsResult() {
            return this.zzaUH == null ? null : this.zzaUH.getJwsResult();
        }

        public Status getStatus() {
            return this.zzSC;
        }
    }

    static class zzd implements SafeBrowsingResult {
        private Status zzSC;
        private String zzaUB;
        private final SafeBrowsingData zzaUL;

        public zzd(Status status, SafeBrowsingData safeBrowsingData) {
            this.zzSC = status;
            this.zzaUL = safeBrowsingData;
            this.zzaUB = null;
            if (this.zzaUL != null) {
                this.zzaUB = this.zzaUL.getMetadata();
            } else if (this.zzSC.isSuccess()) {
                this.zzSC = new Status(8);
            }
        }

        public String getMetadata() {
            return this.zzaUB;
        }

        public Status getStatus() {
            return this.zzSC;
        }
    }

    public PendingResult<AttestationResult> attest(GoogleApiClient googleApiClient, byte[] nonce) {
        return googleApiClient.zza(new AnonymousClass1(this, googleApiClient, nonce));
    }

    public PendingResult<SafeBrowsingResult> lookupUri(GoogleApiClient googleApiClient, List<Integer> threatTypes, String uri) {
        if (threatTypes == null) {
            throw new IllegalArgumentException("Null threatTypes in lookupUri");
        } else if (!TextUtils.isEmpty(uri)) {
            return googleApiClient.zza(new AnonymousClass2(this, googleApiClient, threatTypes, uri));
        } else {
            throw new IllegalArgumentException("Null or empty uri in lookupUri");
        }
    }
}

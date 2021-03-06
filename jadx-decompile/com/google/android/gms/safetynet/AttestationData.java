package com.google.android.gms.safetynet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AttestationData implements SafeParcelable {
    public static final Creator<AttestationData> CREATOR;
    public final int mVersionCode;
    private String zzaUA;

    static {
        CREATOR = new zza();
    }

    AttestationData(int versionCode, String attestationToken) {
        this.mVersionCode = versionCode;
        this.zzaUA = attestationToken;
    }

    public int describeContents() {
        return 0;
    }

    public String getJwsResult() {
        return this.zzaUA;
    }

    public void writeToParcel(Parcel out, int flags) {
        zza.zza(this, out, flags);
    }
}

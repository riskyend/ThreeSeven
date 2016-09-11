package com.google.android.gms.common;

import android.content.Intent;

public class GooglePlayServicesRepairableException extends UserRecoverableException {
    private final int zzRy;

    GooglePlayServicesRepairableException(int connectionStatusCode, String msg, Intent intent) {
        super(msg, intent);
        this.zzRy = connectionStatusCode;
    }

    public int getConnectionStatusCode() {
        return this.zzRy;
    }
}

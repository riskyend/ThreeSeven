.class public Lcom/upsight/android/analytics/internal/dispatcher/schema/DeviceBlockProvider;
.super Lcom/upsight/android/analytics/provider/UpsightDataProvider;
.source "DeviceBlockProvider.java"


# static fields
.field public static final CARRIER_KEY:Ljava/lang/String; = "device.carrier"

.field public static final CONNECTION_KEY:Ljava/lang/String; = "device.connection"

.field private static final DEVICE_TYPE_PHONE:Ljava/lang/String; = "phone"

.field private static final DEVICE_TYPE_TABLET:Ljava/lang/String; = "tablet"

.field public static final HARDWARE_KEY:Ljava/lang/String; = "device.hardware"

.field public static final JAILBROKEN_KEY:Ljava/lang/String; = "device.jailbroken"

.field private static final KERNEL_BUILD_KEY_TEST:Ljava/lang/String; = "test-keys"

.field public static final LIMITED_AD_TRACKING_KEY:Ljava/lang/String; = "device.limit_ad_tracking"

.field public static final MANUFACTURER_KEY:Ljava/lang/String; = "device.manufacturer"

.field private static final OS_ANDROID:Ljava/lang/String; = "android"

.field public static final OS_KEY:Ljava/lang/String; = "device.os"

.field public static final OS_VERSION_KEY:Ljava/lang/String; = "device.os_version"

.field private static final SPACE:Ljava/lang/String; = " "

.field public static final TYPE_KEY:Ljava/lang/String; = "device.type"


# instance fields
.field private final mBus:Lcom/squareup/otto/Bus;


# direct methods
.method constructor <init>(Lcom/upsight/android/UpsightContext;)V
    .locals 3
    .param p1, "upsight"    # Lcom/upsight/android/UpsightContext;

    .prologue
    .line 91
    invoke-direct {p0}, Lcom/upsight/android/analytics/provider/UpsightDataProvider;-><init>()V

    .line 92
    invoke-virtual {p1}, Lcom/upsight/android/UpsightContext;->getCoreComponent()Lcom/upsight/android/UpsightCoreComponent;

    move-result-object v0

    invoke-interface {v0}, Lcom/upsight/android/UpsightCoreComponent;->bus()Lcom/squareup/otto/Bus;

    move-result-object v0

    iput-object v0, p0, Lcom/upsight/android/analytics/internal/dispatcher/schema/DeviceBlockProvider;->mBus:Lcom/squareup/otto/Bus;

    .line 93
    iget-object v0, p0, Lcom/upsight/android/analytics/internal/dispatcher/schema/DeviceBlockProvider;->mBus:Lcom/squareup/otto/Bus;

    invoke-virtual {v0, p0}, Lcom/squareup/otto/Bus;->register(Ljava/lang/Object;)V

    .line 95
    const-string v0, "device.carrier"

    invoke-static {p1}, Lcom/upsight/android/internal/util/NetworkHelper;->getNetworkOperatorName(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0, v0, v1}, Lcom/upsight/android/analytics/internal/dispatcher/schema/DeviceBlockProvider;->put(Ljava/lang/String;Ljava/lang/Object;)V

    .line 96
    const-string v0, "device.connection"

    invoke-static {p1}, Lcom/upsight/android/internal/util/NetworkHelper;->getActiveNetworkType(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0, v0, v1}, Lcom/upsight/android/analytics/internal/dispatcher/schema/DeviceBlockProvider;->put(Ljava/lang/String;Ljava/lang/Object;)V

    .line 97
    const-string v0, "device.hardware"

    sget-object v1, Landroid/os/Build;->MODEL:Ljava/lang/String;

    invoke-virtual {p0, v0, v1}, Lcom/upsight/android/analytics/internal/dispatcher/schema/DeviceBlockProvider;->put(Ljava/lang/String;Ljava/lang/Object;)V

    .line 98
    const-string v0, "device.jailbroken"

    invoke-direct {p0}, Lcom/upsight/android/analytics/internal/dispatcher/schema/DeviceBlockProvider;->isRooted()Z

    move-result v1

    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    invoke-virtual {p0, v0, v1}, Lcom/upsight/android/analytics/internal/dispatcher/schema/DeviceBlockProvider;->put(Ljava/lang/String;Ljava/lang/Object;)V

    .line 99
    const-string v0, "device.manufacturer"

    sget-object v1, Landroid/os/Build;->MANUFACTURER:Ljava/lang/String;

    invoke-virtual {p0, v0, v1}, Lcom/upsight/android/analytics/internal/dispatcher/schema/DeviceBlockProvider;->put(Ljava/lang/String;Ljava/lang/Object;)V

    .line 100
    const-string v0, "device.os"

    const-string v1, "android"

    invoke-virtual {p0, v0, v1}, Lcom/upsight/android/analytics/internal/dispatcher/schema/DeviceBlockProvider;->put(Ljava/lang/String;Ljava/lang/Object;)V

    .line 101
    const-string v0, "device.os_version"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v2, Landroid/os/Build$VERSION;->RELEASE:Ljava/lang/String;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    sget v2, Landroid/os/Build$VERSION;->SDK_INT:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0, v0, v1}, Lcom/upsight/android/analytics/internal/dispatcher/schema/DeviceBlockProvider;->put(Ljava/lang/String;Ljava/lang/Object;)V

    .line 102
    const-string v0, "device.type"

    invoke-direct {p0, p1}, Lcom/upsight/android/analytics/internal/dispatcher/schema/DeviceBlockProvider;->getDeviceType(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0, v0, v1}, Lcom/upsight/android/analytics/internal/dispatcher/schema/DeviceBlockProvider;->put(Ljava/lang/String;Ljava/lang/Object;)V

    .line 103
    return-void
.end method

.method private getDeviceType(Landroid/content/Context;)Ljava/lang/String;
    .locals 3
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    .line 130
    const-string v0, "phone"

    .line 131
    .local v0, "type":Ljava/lang/String;
    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/res/Resources;->getConfiguration()Landroid/content/res/Configuration;

    move-result-object v1

    iget v1, v1, Landroid/content/res/Configuration;->screenLayout:I

    and-int/lit8 v1, v1, 0xf

    const/4 v2, 0x3

    if-lt v1, v2, :cond_0

    .line 133
    const-string v0, "tablet"

    .line 135
    :cond_0
    return-object v0
.end method

.method private isRooted()Z
    .locals 2

    .prologue
    .line 119
    sget-object v0, Landroid/os/Build;->TAGS:Ljava/lang/String;

    .line 120
    .local v0, "buildTags":Ljava/lang/String;
    if-eqz v0, :cond_0

    const-string v1, "test-keys"

    invoke-virtual {v0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_0

    const/4 v1, 0x1

    :goto_0
    return v1

    :cond_0
    const/4 v1, 0x0

    goto :goto_0
.end method


# virtual methods
.method public availableKeys()Ljava/util/Set;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Set",
            "<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .prologue
    .line 140
    new-instance v0, Ljava/util/HashSet;

    const/16 v1, 0x8

    new-array v1, v1, [Ljava/lang/String;

    const/4 v2, 0x0

    const-string v3, "device.os"

    aput-object v3, v1, v2

    const/4 v2, 0x1

    const-string v3, "device.os_version"

    aput-object v3, v1, v2

    const/4 v2, 0x2

    const-string v3, "device.type"

    aput-object v3, v1, v2

    const/4 v2, 0x3

    const-string v3, "device.hardware"

    aput-object v3, v1, v2

    const/4 v2, 0x4

    const-string v3, "device.manufacturer"

    aput-object v3, v1, v2

    const/4 v2, 0x5

    const-string v3, "device.carrier"

    aput-object v3, v1, v2

    const/4 v2, 0x6

    const-string v3, "device.connection"

    aput-object v3, v1, v2

    const/4 v2, 0x7

    const-string v3, "device.jailbroken"

    aput-object v3, v1, v2

    invoke-static {v1}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/util/HashSet;-><init>(Ljava/util/Collection;)V

    return-object v0
.end method

.method public onNetworkChangeEvent(Lcom/upsight/android/analytics/internal/dispatcher/schema/NetworkChangeEvent;)V
    .locals 2
    .param p1, "event"    # Lcom/upsight/android/analytics/internal/dispatcher/schema/NetworkChangeEvent;
    .annotation runtime Lcom/squareup/otto/Subscribe;
    .end annotation

    .prologue
    .line 107
    const-string v0, "device.carrier"

    iget-object v1, p1, Lcom/upsight/android/analytics/internal/dispatcher/schema/NetworkChangeEvent;->networkOperatorName:Ljava/lang/String;

    invoke-virtual {p0, v0, v1}, Lcom/upsight/android/analytics/internal/dispatcher/schema/DeviceBlockProvider;->put(Ljava/lang/String;Ljava/lang/Object;)V

    .line 108
    const-string v0, "device.connection"

    iget-object v1, p1, Lcom/upsight/android/analytics/internal/dispatcher/schema/NetworkChangeEvent;->activeNetworkType:Ljava/lang/String;

    invoke-virtual {p0, v0, v1}, Lcom/upsight/android/analytics/internal/dispatcher/schema/DeviceBlockProvider;->put(Ljava/lang/String;Ljava/lang/Object;)V

    .line 109
    return-void
.end method

.class Lcom/upsight/android/analytics/internal/dispatcher/delivery/BatchSender$2;
.super Ljava/lang/Object;
.source "BatchSender.java"

# interfaces
.implements Lrx/functions/Action0;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/upsight/android/analytics/internal/dispatcher/delivery/BatchSender;->sendFailed(Lcom/upsight/android/analytics/internal/dispatcher/delivery/BatchSender$Request;Lcom/upsight/android/analytics/internal/dispatcher/delivery/BatchSender$FailReason;Ljava/lang/String;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/upsight/android/analytics/internal/dispatcher/delivery/BatchSender;

.field final synthetic val$request:Lcom/upsight/android/analytics/internal/dispatcher/delivery/BatchSender$Request;


# direct methods
.method constructor <init>(Lcom/upsight/android/analytics/internal/dispatcher/delivery/BatchSender;Lcom/upsight/android/analytics/internal/dispatcher/delivery/BatchSender$Request;)V
    .locals 0
    .param p1, "this$0"    # Lcom/upsight/android/analytics/internal/dispatcher/delivery/BatchSender;

    .prologue
    .line 253
    iput-object p1, p0, Lcom/upsight/android/analytics/internal/dispatcher/delivery/BatchSender$2;->this$0:Lcom/upsight/android/analytics/internal/dispatcher/delivery/BatchSender;

    iput-object p2, p0, Lcom/upsight/android/analytics/internal/dispatcher/delivery/BatchSender$2;->val$request:Lcom/upsight/android/analytics/internal/dispatcher/delivery/BatchSender$Request;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public call()V
    .locals 3

    .prologue
    .line 256
    new-instance v0, Lcom/upsight/android/analytics/internal/dispatcher/delivery/BatchSender$RetryTask;

    iget-object v1, p0, Lcom/upsight/android/analytics/internal/dispatcher/delivery/BatchSender$2;->this$0:Lcom/upsight/android/analytics/internal/dispatcher/delivery/BatchSender;

    iget-object v2, p0, Lcom/upsight/android/analytics/internal/dispatcher/delivery/BatchSender$2;->val$request:Lcom/upsight/android/analytics/internal/dispatcher/delivery/BatchSender$Request;

    invoke-direct {v0, v1, v2}, Lcom/upsight/android/analytics/internal/dispatcher/delivery/BatchSender$RetryTask;-><init>(Lcom/upsight/android/analytics/internal/dispatcher/delivery/BatchSender;Lcom/upsight/android/analytics/internal/dispatcher/delivery/BatchSender$Request;)V

    invoke-virtual {v0}, Lcom/upsight/android/analytics/internal/dispatcher/delivery/BatchSender$RetryTask;->run()V

    .line 257
    return-void
.end method

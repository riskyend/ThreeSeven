.class public Lcom/upsight/android/analytics/internal/dispatcher/routing/RouterBuilder;
.super Ljava/lang/Object;
.source "RouterBuilder.java"


# instance fields
.field private mQueueBuilder:Lcom/upsight/android/analytics/internal/dispatcher/delivery/QueueBuilder;

.field private mScheduler:Lrx/Scheduler;


# direct methods
.method constructor <init>(Lrx/Scheduler;Lcom/upsight/android/analytics/internal/dispatcher/delivery/QueueBuilder;)V
    .locals 0
    .param p1, "scheduler"    # Lrx/Scheduler;
    .param p2, "queueBuilder"    # Lcom/upsight/android/analytics/internal/dispatcher/delivery/QueueBuilder;

    .prologue
    .line 31
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 32
    iput-object p1, p0, Lcom/upsight/android/analytics/internal/dispatcher/routing/RouterBuilder;->mScheduler:Lrx/Scheduler;

    .line 33
    iput-object p2, p0, Lcom/upsight/android/analytics/internal/dispatcher/routing/RouterBuilder;->mQueueBuilder:Lcom/upsight/android/analytics/internal/dispatcher/delivery/QueueBuilder;

    .line 34
    return-void
.end method

.method private buildQueues(Lcom/upsight/android/analytics/internal/dispatcher/routing/RoutingConfig;Lcom/upsight/android/analytics/internal/dispatcher/util/Selector;Lcom/upsight/android/analytics/internal/dispatcher/util/Selector;)Ljava/util/Map;
    .locals 8
    .param p1, "config"    # Lcom/upsight/android/analytics/internal/dispatcher/routing/RoutingConfig;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/upsight/android/analytics/internal/dispatcher/routing/RoutingConfig;",
            "Lcom/upsight/android/analytics/internal/dispatcher/util/Selector",
            "<",
            "Lcom/upsight/android/analytics/internal/dispatcher/schema/Schema;",
            ">;",
            "Lcom/upsight/android/analytics/internal/dispatcher/util/Selector",
            "<",
            "Lcom/upsight/android/analytics/internal/dispatcher/schema/Schema;",
            ">;)",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Lcom/upsight/android/analytics/internal/dispatcher/delivery/Queue;",
            ">;"
        }
    .end annotation

    .prologue
    .line 73
    .local p2, "schemaSelectorByName":Lcom/upsight/android/analytics/internal/dispatcher/util/Selector;, "Lcom/upsight/android/analytics/internal/dispatcher/util/Selector<Lcom/upsight/android/analytics/internal/dispatcher/schema/Schema;>;"
    .local p3, "schemaSelectorByType":Lcom/upsight/android/analytics/internal/dispatcher/util/Selector;, "Lcom/upsight/android/analytics/internal/dispatcher/util/Selector<Lcom/upsight/android/analytics/internal/dispatcher/schema/Schema;>;"
    new-instance v1, Ljava/util/HashMap;

    invoke-direct {v1}, Ljava/util/HashMap;-><init>()V

    .line 76
    .local v1, "queues":Ljava/util/Map;, "Ljava/util/Map<Ljava/lang/String;Lcom/upsight/android/analytics/internal/dispatcher/delivery/Queue;>;"
    new-instance v2, Lcom/upsight/android/analytics/internal/dispatcher/delivery/Queue$Trash;

    invoke-direct {v2}, Lcom/upsight/android/analytics/internal/dispatcher/delivery/Queue$Trash;-><init>()V

    .line 77
    .local v2, "trash":Lcom/upsight/android/analytics/internal/dispatcher/delivery/Queue;
    invoke-virtual {v2}, Lcom/upsight/android/analytics/internal/dispatcher/delivery/Queue;->getName()Ljava/lang/String;

    move-result-object v3

    invoke-interface {v1, v3, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 79
    invoke-virtual {p1}, Lcom/upsight/android/analytics/internal/dispatcher/routing/RoutingConfig;->getQueueConfigs()Ljava/util/Map;

    move-result-object v3

    invoke-interface {v3}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v3

    invoke-interface {v3}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v5

    :goto_0
    invoke-interface {v5}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_0

    invoke-interface {v5}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/Map$Entry;

    .line 80
    .local v0, "queue":Ljava/util/Map$Entry;, "Ljava/util/Map$Entry<Ljava/lang/String;Lcom/upsight/android/analytics/internal/dispatcher/delivery/QueueConfig;>;"
    invoke-interface {v0}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v6

    iget-object v7, p0, Lcom/upsight/android/analytics/internal/dispatcher/routing/RouterBuilder;->mQueueBuilder:Lcom/upsight/android/analytics/internal/dispatcher/delivery/QueueBuilder;

    invoke-interface {v0}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    invoke-interface {v0}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/upsight/android/analytics/internal/dispatcher/delivery/QueueConfig;

    invoke-virtual {v7, v3, v4, p2, p3}, Lcom/upsight/android/analytics/internal/dispatcher/delivery/QueueBuilder;->build(Ljava/lang/String;Lcom/upsight/android/analytics/internal/dispatcher/delivery/QueueConfig;Lcom/upsight/android/analytics/internal/dispatcher/util/Selector;Lcom/upsight/android/analytics/internal/dispatcher/util/Selector;)Lcom/upsight/android/analytics/internal/dispatcher/delivery/Queue;

    move-result-object v3

    invoke-interface {v1, v6, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_0

    .line 83
    .end local v0    # "queue":Ljava/util/Map$Entry;, "Ljava/util/Map$Entry<Ljava/lang/String;Lcom/upsight/android/analytics/internal/dispatcher/delivery/QueueConfig;>;"
    :cond_0
    return-object v1
.end method

.method private buildRoutes(Lcom/upsight/android/analytics/internal/dispatcher/routing/RoutingConfig;Ljava/util/Map;)Ljava/util/Map;
    .locals 8
    .param p1, "config"    # Lcom/upsight/android/analytics/internal/dispatcher/routing/RoutingConfig;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/upsight/android/analytics/internal/dispatcher/routing/RoutingConfig;",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Lcom/upsight/android/analytics/internal/dispatcher/delivery/Queue;",
            ">;)",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Lcom/upsight/android/analytics/internal/dispatcher/routing/Route;",
            ">;"
        }
    .end annotation

    .prologue
    .line 94
    .local p2, "queues":Ljava/util/Map;, "Ljava/util/Map<Ljava/lang/String;Lcom/upsight/android/analytics/internal/dispatcher/delivery/Queue;>;"
    new-instance v3, Ljava/util/HashMap;

    invoke-direct {v3}, Ljava/util/HashMap;-><init>()V

    .line 97
    .local v3, "routes":Ljava/util/Map;, "Ljava/util/Map<Ljava/lang/String;Lcom/upsight/android/analytics/internal/dispatcher/routing/Route;>;"
    invoke-virtual {p1}, Lcom/upsight/android/analytics/internal/dispatcher/routing/RoutingConfig;->getRouters()Ljava/util/Map;

    move-result-object v4

    invoke-interface {v4}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v4

    invoke-interface {v4}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v5

    :goto_0
    invoke-interface {v5}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_1

    invoke-interface {v5}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/Map$Entry;

    .line 98
    .local v2, "routeConf":Ljava/util/Map$Entry;, "Ljava/util/Map$Entry<Ljava/lang/String;Ljava/util/List<Ljava/lang/String;>;>;"
    new-instance v1, Ljava/util/LinkedList;

    invoke-direct {v1}, Ljava/util/LinkedList;-><init>()V

    .line 99
    .local v1, "route":Ljava/util/List;, "Ljava/util/List<Lcom/upsight/android/analytics/internal/dispatcher/routing/RouteStep;>;"
    invoke-interface {v2}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/util/List;

    invoke-interface {v4}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v6

    :goto_1
    invoke-interface {v6}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_0

    invoke-interface {v6}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 100
    .local v0, "queue":Ljava/lang/String;
    new-instance v7, Lcom/upsight/android/analytics/internal/dispatcher/routing/RouteStep;

    invoke-interface {p2, v0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/upsight/android/analytics/internal/dispatcher/delivery/Queue;

    invoke-direct {v7, v4}, Lcom/upsight/android/analytics/internal/dispatcher/routing/RouteStep;-><init>(Lcom/upsight/android/analytics/internal/dispatcher/delivery/Queue;)V

    invoke-interface {v1, v7}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_1

    .line 103
    .end local v0    # "queue":Ljava/lang/String;
    :cond_0
    invoke-interface {v2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v4

    new-instance v6, Lcom/upsight/android/analytics/internal/dispatcher/routing/Route;

    invoke-direct {v6, v1}, Lcom/upsight/android/analytics/internal/dispatcher/routing/Route;-><init>(Ljava/util/List;)V

    invoke-interface {v3, v4, v6}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_0

    .line 106
    .end local v1    # "route":Ljava/util/List;, "Ljava/util/List<Lcom/upsight/android/analytics/internal/dispatcher/routing/RouteStep;>;"
    .end local v2    # "routeConf":Ljava/util/Map$Entry;, "Ljava/util/Map$Entry<Ljava/lang/String;Ljava/util/List<Ljava/lang/String;>;>;"
    :cond_1
    return-object v3
.end method


# virtual methods
.method public build(Lcom/upsight/android/analytics/internal/dispatcher/routing/RoutingConfig;Lcom/upsight/android/analytics/internal/dispatcher/util/Selector;Lcom/upsight/android/analytics/internal/dispatcher/util/Selector;Lcom/upsight/android/analytics/internal/dispatcher/routing/RoutingListener;)Lcom/upsight/android/analytics/internal/dispatcher/routing/Router;
    .locals 6
    .param p1, "config"    # Lcom/upsight/android/analytics/internal/dispatcher/routing/RoutingConfig;
    .param p4, "listener"    # Lcom/upsight/android/analytics/internal/dispatcher/routing/RoutingListener;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/upsight/android/analytics/internal/dispatcher/routing/RoutingConfig;",
            "Lcom/upsight/android/analytics/internal/dispatcher/util/Selector",
            "<",
            "Lcom/upsight/android/analytics/internal/dispatcher/schema/Schema;",
            ">;",
            "Lcom/upsight/android/analytics/internal/dispatcher/util/Selector",
            "<",
            "Lcom/upsight/android/analytics/internal/dispatcher/schema/Schema;",
            ">;",
            "Lcom/upsight/android/analytics/internal/dispatcher/routing/RoutingListener;",
            ")",
            "Lcom/upsight/android/analytics/internal/dispatcher/routing/Router;"
        }
    .end annotation

    .prologue
    .line 48
    .local p2, "schemaSelectorByName":Lcom/upsight/android/analytics/internal/dispatcher/util/Selector;, "Lcom/upsight/android/analytics/internal/dispatcher/util/Selector<Lcom/upsight/android/analytics/internal/dispatcher/schema/Schema;>;"
    .local p3, "schemaSelectorByType":Lcom/upsight/android/analytics/internal/dispatcher/util/Selector;, "Lcom/upsight/android/analytics/internal/dispatcher/util/Selector<Lcom/upsight/android/analytics/internal/dispatcher/schema/Schema;>;"
    invoke-direct {p0, p1, p2, p3}, Lcom/upsight/android/analytics/internal/dispatcher/routing/RouterBuilder;->buildQueues(Lcom/upsight/android/analytics/internal/dispatcher/routing/RoutingConfig;Lcom/upsight/android/analytics/internal/dispatcher/util/Selector;Lcom/upsight/android/analytics/internal/dispatcher/util/Selector;)Ljava/util/Map;

    move-result-object v1

    .line 49
    .local v1, "queues":Ljava/util/Map;, "Ljava/util/Map<Ljava/lang/String;Lcom/upsight/android/analytics/internal/dispatcher/delivery/Queue;>;"
    invoke-direct {p0, p1, v1}, Lcom/upsight/android/analytics/internal/dispatcher/routing/RouterBuilder;->buildRoutes(Lcom/upsight/android/analytics/internal/dispatcher/routing/RoutingConfig;Ljava/util/Map;)Ljava/util/Map;

    move-result-object v3

    .line 51
    .local v3, "routes":Ljava/util/Map;, "Ljava/util/Map<Ljava/lang/String;Lcom/upsight/android/analytics/internal/dispatcher/routing/Route;>;"
    new-instance v2, Lcom/upsight/android/analytics/internal/dispatcher/routing/Router;

    iget-object v4, p0, Lcom/upsight/android/analytics/internal/dispatcher/routing/RouterBuilder;->mScheduler:Lrx/Scheduler;

    new-instance v5, Lcom/upsight/android/analytics/internal/dispatcher/util/ByFilterSelector;

    invoke-direct {v5, v3}, Lcom/upsight/android/analytics/internal/dispatcher/util/ByFilterSelector;-><init>(Ljava/util/Map;)V

    invoke-direct {v2, v4, v5, p4}, Lcom/upsight/android/analytics/internal/dispatcher/routing/Router;-><init>(Lrx/Scheduler;Lcom/upsight/android/analytics/internal/dispatcher/util/ByFilterSelector;Lcom/upsight/android/analytics/internal/dispatcher/routing/RoutingListener;)V

    .line 53
    .local v2, "router":Lcom/upsight/android/analytics/internal/dispatcher/routing/Router;
    invoke-interface {v1}, Ljava/util/Map;->values()Ljava/util/Collection;

    move-result-object v4

    invoke-interface {v4}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v4

    :goto_0
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v5

    if-eqz v5, :cond_0

    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/upsight/android/analytics/internal/dispatcher/delivery/Queue;

    .line 54
    .local v0, "queue":Lcom/upsight/android/analytics/internal/dispatcher/delivery/Queue;
    invoke-virtual {v0, v2}, Lcom/upsight/android/analytics/internal/dispatcher/delivery/Queue;->setOnDeliveryListener(Lcom/upsight/android/analytics/internal/dispatcher/delivery/OnDeliveryListener;)V

    .line 55
    invoke-virtual {v0, v2}, Lcom/upsight/android/analytics/internal/dispatcher/delivery/Queue;->setOnResponseListener(Lcom/upsight/android/analytics/internal/dispatcher/delivery/OnResponseListener;)V

    goto :goto_0

    .line 58
    .end local v0    # "queue":Lcom/upsight/android/analytics/internal/dispatcher/delivery/Queue;
    :cond_0
    return-object v2
.end method

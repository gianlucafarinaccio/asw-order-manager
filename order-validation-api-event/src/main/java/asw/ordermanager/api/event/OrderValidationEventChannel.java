package asw.ordermanager.api.event;

public class OrderValidationEventChannel {

    public static final String orderChannel = "order-service.order-created.event";

    public static final String serviceChannel = "product-service.product-created.event";

    public static final String StockLevelChannel = "product-service.update-stock-level.event";

}

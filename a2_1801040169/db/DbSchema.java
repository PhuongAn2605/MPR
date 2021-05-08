package hanu.a2_1801040169.db;

public class DbSchema {

    public final class CartItemTable{
        public static final String NAME = "cartItems";

        public final class Cols{
            public static final String ID = "id";
            public static final String THUMBNAIL = "thumbnail";
            public static final String NAME = "name";
            public static final String PRICE = "price";
            public static final String COUNT = "count";
            public static final String SUM_PRICE = "sum_price";

            public static final String PRODUCT_ID = "product_id";


        }
    }
}

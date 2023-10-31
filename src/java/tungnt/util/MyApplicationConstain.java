/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungnt.util;

/**
 *
 * @author Thanh Tung
 */
public class MyApplicationConstain {

    public static class ShoppingFeatures {

        public static int DEFAULT_PAGE_NUMBER = 1;
        public static final int DEFAULT_SIZE_NUMBER = 10;
        public static final String BOOK_PAGE = "viewShop";
        public static final String BOOK_CONTROLLER = "viewBookController";

    }

    public static class RemoveItemsinCart {

        public static final String CART_PAGE = "viewCartPage";

    }

    public class DispatchFeature {

        public static final String LOGIN_PAGE = "loginPage";
        public static final String LOGIN_CONTROLLER = "loginController";
        public static final String SEARCH_LASTNAME_CONTROLLER = "searchController";
        public static final String DELETE_ACCOUNT_CONTROLLER = "deleteAccountController";
        public static final String UPDATE_ACCOUNT_CONTROLLER = "updateAccountController";
        public static final String START_UP_CONTROLLER = "startUpServletController";
        public static final String ADD_ITEM_TO_CART_CONTROLLER = "addItemToCartController";
        public static final String LOG_OUT_CONTROLLER = "logoutController";
        public static final String VIEW_CART_PAGE = "viewCartPage";
        public static final String REMOVE_CART_CONTROLLER = "removeCartController";
        public static final String CREATE_ACCOUNT_CONTROLLER = "createAccountController";
        public static final String CHECKOUT_CONTROLLER = "checkoutController";
        public static final String VIEW_BOOK_CONTROLLER = "viewBookController";

    }

    public class LoginFeature {

        public static final String LOGIN_PAGE = "loginPage";
        public static final String SEARCH_PAGE = "searchPage";
        public static final String INVALID_PAGE = "invalidPage";

    }

    public class DeleteAccountFeature {

        public static final String LOGOUT_ACTION = "logoutController";

    }

    public class UpdateAccountFeature {

        public static final String UPDATE_ACTION = "searchController";

    }

    public class SearchLastnameFeaturee {

        public static final String RESULT_SEARCH_PAGE = "searchPage";
        public static final String SEARCH_CONTROLLER = "searchController";

    }

    public class ErrorsPage {

        public static final String ERROR_PAGE = "errorpage";

    }

    public class ViewShop {

        public static final String SHOP_PAGE = "viewShop";

    }
    
    public class CheckoutFeature {

        public static final String CHECKOUT_PAGE = "checkoutPage";

    }

}

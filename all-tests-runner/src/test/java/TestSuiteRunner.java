import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        /* ------------------------------------------------------------------
         * 1. MODULE: Group21_20130235_HaHuyDung_Lab7 (Cart & Search)
         * ------------------------------------------------------------------ */
        // CartTestSuite
        CartTestSuite.CART1.class,
        CartTestSuite.CART2.class,
        CartTestSuite.CART3.class,
        CartTestSuite.CART4.class,
        CartTestSuite.CART5.class,

        // SearchTestSuite
        SearchTestSuite.SEARCH1.class,
        SearchTestSuite.SEARCH2.class,
        SearchTestSuite.SEARCH3.class,
        SearchTestSuite.SEARCH4.class,

        /* ------------------------------------------------------------------
         * 2. MODULE: Group21_20130237_PhanTuanDung_Lab7 (ChangePassword & Order)
         * ------------------------------------------------------------------ */
        // ChangePasswordTestSuite
        ChangePasswordTestSuite.ChangePasswordEmptyCurrent.class,
        ChangePasswordTestSuite.ChangePasswordValid.class,
        ChangePasswordTestSuite.ChangePasswordWithEmptyNewPassword.class,
        ChangePasswordTestSuite.ChangePasswordWithSameAsOld.class,
        ChangePasswordTestSuite.InvalidCurrentPasswordChange.class,
        ChangePasswordTestSuite.PasswordChangeWrongConfirmPassword.class,

        // OrderTestSuite
        OrderTestSuite.InvalidOrder.class,
        OrderTestSuite.OrderWithEmptyAddress.class,
        OrderTestSuite.OrderWithEmptyName.class,
        OrderTestSuite.OrderWithEmptyPhone.class,
        OrderTestSuite.OrderWithLargeQuantityProduct.class,
        OrderTestSuite.OrderWithMultipleProducts.class,
        OrderTestSuite.ValidOrder.class,

        /* ------------------------------------------------------------------
         * 3. MODULE: Group21_20130251_NguyenThiMyHanh_Lab7 (Information & ProductFiltering)
         * ------------------------------------------------------------------ */
        // Information
        Infomation.Info1.class,
        Infomation.Info2.class,
        Infomation.Info3.class,

        // ProductFiltering
        ProductFiltering.Filter1.class,
        ProductFiltering.Filter2.class,
        ProductFiltering.Filter3.class,
        ProductFiltering.Filter4.class,
        ProductFiltering.Filter5.class,

        /* ------------------------------------------------------------------
         * 4. MODULE: Group21_20130344_DinhThanhNhan_Lab7 (Login & Register)
         * ------------------------------------------------------------------ */
        // Login TestSuite
        LoginTestSuite.LoginEmptyPassword.class,
        LoginTestSuite.LoginEmptyUsername.class,
        LoginTestSuite.LoginValidCredentials.class,
        LoginTestSuite.LoginWrongPassword.class,

        // Register TestSuite
        RegisterTestSuite.RegisterExistingUsername.class,
        RegisterTestSuite.RegisterInvalidPhoneNumber.class,
        RegisterTestSuite.RegisterPasswordMismatch.class,
        RegisterTestSuite.RegisterShortPassword.class,
        RegisterTestSuite.RegisterValidInformation.class
})
public class TestSuiteRunner {
    // Empty on purpose
}


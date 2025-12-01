import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


// Common Steps: Login

def login() {
	WebUI.openBrowser('')
	WebUI.maximizeWindow()
	WebUI.navigateToUrl('https://mwc.com.vn/')

	WebUI.click(findTestObject('Page_Home/btn_Login'))   // nút mở trang đăng nhập
    WebUI.setText(findTestObject('Page_Login/txt_Username'), 'myhanh26')
    WebUI.setEncryptedText(findTestObject('Page_Login/txt_Password'), '123@1234')
    WebUI.click(findTestObject('Page_Login/btn_Login'))
    WebUI.verifyElementPresent(findTestObject('Page_Home/lbl_LoggedInUser'), 10)

    println("LOGIN thành công")
}

// Test Case [Info-1] Kiểm tra việc xem thông tin cá nhân hiện tại

login()

WebUI.click(findTestObject('Page_Home/btn_MyAccount'))

// Verify các trường thông tin tồn tại và đúng format
WebUI.verifyElementPresent(findTestObject('Page_Profile/txt_Username'), 10)
WebUI.verifyElementPresent(findTestObject('Page_Profile/txt_FullName'), 10)
WebUI.verifyElementPresent(findTestObject('Page_Profile/txt_Email'), 10)
WebUI.verifyElementPresent(findTestObject('Page_Profile/txt_Phone'), 10)
WebUI.verifyElementPresent(findTestObject('Page_Profile/txt_BirthDate'), 10)
WebUI.verifyElementPresent(findTestObject('Page_Profile/select_Province'), 10)
WebUI.verifyElementPresent(findTestObject('Page_Profile/select_District'), 10)
WebUI.verifyElementPresent(findTestObject('Page_Profile/select_Ward'), 10)
WebUI.verifyElementPresent(findTestObject('Page_Profile/txt_Address'), 10)

println("Info-1 PASSED: Hiển thị đầy đủ thông tin cá nhân")

WebUI.closeBrowser()

// Test Case [Info-2] Kiểm tra cập nhật thông tin thành công

login()
WebUI.click(findTestObject('Page_Home/btn_MyAccount'))

WebUI.setText(findTestObject('Page_Profile/txt_FullName'), 'Nguyễn Thị Mỹ Hạnh')
WebUI.setText(findTestObject('Page_Profile/txt_Address'), 'TP. Hồ Chí Minh')
WebUI.click(findTestObject('Page_Profile/btn_Save'))
WebUI.verifyElementText(findTestObject('Page_Profile/lbl_SuccessMsg'), 'Cập nhật tài khoản thành công')

println("Info-2 PASSED: Cập nhật thông tin thành công")

WebUI.closeBrowser()

// Test Case [Info-3] Kiểm tra cập nhật thất bại khi bỏ trống trường bắt buộc

login()
WebUI.click(findTestObject('Page_Home/btn_MyAccount'))
WebUI.setText(findTestObject('Page_Profile/txt_Phone'), '')
WebUI.click(findTestObject('Page_Profile/btn_Save'))
WebUI.verifyElementText(findTestObject('Page_Profile/lbl_ErrorMsg'), 'Please fill out this field')

println("Info-3 PASSED: Hiển thị lỗi bắt buộc khi để trống trường SDT")

WebUI.closeBrowser()

//Lọc sản phẩm
// Filter-1: Lọc Giày Nữ

WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('https://mwc.com.vn/collections/giay-nu')

WebUI.click(findTestObject('Page_Filter/btn_FemaleShoes'))

// Kiểm tra trang chỉ chứa GIÀY NỮ
List<WebElement> listProducts = WebUI.findWebElements(findTestObject('Page_Filter/product_List'), 10)

for (WebElement p : listProducts) {
	assert p.getText().toLowerCase().contains("nữ")
}

println("Filter-1 PASSED: Chỉ hiển thị sản phẩm Giày Nữ")

WebUI.closeBrowser()

// Filter-2: Lọc theo Màu Đen

WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('https://mwc.com.vn/collections/giay-nu')

WebUI.click(findTestObject('Page_Filter/ddl_Color'))
WebUI.click(findTestObject('Page_Filter/option_ColorBlack'))

List<WebElement> listColor = WebUI.findWebElements(findTestObject('Page_Filter/product_ColorTag'), 10)

for (WebElement item : listColor) {
	assert item.getText().toLowerCase().contains("đen")
}

println("Filter-2 PASSED: Chỉ hiển thị sản phẩm màu Đen")

WebUI.closeBrowser()
// Filter-3: Lọc theo giá 100.000 - 200.000

WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('https://mwc.com.vn/collections/giay-nam')

WebUI.click(findTestObject('Page_Filter/btn_SportShoes'))

WebUI.setText(findTestObject('Page_Filter/txt_MinPrice'), '100000')
WebUI.setText(findTestObject('Page_Filter/txt_MaxPrice'), '200000')

WebUI.click(findTestObject('Page_Filter/btn_ApplyPrice'))

List<WebElement> listPrices = WebUI.findWebElements(findTestObject('Page_Filter/product_Price'), 10)

for (WebElement price : listPrices) {
	def p = price.getText().replace(".", "").replace("đ", "").trim().toInteger()
	assert p >= 100000 && p <= 200000
}

println("Filter-3 PASSED: Lọc đúng sản phẩm trong khoảng giá 100-200k")

WebUI.closeBrowser()
// Filter-4: Lọc Màu Trắng + Giá > 300.000

WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('https://mwc.com.vn/collections/giay-nu')

WebUI.click(findTestObject('Page_Filter/ddl_Color'))
WebUI.click(findTestObject('Page_Filter/option_ColorWhite'))

WebUI.setText(findTestObject('Page_Filter/txt_MinPrice'), '300000')
WebUI.click(findTestObject('Page_Filter/btn_ApplyPrice'))

List<WebElement> filterItems = WebUI.findWebElements(findTestObject('Page_Filter/product_Item'), 10)

for (WebElement item : filterItems) {
	def color = item.findElement(By.cssSelector(".color-tag")).getText().toLowerCase()
	def price = item.findElement(By.cssSelector(".product-price"))
				  .getText().replace(".", "").replace("đ", "").trim().toInteger()

	assert color.contains("trắng")
	assert price >= 300000
}

println("Filter-4 PASSED: Lọc kết hợp màu Trắng + giá > 300k thành công")

WebUI.closeBrowser()
// Filter-5: Lọc không có sản phẩm phù hợp

WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('https://mwc.com.vn/collections/giay-nu')

WebUI.click(findTestObject('Page_Filter/ddl_Color'))
WebUI.click(findTestObject('Page_Filter/option_Color_GrayOrange'))

WebUI.setText(findTestObject('Page_Filter/txt_MaxPrice'), '100')
WebUI.click(findTestObject('Page_Filter/btn_ApplyPrice'))

WebUI.verifyElementPresent(findTestObject('Page_Filter/lbl_NoProductFound'), 10)

println("Filter-5 PASSED: Lọc không tìm thấy sản phẩm → hiển thị đúng thông báo")

WebUI.closeBrowser()

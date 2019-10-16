package tests;

import org.testng.annotations.Test;
import utils.Helper;
import utils.pages.pages.MailBoxPage;
import utils.pages.pages.MainPage;

public class SendMailTest {

    private Helper helper           = new Helper();
    private MainPage mainPage       = new MainPage(helper.getDriver());
    private MailBoxPage mailBoxPage = new MailBoxPage(helper.getDriver());


    @Test (description = "Открытие страницы https://www.ozon.ru/")
    public void step1_openMainPage() {
        helper.openUrl("https://mail.ru");
    }

    @Test(description = "Аутентификация")
    public void step2_authentication() {
        mainPage.fillLoginField("enter_your_name");
        mainPage.selectDomain("@bk.ru");
        mainPage.clickSubmitButton();
        mainPage.fillPasswordField("zaq135wsx");
        mainPage.clickSubmitButton();
    }

    @Test(description = "Заполнение и отправка письма")
    public void step3_sendMail() {
        helper.sleep(5);
        mailBoxPage.clickWriteMailButton();
        mailBoxPage.fillToWhomField("enter_your_name@bk.ru");
        mailBoxPage.fillSubjectField("test");
        mailBoxPage.fillTextBox("Отправка тестовой записи");
        mailBoxPage.clickSendMailButton();
    }
}

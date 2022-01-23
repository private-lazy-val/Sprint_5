package ru.praktikum.sprint5;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class AccountTest {

    private final String name;
    private final boolean result;
    private final String message;
    AllureLifecycle oLifecycle = Allure.getLifecycle();

    public AccountTest(String name, boolean result, String message) {
        this.name = name;
        this.result = result;
        this.message = message;
    }
    @Parameterized.Parameters
    public static Object[][] getNameAndCheckResultData() {
        return new Object[][]{
                {"Тимоти Шаламе", true, "Correct name returns true"},
                {"Ти", false, "Minimum number of symbols is 3"},
                {"Тимоти Шаламе1234567", false, "Maximum number of symbols is 19"},
                {"Т ш", true, "Correct name returns true"},
                {"Тимоти Шаламе123456", true, "Correct name returns true"},
                {"Тимоти Ша ламе", false, "Name must contain 1 whitespace"},
                {"ТимотиШаламе", false, "Name must contain 1 whitespace"},
                {" ТимотиШаламе", false, "Name can't begin with whitespace"},
                {"ТимотиШаламе ", false, "Name can't end with whitespace"},
        };
    }

    @Description("Checking if checkNameToEmboss method returns correct result")
    @Test
    public void checkNameToEmbossTest() {
        oLifecycle.updateTestCase(testResult -> testResult.setName(message));

        Account account = new Account(name);
        Assert.assertEquals("Expected " + result + " for name " + name, result, account.checkNameToEmboss());
    }
}
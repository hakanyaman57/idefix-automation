package com.idefix.steps;

import com.idefix.base.DriverFactory;
import com.idefix.context.TestContext;
import com.idefix.models.SearchData;
import com.idefix.pages.HomePage;
import com.idefix.pages.SearchResultsPage;
import com.thoughtworks.gauge.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchSteps {

    private static final Logger logger = LogManager.getLogger(SearchSteps.class);

    @Step("Arama kutusuna json dosyasindaki ilk arama kelimesi yazilir")
    public void typeFirstSearchKeyword() {
        SearchData searchData = TestContext.getTestData().getSearch();
        logger.info("Typing first search keyword: {}", searchData.getFirstKeyword());
        new HomePage(DriverFactory.getDriver()).typeSearchKeyword(searchData.getFirstKeyword());
    }

    @Step("Arama kutusundaki deger temizlenir")
    public void clearSearchKeyword() {
        logger.info("Clearing search input.");
        new HomePage(DriverFactory.getDriver()).clearSearchKeyword();
    }

    @Step("Arama kutusuna json dosyasindaki ikinci arama kelimesi yazilir")
    public void typeSecondSearchKeyword() {
        SearchData searchData = TestContext.getTestData().getSearch();
        logger.info("Typing second search keyword: {}", searchData.getSecondKeyword());
        new HomePage(DriverFactory.getDriver()).typeSearchKeyword(searchData.getSecondKeyword());
    }

    @Step("Klavyeden enter tusuna basilir")
    public void pressEnter() {
        logger.info("Pressing enter on search input.");
        new HomePage(DriverFactory.getDriver()).pressEnterOnSearchInput();
    }

    @Step("Arama sonuclarinin listelendigi dogrulanir")
    public void verifySearchResultsAreListed() {
        logger.info("Verifying listed search results.");
        new SearchResultsPage(DriverFactory.getDriver()).verifyProductsAreListed();
    }

    @Step("Json dosyasinda belirtilen siradaki urun secilir")
    public void selectProductByJsonOrder() {
        int productIndex = TestContext.getTestData().getSearch().getProductIndex();
        logger.info("Selecting product by order: {}", productIndex);
        new SearchResultsPage(DriverFactory.getDriver()).selectProductByOrder(productIndex);
    }
}
